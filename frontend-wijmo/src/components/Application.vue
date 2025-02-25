<template>
    <div>
        <String label="ApplicationNumber" v-model="value.applicationNumber" :editMode="editMode"/>
        <Date label="ApplicationDate" v-model="value.applicationDate" :editMode="editMode"/>
        <ServiceType offline label="ServiceType" v-model="value.serviceType" :editMode="editMode" @change="change"/>
        <ApplicationStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        <Document offline label="문서" v-model="value.documents" :editMode="editMode" @change="change"/>
        <UserData offline label="사용자 정보" v-model="value.userData" :editMode="editMode" @change="change"/>
        <DataCollectorId offline label="dataCollectorId" v-model="value.dataCollectorId" :editMode="editMode" @change="change"/>

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
    name: 'Application',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "applications"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

