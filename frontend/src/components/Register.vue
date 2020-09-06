<!-- Register.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <h1 align="center" justify="center">회원가입</h1>
        <p>개인정보</p>

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
        <store-info-form />

        <v-btn type="submit" outlined @click="Register">
          가입
        </v-btn>
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
      default: 1
    },
    store_category: {
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
  methods: {
    checkShowStoreForm() {
      console.log(this.visible);
      // ** 새로고침해도 이전에 router로 받아온 값이 저장되어있도록 수정해야 합니다.
      // 혹은 router로 받아온 값을 바로 저장해서 그 값만을 사용하도록 해야 합니다.
      // vuex사용?
      return this.$route.params.visible == 1 || this.visible == 1;
    },
    checkConfirmPassword() {
      if (this.form.password != this.form.confirmpassword) {
        this.errors.confirmpassword = "비밀번호 확인이 일치하지 않습니다.";
        return;
      }
    },
    Register() {
      const res = axios.post("/register", {
        user_id: this.form.userInfo.id,
        user_name: this.form.userInfo.name,
        user_pw: this.form.userInfo.pw,
        user_ph: this.form.userInfo.ph,
        user_email: this.form.userInfo.email,
        store_id: this.form.store_id,
        // status 정의에 대해 향후 정확히 할 것.
        status: this.visible
      });
      console.log(res);
    }
  }
};
</script>
