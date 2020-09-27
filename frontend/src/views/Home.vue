<template>
  <div class="home">
    <h1>{{ user }}</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {},
    };
  },
  created() {
    console.log(this.$store.state.auth.user.user_id);
  },
};
</script>
