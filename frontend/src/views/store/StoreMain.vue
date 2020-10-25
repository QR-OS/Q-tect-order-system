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
          src="https://bad.src/not/valid"
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
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      storeInfo: {},
      errorMsg: "",
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
};
</script>
