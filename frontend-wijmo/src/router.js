
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ApplicationManager from "./components/ui/ApplicationGrid"

import DataCollectorManager from "./components/ui/DataCollectorGrid"

import PdfDocumentManager from "./components/ui/PdfDocumentGrid"
import ElectronicSignatureManager from "./components/ui/ElectronicSignatureGrid"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/applications',
                name: 'ApplicationManager',
                component: ApplicationManager
            },

            {
                path: '/dataCollectors',
                name: 'DataCollectorManager',
                component: DataCollectorManager
            },

            {
                path: '/pdfDocuments',
                name: 'PdfDocumentManager',
                component: PdfDocumentManager
            },
            {
                path: '/electronicSignatures',
                name: 'ElectronicSignatureManager',
                component: ElectronicSignatureManager
            },



    ]
})
