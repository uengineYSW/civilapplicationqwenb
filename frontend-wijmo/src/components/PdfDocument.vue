<template>
    <div>
        <String label="DocumentId" v-model="value.documentId" :editMode="editMode"/>
        <String label="Content" v-model="value.content" :editMode="editMode"/>
        <Date label="GenerationDate" v-model="value.generationDate" :editMode="editMode"/>
        <DocumentStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        <UserData offline label="사용자 정보" v-model="value.userData" :editMode="editMode" @change="change"/>
        <Document offline label="문서" v-model="value.documents" :editMode="editMode" @change="change"/>

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
    name: 'PdfDocument',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "pdfDocuments"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

