<!-- Store Information Form -->

<template>
  <v-card class="mx-auto" max-width="344" outlined>
    <v-layout column>
      <v-card-text>
        <v-flex>
          <v-text-field v-model="form.storeInfo.name" label="상호명" required />
        </v-flex>
        <v-flex>
          <v-text-field
            v-model="form.storeInfo.id"
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
                <v-btn @click="SearchPostNum" outlined>
                  우편번호 찾기
                </v-btn>
                <modals-container @focusToExtraAd="UpdateExtraAd" />
              </v-flex>
            </v-layout>
            <v-flex>
              <v-text-field
                label="주소"
                v-model="form.storeInfo.address1"
                disabled
              ></v-text-field>
            </v-flex>
            <v-flex>
              <v-text-field
                v-model="form.storeInfo.address2"
                label="상세주소"
                ref="extraAddress"
                required
              />
            </v-flex>
            <v-layout row>
              <v-flex>
                매장 분류
                <div v-for="(item, index) in storeCategory" :key="index">
                  <input
                    type="checkbox"
                    :id="item.name"
                    v-model="item.checked"
                  />
                  <label :for="item.name">{{ item.name }}</label>
                </div>
              </v-flex>
            </v-layout>
            매장 전화번호
          </v-card-text>
        </v-card>
        <v-sheet></v-sheet>
        <v-btn @click="uploadImage" outlined>이미지 업로드</v-btn>
      </v-card-text>
      <v-card-actions>
        <v-spacer />
      </v-card-actions>
    </v-layout>
  </v-card>
</template>

<script>
import SearchPostNumberModal from "./SearchPostNumber";

export default {
  name: "StoreInfoForm",
  data() {
    return {
      storeCategory: [
        // 프론트 작성을 위한 예시 데이터입니다.
        { name: "한식", checked: false },
        { name: "분식", checked: false },
        { name: "중식", checked: false },
      ],
      form: {
        storeInfo: {
          name: "",
          id: "",
          post_num: "",
          address1: "",
          address2: "",
          tel: "",
          img: "",
          type: [],
          open_time: "",
          close_time: "",
        },
      },
    };
  },
  methods: {
    uploadImage() {},
    SearchPostNum() {
      this.$modal.show(
        SearchPostNumberModal,
        {
          hot_table: "data",
          modal: this.$modal,
        },
        {
          name: "dynamic-modal",
          width: "330px",
          height: "400px",
        }
      );
    },
    UpdateExtraAd(){
      this.$refs.extraAddress.focus();
    }
  },
};
</script>
