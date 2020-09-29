<!-- Store Information Form -->

<template>
  <v-container>
    <v-card class="mx-auto" max-width="500" outlined>
      <v-layout column>
        <v-card-text>
          <v-flex>
            <v-text-field
              v-model="storeInfo.store_name"
              label="상호명"
              required
            />
          </v-flex>
          <v-flex>
            <v-text-field
              v-model="storeInfo.store_num"
              label="사업자번호"
              required
            />
          </v-flex>
          <v-card outlined>
            가게 위치
            <v-card-text>
              <v-layout row>
                <v-flex>
                  <v-text-field
                    v-model="storeInfo.post_num"
                    label="우편번호"
                    disabled
                  ></v-text-field>
                  <v-btn outlined @click="SearchPostNum">
                    우편번호 찾기
                  </v-btn>
                </v-flex>
              </v-layout>
              <v-flex>
                <v-text-field
                  v-model="storeInfo.address1"
                  label="주소"
                  disabled
                ></v-text-field>
              </v-flex>
              <v-flex>
                <v-text-field
                  ref="extraAddress"
                  v-model="storeInfo.address2"
                  label="상세주소"
                  required
                />
              </v-flex>
              <v-flex>
                <v-file-input multiple label="매장 이미지"></v-file-input>
              </v-flex>
              <v-layout row>
                <v-flex>
                  <v-select
                    v-model="storeCategory.value"
                    :items="storeCategory.items"
                    attach
                    chips
                    label="매장 분류"
                    multiple
                    flat
                    class="ma-3"
                  ></v-select>
                </v-flex>
              </v-layout>
              <v-flex>
                <v-text-field
                  v-model="storeInfo.store_tel"
                  label="매장 번호"
                  required
                />
              </v-flex>
              <v-layout row>
                <v-flex>
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
                </v-flex>
              </v-layout>
              <v-layout>
                <v-col>
                  <label>오픈 시간</label>
                  <vue-timepicker
                    v-model="storeInfo.open_time"
                  ></vue-timepicker>
                </v-col>
                <v-col>
                  <label>마감 시간</label>
                  <vue-timepicker
                    v-model="storeInfo.close_time"
                  ></vue-timepicker>
                </v-col>
              </v-layout>
            </v-card-text>
          </v-card>
        </v-card-text>
        <v-card-actions>
          <v-spacer />
        </v-card-actions>
      </v-layout>
    </v-card>

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
    VueTimepicker
  },
  props: ["storeInfo"],
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
          "마켓"
        ],
        value: []
      },
      dialog: false
    };
  },
  methods: {
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
    }
  }
};
</script>
