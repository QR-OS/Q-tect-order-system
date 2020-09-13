<!-- SelectRegisterType -->

<template>
  <v-container class="fill-height" fluid>
    <v-row>
      <v-col class="text-h3" align="center" justify="center">
        회원가입
      </v-col>
    </v-row>
    <v-row>
      <v-col align="center" justify="center">
        <v-btn x-large outlined @click="moveToOwnerRegister">
          매장 관리자
        </v-btn>
      </v-col>
      <v-col>
        <v-btn x-large outlined @click="moveToConsumerRegister">
          일반회원
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  methods: {
    moveToOwnerRegister() {
      this.$router.push({
        name: "Register",
        params: { visible: 1 }
      });
    },
    moveToConsumerRegister() {
      this.$router.push({
        name: "Register",
        params: { visible: 0 }
      });
    }
  }
};
</script>

<style></style>
