<template>

    <v-card outlined>
        <v-card-title>
            FinalizePdf
        </v-card-title>

        <v-card-text>
            <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
            <DocumentStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="finalizePdf"
            >
                FinalizePdf
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
        name: 'FinalizePdfCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.documentId = '';
            this.value.status = {};
        },
        watch: {
        },
        methods: {
            finalizePdf() {
                this.$emit('finalizePdf', this.value);
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

