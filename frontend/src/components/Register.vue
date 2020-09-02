<!-- Register.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row>
      <v-col>
        <h1>회원가입</h1>
        <p>개인정보</p>
        <v-text-field
          v-model="form.userInfo.name"
          label="이름"
          required
        ></v-text-field>
        <v-text-field
          v-model="form.userInfo.id"
          label="아이디"
          required
        ></v-text-field>
        <v-text-field
          v-model="form.userInfo.pw"
          label="비밀번호"
          type="password"
          required
        ></v-text-field>
        <v-text-field
          v-model="form.userInfo.confirmPw"
          @change="checkConfirmPassword()"
          :error-messages="errors.confirmpassword"
          label="비밀번호 확인"
          type="password"
          required
        ></v-text-field>
        <v-text-field
          v-model="form.userInfo.ph"
          label="전화번호"
          required
        ></v-text-field>
        <v-text-field
          v-model="form.userInfo.email"
          label="이메일"
        ></v-text-field>
        <v-card
          v-if="$route.params.is_owner"
          class="mx-auto"
          max-width="344"
          color="#B2EBF2"
        >
          <v-layout column>
            <v-card-text>
              <v-text-field
                v-model="form.storeInfo.name"
                label="상호명"
                required
              />
              <v-text-field
                v-model="form.storeInfo.id"
                label="사업자번호"
                required
              />
              <v-card>
                <v-text-field
                  v-model="form.storeInfo.post_num"
                  label="우편번호"
                  disabled="true"
                ></v-text-field>
                <v-btn @click="execDaumPostcode" outlined>우편번호 찾기</v-btn>
                <v-text-field
                  label="주소"
                  v-model="form.storeInfo.address1"
                  disabled="true"
                ></v-text-field>
                <v-text-field
                  v-model="form.storeInfo.address2"
                  label="상세주소"
                  required
                />
              </v-card>
              <v-sheet></v-sheet>
              <v-btn @click="uploadImage" outlined>이미지 업로드</v-btn>
              
            </v-card-text>
            <v-card-actions>
              <v-spacer />
            </v-card-actions>
          </v-layout>
        </v-card>
        <v-btn type="submit" outlined @click="Register">
          가입
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  props: {
    is_owner: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      form: {
        userInfo: {
          id: "",
          name: "",
          pw: "",
          confilmPw: "",
          ph: "",
          email: "",
        },
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
      errors: {
        confirmpassword: "",
      },
      searchWindow: {
        display: "none",
        height: "300px",
      },
    };
  },
  methods: {
    checkConfirmPassword() {
      if (this.form.password != this.form.confirmpassword) {
        this.errors.confirmpassword = "비밀번호 확인이 일치하지 않습니다.";
        return;
      }
    },
    execDaumPostcode() {
      const currentScroll = Math.max(
        document.body.scrollTop,
        document.documentElement.scrollTop
      );
      // eslint-disable-next-line
      new daum.Postcode({
        onComplete: (data) => {
          if (data.userSelectedType === "R") {
            this.storeInfo.address1 = data.roadAddress;
          } else {
            this.storeInfo.address1 = data.jibunAddress;
          }
          if (data.userSelectedType === "R") {
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.storeInfo.address2 += data.bname;
            }
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.storeInfo.address2 +=
                this.storeInfo.address2 !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            if (this.storeInfo.address2 !== "") {
              this.storeInfo.address2 = ` (${this.storeInfo.address2})`;
            }
          } else {
            this.storeInfo.address2 = "";
          }
          this.form.storeInfo.post_num = data.zonecode;
          this.$refs.storeInfo.address2.focus();
          this.searchWindow.display = "none";
          document.body.scrollTop = currentScroll;
        },
        onResize: (size) => {
          this.searchWindow.height = `${size.height}px`;
        },
        width: "100%",
        height: "100%",
      }).embed(this.$refs.searchWindow);
      this.searchWindow.display = "block";
    },
    uploadImage(){

    },
    Register() {
      const res = axios.post("/register", {
        user_id: this.form.userInfo.id,
        user_name: this.form.userInfo.name,
        user_pw: this.form.userInfo.pw,
        user_ph: this.form.userInfo.ph,
        user_email: this.form.userInfo.email,
        store_id: this.form.store_id,
        status: 1,
      });
      console.log(res);
    },
  },
};
</script>

<style></style>
