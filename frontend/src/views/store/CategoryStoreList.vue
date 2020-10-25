<template>
  <v-container>
    <v-row>
      <v-col>
        <span class="text-h4">{{ categoryName }}</span>
      <v-sheet elevation="0">
    <v-tabs
      background-color="white"
      next-icon="mdi-arrow-right"
      prev-icon="mdi-arrow-left"
      show-arrows
    >
      <v-tabs-slider color="yellow"></v-tabs-slider>
      <v-tab
        v-for="categoryItem in categoryItems"
        :key="categoryItem"
        @click="gotoStoreListPage(categoryItem.category)"
      >
        {{ categoryItem.category }}
      </v-tab>
    </v-tabs>
  </v-sheet>
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
      categoryItems: [
        { category: "한식" },
        { category: "중식" },
        { category: "일식" },
        { category: "분식" },
        { category: "버거" },
        { category: "치킨" },
        { category: "마트" },
        { category: "마트" },
        { category: "마트" }
      ]
    };
  },
  computed: {
    //현재 카테고리를 출력 
    categoryName() {
      return this.$route.query.category;    
      }
  },
  async created() {
    //카테고리들의 목록을 출력
    //const categoryItem = await axios.get(`/categoryName`)
    //해당 카테고리에 포함되는 매장 리스트들 출력
    //const storeInfoList = await axios.get(`?${this.categoryName}`)

  },
  methods: {
    gotoStoreListPage(categoryName) {
      this.$router.push("/categorystorelist?category=" + categoryName);
    }
  }
};
</script>
