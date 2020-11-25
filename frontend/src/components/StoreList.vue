<template>
  <v-container fluid>
    <v-row v-if="storeInfoList.storeList.length == 0">
      목록이 없습니다.
    </v-row>
    <v-row v-else>
      <v-data-iterator
        :items="storeInfoList.storeList"
        :search="storeInfoList.word"
      >
        <template v-slot:default="props">
          <v-row>
            <v-col
              v-for="storeItem in props.items"
              :key="storeItem.store_id"
              sm="6"
              md="4"
              lg="3"
              class="ma-9"
            >
              <v-card outlined min-width="200" @click="gotoStoreMainPage(storeItem.store_id)">
                <v-row class="ma-0 pa-0">
                  <v-col>
                    <v-img
                      class="mt-n8 ml-2"
                      height="80"
                      width="80"
                      src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                    ></v-img>
                  </v-col>

                  <v-col>
                    <v-row>
                      <v-list-item class="pa-0">
                        <v-list-item-content>
                          <v-list-item-subtitle v-if="storeItem.store_state">
                            영업중</v-list-item-subtitle
                          >
                          <v-list-item-subtitle v-if="!storeItem.store_state"
                            >영업안함</v-list-item-subtitle
                          >
                          <v-list-item-title>{{
                            storeItem.store_name
                          }}</v-list-item-title>
                        </v-list-item-content>
                      </v-list-item>
                    </v-row>
                  </v-col>
                </v-row>
                <v-row><v-divider class="mx-5 pa-0"></v-divider></v-row>
                <v-row class="mt-0 mb-0">
                  <v-card-text class="ml-2">
                    open {{ storeItem.open_time }} ~ close
                    {{ storeItem.close_time }}
                  </v-card-text>
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </template>
      </v-data-iterator>
    </v-row>
  </v-container>
</template>
<script>
import axios from "axios";
export default {
  props: ["storeInfoList"],
  data() {
    return {
      search: "",
    };
  },
  computed: {},
  methods: {
    async gotoStoreMainPage(storeId) {
      const res = await axios.get("/user/store_id/" + storeId);
      this.$router.push("/storemain?id=" + res.data.user_id);
    }
  },
  created() {
    this.search = this.storeInfoList.word;
  },
};
</script>
