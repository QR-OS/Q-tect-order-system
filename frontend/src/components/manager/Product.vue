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

        <v-tab v-for="category in categories" :key="category">
          {{ category }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab" class="mx-auto">
        <v-tab-item v-for="ele in menus" :key="ele.product_id">
          <v-card flat>
            <v-container>
              <v-data-iterator
                :items="
                  menus.filter(it =>
                    it.product_category.includes(categories[tab])
                  )
                "
                hide-default-footer
              >
                <template v-slot:default="props">
                  <v-row>
                    <v-col
                      v-for="item in props.items"
                      :key="item.product_id"
                      cols="12"
                      sm="6"
                      md="4"
                      lg="3"
                    >
                      <v-card>
                        <v-img
                          height="200"
                          width="auto"
                          src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                        ></v-img>
                        <v-card-title class="subheading font-weight-bold"
                          >{{ item.product_name }}
                          <v-spacer></v-spacer>
                          <v-btn outlined>
                            수정
                          </v-btn>
                        </v-card-title>

                        <v-spacer></v-spacer>
                        <v-list dense>
                          <v-list-item>
                            <v-list-item-content
                              class="align-start font-weight-bold"
                              >{{ item.product_price }}</v-list-item-content
                            >
                            <v-spacer></v-spacer>
                            <div class="align-start font-weight-bold">
                              {{ item.product_stock }}
                            </div>
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
    <v-dialog v-model="dialog" max-width="290">
      <v-card class="overflow-hidden" color="purple lighten-1" dark>
        <v-toolbar flat color="purple">
          <v-icon>mdi-account</v-icon>
          <v-toolbar-title class="font-weight-light"
            >User Profile</v-toolbar-title
          >
          <v-spacer></v-spacer>
          <v-btn
            color="purple darken-3"
            fab
            small
            @click="isEditing = !isEditing"
          >
            <v-icon v-if="isEditing">mdi-close</v-icon>
            <v-icon v-else>mdi-pencil</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text>
          <v-text-field
            :disabled="!isEditing"
            color="white"
            label="Name"
          ></v-text-field>
          <v-autocomplete
            :disabled="!isEditing"
            :items="states"
            :filter="customFilter"
            color="white"
            item-text="name"
            label="State"
          ></v-autocomplete>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :disabled="!isEditing" color="success" @click="save">
            Save
          </v-btn>
        </v-card-actions>
        <v-snackbar v-model="hasSaved" :timeout="2000" absolute bottom left>
          Your profile has been updated
        </v-snackbar>
      </v-card>
      <v-card>
        <v-card-title class="headline"
          >Use Google's location service?</v-card-title
        >

        <v-card-text>
          Let Google help apps determine location. This means sending anonymous
          location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="green darken-1" text @click="dialog = false">
            Disagree
          </v-btn>

          <v-btn color="green darken-1" text @click="dialog = false">
            Agree
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      tab: null,
      itemsPerPage: 6,
      menus: {},
      categories: {},
      dialog: {
        show: false,
        store_id: "",
        product_id: ""
      }
    };
  },
  async created() {
    const categoryResponse = await axios.get(
      "/product/sdafjsdfojasdfojasdjioasd/category"
    );
    this.categories = categoryResponse.data;
    const menuResponse = await axios.get("/product/sdafjsdfojasdfojasdjioasd");
    this.menus = menuResponse.data;
  },
  async method() {}
};
</script>
