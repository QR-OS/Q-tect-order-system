<template>
  <v-container class="ma-0 pa-0">
    <v-sheet class="ma-0 pa-6" color="white">
      <v-row>
        <v-col align="center">
          <v-img
            src="https://picsum.photos/id/11/500/300"
            lazy-src="https://picsum.photos/id/11/100/60"
            max-width="300"
            max-height="200"
          >
            <template v-slot:placeholder>
              <v-row class="fill-height ma-0" align="center" justify="center">
                <v-progress-circular
                  indeterminate
                  color="grey lighten-5"
                ></v-progress-circular>
              </v-row>
            </template>
          </v-img>
        </v-col>
      </v-row>
      <v-row class="mt-2">
        <v-col class="text-h4" align="center">
          {{ productInfo.product_name }}
        </v-col>
      </v-row>
      <v-row class="ma-3">
        <v-col class="font-weight-bold">
          기본
        </v-col>
        <v-col align="end"> {{ productInfo.product_price }}원 </v-col>
      </v-row>
      <v-row><v-divider light></v-divider></v-row>
      <v-row class="mx-3">
        <v-col class="font-weight-bold" md="6" xs="6">
          수량
        </v-col>
        <v-col align="end">
          <v-btn depressed fab x-small @click="amountUp">
            <v-icon>mdi-plus </v-icon>
          </v-btn>
        </v-col>
        <v-col align="center">
          {{ productAmount }}
        </v-col>
        <v-col class="px-0" align="center">
          <v-btn depressed fab x-small @click="amountDown">
            <v-icon>mdi-minus </v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row><v-divider light></v-divider></v-row>
      <v-row class="mx-3">
        <v-col class="font-weight-bold">
          합계
        </v-col>
        <v-col align="end"> {{ totalAmount }}원 </v-col>
      </v-row>
      <v-row>
        <v-col align="center">
          <v-btn depressed @click="addToCart">
            장바구니에 담기
          </v-btn>
        </v-col>
      </v-row>
    </v-sheet>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";

export default {
  props: ["storeId", "productId"],
  data() {
    return {
      productInfo: {
        product_name: "",
        product_price: 0,
      },
      productAmount: 1,
    };
  },
  async created() {
    try {
      let res = await axios.get(
        "/product/" + this.productId + "/" + this.storeId
      );
      this.productInfo = res.data;
    } catch (error) {
      this.errorMsg = error.response.data.message;
      //this.productInfo.product_name = "sample";
      //this.productInfo.product_price = 4500;
      this.$emit("close", true);
    }
  },
  computed: {
    totalAmount() {
      return this.productAmount * this.productInfo.product_price;
    },

  },
  methods: {
    ...mapActions({
      addProductToCart: "bucket/addProductToCart",
    }),
    async updatePage(){

    },
    amountUp() {
      this.productAmount += 1;
    },
    amountDown() {
      if (this.productAmount > 1) this.productAmount -= 1;
    },
    addToCart() {
      this.productInfo.productId = this.productId;
      this.addProductToCart(this.productInfo);
      this.$emit("close", false);
    },
  },
};
</script>
