<!-- Register.Vue -->

<template>
  <v-container pt-10 class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card :loading="isLoading" class="mx-auto" max-width="500" outlined>
          <v-container pa-md-5 pa-xs-4>
            <v-card-text>
              <p class="font-weight-bold title mb-4">회원 가입</p>
              <b>개인 정보</b>
              <v-alert v-if="errorMsg" dense outlined type="error">
                {{ errorMsg }}
              </v-alert>
              <!-- user 공통 개인정보 입력 폼 -->
              <v-text-field
                v-model="userInfo.user_name"
                :error-messages="errors.name"
                label="이름"
                @input="removeError('name')"
              ></v-text-field>
              <v-text-field v-model="userInfo.user_id" readonly></v-text-field>
              <v-text-field
                v-model="userInfo.user_pw"
                :success-messages="success.pw"
                :error-messages="errors.pw"
                label="비밀번호"
                type="password"
                @input="removeError('pw')"
                @change="checkpw"
              ></v-text-field>
              <v-text-field
                v-model="userInfo.confirmPw"
                :error-messages="errors.confirmPw"
                label="비밀번호 확인"
                type="password"
                @input="removeError('confirmPw')"
                @change="checkConfirmPw"
              ></v-text-field>
              <v-text-field
                v-model="userInfo.user_ph"
                :error-messages="errors.ph"
                label="전화번호"
                @input="removeError('ph')"
                @change="checkPh"
              ></v-text-field>
              <v-text-field
                v-model="userInfo.user_email"
                :error-messages="errors.email"
                label="이메일"
                @input="removeError('email')"
                @change="checkEmail"
              ></v-text-field>
            </v-card-text>
            <v-card-actions class="justify-center align-center">
              <v-btn type="submit" outlined @click="updateInfo">
                수정
              </v-btn>
            </v-card-actions>
          </v-container>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "Register",
  data() {
    return {
      userInfo: {},
      originalUserInfo: {},
      errors: {
        id: "",
        pw: "",
        confirmPw: "",
        ph: "",
        email: "",
        name: "",
      },
      success: {
        id: "",
      },
      confilmPw: "",
      errorMsg: "",
      isLoading: true,
    };
  },
  computed: {
    ...mapState("auth", ["user"]),
  },
  async created() {
    const cloneObj = (obj) => JSON.parse(JSON.stringify(obj));
    let res = await axios.get("/user/" + this.user.user_id);
    res = res.data;
    this.originalUserInfo = cloneObj(res);
    this.originalUserInfo.user_pw = "";
    this.userInfo = cloneObj(this.originalUserInfo);
    this.isLoading = false;
  },
  methods: {
    ...mapActions({
      getLogout: "auth/logout",
    }),
    removeError(field) {
      if (field == "id") this.success[field] = "";
      this.errors[field] = "";
    },
    checkpw() {
      let pwreg = /^(?=.*[A-Za-z]+)(?=.*[0-9]+)(?=.*[`~!@#$%^&*()\-_+=;:"'?.,<>[\]{}/\\|]*).{8,32}$/;
      if (!pwreg.test(this.userInfo.user_pw)) {
        this.errors.pw =
          "비밀번호는 8자 이상의 영문자와 숫자를 필수로 사용해야 합니다.";
        return;
      }
    },
    checkConfirmPw() {
      if (this.userInfo.user_pw != this.confirmPw) {
        this.errors.confirmPw = "비밀번호 확인이 일치하지 않습니다.";
        return;
      }
    },
    checkPh() {
      if (!this.userInfo.user_ph) {
        this.errors.ph = "전화번호를 입력해 주세요.";
        return;
      }
      let phreg = /(^02.{0}|^01.{1}|[0-9]{3})-([0-9]{3,4})-([0-9]{4})$/;
      if (!phreg.test(this.userInfo.user_ph)) {
        this.errors.ph = "전화번호 형식에 맞추어 입력해주세요.";
        return;
      }
    },
    checkEmail() {
      let reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (!reg.test(this.userInfo.user_email)) {
        this.errors.email = "이메일 형식에 맞추어 입력해주세요.";
        return;
      }
    },
    checkError() {
      if (
        this.errors.id != "" ||
        this.errors.name != "" ||
        this.errors.pw != "" ||
        this.errors.confirmPw != "" ||
        this.errors.ph != "" ||
        this.errors.email != ""
      ) {
        return false;
      }
      return true;
    },
    checkForm() {
      let result = true;
      if (this.userInfo.user_id == "") {
        this.errors.id = "아이디를 입력해주세요";
        result = false;
      }
      if (this.userInfo.user_name == "") {
        this.errors.name = "이름을 입력해주세요";
        result = false;
      }
      if (this.userInfo.user_ph == "") {
        this.errors.ph = "전화번호를 입력해주세요";
        result = false;
      }
      if (this.userInfo.user_email == "") {
        this.errors.email = "이메일을 입력해주세요";
        result = false;
      }
      return result;
    },
    async updateInfo() {
      if (!this.checkError() || !this.checkForm()) {
        return;
      }
      this.isLoading = true;
      try {
        let body = {
          user_id: this.userInfo.user_id,
        };
        for (const key in this.userInfo) {
          if (this.userInfo[key] != this.originalUserInfo[key]) {
            body[key] = this.userInfo[key];
          }
        }

        await axios.patch("/user", body);

        if (Object.prototype.hasOwnProperty.call(body, "user_pw")) {
          this.getLogout();
        }

        this.$router.push({
          name: "Home",
        });
      } catch (error) {
        this.errorMsg = error.response.data.message;
      }
      this.isLoading = false;
    },
  },
};
</script>
