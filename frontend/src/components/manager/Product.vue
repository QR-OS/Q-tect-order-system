<template>
  <v-container class="mx-auto">
    <v-card class="mx-auto">
      <v-tabs
        v-model="tab"
        dark
        background-color="teal darken-3"
        show-arrows
        center-active
      >
        <v-tabs-slider color="teal lighten-3"></v-tabs-slider>

        <v-tab v-for="tabtitle in items" :key="tabtitle.tab">
          Item {{ tabtitle.tab }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab" class="mx-auto">
        <v-tab-item v-for="ele in items" :key="ele.tab">
          <v-card flat>
            <v-container>
              <v-data-iterator :items="ditems" hide-default-footer>
                <template v-slot:default="props">
                  <v-row>
                    <v-col
                      v-for="item in props.items"
                      :key="item.name"
                      cols="12"
                      sm="6"
                      md="4"
                      lg="3"
                    >
                      <v-card>
                        <v-card-title class="subheading font-weight-bold">{{
                          item.name
                        }}</v-card-title>

<<<<<<< HEAD
                        <v-spacer></v-spacer>
                        <v-btn>dd</v-btn>
=======
                        <v-divider></v-divider>

>>>>>>> develop
                        <v-list dense>
                          <v-list-item>
                            <v-list-item-content>Calories:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.calories
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Fat:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.fat
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Carbs:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.carbs
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Protein:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.protein
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Sodium:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.sodium
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Calcium:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.calcium
                            }}</v-list-item-content>
                          </v-list-item>

                          <v-list-item>
                            <v-list-item-content>Iron:</v-list-item-content>
                            <v-list-item-content class="align-end">{{
                              item.iron
                            }}</v-list-item-content>
                          </v-list-item>
                        </v-list>
                      </v-card>
                    </v-col>
                  </v-row>
                </template>
              </v-data-iterator>
            </v-container>
          </v-card>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-container>
</template>
<script>
<<<<<<< HEAD
import axios from "axios";

=======
>>>>>>> develop
export default {
  data() {
    return {
      tab: null,
      items: [
        { tab: "One", content: "Tab 1 Content" },
        { tab: "Two", content: "Tab 2 Content" },
        { tab: "Three", content: "Tab 3 Content" },
        { tab: "Four", content: "Tab 4 Content" },
        { tab: "Five", content: "Tab 5 Content" },
        { tab: "Six", content: "Tab 6 Content" },
        { tab: "Seven", content: "Tab 7 Content" },
        { tab: "Eight", content: "Tab 8 Content" },
        { tab: "Nine", content: "Tab 9 Content" },
        { tab: "Ten", content: "Tab 10 Content" }
      ],
      itemsPerPage: 6,
      ditems: [
        {
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: "14%",
          iron: "1%"
        },
        {
          name: "Ice cream sandwich",
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          sodium: 129,
          calcium: "8%",
          iron: "1%"
        },
        {
          name: "Eclair",
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          sodium: 337,
          calcium: "6%",
          iron: "7%"
        },
        {
          name: "Cupcake",
          calories: 3053,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: "3%",
          iron: "8%"
        },
        {
          name: "Jelly",
          calories: 305421,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: "3%",
          iron: "8%"
        },
        {
          name: "Bob",
          calories: 301235,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: "3%",
          iron: "8%"
        }
      ],
      menus: {}
    };
  },
  async created() {
    this.menus = await axios.get("/product/sdafjsdfojasdfojasdjioasd");
  }
};
</script>
