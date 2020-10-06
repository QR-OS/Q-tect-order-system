<template>
  <v-container>
    <v-row>
      <v-card class="ma-12" v-for="storeItem in storeInfoList" :key="storeItem.id">
        <v-row class="ma-0 pa-0" name="1">
          <v-col>
            <v-img
              class="mt-n8 ml-2"
              height="80"
              width="80"
              src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
            ></v-img>
          </v-col>

          <v-col>
            <v-row name="2">
              <v-list-item class="pa-0">
                <v-list-item-content>
                  <v-list-item-subtitle v-if="storeItem.store_state">영업중</v-list-item-subtitle>
                  <v-list-item-subtitle v-if="!storeItem.store_state">영업안함</v-list-item-subtitle>
                  <v-list-item-title>{{ storeItem.store_name }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-row>
          </v-col>
        </v-row>
        <v-row><v-divider class="mx-5 pa-0"></v-divider></v-row>
        <v-row class="mt-0 mb-0">
          <v-card-text class="ml-2">
            open {{ storeItem.open_time }} ~ close {{ storeItem.close_time }}
          </v-card-text>
        </v-row>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: ['storeInfoList'],
  methods: {}
};
</script>
