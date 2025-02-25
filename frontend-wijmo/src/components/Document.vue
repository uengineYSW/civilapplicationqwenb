<template>

    <div>
        <div class="detail-title">
        Document
        </div>
        <v-col>
            <String label="DocumentName" v-model="value.documentName" :editMode="editMode"/>
            <String label="Type" v-model="value.type" :editMode="editMode"/>
            <String label="Size" v-model="value.size" :editMode="editMode"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'Document',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'Documents',
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
        if (this.value && this.value.idid !== undefined) {
            this.value = await this.repository.findById(this.value.idid)
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

