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
            class="mb-10"
            v-if="orderList.length === 0 || typeof orderList === 'undefined'"
          >
            주문내역이 없습니다!
          </v-row>
          <v-row
            align="center"
            justify="center"
            v-for="(item, idx) in orderList"
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
                  <v-row>
                    <v-col>
                      {{ item.ceo_product_name }} 외
                      {{ item.detail_order_count }}개
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col>{{ item.total_price }}원</v-col>
                  </v-row>
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
import axios from "axios";
import moment from "moment";

export default {
  data() {
    return {
      orderList: [],
      orderTime: [],
      currentDate: moment().utcOffset(0),
      errorMsg: "",
    };
  },
  async mounted() {
    try {
      const res = await axios.get(
        "/order/" + this.$store.state.auth.user.user_id
      );
      this.orderList = res.data;
      this.orderList.sort(function(a, b) {
        return new Date(b.order_time) - new Date(a.order_time);
      });
    } catch (error) {
      if (error.response.status !== 400)
        this.errorMsg = error.response.data.message;
    }
  },
  computed: {
    getTime() {
      this.fetch();
      let rtn = [];
      const offset = this.currentDate.utcOffset();
      for (let i in this.orderList) {
        let orderTime = moment(this.orderList[i].order_time).utcOffset(offset);
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
    fetch() {
      this.currentDate = moment();
    },
  },
};
</script>
