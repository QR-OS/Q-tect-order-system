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
    <v-row><v-col><v-divider light></v-divider></v-col></v-row>
    <v-row justify="center">
      <v-col cols="6" sm="6" align-self="center">
      <v-chip
        v-for="item in categoryItems"
        :key="item.category"
        class="ma-2"
        outlined
        large
        chip
        color="amber darken-1"
        @click="gotoStoreListPage(item.category)"
        >{{ item.category }}</v-chip
      ></v-col></v-row>
  </v-container>
</template>

<script>
export default {
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
      searchWord: "",
    };
  },
  async created() {
    //카테고리를 불러오는 API
    //const categoryItem = await axios.get(`/categoryName`)
  },
  methods: {
    searchFunc() {
      this.$router.push("/searchstorelist?word=" + this.searchWord);
    },
    //카테고리 별 매장 리스트 페이지
    gotoStoreListPage(categoryName) {
      this.$router.push("/categorystorelist?category=" + categoryName);
    }
  }
};
</script>
