<template>
  <v-container class="fill-height" fluid>
    <v-img
      src="https://bad.src/not/valid"
      lazy-src="https://picsum.photos/id/11/100/60"
      max-width="500"
      max-height="300"
    >
      <template v-slot:placeholder>
        <v-row class="fill-height ma-0" align="center" justify="center">
          <v-progress-circular
            indeterminate
            color="grey lighten-5"
          ></v-progress-circular>
        </v-row>
      </template>
    </v-img>
    <div>
      <p></p>
    </div>
  </v-container>
</template>

<script>
export default {};
</script>
