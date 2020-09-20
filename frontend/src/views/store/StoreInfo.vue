<template>
  <v-container>
    <v-container v-if="loadingFlag == false">
      <v-alert outlined type="warning" prominent border="left">
        로딩중입니다......!
      </v-alert>
    </v-container>
    <v-container
      v-else-if="storeInfo.store_name != null && loadingFlag == true"
    >
      <StoreInfoComponent :store-info="storeInfo"></StoreInfoComponent>
      <div style="text-align: center;">
        <v-btn class="ma-2" outlined large fab color="indigo" @click="data">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </div>
      <v-snackbar v-model="snackbar" :timeout="timeout">
        {{ answerMsg }}

        <template v-slot:action="{ attrs }">
          <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
            Close
          </v-btn>
        </template>
      </v-snackbar>
    </v-container>
    <v-container v-else>
      <v-alert outlined type="warning" prominent border="left">
        매장을 등록하지 않으셨습니다! 매장 등록을 먼저 해주시길 바랍니다!
      </v-alert>
    </v-container>
  </v-container>
</template>

<script>
import StoreInfoComponent from "../../components/StoreInfoForm";
import axios from "axios";

export default {
  components: {
    StoreInfoComponent
  },
  data() {
    return {
      storeInfo: {},
      loadingFlag: false,
      answerMsg: "",
      snackbar: false,
      timeout: 2000
    };
  },
  async created() {
    try {
      console.log("들어옴");
      let res = await axios.get("/store/tnals97");
      this.storeInfo = res.data;
      console.log(this.storeInfo);
      this.loadingFlag = true;
    } catch (err) {
      this.storeInfo = err;
    }
  },
  methods: {
    data() {
      console.log(this.storeInfo);
      axios.patch("/store/tnals97", this.storeInfo).then(res => {
        this.answerMsg = res.data;
        this.snackbar = true;
      });
    }
  }
};
</script>
<style>
button {
  position: relative;
}
</style>
