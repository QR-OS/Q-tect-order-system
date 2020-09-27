<!-- FindIdPw.Vue -->

<template>
  <v-container class="fill-height" oulined fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-scroll-x-reverse-transition>
          <v-card v-show="show" outlined class="mx-auto" max-width="400">
            <v-toolbar dense flat>
              <v-tabs v-model="tab" color="amber darken-1" grow>
                <v-tab v-for="item in items" :key="item">
                  {{ item }}
                </v-tab>
              </v-tabs>
            </v-toolbar>
            <v-tabs-items v-model="tab">
              <v-tab-item>
                <v-card-text class="px-10 pt-10">
                  <v-text-field
                    v-model="user.name"
                    label="이름"
                    type="text"
                    :error="!!errorMsg"
                    dense
                    outlined
                    @input="removeError"
                  />
                  <v-text-field
                    v-model="user.email"
                    dense
                    outlined
                    label="이메일"
                    type="text"
                    :error="!!errorMsg"
                    :error-messages="errorMsg"
                    @input="removeError"
                  />
                </v-card-text>
                <v-card-actions class="pr-10 pb-10">
                  <v-spacer />
                  <v-btn
                    class="white--text"
                    type="submit"
                    color="amber darken-1"
                    depressed
                    @click="findId"
                  >
                    아이디 찾기
                  </v-btn>
                </v-card-actions>
              </v-tab-item>
              <v-tab-item>
                <v-card-text class="px-10 pt-10">
                  <v-text-field
                    v-model="user.name"
                    label="이름"
                    type="text"
                    :error="!!errorMsg"
                    dense
                    outlined
                    @input="removeError"
                  />
                  <v-text-field
                    v-model="user.id"
                    dense
                    outlined
                    label="아이디"
                    type="text"
                    :error="!!errorMsg"
                    :error-messages="errorMsg"
                    @input="removeError"
                  />
                </v-card-text>
                <v-card-actions class="pr-10 pb-10">
                  <v-spacer />
                  <v-btn
                    class="white--text"
                    type="submit"
                    color="amber darken-1"
                    depressed
                    @click="findPw"
                  >
                    비밀번호 찾기
                  </v-btn>
                </v-card-actions>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-scroll-x-reverse-transition>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar">
      {{ snackText }}

      <template v-slot:action="{ attrs }">
        <v-btn text v-bind="attrs" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
//import axios from "axios";

export default {
  name: "FindIdPw",
  data() {
    return {
      show: false,
      tab: null,
      items: ["아이디 찾기", "비밀번호 찾기"],
      user: {
        id: "",
        name: "",
        email: "",
      },
      snackText: "",
      errorMsg: "",
      snackbar: false,
    };
  },
  watch: {
    tab() {
      this.errorMsg = "";
    },
  },
  mounted() {
    this.show = true;
  },
  methods: {
    async findId() {
      if (!this.user.name || !this.user.email) {
        this.errorMsg = "이름과 이메일을 입력하세요.";
        return;
      }
      // 대충 아이디와 메일이 둘다 일치하면 메일을 보내고 성공했다는 메시지를,,
      // 전송 성공시 스낵바로 성공했음을 알림.
      //try {
      //  await axios();
      this.snackText = "아이디는 000 입니다.";
      this.snackbar = true;
      //} catch (error) {
      //  this.errorMsg = error.response.data.message;
      //}
    },
    removeError() {
      this.errorMsg = "";
    },
    async findPw() {
      if (!this.user.id || !this.user.name) {
        this.errorMsg = "이름과 아이디를 입력하세요.";
        return;
      }
      //try {
      //  await axios();
      this.snackText = "가입 시 입력하셨던 메일로 아이디가 전송되었습니다.";
      this.snackbar = true;
      //} catch (error) {
      //  this.errorMsg = error.response.data.message;
      //}
    },
  },
};
</script>
