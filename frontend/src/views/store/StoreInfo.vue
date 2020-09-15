<template>
  <v-container>
    <StoreInfoComponent :store-info="storeInfo"></StoreInfoComponent>
    <div style="text-align: center;">
      <v-btn class="ma-2" outlined large fab color="indigo" @click="data">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </div>
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
      storeInfo: {}
    };
  },
  created() {
    axios
      .get("/store/maum97")
      .then(res => {
        console.log(res.data);
        this.storeInfo = res.data;
      })
      .cathch(error => {
        console.log(error.message);
      });
  },
  methods: {
    data() {
      console.log(this.storeInfo);
    }
  }
};
</script>
<style>
button {
  position: relative;
}
</style>
