<template>


  <div class="album py-5 bg-light">
    <div class="container">
      <isotope ref="cpt" id="root_isotope1" :item-selector="'element-item'" :list="listaRepuestos" :options='option' @filter="filterOption=arguments[0]" @sort="sortOption=arguments[0]" @layout="currentLayout=arguments[0]">
        <card-repuesto v-for="element,index in listaRepuestos" :key="index" :repuesto="element">
        </card-repuesto>
      </isotope>
    </div>
  </div>

</template>

<script>
  import isotope from 'vueisotope'
  import CardRepuesto from "./CardRepuesto";
  import { mapGetters } from 'vuex'


  export default {
    name: "RsuMain",
    components: {
      CardRepuesto,
      isotope,
    },
    mounted(){
      this.$store.dispatch('getRespuestos');
    },
    data() {
      return {
        option: {
          itemSelector: ".element-item",
          getFilterData: {
            "show all": function() {
              return true;
            },
            metal: function(el) {
              return !!el.metal;
            },
            transition: function(el) {
              return el.category === "transition";
            },
            "alkali and alkaline-earth": function(el) {
              return el.category === "alkali" || el.category === "alkaline-earth";
            },
            "not transition": function(el) {
              return el.category !== "transition";
            },

            "metal but not transition": function(el) {
              return !!el.metal && el.category !== "transition";
            },
            "number > 50": function(el) {
              return el.number > 50;
            },
            "name ends with ium": function(el) {
              return el.name.match(/ium$/);
            }
          },
          getSortData: {
            nombre: "nombre",
            descripcion: "symbol",
            number: "number",
            weight: "weight",
            category: "category"
          }
        }
      }
    },
    computed: {
      ...mapGetters([
        'listaRepuestos'
        // ...
      ])
    }

  }
</script>

<style >

  /* ---- isotope ---- */

  .grid {
    border: 1px solid #333;
  }

  /* clear fix */
  .grid:after {
    content: '';
    display: block;
    clear: both;
  }

  .isoDefault {
    min-height: 510px;
  }


  /* ---- .element-item ---- */

  .element-item {
    position: relative;
    float: left;
    width: 200px;
    height: 300px;
    margin: 10px;
    padding: 10px;
    background: #ebeeec;
    color: #262524;
  }

  .element-item > * {
    margin: 0;
    padding: 0;
  }



</style>
