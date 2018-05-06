<template>
  <!--<div class="card" >-->
    <!--<div class="card-body">-->
      <!--<h4 class="card-title"></h4>-->
      <!--<p class="card-text"></p>-->
      <!--<p class="card-text">-->
        <!--<small class="text-muted">Enter some text here...</small>-->
      <!--</p>-->
    <!--</div>-->
    <!--<img class="card-img-bottom" src="https://img.webnots.com/2017/04/Bootstrap-Card-Image.png" alt="Card Image Down">-->
  <!--</div>-->
  <v-card style="width:25rem;" class="elevation-1">
    <v-card-media src="https://vuetifyjs.com/static/doc-images/cards/desert.jpg" height="200px">
    </v-card-media>
    <v-card-title primary-title>
      <div>
        <h3 class="headline mb-0"> {{ repuesto.Nombre }}</h3>
        <div>{{ repuesto.Marca }}</div>
        <div>{{ repuesto.Marca }}</div>
        <div>{{ repuesto.Marca }}</div>
      </div>
    </v-card-title>
    <!--<v-card-actions>-->
      <!--<v-btn flat color="orange">Share</v-btn>-->
      <!--<v-btn flat color="orange">Explore</v-btn>-->
    <!--</v-card-actions>-->
  </v-card>

</template>

<script>
  export default {
    name: "CardRepuesto",
    props: ['repuesto'],
  }
</script>

<style scoped>

</style>
