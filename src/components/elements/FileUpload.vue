<template>

    <div>
        <input type="file" multiple accept="image/*" @change="detectFiles($event.target.files)">
        <div class="progress-bar" :style="{ width: progressUpload + '%'}">{{ progressUpload }}%</div>
    </div>

</template>

<script>

    import  firebaseApp from '@/firebase-config'
    export default {
        data () {
            return {
                progressUpload: 0,
                file: File,
                uploadTask: '',
                downloadURL: ''
            }
        },
        methods: {
            detectFiles (fileList) {
                Array.from(Array(fileList.length).keys()).map( x => {
                    this.upload(fileList[x])
                })
            },
            upload (file) {
                this.uploadTask = firebaseApp.storage.ref('imagenes/repuestos').put(file);

                this.uploadTask.then(snapshot => {
                    this.downloadURL = this.uploadTask.snapshot.downloadURL;
                    this.$emit('url', this.downloadURL)
                })
            }
        },
        watch: {
            uploadTask: function() {
                this.uploadTask.on('state_changed', sp => {
                    this.progressUpload = Math.floor(sp.bytesTransferred / sp.totalBytes * 100)
                })
            }
        }
    }
</script>

<style>
    .progress-bar {
        margin: 10px 0;
    }
</style>
