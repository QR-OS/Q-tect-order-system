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
        <v-btn class="ma-2" outlined large fab color="indigo" @click="edit">
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
      storeInfo: {
        Nholiday: {
          items: [
            { key: 1, value: "월" },
            { key: 2, value: "화" },
            { key: 3, value: "수" },
            { key: 4, value: "목" },
            { key: 5, value: "금" },
            { key: 6, value: "토" },
            { key: 7, value: "일" }
          ],
          value: []
        }
      },
      loadingFlag: false,
      answerMsg: "",
      snackbar: false,
      timeout: 2000
    };
  },
  async created() {
    try {
      let res = await axios.get("/store/tnals97");
      this.storeInfo = Object.assign(this.storeInfo, res.data);
      for (let i in this.storeInfo.holiday) {
        if (this.storeInfo.holiday[i] == "1") {
          this.storeInfo.Nholiday.value.push(this.storeInfo.Nholiday.items[i]);
        }
      }
      this.loadingFlag = true;
    } catch (err) {
      this.storeInfo = err;
    }
  },
  methods: {
    edit() {
      let hStr = "";
      let pre = 1;
      for (let day of this.storeInfo.Nholiday.value) {
        for (let i = pre; i <= 7; i++) {
          if (i == day.key) {
            hStr += 1;
            pre = i + 1;
            break;
          } else hStr += 0;
        }
      }
      for (let i = hStr.length; i < 7; i++) hStr += 0;
      this.storeInfo.holiday = hStr;
      axios.patch("/store/tnals97", this.storeInfo).then(res => {
        this.answerMsg = res.data.message;
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
