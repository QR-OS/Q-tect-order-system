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
                    solo
                  ></v-select>
                </v-flex>
              </v-layout>
            </v-card-text>
          </v-card>
          <v-sheet></v-sheet>
          <v-btn outlined @click="uploadImage()">이미지 업로드</v-btn>
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
export default {
  name: "StoreInfoForm",
  components: {
    SearchPostNumber
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
    uploadImage() {},
    SearchPostNum() {
      this.dialog = true;
    },
    parents(code, address) {
      this.storeInfo.post_num = code;
      this.storeInfo.address1 = address;
      this.dialog = false;
    }
  }
};
</script>
