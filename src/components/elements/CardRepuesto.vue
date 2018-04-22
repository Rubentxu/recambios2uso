<template>
  <div class="card" style="width:20rem;">
    <div class="card-body">
      <h4 class="card-title">{{ repuesto.nombre }}</h4>
      <p class="card-text">{{ repuesto.descripcion }}</p>
      <p class="card-text">
        <small class="text-muted">Enter some text here...</small>
      </p>
    </div>
    <img class="card-img-bottom" src="https://img.webnots.com/2017/04/Bootstrap-Card-Image.png" alt="Card Image Down">
  </div>

</template>

<script>
  export default {
    name: "CardRepuesto",
    props: ['repuesto'],
  }
</script>

<style scoped>

</style>
