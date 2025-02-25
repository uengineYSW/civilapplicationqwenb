<template>
    <div>
        <String label="SignatureId" v-model="value.signatureId" :editMode="editMode"/>
        <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
        <String label="UserId" v-model="value.userId" :editMode="editMode"/>
        <Date label="SignatureDate" v-model="value.signatureDate" :editMode="editMode"/>
        <SignatureStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        <UserData offline label="사용자 정보" v-model="value.userData" :editMode="editMode" @change="change"/>
        <Document offline label="문서" v-model="value.document" :editMode="editMode" @change="change"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'ElectronicSignature',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "electronicSignatures"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

