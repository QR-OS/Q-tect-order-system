<template>
  <v-container>
      <v-row class="d-flex justify-center">
      <v-col cols="12" sm="6">
        <v-text-field
          hint
          outlined
          append-icon="mdi-magnify"
           v-model="searchWord"
          @click:append="searchFunc()"
        ></v-text-field>
      </v-col>
    </v-row>
    <StoreListComponent :store-info-list="storeInfoList"></StoreListComponent>
  </v-container>
</template>

<script>
import StoreListComponent from "../../components/StoreList";
//import axios from "axios";
export default {
  components: {
    StoreListComponent
  },

  data() {
    return {
      storeInfoList: [
        { store_id: "1",
          store_name: "신전떡볶이",
          store_state: true,
          open_time: "12:00",
          close_time: "23:00" },
        { store_id: "2",
          store_name: "라디오회관",
          store_state: false,
          open_time: "17:00",
          close_time: "2:00" },
        { store_id: "3",
          store_name: "크라운호프",
          store_state: true,
          open_time: "18:00",
          close_time: "5:00" },
      ],
      searchWord: "",
    };
  },
  computed: {
  },
  async created() {
    this.searchWord = this.$route.query.word;
    //해당 검색어에 포함되는 매장 리스트들 출력
    //const storeInfoList = await axios.get(`?${this.searchWordCompo}`)

  },
  methods: {
      searchFunc() {
      this.$router.push("/searchstorelist?word=" + this.searchWord);
    },
  }
};
</script>
