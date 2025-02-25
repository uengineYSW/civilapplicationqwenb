<template>
    <v-card outlined>
        <v-card-title>
            ConfirmPdf
        </v-card-title>

        <v-card-text>
            <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
            <String label="ConfirmedBy" v-model="value.confirmedBy" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="confirmPdf"
            >
                ConfirmPdf
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'ConfirmPdfCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.documentId = '';
            this.value.confirmedBy = '';
        },
        watch: {
        },
        methods: {
            confirmPdf() {
                this.$emit('confirmPdf', this.value);
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

