<template>
  <div>
    <main role="main">
      <rsu-jumbotron></rsu-jumbotron>
      <rsu-main></rsu-main>
    </main>
    <rsu-footer></rsu-footer>
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
