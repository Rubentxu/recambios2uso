<template>
    <v-app>
        <v-toolbar color="primary">
         <span class="group pa-2 teal">
           <v-icon medium dark>widgets</v-icon>

        </span>
            <v-avatar tile="true" size="50px">
                <img src="./assets/ruedaLogo.png" alt="avatar">
            </v-avatar>
            <v-toolbar-title style="color: aliceblue">
                Recambios Segundo Uso
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-toolbar-items class="hidden-sm-and-down">
                <v-btn style="color: aliceblue" flat>Landing</v-btn>
            </v-toolbar-items>

        </v-toolbar>
        <v-content>
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        data() {
            return {
                clipped: false,
                drawer: true,
                fixed: false,
                items: [{
                    icon: 'bubble_chart',
                    title: 'Inspire'
                }],
                miniVariant: false,
                right: true,
                rightDrawer: false,
                title: 'Vuetify.js'
            }
        },
        name: 'App'
    }
</script>

<style>
    .container.fluid {
        padding: 0;
    }
</style>