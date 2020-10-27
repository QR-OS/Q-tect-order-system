<!-- Store Information Form -->

<template>
  <v-container>
    <v-card class="mx-auto" max-width="500" outlined>
      <v-row>
        <v-col class="pt-0">
          <v-card-text>
            <b>가게 정보</b>
            <v-text-field
              v-model="storeInfo.store_name"
              :error-messages="errors.storeName"
              @input="removeError('storeName')"
              label="상호명"
              required
            />
            <v-text-field
              v-model="storeInfo.store_num"
              :error-messages="errors.storeNum"
              label="사업자번호"
              hint="ex) 000-00-00000"
              @input="removeError('storeNum')"
              @change="checkStoreNum"
              required
            />
            <v-row>
              <v-col
                class="px-0 pt-8 font-weight-medium"
                align="end"
                justify="end"
                md="2"
                sm="2"
                xs="2"
              >
                가게 위치
              </v-col>
              <v-col class="pb-0" md="5" sm="5" xs="5">
                <v-text-field
                  v-model="storeInfo.post_num"
                  :error-messages="errors.postNum"
                  @change="removeError('postNum')"
                  label="우편번호"
                  readonly
                ></v-text-field>
              </v-col>
              <v-col class="pt-5" md="2" sm="2" xs="2">
                <v-btn outlined @click="SearchPostNum">
                  우편번호 찾기
                </v-btn>
              </v-col>
            </v-row>
            <v-row>
              <v-col class="pt-0" offset="2" md="9" xm="9" xs="9">
                <v-text-field
                  v-model="storeInfo.address1"
                  :error="!!errors.postNum"
                  label="주소"
                  readonly
                ></v-text-field>
                <v-text-field
                  v-model="storeInfo.address2"
                  :error="!!errors.postNum"
                  label="상세주소"
                  required
                />
              </v-col>
            </v-row>
            <v-file-input
              v-model="storeInfo.img"
              multiple
              label="매장 이미지"
            ></v-file-input>

            <v-select
              v-model="storeCategory.value"
              :items="storeCategory.items"
              :error-messages="errors.category"
              attach
              chips
              label="매장 분류"
              multiple
              flat
              class="ma-3"
            ></v-select>

            <v-text-field
              v-model="storeInfo.store_tel"
              :error-messages="errors.tel"
              @input="removeError('tel')"
              @change="checkPh()"
              label="매장 번호"
              hint="ex) 02-123-4567"
              required
            />
            <v-select
              v-model="storeInfo.Nholiday.value"
              :items="storeInfo.Nholiday.items"
              item-text="value"
              item-value="value"
              attach
              chips
              label="휴무일"
              multiple
              return-object
              flat
              class="ma-3"
              @change="SortHoliday()"
            ></v-select>
            <v-col>
              <label>오픈 시간</label>
              <vue-timepicker
                class="ml-3"
                v-model="storeInfo.open_time"
              ></vue-timepicker>
            </v-col>
            <v-col>
              <label>마감 시간</label>
              <vue-timepicker
                class="ml-3"
                v-model="storeInfo.close_time"
              ></vue-timepicker>
            </v-col>
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>
    <v-row class="justify-center">
      <v-btn class="mt-3" type="submit" outlined @click="register">
        {{ btnName }}
      </v-btn>
    </v-row>
    <v-dialog v-if="dialog" v-model="dialog" width="500">
      <SearchPostNumber @child="parents"></SearchPostNumber>
    </v-dialog>
  </v-container>
</template>

<script>
import SearchPostNumber from "./SearchPostNumber";
import VueTimepicker from "vue2-timepicker/src/vue-timepicker.vue";

export default {
  name: "StoreInfoForm",
  components: {
    SearchPostNumber,
    VueTimepicker,
  },
  props: {
    storeInfo: {
      type: Object,
      default() {
        return {};
      },
    },
    btnName: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      storeCategory: {
        items: [
          "한식",
          "분식",
          "중식",
          "일식",
          "양식",
          "아시안",
          "치킨",
          "패스트푸드",
          "카페/디저트",
          "마켓",
        ],
        value: [],
      },
      errors: {
        storeName: "",
        storeNum: "",
        postNum: "",
        tel: "",
        category: "",
      },
      dialog: false,
    };
  },
  watch: {
    "storeCategory.value"() {
      return this.removeError("category");
    },
    "storeInfo.post_num"() {
      return this.removeError("postNum");
    },
  },
  methods: {
    removeError(field) {
      this.errors[field] = "";
    },
    SearchPostNum() {
      this.dialog = true;
    },
    parents(code, address) {
      this.storeInfo.post_num = code;
      this.storeInfo.address1 = address;
      this.dialog = false;
    },
    SortHoliday() {
      function compare(a, b) {
        if (a.key < b.key) return -1;
        if (a.key > b.key) return 1;
        return 0;
      }
      this.storeInfo.Nholiday.value.sort(compare);
    },

    // 전화번호 형식 확인
    async checkPh() {
      if (!this.storeInfo.store_tel) {
        this.errors.tel = "전화번호를 입력해 주세요.";
        return;
      }
      const phreg = /(^02.{0}|^01.{1}|[0-9]{3})-([0-9]{3,4})-([0-9]{4})$/;
      if (!phreg.test(this.storeInfo.store_tel)) {
        this.errors.tel = "전화번호 형식에 맞추어 입력해주세요.";
        return;
      }
    },

    // 전화번호 형식 확인
    async checkStoreNum() {
      if (!this.storeInfo.store_num) {
        this.errors.storeNum = "사업자등록번호를 입력해 주세요.";
        return;
      }
      const phreg = /([0-9]{3})-([0-9]{2})-([0-9]{5})$/;
      if (!phreg.test(this.storeInfo.store_num)) {
        this.errors.storeNum = "사업자등록번호 형식에 맞추어 입력해주세요.";
        return;
      }
    },

    checkForm() {
      let result = true;
      if (this.storeInfo.store_name == "") {
        this.errors.storeName = "상호명을 입력해주세요";
        result = false;
      }
      if (this.storeInfo.store_num == "") {
        this.errors.storeNum = "사업자등록번호를 입력해주세요";
        result = false;
      }
      if (this.storeInfo.post_num == "") {
        this.errors.postNum = "주소를 입력해주세요";
        result = false;
      }
      if (this.storeInfo.store_tel == "") {
        this.errors.tel = "전화번호를 입력해주세요";
        result = false;
      }
      if (this.storeCategory.value.length === 0) {
        this.errors.category = "매장 분류를 입력해주세요";
        result = false;
      }
      return result;
    },
    checkError() {
      if (
        this.errors.storeNum != "" ||
        this.errors.storeName != "" ||
        this.errors.postNum != "" ||
        this.errors.tel != "" ||
        this.errors.category != ""
      ) {
        return false;
      }
      return true;
    },
    async register() {
      if (this.checkForm() && this.checkError()) {
        /*
          매장 카테고리를 부모로 보내줍니다.
          부모에서는 다음과 같이 처리를 해주었습니다.

          컴포넌트에서 다음과 같이 이벤트를 받아옵니다.
          @update="categoryUpdate"

          실행되는 메소드의 내용은 다음과 같습니다.
          categoryUpdate(value) {
            this.form.storeCategory=value;
          },
        */
        this.$emit("update", this.storeCategory.value);
        /*
          검사 결과가 이상이 없을 경우 true값을 인자로 넣어주고 submit이벤트를 호출합니다.
        */
        this.$emit("submit", true);
      } else {
        this.$emit("submit", false);
      }
    },
  },
};
</script>
