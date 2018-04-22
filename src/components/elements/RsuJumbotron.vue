<template>
  <section class="jumbotron text-center">
    <div class="container">
      <h1 class="jumbotron-heading">Recambios 2º Uso</h1>
      <p class="text-white">Olvídate de paseos hasta el desguace y de reutilizar piezas que ya han tenido uso durante años, asegúrate todas las calidades y garantías que te ofrecemos desde Repuestos para Coches. Piezas de primera mano a precios sin competencia en el mercado.
        Contacte con nosotros, en horario de Lunes a Viernes 9:30-14:00 y de 16:00-19:30 en invierno.

        También puedes contactar con nosotros por teléfono: 698 28 67 14

        O por Email : recambios2uso@gmail.com

        Muchas gracias por elegirnos, quedará satisfecho de su compra.</p>

    </div>
  </section>
</template>

<script>
    export default {
        name: "RsuJumbotron"
    }
</script>

<style >
  .jumbotron {
    padding-top: var(--jumbotron-padding-y);
    padding-bottom: var(--jumbotron-padding-y);
    margin-bottom: 0;
    background-color: #fff;
    background-image: url('../../assets/imagenCabecera.jpg');
  }

  @media (min-width: 768px) {
    .jumbotron {
      padding-top: calc(var(--jumbotron-padding-y) * 2);
      padding-bottom: calc(var(--jumbotron-padding-y) * 2);
    }
  }

  .jumbotron p:last-child {
    margin-bottom: 0;
  }

  .jumbotron-heading {
    font-weight: 300;
  }

  .jumbotron .container {
    max-width: 40rem;
  }

</style>
