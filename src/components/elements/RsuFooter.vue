<template>
  <footer class="text-muted">
    <!--<div class="container">-->
      <!--<div class="mcontent">-->
        <!--<div class="box mr20">-->
          <!--<h2>Contacto</h2>-->
          <!--<p>Telefono : 698 28 67 14<br>-->
            <!--Email : <a href="#">recambios2uso@gmail.com </a></p>-->
        <!--</div>-->
        <!--<div class="box mr20">-->
          <!--<h2>Direccion</h2>-->
          <!--<p>C/ La Defensa s/n-->
            <!--29011 Malaga (España)</p>-->
        <!--</div>-->
        <!--<div class="box2">-->
          <!--<h2>Los mejores recambios de segunda mano para tu coche.</h2>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <div class="row">
      <div class="col-sm-3 offset-md-3">
        <div class="card ">
          <div class="card-body">
            <h5 class="card-title">Contacto</h5>
            <p class="card-text">Telefono : 698 28 67 14</p>
            <p class="card-text">Email : <a href="#">recambios2uso@gmail.com </a></p>
          </div>
        </div>
      </div>
      <div class="col-sm-3">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Direccion</h5>
            <p class="card-text">C/ La Defensa s/n 29011</p>
            <p> Malaga (España)</p>
            </div>
          </div>
        </div>
      </div>


    </footer>
  </template>

  <script>
      export default {
          name: "RsuFooter"
      }
  </script>

  <style >

    footer {
      padding-top: 3rem;
      padding-bottom: 3rem;
      background: #2d2b40;  /* fallback for old browsers */
      background: -webkit-linear-gradient(to bottom, #343A40, #000000);  /* Chrome 10-25, Safari 5.1-6 */
      background: linear-gradient(to bottom, #343A40, #000000); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */


    }

    footer p {
      margin-bottom: .25rem;
    }

    .mcontent {
      min-height: 153px;
      background: #ebebeb url(../../assets/main.gif) left top repeat-x;
      width: 698px;
      border-left: 1px solid #f7f7f7;
      margin: 0 auto;
      border-right: 1px solid #f7f7f7;
      padding: 20px;
    }

  </style>
