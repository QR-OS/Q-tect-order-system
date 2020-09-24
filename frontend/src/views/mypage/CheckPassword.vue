<!-- CheckPassword.Vue -->

<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card class="mx-auto" max-width="400" outlined>
          <v-container pa-md-4 pa-xs-2>
            <v-toolbar dense flat>
              <v-card-title>비밀번호 확인</v-card-title>
            </v-toolbar>
            <v-card-text>
              <v-text-field
                v-model="password"
                label="비밀번호"
                type="password"
                @input="removeError()"
                :error="!!errorMsg"
                :error-messages="errorMsg"
                dense
              />
            </v-card-text>
            <v-spacer />
            <v-card-actions>
              <v-spacer />
              <v-btn
                class="white--text"
                type="submit"
                color="amber darken-1"
                depressed
                @click="moveToMyPage"
              >
                확인
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
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      password: "",
      errorMsg: "",
    };
  },
  computed: {
    ...mapState(["user"]),
  },
  methods: {
    ...mapActions({
      updateToken: "login",
    }),
    removeError() {
      this.errorMsg = "";
    },
    async moveToMyPage() {
      if (!this.password) {
        this.errorMsg = "비밀번호를 입력하세요.";
        return;
      }
      try {
        let res = await axios.post("/login", {
          user_id: this.user.user_id,
          user_pw: this.password,
        });
        this.updateToken(res.data.accessToken);
        this.$router.push({
          name: "MyPage",
        });
      } catch (error) {
        if (error.response.data.status == "400") {
          this.errorMsg = "비밀번호가 일치하지 않습니다.";
        } else this.errorMsg = error.response.data.message;
      }
    },
  },
};
</script>
