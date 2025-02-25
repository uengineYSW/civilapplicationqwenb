<template>
    <v-card outlined>
        <v-card-title>
            VerifyElectronicSignature
        </v-card-title>

        <v-card-text>
            <String label="SignatureId" v-model="value.signatureId" :editMode="editMode"/>
            <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
            <Boolean label="Validity" v-model="value.validity" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="verifyElectronicSignature"
            >
                VerifyElectronicSignature
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
        name: 'VerifyElectronicSignatureCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.signatureId = '';
            this.value.documentId = '';
            this.value.validity = false;
        },
        watch: {
        },
        methods: {
            verifyElectronicSignature() {
                this.$emit('verifyElectronicSignature', this.value);
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

