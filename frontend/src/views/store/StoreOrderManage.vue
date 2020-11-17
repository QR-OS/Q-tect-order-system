<template>
  <v-container>
    <v-row>
      <v-col>
        <v-alert v-if="!!errorMsg">{{ errorMsg }}</v-alert>
      </v-col>
    </v-row>
    <v-btn
      class="ma-3"
      dark
      depressed
      color="yellow darken-2"
      @click="selectAllComplete"
    >
      완료
    </v-btn>
    <v-btn
      class="ma-3"
      dark
      depressed
      color="yellow darken-2"
      @click="selectAllReject"
    >
      삭제
    </v-btn>
    <v-data-table
      :loading="!!isLoading"
      loading-text="Loading... Please wait"
      v-model="selected"
      :headers="headers"
      :items="orderList"
      :sort-by="'order_time'"
      :sort-desc="true"
      multi-sort
      item-key="order_id"
      show-select
      class="elevation-1"
      @click:row="showDetailOrder"
    >
    </v-data-table>
    <v-dialog
      scrollable
      v-if="dialog"
      v-model="dialog"
      max-width="600"
      max-height="700"
    >
      <v-container class="pa-0">
        <v-card class="pb-5 px-0 ma-0">
          <v-row class="px-0 mr-0 font-weight-bold title ml-6 pt-5">
            주문 내역
          </v-row>
          <v-row class="mr-0 pa-0">
            <v-col class="mr-0 pa-0" align="center">
              <v-card
                flat
                align="start"
                color="grey lighten-3"
                class="pb-2 align-center"
                max-width="500"
                min-width="300"
              >
                <v-list color="grey lighten-3">
                  <v-list-item v-for="(item, idx) in orderDetail" :key="idx">
                    <v-list-item-content class="py-0">
                      <v-list-item-title class="font-weight-bold ml-6">
                        <v-row>
                          <v-col>
                            {{ item.product_name }}
                          </v-col>
                          <v-col
                            class="mr-6 font-weight-medium"
                            align="end"
                            justify="end"
                          >
                            {{ item.order_quantity }} 개
                            {{ item.product_price }}원
                          </v-col>
                        </v-row>
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
                <v-row class="mx-3 mb-2"><v-divider /></v-row>
                <v-row justify="end" class="font-weight-bold mr-10 my-3">
                  총 금액 {{ orderItem.total_price }}원
                </v-row>
              </v-card>
            </v-col>
          </v-row>
          <v-row class="mr-0 pa-0 ml-6 mt-3">
            <v-col class="font-weight-bold"
              >주문 상태 : {{ orderItem.order_state }}</v-col
            >
          </v-row>
          <v-row class="mr-0 pa-0 ml-6">
            <v-col class="font-weight-bold"
              >주문 방법 : {{ orderItem.order_type }}</v-col
            >
          </v-row>
          <v-row class="mr-0 pa-0 ml-6">
            <v-col class="font-weight-bold"
              >접수 시간 : {{ orderItem.order_time }}</v-col
            >
          </v-row>
          <v-row class="mr-0 pa-0 ml-6">
            <v-col class="font-weight-bold"
              >완료 예정 시간 : {{ orderItem.book_time }}</v-col
            >
          </v-row>
          <v-row class="mr-0 pa-0 ml-6">
            <v-col class="font-weight-bold"
              >결제 방법 : {{ orderItem.pay_type }}</v-col
            >
          </v-row>
          <v-card-actions
            v-if="orderItem.order_state == '주문 접수'"
            class="justify-center"
          >
            <v-btn
              dark
              depressed
              color="yellow darken-2"
              @click="
                () => {
                  dialog = false;
                  patchOrderState('주문 준비 중');
                }
              "
              >주문 확인</v-btn
            >
            <v-btn
              dark
              depressed
              color="yellow darken-2"
              @click="
                orderRejectDialogOpen(() => {
                  dialog = false;
                  rejectDialog = false;
                  patchOrderState('주문 거절');
                })
              "
              >주문 거절</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-container>
    </v-dialog>
    <v-dialog
      persistent
      v-if="rejectDialog"
      v-model="rejectDialog"
      max-width="350"
      max-height="250"
    >
      <v-card>
        <v-card-title>
          정말 주문을 삭제하시겠습니까?
        </v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="nextAction">
            삭제
          </v-btn>
          <v-btn
            color="green darken-1"
            text
            @click="
              () => {
                dialog = false;
                rejectDialog = false;
              }
            "
          >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import axios from 'axios';
import moment from 'moment';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
  data() {
    return {
      storeId: "",
      selected: [],
      headers: [
        {
          text: "주문 방법",
          align: "start",
          value: "order_type",
        },
        { text: "접수시간", value: "order_time" },
        { text: "완료 예정 시간", value: "book_time" },
        { text: "총 금액", value: "total_price" },
        { text: "결제방법", value: "pay_type" },
        { text: "완료여부", value: "order_state" },
      ],
      orderList: [],
      orderDetail: [],
      orderItem: {},
      dialog: false,
      rejectDialog: false,
      errorMsg: "",
      isLoading: true,
      nextAction: () => {},
    };
  },
  async created() {
    this.socketConnect();
    try {
      const storeInfo = await axios.get(
        "/store/" + this.$store.state.auth.user.user_id
      );
      this.storeId = storeInfo.data.store_id;
      const res = await axios.get("/orders/" + this.storeId);
      this.orderList = res.data;
      let idx = 0;
      while (idx > -1) {
        idx = this.orderList.findIndex(
          (item) =>
            item.order_state !== "주문 접수" &&
            item.order_state !== "주문 준비 중"
        );
        if (idx > -1) this.orderList.splice(idx, 1);
        else break;
      }
      this.orderList.forEach((val, idx) => {
        this.orderList[idx].order_time = moment(val.order_time).format(
          "YYYY/MM/DD HH:mm"
        );
        this.orderList[idx].order_type = val.order_type === 0 ? "주문" : "예약";
        if (val.book_time === null || typeof val.book_time !== "undefined") {
          this.orderList[idx].book_time = val.order_time;
        }
      });
      this.isLoading = false;
    } catch (error) {
      this.errorMsg = error.response.message;
    }
  },
  methods: {
    async showDetailOrder(item) {
      try {
        const res = await axios.get(
          "/detailorder/" + item.order_id + "/" + item.user_id
        );
        this.orderDetail = res.data;
        this.orderItem = item;
      } catch (error) {
        this.errorMsg = error.response.message;
      }
      this.dialog = true;
    },
    async orderRejectDialogOpen(callback) {
      this.rejectDialog = true;
      this.nextAction = callback;
    },
    async patchOrderState(state) {
      this.isLoading = true;
      try {
        await axios.patch(
          "/order/" + this.orderItem.order_id + "/" + this.storeId,
          { order_state: state }
        );
      } catch (error) {
        this.errorMsg = error.response.message;
      }
      this.isLoading = false;
      this.orderItem.order_state = state;
      const socketMsg = { order_state : state };
      this.stompClient.send("/socket.manager/" + this.storeId + "/" + this.orderItem.order_id, JSON.stringify(socketMsg), {});
    },
    async selectAllComplete() {
      for (const val of this.selected) {
        if (val.order_state === "주문 준비 중") {
          this.orderItem = val;
          await this.patchOrderState("주문 완료");
        }
      }
      this.selected = [];
    },
    async selectAllReject() {
      this.orderRejectDialogOpen(async () => {
        this.rejectDialog = false;
        for (const val of this.selected) {
          if (val.order_state === "주문 접수") {
            this.orderItem = val;
            await this.patchOrderState("주문 거절");
          }
        }
      });
    },
    socketConnect() {
      const serverURL = "http://localhost:3000/api";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          this.$log.info('소켓 연결 성공', frame);
          this.connected = true;
          this.stompClient.subscribe(`/socket/manager/${this.$route.query.storeId}`, res => {
            this.orderForm.order_state = JSON.parse(res.body).order_state;
          });
        }
      ),
      error => {
        this.$log.info('소켓 연결 실패', error);
        this.connected = false;
      }
    },
  },
};
</script>
