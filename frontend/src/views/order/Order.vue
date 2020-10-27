<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="900" outlined>
          <v-card-title
            class="font-weight-bold title mb-4 mt-6 align-center justify-center"
          >
            주문하기
          </v-card-title>
          <v-row>
            <v-col align="end">
              <v-btn :disabled="orderType === 0" @click="selectOrderType(0)">
                주문하기
              </v-btn>
            </v-col>
            <v-col>
              <v-btn :disabled="orderType === 1" @click="selectOrderType(1)"
                >예약하기</v-btn
              >
            </v-col>
          </v-row>
          <p class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            {{ cart[0].storeName }}
          </p>
          <p class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            주문 내역
          </p>
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
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            결제 방법
          </v-row>
          <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
            쿠폰 선택
          </v-row>
          <v-container v-if="orderType === 1">
            <v-row class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
              예약 시간
            </v-row>
          </v-container>
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

export default {
  computed: {
    ...mapState("bucket", ["cart"]),
    total() {
      let rtn = this.cart.reduce((result, product) => {
        result += Number(product.totalPrice);
        return result;
      }, 0);
      return rtn;
    },
  },
  data() {
    return {
      orderType: 0,
    };
  },
  methods: {
    ...mapActions({
      addProductToCart: "bucket/addProductToCart",
      deleteProductToCart: "bucket/deleteProductToCart",
      clearCart: "bucket/clearCart",
    }),
    order() {},
    selectOrderType(type) {
      this.orderType = type;
    },
  },
};
</script>
