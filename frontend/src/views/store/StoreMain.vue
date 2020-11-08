<template>
  <v-container>
        <v-row> <!--간단한 메장정보-->
          <v-col cols="3">
          <v-img
          src="https://picsum.photos/id/11/500/300"
          lazy-src="https://picsum.photos/id/11/100/60"
          max-width="300"
          max-height="300"
        >
        <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular
                indeterminate
                color="grey lighten-5"
              ></v-progress-circular>
            </v-row>
          </template></v-img>
          </v-col>
          <v-col cols="7" class="mt-10">
            <v-row>
            <v-subheader :inset="inset"  v-if="storeInfo.store_state">
        영업중
      </v-subheader>
      <v-subheader :inset="inset"  v-if="!storeInfo.store_state">
        영업안함
      </v-subheader>
            <span class="text-h4">{{ storeInfo.store_name }}</span>
            </v-row>
            <v-row class="mt-5 ml-3">
              open {{ storeInfo.open_time }} ~ close
                    {{ storeInfo.close_time }}
            </v-row>
            <v-row class="ml-3">
              <v-subheader :inset="inset">
        휴무일
      </v-subheader>
            </v-row>
          </v-col>
        </v-row>
        <v-row>     <!--자세한 메장정보-->
          <v-expansion-panels flat>
          <v-expansion-panel>
          <v-expansion-panel-header>
          </v-expansion-panel-header>
               <v-expansion-panel-content>
        <v-col>
          <v-row>
            <v-subheader :inset="inset">
              사업자 번호
          </v-subheader>
          </v-row>
          <v-row>
          <v-divider class="mr-200"></v-divider>
          </v-row>
          <v-row>{{ storeInfo.store_num }}</v-row>
          <v-row>
            <v-subheader :inset="inset">
              매장 전화번호
          </v-subheader>
          <v-divider light></v-divider>
          {{ storeInfo.store_tel }}
          </v-row>
          <v-row>
            <v-subheader :inset="inset">
              매장 분류
          </v-subheader>
          <v-divider light></v-divider>
          <v-chip>한식</v-chip><v-chip>분식</v-chip>
          </v-row>
        </v-col>
        <v-col>
          <v-row>
          <v-subheader :inset="inset">
              가게위치
          </v-subheader>
          </v-row>
          <v-row><v-divider light></v-divider></v-row>
          <div class="" color="gray">우편번호</div> {{ storeInfo.post_num }}
          <div small color="gray">주소</div> {{ storeInfo.address1 }}
          <div small color="gray">상세주소</div> {{ storeInfo.address2 }}
        </v-col>
      </v-expansion-panel-content>
       </v-expansion-panel>
          </v-expansion-panels>
        </v-row>
        <v-row>
          <v-divider light></v-divider>
        </v-row>
        <v-row class="mt-5 mr-5 mb-5">
            <span class="text-h4">메뉴</span>
            <v-spacer></v-spacer>
        </v-row>
        <v-row>     <!--메뉴들 정보-->
    <v-card class="mx-auto" flat>
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
                        <v-card-title @click="orderProduct(item.product_id)"
                          >{{ item.product_name }}
                          <v-spacer></v-spacer>{{ item.product_price }}
                        </v-card-title>
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
        </v-row>
        <!--메뉴 누르면 뜨는 다이얼로그-->
        <v-dialog v-if="dialog" v-model="dialog" max-width="500" max-height="700">
        <select-product
          :storeId="storeInfo"
          :productId="productId"
          @close="close"
        ></select-product>
      </v-dialog>
      <v-snackbar v-model="snackbar">
        {{ snackText }}

        <template v-slot:action="{ attrs }">
          <v-btn text v-bind="attrs" @click="snackbar = false">
            Close
          </v-btn>
        </template>
      </v-snackbar>
  </v-container>
</template>
<script>
import axios from "axios";
import { mapState } from "vuex";
import SelectProduct from "../../components/bucket/SelectProduct.vue";
export default {
  components: {
    "select-product": SelectProduct,
  },
  async mounted() {

  },
  computed: {
    ...mapState("auth", ["user"]),
  },
  data() {
    return {
      storeInfo: {},
      errorMsg: "",
      dialog: false,
      snackbar: false,
      snackText: "",
      productId: "",
      tab: null,
      menus: {},
      categories: {},
      dayOfTheWeek: ['월', '화', '수', '목', '금', '토', '일'],
    };
  },
  async created() {try {
      const res = await axios.get("/store/" + this.$route.query.id);
      this.storeInfo = res.data;
    } catch (error) {
      this.errorMsg = error.response.data.message;
    }
    this.fetch();
  },
  methods: {
    async fetch() {
      //const userInfoRes = await axios.get(`/user/${this.$route.query.id}`);
      //this.userInfo.storeId = userInfoRes.data.store_id;

      const categoryResponse = await axios.get(
        `/product/${this.storeInfo.store_id}/category`
      );
      this.categories = categoryResponse.data;

      const menuResponse = await axios.get(`/product/${this.storeInfo.store_id}`);
      this.menus = menuResponse.data;
    },
    orderProduct(productId) {
      this.productId = productId;
      this.dialog = true;
    },
    close(arg) {
      this.dialog = false;
      if (arg === 0) {
        return;
      }
      if (arg === -1) {
        this.snackText = "에러가 발생했습니다.";
      } else if (arg === 1) {
        this.snackText = "상품이 장바구니에 담겼습니다.";
      }
      this.snackbar = true;
    },
  },
};
</script>
