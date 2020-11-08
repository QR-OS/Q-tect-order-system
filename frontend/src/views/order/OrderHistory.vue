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
            주문 내역
          </v-card-title>
          <v-row
            align="center"
            justify="center"
            v-for="(item, idx) in orderlist"
            :key="idx"
          >
            <v-card class="mx-md-10 mx-xs-6 pb-2 mb-4">
              <v-container>
                <v-card-title>
                  {{ item.store_name }}
                </v-card-title>
                <v-card-subtitle>
                  {{ getTime[idx] }}
                </v-card-subtitle>
                <v-card-text>
                  <div>{{ item.total_price }}원</div>
                </v-card-text>
                <v-card-actions>
                  <v-btn
                    class="mx-2"
                    outlined
                    @click="moveToStoreMain(item.store_id)"
                  >
                    <v-icon>
                      mdi-store
                    </v-icon>
                    가게 보기
                  </v-btn>
                  <v-btn
                    class="mx-2"
                    outlined
                    @click="moveToOrderState(item.order_id, item.store_id)"
                  >
                    <v-icon>
                      mdi-clipboard-text-outline
                    </v-icon>
                    상세 내역
                  </v-btn>
                </v-card-actions>
              </v-container>
            </v-card>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
//import axios from "axios";
import moment from "moment";

export default {
  data() {
    return {
      orderlist: [
        {
          store_name: "신전떡볶이",
          order_type: 0,
          order_time: "2020-11-03T12:00:38.000+00:00",
          book_time: null,
          order_id: "16",
          total_price: 5500,
          pay_type: "신용카드",
          order_state: "주문 접수",
          store_id: "7E803T0qnH7mevP1MYhEwH0Vo",
          user_id: "1234",
        },
        {
          store_name: "신전떡볶이",
          order_type: 0,
          order_time: "2020-11-08T20:14:48.000+00:00",
          book_time: null,
          order_id: "15",
          total_price: 9500,
          pay_type: "신용카드",
          order_state: "주문 접수",
          store_id: "7E803T0qnH7mevP1MYhEwH0Vo",
          user_id: "1234",
        },
      ],
      orderTime: [],
      currentDate: moment(),
      errorMsg: "",
    };
  },
  async mounted() {
    try {
      console.log("mounted");
    } catch (error) {
      this.errorMsg = error.response.data.message;
    }
  },
  computed: {
    getTime() {
      let rtn = [];
      for (let i in this.orderlist) {
        let orderTime = moment(
          this.orderlist[i].order_time,
          "YYYY-MM-DD HH:mm"
        );
        let diff = moment.duration(this.currentDate.diff(orderTime));
        if (this.currentDate.format("YYYY") !== orderTime.format("YYYY")) {
          rtn.push(moment(orderTime).format("YYYY/MM/DD"));
        } else if (diff.asMonths() >= 1) {
          rtn.push(moment(orderTime).format("MM/DD"));
        } else if (diff.asDays() >= 1) {
          rtn.push(Math.floor(diff.asDays()) + "일 전");
        } else if (diff.asHours() >= 1) {
          rtn.push(Math.floor(diff.asHours()) + "시간 전");
        } else if (diff.asMinutes() >= 1) {
          rtn.push(Math.floor(diff.asMinutes()) + "분 전");
        } else rtn.push("방금 전");
      }
      return rtn;
    },
  },
  methods: {
    moveToStoreMain(storeId) {
      // storeid를 통해 주인의 userid를 받아옴
      console.log(storeId);
      let userId = "test123";
      this.$router.push({
        name: "StoreMain",
        query: { id: userId },
      });
    },
    moveToOrderState(orderId, storeId) {
      this.$router.push({
        name: "OrderState",
        query: { orderId: orderId, storeId: storeId },
      });
    },
  },
};
</script>
