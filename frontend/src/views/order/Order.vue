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
            주문하기
          </v-card-title>
          <v-row v-if="bookable">
            <v-col align="end">
              <v-btn
                :disabled="form.order_type === 0"
                @click="selectOrder_type(0)"
              >
                주문하기
              </v-btn>
            </v-col>
            <v-col>
              <v-btn
                :disabled="form.order_type === 1"
                @click="selectOrder_type(1)"
                >예약하기</v-btn
              >
            </v-col>
          </v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            {{ cart[0].storeName }}
          </v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2 my-3">
            주문 내역
          </v-row>
          <v-card flat color="grey lighten-3" class="mx-md-10 mx-xs-6 pb-2">
            <v-list color="grey lighten-3">
              <v-list-item v-for="(item, idx) in cart" :key="idx">
                <v-list-item-content>
                  <v-list-item-title class="font-weight-bold ml-6">
                    <v-row>
                      <v-col>
                        {{ item.productName }}
                      </v-col>
                      <v-col
                        class="mr-6 font-weight-medium"
                        align="end"
                        justify="end"
                      >
                        {{ item.productAmount }} 개 {{ item.totalPrice }}원
                      </v-col>
                    </v-row>
                  </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-row class="mx-3 mb-2"><v-divider /></v-row>
            <v-row justify="end" class="font-weight-bold mr-10 my-3">
              총 금액 {{ total }}원
            </v-row>
          </v-card>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2 mt-3">
            결제 방법
          </v-row>
          <v-row>
            <v-col class="ml-md-14 ml-xs-10 ml-2">
              <v-radio-group v-model="form.pay_type" column>
                <v-radio label="신용카드" value="신용카드"></v-radio>
                <v-radio label="만나서 결제" value="만나서 결제"></v-radio>
              </v-radio-group>
            </v-col>
          </v-row>
          <v-row class="mx-md-10 mx-xs-6 mx-2 mb-5"> <v-divider /></v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            쿠폰 선택
          </v-row>
          <v-row>
            <v-col class="ml-md-14 ml-xs-10 ml-2">
              <v-radio-group v-model="coupon" column>
                <v-radio
                  v-for="(item, idx) in coupons"
                  :key="idx"
                  :label="item.name"
                  :value="item"
                  :disabled="item.case > total"
                ></v-radio>
              </v-radio-group>
            </v-col>
          </v-row>
          <v-container v-if="form.order_type === 1">
            <v-row class="mx-md-8 mx-xs-4 mx-2 mb-5"> <v-divider /></v-row>
            <v-row class="font-weight-bold title ml-md-8 ml-xs-4">
              예약 시간
            </v-row>
            <v-row justify="center">
              <v-spacer />
              <v-col>
                <v-menu
                  v-model="fromDateMenu"
                  bottom
                  offset-y
                  :close-on-content-click="false"
                  transition="scale-transition"
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="날짜"
                      dense
                      outlined
                      readonly
                      :error="!!timeErrorMsg"
                      :value="date"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    locale="en-in"
                    v-model="date"
                    :allowed-dates="allowedDates"
                    :min="currentDate"
                    @update:picker-date="pickerUpdate($event)"
                    no-title
                    @input="fromDateMenu = false"
                  ></v-date-picker>
                </v-menu>
              </v-col>
              <v-col>
                <v-menu
                  v-model="fromTimeMenu"
                  bottom
                  offset-y
                  :close-on-content-click="false"
                  transition="scale-transition"
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      label="시간"
                      dense
                      outlined
                      readonly
                      :error="!!timeErrorMsg"
                      :error-messages="timeErrorMsg"
                      :value="reservationTime"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-time-picker
                    dense
                    v-model="reservationTime"
                    scrollable
                  ></v-time-picker>
                </v-menu>
              </v-col>
              <v-spacer />
            </v-row>
          </v-container>
          <v-row class="mx-md-10 mx-xs-6 mx-2 mb-5"> <v-divider /></v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2 mb-5">
            결제 금액
          </v-row>
          <v-card
            flat
            color="grey lighten-3"
            class="font-weight-bold mx-md-10 mx-xs-6 pb-2"
          >
            <v-row class="mx-6 pt-3">
              <v-col> 주문금액</v-col>
              <v-col justify="end" align="end">{{ total }}원</v-col>
            </v-row>
            <v-row v-if="discountValue !== 0" class="mx-6">
              <v-col> 쿠폰할인</v-col>
              <v-col justify="end" align="end">{{ discountValue }}원</v-col>
            </v-row>
            <v-row class="mx-3 mb-2"><v-divider /></v-row>
            <v-row class="mx-6">
              <v-col>총 결제금액</v-col>
              <v-col justify="end" align="end">{{ payValue }}원</v-col>
            </v-row>
          </v-card>
          <v-row justify="center">
            <v-btn
              class="my-5 white--text"
              color="amber darken-1"
              @click="order"
              depressed
            >
              결제하기
            </v-btn>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
import moment from "moment";

export default {
  computed: {
    ...mapState("bucket", ["cart", "storeId", "storeName"]),
    total() {
      let rtn = this.cart.reduce((result, product) => {
        result += Number(product.totalPrice);
        return result;
      }, 0);
      return rtn;
    },
    discountValue() {
      if (this.coupon.type == "percent") {
        return Math.min(
          this.coupon.maximum,
          Math.floor((this.coupon.discount * this.total) / 100)
        );
      } else {
        if (this.coupon.case <= this.total) {
          return this.coupon.discount;
        } else return 0;
      }
    },
    payValue() {
      return this.total - this.discountValue;
    },
  },
  data() {
    return {
      form: {
        pay_type: "신용카드",
        order_type: 0,
      },
      coupon: {},
      fromDateMenu: false,
      fromTimeMenu: false,
      date: moment().format("YYYY-MM-DD"),
      currentDate: moment().format("YYYY-MM-DD"),
      reservationTime: "",
      availableDates: [],
      holiday: [],
      bookable: true,
      openTime: "00:00",
      closeTime: "00:00",
      errorMsg: "",
      timeErrorMsg: "",
      // 예시
      coupons: [
        {
          id: "1234",
          name: "10% 할인 쿠폰",
          type: "percent",
          maximum: 5000,
          case: 20000,
          discount: 10,
        },
        {
          id: "1234",
          name: "신규 회원 5% 할인 쿠폰",
          type: "percent",
          maximum: 2000,
          case: 0,
          discount: 5,
        },
        {
          id: "1234",
          name: "1000원 할인 쿠폰",
          type: "discount",
          maximum: 1000,
          case: 10000,
          discount: 1000,
        },
        {
          id: "1234",
          name: "선택안함",
          type: "discount",
          maximum: 0,
          case: 0,
          discount: 0,
        },
      ],
    };
  },
  async mounted() {
    try {
      const storeUserId = await axios.get(
        "user/store_id/" + this.cart[0].storeId
      );
      const res = await axios.get("/store/" + storeUserId.data.user_id);
      this.holiday = res.data.holiday.split("").map(Number);
      this.bookable = res.data.bookable;
      this.openTime = res.data.open_time;
      this.closeTime = res.data.close_time;
    } catch (error) {
      this.errorMsg = error.response.data.message;
    }
  },
  methods: {
    ...mapActions({
      addProductToCart: "bucket/addProductToCart",
      deleteProductToCart: "bucket/deleteProductToCart",
      clearCart: "bucket/clearCart",
    }),
    isTimeBetween(startTime, endTime, serverTime) {
      let start = moment(startTime, "H:mm");
      let end = moment(endTime, "H:mm");
      let server = moment(serverTime, "H:mm");
      if (end < start) {
        return (
          (server >= start && server <= moment("23:59:59", "h:mm:ss")) ||
          (server >= moment("0:00:00", "h:mm:ss") && server < end)
        );
      }
      return server >= start && server < end;
    },
    checkform() {
      if (this.form.order_type === 1) {
        if (this.reservationTime === "") {
          this.timeErrorMsg = "예약 시간을 올바르게 입력해주세요!";
          return false;
        }
        let bookTime = this.date + "T" + this.reservationTime;
        if (moment(bookTime).isAfter(moment())) this.form.book_time = bookTime;
        else {
          this.timeErrorMsg = "예약 시간을 올바르게 입력해주세요!";
          return false;
        }
        if (
          !this.isTimeBetween(
            this.openTime,
            this.closeTime,
            this.reservationTime
          )
        ) {
          this.timeErrorMsg = "예약 시간을 올바르게 입력해주세요!";
          return false;
        }
      }
      return true;
    },
    async order() {
      this.date = moment().format("YYYY-MM-DD");
      this.currentDate = moment().format("YYYY-MM-DD");
      if (!this.checkform()) {
        return;
      }
      let body = Object.assign({}, this.form);
      body.total_price = this.payValue;
      body.order_state = "주문 접수";
      body.store_id = this.cart[0].storeId;
      body.user_id = this.$store.state.auth.user.user_id;
      try {
        let res = await axios.post("/order", body);
        let orderId = Number(res.data.order_id);
        let productBody = {
          order_id: orderId,
          store_id: this.cart[0].storeId,
          user_id: this.$store.state.auth.user.user_id,
        };
        for (let item of this.cart) {
          productBody.product_id = item.productId;
          productBody.order_quantity = item.productAmount;
          productBody.product_name = item.productName;
          productBody.product_price = item.totalPrice;
          let res = await axios.post("/detailorder", productBody);
        }
        this.clearCart();
        this.$router.push({
          name: "OrderState",
          query: { orderId: res.data.order_id, storeId: res.data.store_id },
        });
      } catch (error) {
        this.errorMsg = error.response.data.message;
      }
    },
    selectOrder_type(type) {
      this.form.order_type = type;
    },
    allowedDates(a) {
      return this.availableDates.includes(a);
    },
    pickerUpdate: function(val) {
      let totalDay = moment(val, "YYYY-MM").daysInMonth();

      let availableDates = [];

      let monthNow = moment().format("M");
      let monthSelected = moment(val).format("M");
      let day;

      if (monthNow == monthSelected) day = moment().format("D");
      else day = 1;

      for (let i = day; i <= totalDay; i++) {
        let date = moment()
          .month(val.split("-")[1] - 1)
          .date(i)
          .format("YYYY-MM-DD");
        for (let j = 0; j < 7; j++) {
          if (this.holiday[j] === 0) continue;
          if (moment(date).day() !== (j + 1) % 7) availableDates.push(date);
        }
      }
      this.availableDates = availableDates;
      this.allowedDates();
    },
  },
};
</script>
