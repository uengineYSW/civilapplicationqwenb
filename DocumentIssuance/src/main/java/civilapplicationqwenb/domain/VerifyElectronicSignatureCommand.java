package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class VerifyElectronicSignatureCommand {

    private String signatureId;
    private String documentId;
    private Boolean validity;
}
