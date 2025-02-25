<template>
    <v-card outlined style="border-radius: 0; margin-top:-10px; padding-bottom:10px;">
        <v-card-title>
            신청 상세보기
        </v-card-title>
    
        <v-card-text style="margin-top:-30px;">
            <v-row no-gutters style="margin: 15px 0px -30px -15px;">
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="ApplicationNumber" v-model="value.parameters.applicationNumber" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <ServiceType offline label="ServiceType" v-model="value.parameters.serviceType" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Date class="attributes-list" label="ApplicationDate" v-model="value.parameters.applicationDate" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <ApplicationStatus offline label="Status" v-model="value.parameters.status" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <DocumentList offline label="Documents" v-model="value.parameters.documents" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <UserData offline label="UserData" v-model="value.parameters.userData" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col>
                    <v-btn class="gs-query-search-btn contrast-primary-text"
                        @click="search"
                        small
                        color="primary"
                    >
                        <v-icon small>mdi-magnify</v-icon>검색
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'ApplicationDetails',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {
                apiPath: 'applications/search/findByApplicationDetails',
                parameters: {}
            },
        }),
        created() {
        },
        watch: {
        },
        methods: {
            search() {
                let search = null;
                search = this.value;
                this.$emit('search', search);
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
<style>
.attributes-list{
    margin-left: 15px;
}
</style>

