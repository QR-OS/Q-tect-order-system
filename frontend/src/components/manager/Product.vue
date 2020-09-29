<template>
  <v-container class="mx-auto">
    <v-card class="mx-auto">
      <v-tabs
        v-model="tab"
        background-color="yellow darken-2"
        show-arrows
        center-active
      >
        <v-tabs-slider color="yellow darken-5"></v-tabs-slider>

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
                  menus.filter((it) =>
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
                          <v-btn
                            outlined
                            @click="openProductUpdateDialog(item)"
                          >
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
    <v-dialog v-if="dialog.show" v-model="dialog.show" max-width="350">
      <v-card class="overflow-hidden">
        <v-toolbar flat color="yellow darken-2">
          <v-toolbar-title class="font-weight ma-2"
            >상품 정보 수정</v-toolbar-title
          >
          <v-spacer></v-spacer>
          <v-btn
            color="yellow darken-3"
            fab
            small
            @click="dialog.isEditing = !dialog.isEditing"
          >
            <v-icon v-if="dialog.isEditing">mdi-close</v-icon>
            <v-icon v-else>mdi-pencil</v-icon>
          </v-btn>
        </v-toolbar>
        <v-container fluid>
          <v-card-text>
            <v-col>
              <v-row>
                <v-text-field
                  :disabled="!dialog.isEditing"
                  v-model="dialog.data.product_name"
                  label="상품명"
                ></v-text-field>
              </v-row>
              <v-row>
                <v-autocomplete
                  v-model="dialog.data.product_category"
                  :disabled="!dialog.isEditing"
                  :items="categories"
                  item-text="category"
                  label="카테고리"
                ></v-autocomplete>
              </v-row>
              <v-row>
                <v-text-field
                  :disabled="!dialog.isEditing"
                  v-model="dialog.data.product_price"
                  label="상품가격"
                ></v-text-field>
              </v-row>
              <v-row>
                <v-text-field
                  :disabled="!dialog.isEditing"
                  v-model="dialog.data.product_stock"
                  label="상품재고"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-switch
                  v-model="dialog.data.product_state"
                  :disabled="!dialog.isEditing"
                  label="판매여부"
                  color="orange"
                  value="판매여부"
                  hide-details
                ></v-switch>
              </v-row>
              <v-row>
                <v-text-field
                  :disabled="!dialog.isEditing"
                  v-model="dialog.data.product_image"
                  label="이미지를 넣을 공간입니다."
                ></v-text-field>
              </v-row>
            </v-col>
          </v-card-text>
        </v-container>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="yellow darken-3"
            @click="closeProductUpdateDialog"
            outlined
          >
            취소
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="yellow darken-3" @click="deleteProduct" outlined>
            삭제
          </v-btn>
          <v-btn
            :disabled="!dialog.isEditing"
            color="yellow darken-3"
            @click="update"
            outlined
          >
            저장
          </v-btn>
        </v-card-actions>
        <!-- <v-snackbar v-model="hasSaved" :timeout="2000" absolute bottom left>
          Your profile has been updated
        </v-snackbar> -->
      </v-card>
    </v-dialog>
  </v-container>
</template>
<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState("auth", ["user"]),
  },
  data() {
    return {
      userInfo: { storeId: "" },
      tab: null,
      itemsPerPage: 6,
      menus: {},
      categories: {},
      dialog: {
        show: false,
        isEditing: false,
        data: {
          store_id: "",
          product_id: "",
          product_category: "",
          product_image: "",
          product_name: "",
          product_price: "",
          product_stock: "",
          product_state: "",
        },
      },
    };
  },
  async created() {
    const userInfoRes = await axios.get(`/user/${this.user.user_id}`);
    this.userInfo.storeId = userInfoRes.data.store_id;

    const categoryResponse = await axios.get(
      `/product/${this.userInfo.storeId}/category`
    );
    this.categories = categoryResponse.data;

    const menuResponse = await axios.get(`/product/${this.userInfo.storeId}`);
    this.menus = menuResponse.data;
  },
  methods: {
    openProductUpdateDialog: function(item) {
      this.dialog.show = true;
      this.dialog.isEditing = false;
      this.dialog.data = {
        store_id: item.store_id,
        product_id: item.product_id,
        product_category: item.product_category,
        product_image: item.product_image,
        product_name: item.product_name,
        product_price: item.product_price,
        product_stock: item.product_stock,
        product_state: item.product_state,
      };
    },
    closeProductUpdateDialog: function() {
      this.dialog.show = false;
      this.dialog.data = null;
    },
    update: function() {
      console.log("update");
      const res = axios.patch("/product", this.dialog.data);
      console.log(res.data);
    },
    deleteProduct: function() {
      console.log("delete");
    },
  },
};
</script>
