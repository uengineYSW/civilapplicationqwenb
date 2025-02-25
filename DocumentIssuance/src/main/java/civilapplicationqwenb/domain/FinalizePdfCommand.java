package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class FinalizePdfCommand {

    private String documentId;
    private DocumentStatus status;
}
