<template>
  <v-container fluid>

    <v-data-iterator
      :items="storeInfoList.storeList"
      :items-per-page.sync="itemsPerPage"
      :page="page"
      :search="storeInfoList.word"
      :sort-desc="sortDesc"
      hide-default-footer
    >
      <template v-slot:header>
        <v-row class="d-flex justify-center">
      <v-col cols="12" sm="6">
          <v-text-field
           hint
          outlined
          append-icon="mdi-magnify"
           v-model="storeInfoList.word"
          ></v-text-field>
      </v-col>
        </v-row>
          <template v-if="$vuetify.breakpoint.mdAndUp">
            <v-spacer></v-spacer>
          </template>
      </template>

      <template v-slot:default="props">
        <v-row>
          <v-col
            v-for="storeItem in props.items"
            :key="storeItem.id"
            col="12"
            sm="6"
            md="4"
            lg="3"
          >
      <v-card class="ma-12" outlined width="250">
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

          </v-col>
        </v-row>
      </template>

      <template v-slot:footer>
        <v-row
          class="mt-2"
          align="center"
          justify="center"
        >
          <v-menu offset-y>
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                dark
                text
                color="primary"
                class="ml-2"
                v-bind="attrs"
                v-on="on"
              >
                {{ itemsPerPage }}
                <v-icon>mdi-chevron-down</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(number, index) in itemsPerPageArray"
                :key="index"
                @click="updateItemsPerPage(number)"
              >
                <v-list-item-title>{{ number }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>

          <v-spacer></v-spacer>

          <span
            class="mr-4
            grey--text"
          >
            Page {{ page }} of {{ numberOfPages }}
          </span>
          <v-btn
          outlined
          small
            fab
            dark
            color="blue darken-3"
            class="mr-1"
            @click="formerPage"
          >
            <v-icon>mdi-chevron-left</v-icon>
          </v-btn>
          <v-btn
            outlined
            small
            fab
            dark
            color="blue darken-3"
            class="ml-1"
            @click="nextPage"
          >
            <v-icon>mdi-chevron-right</v-icon>
          </v-btn>
        </v-row>
      </template>
    </v-data-iterator>
  </v-container>
</template>
<script>
  export default {
    props: ['storeInfoList'],
    data () {
      return {
        itemsPerPageArray: [4, 8, 12],
        search: '',
        filter: {},
        sortDesc: false,
        page: 1,
        itemsPerPage: 4,
        sortBy: 'name',
        
      }
    },
    computed: {
      numberOfPages () {
        return Math.ceil(this.storeInfoList.storeList.length / this.itemsPerPage)
      },
      filteredKeys () {
        return this.keys.filter(key => key !== 'Name')
      },
    },
    methods: {
      nextPage () {
        if (this.page + 1 <= this.numberOfPages) this.page += 1
      },
      formerPage () {
        if (this.page - 1 >= 1) this.page -= 1
      },
      updateItemsPerPage (number) {
        this.itemsPerPage = number
      },
    },
    created() {
      this.search = this.storeInfoList.word;
    },
  }
</script>