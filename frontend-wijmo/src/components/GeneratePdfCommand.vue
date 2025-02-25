<template>

    <v-card outlined>
        <v-card-title>
            GeneratePdf
        </v-card-title>

        <v-card-text>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <UserData offline label="UserData" v-model="value.userData" :editMode="editMode" @change="change"/>
            <DocumentList offline label="Documents" v-model="value.documents" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="generatePdf"
            >
                GeneratePdf
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'GeneratePdfCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.content = '';
            this.value.userData = {};
            this.value.documents = [];
        },
        watch: {
        },
        methods: {
            generatePdf() {
                this.$emit('generatePdf', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

