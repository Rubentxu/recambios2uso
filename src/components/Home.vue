<template>
  <div>
    <main role="main">
      <rsu-jumbotron></rsu-jumbotron>
      <rsu-main></rsu-main>
    </main>
    <rsu-footer></rsu-footer>
    <v-container fluid>
      <v-layout row wrap>
        <v-flex xs12 text-xs-center mt-5>
          <h1>Welcome to Awesome App</h1>
        </v-flex>
        <v-flex xs12 sm6 offset-sm3 mt-3>
          <blockquote class="blockquote text-xs-center">
            It's a basic Single Page application <br>
            with Google Firebase authentication
          </blockquote>
        </v-flex>
        <v-flex xs12 sm6 offset-sm3 mt-3 class="text-xs-center" mt-5>
          <v-btn color="primary" to="/signup">Sign Up</v-btn>
          <v-btn to="/signin">Sign In</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </div>

</template>

<script>
  import RsuFooter from "./elements/RsuFooter";
  import RsuMain from "./elements/RsuMain";
  import RsuJumbotron from "./elements/RsuJumbotron";

  export default {
    name: "Home",
    components: {RsuJumbotron, RsuFooter, RsuMain}
  }
</script>

<style>
  :root {
    --jumbotron-padding-y: 3rem;
  }



  .box-shadow {
    box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
  }

</style>
