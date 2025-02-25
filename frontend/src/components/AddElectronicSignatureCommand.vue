<template>
    <v-card outlined>
        <v-card-title>
            AddElectronicSignature
        </v-card-title>

        <v-card-text>
            <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
            <String label="SignatureData" v-model="value.signatureData" :editMode="editMode"/>
            <Date label="SignatureDate" v-model="value.signatureDate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="addElectronicSignature"
            >
                AddElectronicSignature
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
        name: 'AddElectronicSignatureCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.documentId = '';
            this.value.signatureData = '';
            this.value.signatureDate = '2025-02-25';
        },
        watch: {
        },
        methods: {
            addElectronicSignature() {
                this.$emit('addElectronicSignature', this.value);
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

