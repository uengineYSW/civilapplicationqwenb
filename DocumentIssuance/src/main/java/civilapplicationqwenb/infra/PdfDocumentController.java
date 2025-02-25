package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/pdfDocuments")
@Transactional
public class PdfDocumentController {

    @Autowired
    PdfDocumentRepository pdfDocumentRepository;

    @RequestMapping(
        value = "/pdfDocuments/generatepdf",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public PdfDocument generatePdf(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody GeneratePdfCommand generatePdfCommand
    ) throws Exception {
        System.out.println("##### /pdfDocument/generatePdf  called #####");
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.generatePdf(generatePdfCommand);
        pdfDocumentRepository.save(pdfDocument);
        return pdfDocument;
    }

    @RequestMapping(
        value = "/pdfDocuments/{id}/confirmpdf",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public PdfDocument confirmPdf(
        @PathVariable(value = "id") String id,
        @RequestBody ConfirmPdfCommand confirmPdfCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pdfDocument/confirmPdf  called #####");
        Optional<PdfDocument> optionalPdfDocument = pdfDocumentRepository.findById(
            id
        );

        optionalPdfDocument.orElseThrow(() -> new Exception("No Entity Found"));
        PdfDocument pdfDocument = optionalPdfDocument.get();
        pdfDocument.confirmPdf(confirmPdfCommand);

        pdfDocumentRepository.save(pdfDocument);
        return pdfDocument;
    }

    @RequestMapping(
        value = "/pdfDocuments/{id}/addelectronicsignature",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public PdfDocument addElectronicSignature(
        @PathVariable(value = "id") String id,
        @RequestBody AddElectronicSignatureCommand addElectronicSignatureCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /pdfDocument/addElectronicSignature  called #####"
        );
        Optional<PdfDocument> optionalPdfDocument = pdfDocumentRepository.findById(
            id
        );

        optionalPdfDocument.orElseThrow(() -> new Exception("No Entity Found"));
        PdfDocument pdfDocument = optionalPdfDocument.get();
        pdfDocument.addElectronicSignature(addElectronicSignatureCommand);

        pdfDocumentRepository.save(pdfDocument);
        return pdfDocument;
    }

    @RequestMapping(
        value = "/pdfDocuments/{id}/finalizepdf",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public PdfDocument finalizePdf(
        @PathVariable(value = "id") String id,
        @RequestBody FinalizePdfCommand finalizePdfCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /pdfDocument/finalizePdf  called #####");
        Optional<PdfDocument> optionalPdfDocument = pdfDocumentRepository.findById(
            id
        );

        optionalPdfDocument.orElseThrow(() -> new Exception("No Entity Found"));
        PdfDocument pdfDocument = optionalPdfDocument.get();
        pdfDocument.finalizePdf(finalizePdfCommand);

        pdfDocumentRepository.save(pdfDocument);
        return pdfDocument;
    }
}
//>>> Clean Arch / Inbound Adaptor
