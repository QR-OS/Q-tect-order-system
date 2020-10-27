<template>
  <div class="fill-height" fluid>
    <v-container v-if="!!errorMsg" class="fill-height" fluid>
      <v-row align="center" justify="center">
        <v-col align="center" justify="center">
          <v-card class="mx-auto" max-width="600" outlined>
            <div class="text-h3 my-5">에러가 발생했습니다 : {{ errorMsg }}</div>
            <v-btn
              class="mb-5 white--text"
              color="amber darken-1"
              depressed
              @click="$router.push({ name: 'Home' })"
            >
              홈으로 돌아가기
            </v-btn>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <v-container v-else class="fill-height" fluid>
      <v-row class="my-0" align="center" justify="center">
        <v-img
          src="https://picsum.photos/id/11/500/300"
          lazy-src="https://picsum.photos/id/11/100/60"
          max-width="500"
          max-height="300"
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
      </v-row>
      <v-row class="mt-n10" align="center" justify="center">
        <v-card outlined>
          <v-card-title>{{ storeInfo.store_name }}</v-card-title>
        </v-card>
      </v-row>
      <v-btn outlined @click="orderProduct">
        메뉴 선택
      </v-btn>
      <v-dialog v-if="dialog" v-model="dialog" max-width="500" max-height="700">
        <select-product
          :storeInfo="storeInfo"
          productId="1234"
          @close="close"
        ></select-product>
      </v-dialog>
      <v-snackbar v-model="snackbar">
        {{ snackText }}

        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="snackbar = false">
            Close
          </v-btn>
        </template>
      </v-snackbar>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import SelectProduct from "../../components/bucket/SelectProduct.vue";

export default {
  components: {
    "select-product": SelectProduct,
  },
  data() {
    return {
      storeInfo: {},
      errorMsg: "",
      dialog: false,
      snackbar: false,
      snackText: "",
    };
  },
  async mounted() {
    try {
      let res = await axios.get("/store/" + this.$route.query.id);
      this.storeInfo = res.data;
    } catch (error) {
      this.errorMsg = error.response.data.message;
    }
  },
  methods: {
    orderProduct() {
      this.dialog = true;
    },
    close(arg) {
      this.dialog = false;
      if (arg === 0) {
        return;
      }
      if (arg === -1) {
        this.snackText = "에러가 발생했습니다.";
      } else if (arg === 1) {
        this.snackText = "상품이 장바구니에 담겼습니다.";
      }
      this.snackbar = true;
    },
  },
};
</script>
