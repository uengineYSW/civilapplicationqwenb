package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SubmitApplicationCommand {

    private String applicationNumber;
    private List<Document> documents;
}
