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
// @RequestMapping(value="/electronicSignatures")
@Transactional
public class ElectronicSignatureController {

    @Autowired
    ElectronicSignatureRepository electronicSignatureRepository;

    @RequestMapping(
        value = "/electronicSignatures/verifyelectronicsignature",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public ElectronicSignature verifyElectronicSignature(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody VerifyElectronicSignatureCommand verifyElectronicSignatureCommand
    ) throws Exception {
        System.out.println(
            "##### /electronicSignature/verifyElectronicSignature  called #####"
        );
        ElectronicSignature electronicSignature = new ElectronicSignature();
        electronicSignature.verifyElectronicSignature(
            verifyElectronicSignatureCommand
        );
        electronicSignatureRepository.save(electronicSignature);
        return electronicSignature;
    }
}
//>>> Clean Arch / Inbound Adaptor
