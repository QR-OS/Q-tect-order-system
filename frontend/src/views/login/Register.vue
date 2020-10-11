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
                v-model="form.userInfo.name"
                :error-messages="errors.name"
                label="이름"
                @input="removeError('name')"
              ></v-text-field>
              <v-text-field
                v-model="form.userInfo.id"
                :success-messages="success.id"
                :error-messages="errors.id"
                label="아이디"
                hint="6자 이상의 영문자와 숫자로 작성해주세요."
                @input="removeError('id')"
                @change="checkID"
              ></v-text-field>
              <v-text-field
                v-model="form.userInfo.pw"
                :success-messages="success.pw"
                :error-messages="errors.pw"
                label="비밀번호"
                hint="8자 이상의 영어 소문자와 숫자로 작성해주세요."
                type="password"
                @input="removeError('pw')"
                @change="checkpw"
              ></v-text-field>
              <v-text-field
                v-model="form.userInfo.confirmPw"
                :error-messages="errors.confirmPw"
                label="비밀번호 확인"
                type="password"
                @input="removeError('confirmPw')"
                @change="checkConfirmPw"
              ></v-text-field>
              <v-text-field
                v-model="form.userInfo.ph"
                :error-messages="errors.ph"
                label="전화번호"
                hint="ex) 010-0000-0000"
                @input="removeError('ph')"
                @change="checkPh"
              ></v-text-field>
              <v-text-field
                v-model="form.userInfo.email"
                :error-messages="errors.email"
                label="이메일"
                hint="ex) example@gmail.com, example@naver.com 등"
                @input="removeError('email')"
                @change="checkEmail"
              ></v-text-field>
            </v-card-text>
            <!-- 가게 정보 입력 폼-->
            <store-info-form
              v-if="$route.query.regType == 2"
              ref="storeForm"
              :storeInfo="form.storeInfo"
              :btnName="btnName"
              @update="categoryUpdate"
              @register="register"
            />
            <v-card-actions
              v-if="$route.query.regType != 2"
              class="justify-center"
            >
              <v-btn type="submit" outlined @click="register(true)">
                가입
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
import StoreInfoForm from "../../components/StoreInfoForm";

export default {
  name: "Register",
  components: {
    "store-info-form": StoreInfoForm,
  },
  data() {
    return {
      form: {
        userInfo: {
          id: "",
          name: "",
          pw: "",
          confirmPw: "",
          ph: "",
          email: "",
          store_id: "",
        },
        storeInfo: {
          store_name: "",
          store_num: "",
          post_num: "",
          address1: "",
          address2: "",
          store_tel: "",
          img: [],
          open_time: "",
          close_time: "",
          orderable: true,
          bookable: false,
          store_state: true,
          Nholiday: {
            items: [
              { key: 1, value: "월" },
              { key: 2, value: "화" },
              { key: 3, value: "수" },
              { key: 4, value: "목" },
              { key: 5, value: "금" },
              { key: 6, value: "토" },
              { key: 7, value: "일" },
            ],
            value: [],
          },
        },
        storeCategory: [],
      },
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
      errorMsg: "",
      isLoading: false,
      btnName: "가입",
    };
  },

  // 만일 1, 2 type외의 다른 값이 들어오면 페이지 접근을 차단함.
  mounted() {
    if (!(this.$route.query.regType == 1 || this.$route.query.regType == 2)) {
      this.moveToRegister();
    }
  },
  methods: {
    categoryUpdate(value) {
      this.form.storeCategory = value;
    },
    // 인풋이 들어올 경우 Error 메시지를 삭제함.
    removeError(field) {
      if (field == "id") this.success[field] = "";
      this.errors[field] = "";
    },

    // 아이디 형식, 중복 체크
    async checkID() {
      const idreg = /^[a-z0-9]{6,12}$/;
      if (!idreg.test(this.form.userInfo.id)) {
        this.errors.id =
          "아이디는 6~12자의 영문 소문자, 숫자만 사용 가능합니다.";
        return;
      }
      try {
        await axios.get("/user/" + this.form.userInfo.id);
        this.errors.id = "중복된 아이디입니다.";
      } catch (error) {
        if (error.response.data.status == "400") {
          this.success.id = "사용할 수 있는 아이디입니다.";
        } else {
          this.errors.id = error.response.data.message;
        }
      }
    },

    // 비밀번호 형식 체크
    async checkpw() {
      const pwreg = /^(?=.*[A-Za-z]+)(?=.*[0-9]+)(?=.*[`~!@#$%^&*()\-_+=;:"'?.,<>[\]{}/\\|]*).{8,32}$/;
      if (!pwreg.test(this.form.userInfo.pw)) {
        this.errors.pw =
          "비밀번호는 8자 이상의 영문자와 숫자를 필수로 사용해야 합니다.";
        return;
      }
    },

    // 비밀번호가 서로 일치하는지 확인
    async checkConfirmPw() {
      if (this.form.userInfo.pw != this.form.userInfo.confirmPw) {
        this.errors.confirmPw = "비밀번호 확인이 일치하지 않습니다.";
        return;
      }
    },

    // 전화번호 형식 확인
    async checkPh() {
      if (!this.form.userInfo.ph) {
        this.errors.ph = "전화번호를 입력해 주세요.";
        return;
      }
      const phreg = /(^02.{0}|^01.{1}|[0-9]{3})-([0-9]{3,4})-([0-9]{4})$/;
      if (!phreg.test(this.form.userInfo.ph)) {
        this.errors.ph = "전화번호 형식에 맞추어 입력해주세요.";
        return;
      }
    },
    async checkEmail() {
      const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (!reg.test(this.form.userInfo.email)) {
        this.errors.email = "이메일 형식에 맞추어 입력해주세요.";
        return;
      }
    },
    moveToRegister() {
      this.$router.push({
        name: "SelectRegisterType",
      });
    },
    checkForm() {
      let result = true;
      if (this.form.userInfo.id == "") {
        this.errors.id = "아이디를 입력해주세요";
        result = false;
      }
      if (this.form.userInfo.name == "") {
        this.errors.name = "이름을 입력해주세요";
        result = false;
      }
      if (this.form.userInfo.pw == "") {
        this.errors.pw = "비밀번호를 입력해주세요";
        result = false;
      }
      if (this.form.userInfo.confirmPw == "") {
        this.errors.confirmPw = "비밀번호를 입력해주세요";
        result = false;
      }
      if (this.form.userInfo.ph == "") {
        this.errors.ph = "전화번호를 입력해주세요";
        result = false;
      }
      if (this.form.userInfo.email == "") {
        this.errors.email = "이메일을 입력해주세요";
        result = false;
      }
      return result;
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
    async register(ck) {
      if (!this.checkForm() || !this.checkError() || ck == false) {
        return;
      }
      this.isLoading = true;
      if (this.$route.query.regType == 2) {
        try {
          let body = Object.assign({}, this.form.storeInfo);
          delete body.img;
          delete body.Nholiday;
          let holidayStr = "";

          for (let day of this.form.storeInfo.Nholiday.items) {
            if (this.form.storeInfo.Nholiday.value.includes(day)) {
              holidayStr += "1";
            } else holidayStr += "0";
          }

          body.holiday = holidayStr;
          body.open_time = body.open_time.toString("HH:mm");
          body.close_time = body.close_time.toString("HH:mm");

          const res = await axios.post("/store", body);
          this.form.userInfo.storeId = res.data.store_id;
        } catch (error) {
          this.errorMsg = error.response.data.message;
          this.isLoading = false;
          return;
        }
      } else this.form.userInfo.storeId = null;
      try {
        await axios.post("/user", {
          user_id: this.form.userInfo.id,
          user_name: this.form.userInfo.name,
          user_pw: this.form.userInfo.pw,
          user_ph: this.form.userInfo.ph,
          user_email: this.form.userInfo.email,
          store_id: this.form.userInfo.storeId,
          status: this.$route.query.regType,
        });
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
