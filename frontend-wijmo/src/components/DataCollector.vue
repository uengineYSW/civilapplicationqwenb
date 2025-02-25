<template>
    <div>
        <String label="DataCollectorId" v-model="value.dataCollectorId" :editMode="editMode"/>
        <String label="Schedule" v-model="value.schedule" :editMode="editMode"/>
        <Date label="NextRunTime" v-model="value.nextRunTime" :editMode="editMode"/>
        <CollectionStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
        <UserData offline label="사용자 정보" v-model="value.userData" :editMode="editMode" @change="change"/>
        <Document offline label="문서" v-model="value.document" :editMode="editMode" @change="change"/>
        <ApplicationId offline label="applicationId" v-model="value.applicationId" :editMode="editMode" @change="change"/>

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
    name: 'DataCollector',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "dataCollectors"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>

