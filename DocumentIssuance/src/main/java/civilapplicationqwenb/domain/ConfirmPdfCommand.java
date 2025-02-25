package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ConfirmPdfCommand {

    private String documentId;
    private String confirmedBy;
}
