<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="900" outlined>
          <v-card-title
            class="font-weight-bold title mb-4 mt-6 align-center justify-center"
          >
            장바구니
          </v-card-title>
          <v-container v-if="cart.length === 0 || typeof cart === 'undefined'">
            <v-row align="center" justify="center">
              장바구니에 담긴 상품이 없습니다!
            </v-row>
          </v-container>
          <v-container v-else>
            <p class="font-weight-bold title ml-md-10 ml-xs-6 ml-2">
              {{ cart[0].storeName }}
            </p>
            <v-row
              align="center"
              justify="center"
              v-for="(item, idx) in cart"
              :key="idx"
            >
              <v-col cols="12" sm="10" md="10" lg="10" xs="8">
                <v-scroll-y-reverse-transition>
                  <v-card class="pb-3">
                    <v-container class="pa-0">
                      <v-row>
                        <v-col align="end" class="pr-6">
                          <v-btn
                            text
                            fab
                            x-small
                            @click="deleteProductToCart(item)"
                          >
                            <v-icon>mdi-close</v-icon>
                          </v-btn>
                        </v-col>
                      </v-row>
                      <v-row>
                        <v-col class="font-weight-bold ml-6 mt-n10">
                          {{ item.productName }}
                        </v-col>
                      </v-row>
                      <v-row class="mx-3 mb-2"><v-divider /></v-row>
                      <v-row class="mx-3">
                        <v-col sm="2" md="2" lg="2" class="font-weight-bold">
                          수량
                        </v-col>
                        <v-col sm="1" md="1" lg="1">
                          <v-btn depressed fab x-small @click="amountUp(item)">
                            <v-icon>mdi-plus </v-icon>
                          </v-btn>
                        </v-col>
                        <v-col align="center">
                          {{ item.productAmount }}
                        </v-col>
                        <v-col class="px-0">
                          <v-btn
                            depressed
                            fab
                            x-small
                            @click="amountDown(item)"
                          >
                            <v-icon>mdi-minus </v-icon>
                          </v-btn>
                        </v-col>
                        <v-spacer class="d-none d-sm-block" />
                      </v-row>
                      <v-row class="mx-3">
                        <v-col
                          sm="2"
                          md="2"
                          lg="2"
                          xs="2"
                          class="font-weight-bold"
                        >
                          합계
                        </v-col>
                        <v-col> {{ item.totalPrice }}원 </v-col>
                      </v-row>
                    </v-container>
                  </v-card>
                </v-scroll-y-reverse-transition>
              </v-col>
            </v-row>
            <v-row justify="end" class="font-weight-bold mr-10 my-3">
              총 금액 {{ total }}원
            </v-row>
            <v-row justify="center">
              <v-btn
                class="mb-5 white--text"
                color="amber darken-1"
                @click="order"
                depressed
                >주문하기</v-btn
              >
            </v-row>
          </v-container>
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
  methods: {
    ...mapActions({
      addProductToCart: "bucket/addProductToCart",
      deleteProductToCart: "bucket/deleteProductToCart",
      clearCart: "bucket/clearCart",
    }),
    amountUp(product) {
      let item = Object.assign({}, product);
      item.productAmount = 1;
      item.totalPrice = item.productPrice;
      this.addProductToCart(item);
    },
    amountDown(product) {
      if (product.productAmount <= 1) return;
      let item = Object.assign({}, product);
      item.productAmount = -1;
      item.totalPrice = item.productPrice * -1;
      this.addProductToCart(item);
    },
    order() {
      if (typeof this.cart !== "undefined" && this.cart.length > 0) {
        this.$router.push({ name: "Order" });
      }
    },
  },
};
</script>
