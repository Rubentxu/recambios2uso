<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>Admin page</h1>
      </v-flex>
      <v-flex xs12 class="text-xs-center" mt-3>
        <p>This is a user's home page</p>
        <file-upload></file-upload>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import FileUpload from '@/components/elements/FileUpload'
  export default {
      name: "FileUpload",
      components: {
          FileUpload
      }
  }
</script>
