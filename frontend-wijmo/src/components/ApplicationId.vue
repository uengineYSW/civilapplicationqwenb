<template>

    <div>
        <div class="detail-title">
        ApplicationId
        </div>
        <v-col>
            <ServiceType offline label="서비스 유형" v-model="value.serviceType" :editMode="editMode" @change="change"/>
            <ApplicationStatus offline label="신청 상태" v-model="value.applicationStatus" :editMode="editMode" @change="change"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'ApplicationId',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'ApplicationIds',
    }),
    props: {
    },
    
    watch: {
        value(val){
            this.value = val;
            this.change();
        },
    },
    computed:{
        nameField(){
            var name = '';
            if(Object.keys(this.value).length < 3){
                name = "id"
            }else{
                const excludedKeys = ['_links','index'];
                const filteredKeys = Object.keys(this.value).filter(key => {
                    const valueType = typeof this.value[key];
                    return !excludedKeys.includes(key) && key !== 'id' && valueType !== 'object' && valueType !== 'number';
                });
                if(filteredKeys == []){
                    name = "id"
                }else{
                    name = filteredKeys[1]; 
                }
            }
            return name
        }
    },
    async created(){
        if (this.value && this.value.applicationNumber !== undefined) {
            this.value = await this.repository.findById(this.value.applicationNumber)
        }
    },
    methods: {
        pick(val){
            this.value = val;
            this.change();
        },
    }
}
</script>

