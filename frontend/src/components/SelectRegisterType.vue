<!-- SelectRegisterType -->

<template>
  <v-container class="fill-height" fluid>
    <v-row>
      <v-col>
        <h1>회원가입</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-btn outlined @click="MoveToOwnerRegister">
        매장 관리자
      </v-btn>
      <v-btn outlined @click="MoveToConsumerRegister">
        일반회원
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
export default {
  methods: {
    MoveToOwnerRegister() {
      this.$router.push({
        name: "Register",
        params: { is_owner: 1 },
      });
    },
    MoveToConsumerRegister() {
      this.$router.push({
        name: "Register",
        params: { is_owner: 0 },
      });
    },
  },
};
</script>

<style></style>
