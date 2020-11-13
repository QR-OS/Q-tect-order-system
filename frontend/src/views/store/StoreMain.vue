<template>
  <v-container v-if="!!errorMsg" class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col align="center" justify="center">
        <v-card class="mx-auto" max-width="600" outlined>
          <div class="text-h3 my-5">에러가 발생했습니다 : {{ errorMsg }}</div>
          <v-btn
            class="mb-5 white--text"
            color="amber darken-1"
            depressed
            @click="$router.push({ name: 'Home' })"
          >
            홈으로 돌아가기
          </v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
  <v-container v-else>
    <v-row class="mx-3 mt-3">
      <!--간단한 메장정보-->
      <v-col cols="3">
        <v-img
          src="https://picsum.photos/id/11/500/300"
          lazy-src="https://picsum.photos/id/11/100/60"
          max-width="300"
          max-height="300"
        >
          <template v-slot:placeholder>
            <v-row class="fill-height ma-0" align="center" justify="center">
              <v-progress-circular indeterminate color="grey lighten-5">
              </v-progress-circular>
            </v-row>
          </template>
        </v-img>
      </v-col>
      <v-col cols="7" class="mt-10">
        <v-row>
          <v-col>
            <v-subheader :inset="inset" v-if="storeInfo.store_state">
              영업중
            </v-subheader>
            <v-subheader :inset="inset" v-if="!storeInfo.store_state">
              영업안함
            </v-subheader>
          </v-col>
          <v-col align="start" class="pa-0 text-md-h4 font-weight-bold">
            {{ storeInfo.store_name }}
          </v-col>
        </v-row>
        <v-row class="mt-5 ml-3">
          <v-col>
            open {{ storeInfo.open_time }} ~ close
            {{ storeInfo.close_time }}
          </v-col>
        </v-row>
        <v-row class="mx-3">
          <v-col md="3" sm="5">
            <v-subheader :inset="inset">
              휴무일
            </v-subheader>
          </v-col>
        </v-row>
        <v-row><v-divider /></v-row>
        <v-row class="mx-3">
          <v-col
            class="mx-0 px-0"
            md="1"
            sm="2"
            xs="4"
            v-for="(item, idx) in storeInfo.holiday"
            :key="idx"
          >
            <v-chip>{{ item }}</v-chip>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <!--자세한 메장정보-->
        <v-expansion-panels flat>
          <v-expansion-panel>
            <v-expansion-panel-header class="font-weight-bold">
              가게정보</v-expansion-panel-header
            >
            <v-expansion-panel-content>
              <v-row>
                <v-col col="12" xs="12" sm="6" md="3">
                  <v-row>
                    <v-col>
                      <v-subheader :inset="inset">
                        사업자 번호
                      </v-subheader>
                      <v-divider></v-divider>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="ml-4">{{ storeInfo.store_num }} </v-col>
                  </v-row>
                </v-col>
                <v-col col="12" xs="12" sm="6" md="3">
                  <v-row>
                    <v-col>
                      <v-subheader :inset="inset">
                        매장 전화번호
                      </v-subheader>
                      <v-divider></v-divider>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col class="ml-4">{{ storeInfo.store_tel }} </v-col>
                  </v-row>
                </v-col>
                <v-col>
                  <v-row>
                    <v-col>
                      <v-subheader :inset="inset">
                        매장 분류
                      </v-subheader>
                      <v-divider></v-divider>
                    </v-col>
                  </v-row>
                  <v-row class="mx-4">
                    <v-col
                      class="mx-0 px-0"
                      align="center"
                      v-for="(item, idx) in storeCategory"
                      :key="idx"
                    >
                      <v-chip>{{ item.store_type }}</v-chip>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-row>
                    <v-col>
                      <v-subheader :inset="inset">
                        가게위치
                      </v-subheader>
                      <v-divider></v-divider>
                    </v-col>
                  </v-row>
                  <v-row class="ml-4">
                    <v-col md="2" sm="3" class="grey--text">
                      우편번호
                    </v-col>
                    <v-col>
                      {{ storeInfo.post_num }}
                    </v-col>
                  </v-row>
                  <v-row class="ml-4">
                    <v-col md="2" sm="3" class="grey--text">주소</v-col>
                    <v-col>{{ storeInfo.address1 }}</v-col>
                  </v-row>
                  <v-row class="ml-4">
                    <v-col md="2" sm="3" class="grey--text">
                      상세주소
                    </v-col>
                    <v-col>
                      {{ storeInfo.address2 }}
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
    <v-row>
      <v-divider></v-divider>
    </v-row>
    <v-row class="ma-5">
      <span class="text-md-h5 text-sm-h6 font-weight-bold">메뉴</span>
      <v-spacer></v-spacer>
    </v-row>
    <v-row>
      <v-col>
        <!--메뉴들 정보-->
        <v-card class="mx-auto" flat>
          <v-tabs v-model="tab" background-color="yellow darken-2">
            <v-tabs-slider color="yellow darken-5"></v-tabs-slider>

            <v-tab v-for="category in categories" :key="category">
              {{ category }}
            </v-tab>
          </v-tabs>

          <v-tabs-items v-model="tab">
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
                          <v-card @click.native="orderProduct(item.product_id)">
                            <v-img
                              height="200"
                              width="auto"
                              src="https://cdn.vuetifyjs.com/images/cards/store.jpg"
                            ></v-img>
                            <v-card-title
                              >{{ item.product_name }} <v-spacer></v-spacer
                              >{{ item.product_price }}
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
      </v-col>
    </v-row>
    <v-fab-transition>
      <v-btn
        color="yellow darken-2 mb-5 mr-5"
        fixed
        fab
        dark
        bottom
        right
        @click="$router.push({ name: 'Bucket' })"
      >
        <v-icon>mdi-cart</v-icon>
      </v-btn>
    </v-fab-transition>
    <!--메뉴 누르면 뜨는 다이얼로그-->
    <v-dialog v-if="dialog" v-model="dialog" max-width="500" max-height="700">
      <select-product
        :storeInfo="storeInfo"
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
  async mounted() {},
  computed: {
    ...mapState("auth", ["user"]),
  },
  data() {
    return {
      storeInfo: {},
      storeCategory: [],
      errorMsg: "",
      dialog: false,
      snackbar: false,
      snackText: "",
      productId: "",
      tab: null,
      menus: {},
      categories: [],
      dayOfTheWeek: ["월", "화", "수", "목", "금", "토", "일"],
      search: "",
      inset: false,
    };
  },
  async created() {
    try {
      const res = await axios.get("/store/" + this.$route.query.id);
      this.storeInfo = res.data;
      let holiday = [];
      const parsedholiday = this.storeInfo.holiday.split("").map(Number);
      parsedholiday.forEach((element, idx) => {
        if (element === 1) {
          holiday.push(this.dayOfTheWeek[idx]);
        }
      });
      this.storeInfo.holiday = holiday;
      const storeCategoryResponse = await axios.get(
        "/store/category/" + this.$route.query.id
      );
      this.storeCategory = storeCategoryResponse.data;
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

      const menuResponse = await axios.get(
        `/product/${this.storeInfo.store_id}`
      );
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
