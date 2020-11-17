<template>
  <v-container class="ma-0 pa-0">
    <v-sheet class="ma-0 pa-6" color="white">
      <v-row>
        <v-col align="center">
          <v-img
            :src="productInfo.product_image"
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
      <v-dialog v-if="dialog" v-model="dialog" max-width="300" max-height="100">
        <v-card>
          <v-card-title class="text-center">
            장바구니에는 같은 가게의 상품만 담을 수 있습니다.
          </v-card-title>
          <v-card-text class="text-center">
            선택하신 상품을 장바구니에 담을 경우 이전에 담은 상품은 삭제됩니다.
          </v-card-text>
          <v-card-action>
            <v-row class="mx-0 pb-2" align="center" justify="center">
              <v-btn text @click="cancel">취소</v-btn>
              <v-btn text @click="clearAndAdd">담기</v-btn>
            </v-row>
          </v-card-action>
        </v-card>
      </v-dialog>
    </v-sheet>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapActions } from "vuex";

export default {
  props: ["storeInfo", "productId"],
  data() {
    return {
      productInfo: {
        product_name: "",
        product_price: 0,
      },
      productAmount: 1,
      dialog: false,
    };
  },
  async created() {
    try {
      let res = await axios.get(
        "/product/" + this.productId + "/" + this.storeInfo.store_id
      );
      this.productInfo = res.data;
    } catch (error) {
      this.$emit("close", -1);
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
      clearCart: "bucket/clearCart",
    }),
    async updatePage() {},
    amountUp() {
      this.productAmount += 1;
    },
    amountDown() {
      if (this.productAmount > 1) this.productAmount -= 1;
    },
    async addToCart() {
      // order : cart에 추가할 상품 정보
      let totalPrice = this.totalAmount;
      let order = {
        storeId: this.storeInfo.store_id,
        storeName: this.storeInfo.store_name,
        productId: this.productId,
        productName: this.productInfo.product_name,
        productAmount: this.productAmount,
        productPrice: this.productInfo.product_price,
        totalPrice: totalPrice,
      };
      try {
        let res = await this.addProductToCart(order);
      } catch (error) {
        if (error.name == "DifferentStoreIdError") this.dialog = true;
        else {
          this.$emit("close", -1);
        }
        return;
      }
      this.$emit("close", 1);
    },
    cancel() {
      this.dialog = false;
      this.$emit("close", 0);
    },
    async clearAndAdd() {
      this.clearCart();
      await this.addToCart();
    },
  },
};
</script>
