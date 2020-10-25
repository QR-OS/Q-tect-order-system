<template>

  <v-container>
      <v-row class="d-flex justify-center">
      <v-col cols="12" sm="6">
        <v-text-field
          hint
          outlined
          append-icon="mdi-magnify"
           v-model="searchWord.word"
        ></v-text-field>
      </v-col>
    </v-row>
    <StoreListComponent :store-info-list="searchWord"></StoreListComponent>
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
      allStoreList: [
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
          { store_id: "3",
          store_name: "이디야",
          store_state: true,
          open_time: "9:00",
          close_time: "11:00" },
          { store_id: "3",
          store_name: "버거킹",
          store_state: true,
          open_time: "9:00",
          close_time: "23:00" },
          { store_id: "3",
          store_name: "생맥주와 통감자",
          store_state: true,
          open_time: "18:00",
          close_time: "5:00" },
          { store_id: "3",
          store_name: "에이바우트",
          store_state: true,
          open_time: "7:00",
          close_time: "11:00" },
      ],
      searchWord: {
        word: '',
      },
    };
  },
  computed: {
  },
  async created() {
    this.searchWord.word = this.$route.query.word;
    this.searchWord.storeList = this.allStoreList;
  },
  methods: {
      searchFunc() {
      //this.$router.push("/searchstorelist?word=" + this.searchWord);
    },
  }
};
</script>
