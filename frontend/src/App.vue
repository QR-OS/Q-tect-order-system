<template>
  <div>
    <general-layout></general-layout>
    <router-view></router-view>
  </div>
</template>

<script>
import GeneralLayout from "./components/Layout/general.vue";

export default {
  name: "App",
  components: {
    GeneralLayout
  },
  data() {
    return {};
  }
};
</script>
