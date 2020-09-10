<!-- Register.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="18" md="15" sm="10" xs="3">
        <v-card flat>
          <h1 align="center" justify="center">회원 가입</h1>
          <p><b>개인 정보</b></p>

          <!-- user 공통 개인정보 입력 폼 -->
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
            :error-messages="errors.confirmpassword"
            label="비밀번호 확인"
            type="password"
            required
            @change="checkConfirmPassword()"
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
          <v-spacer />
          <store-info-form v-if="visible === 2" />
          <div align="center" justify="center">
            <v-btn type="submit" outlined @click="register">
              가입
            </v-btn>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
import StoreInfoForm from "./StoreInfoForm";

export default {
  name: "Register",
  components: {
    "store-info-form": StoreInfoForm
  },
  props: {
    visible: {
      type: Number,
      default: 0
    },
    storeCategory: {
      type: Array,
      default() {
        return [];
      }
    }
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
          email: ""
        }
      },
      errors: {
        confirmpassword: ""
      }
    };
  },
  mounted() {
    if (this.visible === 0) {
      this.moveToRegister();
    }
  },
  methods: {
    checkShowStoreForm() {
      console.log(this.visible);
      return this.$store.Regtype === 2;
    },
    checkConfirmPassword() {
      if (this.form.password != this.form.confirmpassword) {
        this.errors.confirmpassword = "비밀번호 확인이 일치하지 않습니다.";
        return;
      }
    },
    moveToRegister() {
      this.$router.push({
        name: "SelectRegisterType"
      });
    },
    register() {
      const res = axios.post("/register", {
        user_Id: this.form.userInfo.id,
        user_name: this.form.userInfo.name,
        user_pw: this.form.userInfo.pw,
        user_ph: this.form.userInfo.ph,
        user_email: this.form.userInfo.email,
        store_id: this.form.storeId,
        status: this.visible
      });
      console.log(res);
    }
  }
};
</script>
