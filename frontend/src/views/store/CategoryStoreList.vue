<template>
  <v-container>
    <StoreListComponent></StoreListComponent>
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
    return {};
  },
  computed: {
    categoryName() {
      return this.$route.params;
    }
  },
  async created() {
    //const res = await axios.get(`/${this.categoryName}`)
  },
  methods: {}
};
</script>
