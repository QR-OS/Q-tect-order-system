<template>
  <v-container class="mx-auto">
    <v-row class="mx-2" v-if="fetched==false">
      <v-col v-for="i in 9" :key="i" cols="12" md="4">
        <v-skeleton-loader type="card-avatar, article, actions" class="mx-auto">
        </v-skeleton-loader>
        </v-col>
    </v-row>
    
    <v-card 
      v-if="fetched && (state==false)"
            color="#385F73"
            dark
            class="mx-auto"
            max-width="1000"
          >
            <v-card-title class="headline">
              상품/메뉴를 등록해보세요!
            </v-card-title>

            <v-card-subtitle>상품/메뉴를 추가하여 고객들이 이용할 수 있도록 하고, 서비스를 통한 재고관리를 이용해보세요!</v-card-subtitle>

            <v-card-actions><v-spacer></v-spacer>
              <v-btn text @click="openProductCreateDialog()">
                등록하기
              </v-btn>
            </v-card-actions>
          </v-card>

    <v-card class="mx-auto" v-if="state && fetched">
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
                :search="search"
                hide-default-footer
              >
                <template v-slot:header>
                  <v-toolbar class="mb-1">
                    <v-text-field
                      v-model="search"
                      clearable
                      flat
                      solo
                      hide-details
                      prepend-inner-icon="mdi-magnify"
                      label="검색"
                    ></v-text-field>
                    <template v-if="!$vuetify.breakpoint.mdAndUp">
                      <v-spacer></v-spacer>

                      <v-btn large depressed @click="openProductCreateDialog()">
                        <v-icon>mdi-plus</v-icon>
                      </v-btn>
                    </template>
                    <template v-if="$vuetify.breakpoint.mdAndUp">
                      <v-spacer></v-spacer>

                      <v-btn large depressed @click="openProductCreateDialog()">
                        <v-icon>mdi-plus</v-icon>
                        메뉴추가
                      </v-btn>
                    </template>
                  </v-toolbar>
                </template>
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
                  label="준비중"
                  color="orange"
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
      </v-card>
    </v-dialog>
    <v-dialog
      v-if="createDialog.show"
      v-model="createDialog.show"
      max-width="350"
    >
      <v-card class="overflow-hidden">
        <v-toolbar flat color="yellow darken-2">
          <v-toolbar-title class="font-weight ma-2">상품 추가</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-container fluid>
          <v-card-text>
            <v-col>
              <v-row>
                <v-text-field
                  v-model="createDialog.data.product_name"
                  label="상품명"
                ></v-text-field>
              </v-row>
              <!-- <v-row>
                <v-select
      v-model="createDialog.data.product_category"
      :items="categories"
      label="카테고리"
    >
      <template v-slot:append-item>
        <v-divider class="mb-2"></v-divider>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              카테고리 추가하기
             
            </v-list-item-title>
             <v-text-field
                  v-model="createDialog.data.product_category"
                  label="상품명"
                ></v-text-field>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-select>
              </v-row> -->
              <v-row>
                
                <v-text-field
                  v-model="createDialog.data.product_category"
                  label="카테고리"
                ></v-text-field>
            
              </v-row>
              <v-row>
                <v-text-field
                  v-model="createDialog.data.product_price"
                  label="상품가격"
                ></v-text-field>
              </v-row>
              <v-row>
                <v-text-field
                  v-model="createDialog.data.product_stock"
                  label="상품재고"
                ></v-text-field>
                <v-spacer></v-spacer>
                <v-switch
                  v-model="createDialog.data.product_state"
                  label="준비중"
                  color="orange"
                  hide-details
                ></v-switch>
              </v-row>
              <v-row>
                <v-text-field
                  v-model="createDialog.data.product_image"
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
            @click="closeProductCreateDialog"
            outlined
          >
            취소
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="yellow darken-3" @click="create" outlined>
            생성
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar
      v-model="snackbar.show"
      :timeout="snackbar.timeout"
      :color="snackbar.color"
    >
      {{ snackbar.text }}

      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="snackbar.show = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
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
      fetched:false,
      state:true,
      userInfo: { storeId: "" },
      tab: null,
      itemsPerPage: 6,
      menus: {},
      categories: {},
      dialog: {
        title: "",
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
      createDialog: {
        show: false,
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
      search: "",
      snackbar: {
        show: false,
        timeout: 2000,
        text: "",
      },
    };
  },
  async created() {
    this.fetch();
  },
  methods: {
    async fetch() {
      this.fetched = false;
      const userInfoRes = await axios.get(`/user/${this.user.user_id}`);
      this.userInfo.storeId = userInfoRes.data.store_id;
      
      try{
      const categoryResponse = await axios.get(
        `/product/${this.userInfo.storeId}/category`
      );
      this.categories = categoryResponse.data;
      }catch(err){
        this.fetched = true;
        console.log(err);
        this.categories = [];
        console.log(2);
        this.state=false;
      }
      
      try{
      const menuResponse = await axios.get(`/product/${this.userInfo.storeId}`);
      this.menus = menuResponse.data;
      }catch(err){
        this.fetched = true;
        console.log(err);
        this.menus =[];
        this.state=false;
      }
      this.fetched = true;
    },
    openProductUpdateDialog(item) {
      this.dialog.title = "상품 정보 수정";
      this.dialog.show = true;
      this.dialog.isEditing = false;
      this.dialog.data = {
        store_id: item.store_id,
        product_id: item.product_id,
        product_category: item.product_category,
        product_image: item.product_image,
        product_name: item.product_name,
        product_price: parseInt(item.product_price),
        product_stock: parseInt(item.product_stock),
        product_state: item.product_state,
      };
    },
    closeProductUpdateDialog() {
      this.dialog.show = false;
      this.dialog.data = null;
    },
    openProductCreateDialog() {
      this.createDialog.data.product_state = true;
      this.createDialog.show = true;
    },
    closeProductCreateDialog() {
      this.createDialog.show = false;
    },
    async create() {
      try {
        await axios.post("/product", {
          store_id: this.userInfo.storeId,
          product_category: this.createDialog.data.product_category,
          product_image: this.createDialog.data.product_image,
          product_name: this.createDialog.data.product_name,
          product_price: parseInt(this.createDialog.data.product_price),
          product_stock: parseInt(this.createDialog.data.product_stock),
          product_state: this.createDialog.data.product_state,
        });
        await this.fetch();
        this.showSnackbar("success", "상품 생성을 성공했습니다.");
        this.fetch();
        this.createDialog.data = null;
        this.createDialog.show = false;
        this.state = true;
      } catch (err) {
        this.showSnackbar("error", err.message);
      }
    },
    async update() {
      try {
        await axios.patch("/product", this.dialog.data);
        await this.fetch();
        this.showSnackbar("success", "업데이트에 성공했습니다.");
      } catch (err) {
        this.showSnackbar("error", err.message);
      }
      this.dialog.show = false;
      this.dialog.data = null;
    },
    async deleteProduct() {
      try {
        await axios.delete(
          `/product/${this.dialog.data.product_id}/${this.userInfo.storeId}`
        );
        await this.fetch();
        this.showSnackbar("success", "상품 삭제를 성공했습니다.");
        this.dialog.show = false;
        this.dialog.data = null;
      } catch (err) {
        this.showSnackbar("error", err.message);
      }
    },
    showSnackbar(color, text) {
      this.snackbar.show = true;
      this.snackbar.color = color;
      this.snackbar.text = text;
    },
  },
};
</script>
