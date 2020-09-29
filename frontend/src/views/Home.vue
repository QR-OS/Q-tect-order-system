<template>
<<<<<<< HEAD
  <v-container>
    <v-row class="d-flex justify-center">
      <v-col cols="12" sm="6">
        <v-text-field
          hint
          outlined
          append-icon="mdi-magnify"
          @click:append="searchFunc"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row><v-divider light></v-divider></v-row>
    <v-row justify="center">
      <v-chip
        v-for="item in categoryItem"
        :key="item.category"
        class="ma-2"
        outlined
        rounded-circle
        large
        chip
        color="amber darken-1"
        @click="$router.push(`/categorystorelist`)"
        >{{ item.category }}</v-chip
      >
      <v-chip class="ma-2" large chip color="amber lighten-3" text-color="white"
        >중식</v-chip
      >
      <v-chip class="ma-2" large chip color="amber darken-1" text-color="white"
        >일식</v-chip
      >
      <v-chip class="ma-2" large chip color="amber lighten-3" text-color="white"
        >마트</v-chip
      >
    </v-row>
  </v-container>
=======
  <div class="home">
    <h1>{{ user }}</h1>
  </div>
>>>>>>> 929d0ee9741918d5129a6c64ddffc5e179f7b30f
</template>

<script>
export default {
  data() {
    return {
<<<<<<< HEAD
      categoryItem: [
        { category: "한식" },
        { category: "중식" },
        { category: "일식" },
        { category: "마트" }
      ]
    };
  },
  methods: {
    searchFunc() {
      console.log("1");
    },
    gotoStoreListPage() {}
  }
=======
      user: {},
    };
  },
  created() {
    console.log(this.$store.state.auth.user.user_id);
  },
>>>>>>> 929d0ee9741918d5129a6c64ddffc5e179f7b30f
};
</script>
