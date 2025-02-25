<template>
    <div style="max-height:80vh;">
        <div class="hierarchy">
            <div>문서 발급 &nbsp; ></div>
            <div>&nbsp; PDF문서</div>
        </div>
        <div class="gs-bundle-of-buttons" style="max-height:10vh;">
            <v-btn @click="addNewRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-plus-circle-outline</v-icon>등록
            </v-btn>
            <v-btn  @click="editSelectedRow" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-pencil</v-icon>수정
            </v-btn>
            <v-btn @click="generatePdfDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>PDF문서 생성
            </v-btn>
            <v-dialog v-model="generatePdfDialog" width="500">
                <GeneratePdfCommand
                    @closeDialog="generatePdfDialog = false"
                    @generatePdf="generatePdf"
                ></GeneratePdfCommand>
            </v-dialog>
            <v-btn @click="confirmPdfDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>PDF문서 확인
            </v-btn>
            <v-dialog v-model="confirmPdfDialog" width="500">
                <ConfirmPdfCommand
                    @closeDialog="confirmPdfDialog = false"
                    @confirmPdf="confirmPdf"
                ></ConfirmPdfCommand>
            </v-dialog>
            <v-btn @click="addElectronicSignatureDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>전자서명 추가
            </v-btn>
            <v-dialog v-model="addElectronicSignatureDialog" width="500">
                <AddElectronicSignatureCommand
                    @closeDialog="addElectronicSignatureDialog = false"
                    @addElectronicSignature="addElectronicSignature"
                ></AddElectronicSignatureCommand>
            </v-dialog>
            <v-btn @click="finalizePdfDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('Guest')">
                <v-icon small>mdi-minus-circle-outline</v-icon>PDF문서 최종화
            </v-btn>
            <v-dialog v-model="finalizePdfDialog" width="500">
                <FinalizePdfCommand
                    @closeDialog="finalizePdfDialog = false"
                    @finalizePdf="finalizePdf"
                ></FinalizePdfCommand>
            </v-dialog>
            <v-btn @click="deleteSelectedRows" class="contrast-primary-text" small color="primary" :disabled="!hasRole('')">
                <v-icon small>mdi-minus-circle-outline</v-icon>삭제
            </v-btn>
            <excel-export-button class="contrast-primary-text" :exportService="this.exportService" :getFlex="getFlex" />
        </div>
        <PdfDocumentView @search="search"></PdfDocumentView>
        <SignatureStatus @search="search"></SignatureStatus>


        <!-- the grid -->
        <wj-flex-grid
            ref="flexGrid"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexInitialized"
            :selectionChanged="onSelectionChanged"
            style="margin-top:10px; max-height:65vh;"
            class="wj-felx-grid"
        >
            <wj-flex-grid-filter :filterColumns="['RowHeader','content','generationDate','status','userData','documents',]" />
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="content" header="Content" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="generationDate" header="GenerationDate" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="status" header="Status" width="2*" :isReadOnly="true" align="center" />
            <wj-flex-grid-column binding="userData" header="사용자 정보" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <UserData v-model="cell.item.userData" :editMode="editMode"></UserData>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
            <wj-flex-grid-column binding="documents" header="문서" width="2*" :isReadOnly="true" align="center">
                <wj-flex-grid-cell-template cellType="Cell" v-slot="cell">   
                    <Document v-model="cell.item.documents" :editMode="editMode"></Document>
                </wj-flex-grid-cell-template>
            </wj-flex-grid-column>
        </wj-flex-grid>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <template>
                    <v-card>
                        <v-toolbar
                            color="primary"
                            class="elevation-0"
                            height="50px"
                        >
                            <div style="color:white; font-size:17px; font-weight:700;">PDF문서 등록</div>
                            <v-spacer></v-spacer>
                            <v-icon
                                color="white"
                                small
                                @click="closeDialog()"
                            >mdi-close</v-icon>
                        </v-toolbar>
                        <v-card-text>
                            <PdfDocument :offline="offline"
                                :isNew="!itemToEdit"
                                :editMode="true"
                                v-model="newValue"
                                @add="append"
                                @edit="edit"
                            />
                        </v-card-text>
                    </v-card>
                </template>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid'

export default {
    name: 'pdfDocumentGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'pdfDocuments',
        generatePdfDialog: false,
        confirmPdfDialog: false,
        addElectronicSignatureDialog: false,
        finalizePdfDialog: false,
    }),
    watch: {
    },
    methods:{
        generatePdf(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "/generatepdf", params)
                this.$EventBus.$emit('show-success','GeneratePDF 성공적으로 처리되었습니다.')
                this.generatePdfDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        confirmPdf(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "confirmpdf", params)
                this.$EventBus.$emit('show-success','ConfirmPDF 성공적으로 처리되었습니다.')
                this.confirmPdfDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        addElectronicSignature(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "addelectronicsignature", params)
                this.$EventBus.$emit('show-success','AddElectronicSignature 성공적으로 처리되었습니다.')
                this.addElectronicSignatureDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
        finalizePdf(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "finalizepdf", params)
                this.$EventBus.$emit('show-success','FinalizePDF 성공적으로 처리되었습니다.')
                this.finalizePdfDialog = false
            }catch(e){
                this.$EventBus.$emit('show-error', e);
            }
            
        },
    }
}
</script>