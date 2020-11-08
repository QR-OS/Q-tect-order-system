<template>
  <v-container>
    <v-row>
      <v-col>
        <span class="text-h4">{{ categoryName }}</span>
      <v-sheet elevation="0">
    <v-tabs
      v-model="Cname"
      background-color="white"
      color="gray"
      next-icon="mdi-arrow-right"
      prev-icon="mdi-arrow-left"
      show-arrows
    >
      <v-tabs-slider color="white"></v-tabs-slider>
      <v-tab
        v-for="(categoryItem, index) in categoryItems"
        :key="index"
        @click="gotoStoreListPage(categoryItem.category)"
      >
        {{ categoryItem.category }}
      </v-tab>
    </v-tabs>
  </v-sheet>
      </v-col>
  </v-row>
    <StoreListComponent :store-info-list="searchWord"></StoreListComponent>
  </v-container>
</template>

<script>
import StoreListComponent from "../../components/StoreList";
import axios from "axios";
export default {
  components: {
    StoreListComponent
  },

  data() {
    return {
      categoryItems: [
        { category: "한식" },
        { category: "분식" },
        { category: "중식" },
        { category: "일식" },
        { category: "양식" },
        { category: "아시안" },
        { category: "치킨" },
        { category: "패스트푸드" },
        { category: "카페/디저트" },
        { category: "주점" },
        { category: "마켓" }
      ],
      searchWord: {
        word: '',
        storeList : [],
      },
      Cname: "",
    };
  },
  computed: {
    //현재 카테고리를 출력 
    categoryName() {
      return this.$route.query.category;    
      }
  },
  async created() {
    //해당 카테고리에 포함되는 매장 리스트들 출력
    this.Cname = this.$route.query.category;
    try {
    const storeInfoList = await axios.get("/store?category="+ this.$route.query.category);
    this.searchWord.storeList = storeInfoList.data;
    }
    catch(err){
      console.log(err);
    }
  },
  methods: {
    gotoStoreListPage(categoryName) {
      this.$router.push("/categorystorelist?category=" + categoryName);
    }
  }
};
</script>
