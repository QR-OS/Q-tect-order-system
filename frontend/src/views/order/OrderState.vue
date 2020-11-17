<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="900" outlined>
          <v-alert v-if="errorMsg" dense outlined type="error">
            {{ errorMsg }}
          </v-alert>
          <v-card-title
            class="font-weight-bold title mb-4 mt-6 align-center justify-center"
          >
            주문 현황
          </v-card-title>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            {{ storeName }}
          </v-row>
          <v-row
            align="center"
            justify="center"
            class="my-3"
            v-if="orderForm.order_state == '주문 준비 완료'"
          >
            QR코드 이미지
          </v-row>
          <v-row
            class="text-h4 font-weight-bold"
            align="center"
            justify="center"
            v-model="orderForm.order_state"
          >
            {{ orderForm.order_state }}
          </v-row>
          <v-row
            align="center"
            justify="center"
            v-if="orderForm.order_state == '주문 준비 완료'"
          >
            수령시 카운터에 QR코드를 보여주세요!
          </v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2 my-3">
            주문 내역
          </v-row>
          <v-row align="center" justify="center">
            <v-card
              flat
              color="grey lighten-3"
              class="mx-md-10 mx-xs-6 pb-2"
              max-width="500"
              min-width="300"
            >
              <v-list color="grey lighten-3">
                <v-list-item v-for="(item, idx) in orderlist" :key="idx">
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
                총 금액 {{ orderForm.total_price }}원
              </v-row>
            </v-card>
          </v-row>
          <v-row class="ml-md-10 ml-xs-6 ml-2 my-3 mb-10">
            <v-col class="font-weight-bold"
              >결제 방법 : {{ orderForm.pay_type }}</v-col
            >
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
  data() {
    return {
      orderForm: {},
      orderlist: [],
      storeName: "",
      errorMsg: "",
    };
  },
  async mounted() {
    try {
      const res = await axios.get(
        `/order/${this.$route.query.orderId}/${this.$route.query.storeId}`
      );
      this.orderForm = res.data;
      const list = await axios.get(
        `/detailorder/${this.$route.query.orderId}/${this.$store.state.auth.user.user_id}`
      );
      this.orderlist = list.data;

      const res2 = await axios.get(`user/store_id/${this.$route.query.storeId}`);
      const storeInfo = await axios.get('/store/' + res2.data.user_id);
      this.storeName = storeInfo.data.store_name;
    } catch (error) {
      this.errorMsg = error.response.data.message;
    }
  },
  created() {
    this.connect();
  },
  methods: {
    connect() {
      const serverURL = 'http://localhost:3000/api';
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          this.$log.info('소켓 연결 성공', frame);
          this.connected = true;
          this.stompClient.subscribe(`/socket/${this.$route.query.storeId}/user/${this.$route.query.orderId}`, res => {
            console.log(res.body);
            this.orderForm.order_state = JSON.parse(res.body).order_state;
          });
        },
        error => {
          this.$log.info('소켓 연결 실패', error);
          this.connected = false;
        }
      )
    }
  }
};
</script>
