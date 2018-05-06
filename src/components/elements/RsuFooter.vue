<template>
    <footer>
        <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
                <v-flex xs6>
                    <v-card color="blue-grey darken-2" class="white--text mcontent elevation-9">
                        <div primary-title>
                            <div class="headline">Contacto</div>

                        </div>
                        <div>Telefono : 698 28 67 14</div>
                        <div>Email : <a href="#">recambios2uso@gmail.com </a></div>
                    </v-card>
                </v-flex>
                <v-flex xs6>
                    <v-card color="blue-grey darken-2" class="white--text mcontent elevation-9" >
                        <div primary-title>
                            <div class="headline">Direccion</div>

                        </div>
                        <div>C/ La Defensa s/n 29011</div>
                        <div> Malaga (España)</div>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-container>
    </footer>
</template>

<script>
    export default {
        name: "RsuFooter"
    }
</script>

<style>

    .card-body {

    }

    footer {
        padding-top: 3rem;
        padding-bottom: 3rem;
        background: #2d2b40; /* fallback for old browsers */
        background: -webkit-linear-gradient(to bottom, #343A40, #000000); /* Chrome 10-25, Safari 5.1-6 */
        background: linear-gradient(to bottom, #343A40, #000000); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

    }

    footer p {
        margin-bottom: .25rem;
    }

    .mcontent {
        min-height: 153px;
        padding: 20px;
        font-size: 1.3em;
    }

</style>
