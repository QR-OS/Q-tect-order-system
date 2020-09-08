<!-- Store Information Form -->

<template>
  <v-container>
    <v-card class="mx-auto" max-width="500" outlined>
      <v-layout column>
        <v-card-text>
          <v-flex>
            <v-text-field
              v-model="form.storeInfo.store_name"
              label="상호명"
              required
            />
          </v-flex>
          <v-flex>
            <v-text-field
              v-model="form.storeInfo.store_id"
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
                    v-model="form.storeInfo.post_num"
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
                  v-model="form.storeInfo.address1"
                  label="주소"
                  disabled
                ></v-text-field>
              </v-flex>
              <v-flex>
                <v-text-field
                  ref="extraAddress"
                  v-model="form.storeInfo.address2"
                  label="상세주소"
                  required
                />
              </v-flex>
              <v-layout row>
                <v-flex>
                  매장 분류
                  <div v-for="(item, index) in storeCategory" :key="index">
                    <input
                      :id="item.name"
                      v-model="item.checked"
                      type="checkbox"
                    />
                    <label :for="item.name">{{ item.name }}</label>
                  </div>
                </v-flex>
              </v-layout>
              매장 전화번호
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
  props: ["storeInfoData"],
  data() {
    return {
      storeCategory: [
        // 프론트 작성을 위한 예시 데이터입니다.
        { name: "한식", checked: false },
        { name: "분식", checked: false },
        { name: "중식", checked: false }
      ],
      form: {
        storeInfo: this.storeInfoData
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
      this.form.storeInfo.post_num = code;
      this.form.storeInfo.address1 = address;
      this.dialog = false;
    }
  }
};
</script>
