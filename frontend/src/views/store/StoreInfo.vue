<template>
  <v-container>
    <StoreInfoComponent :store-info-data="storeInfo"></StoreInfoComponent>
    <div style="text-align: center;">
      <v-btn class="ma-2" outlined large fab color="indigo" @click="data">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </div>
  </v-container>
</template>

<script>
import StoreInfoComponent from "../../components/StoreInfoForm";
export default {
  components: {
    StoreInfoComponent
  },
  data() {
    return {
      storeInfo: {
        store_name: "",
        store_id: "",
        post_num: "",
        address1: "",
        address2: "",
        store_tel: "",
        img: "",
        store_type: [],
        open_time: "",
        close_time: ""
      }
    };
  },
  created() {
    /*axios.get('/api/store/maum97').then(res => {
            this.storeInfo = res.data;
        });//user id를 통한 접근*/
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
