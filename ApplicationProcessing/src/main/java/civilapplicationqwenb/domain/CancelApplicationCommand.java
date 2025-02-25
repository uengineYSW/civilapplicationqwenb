package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelApplicationCommand {

    private String applicationNumber;
    private String cancellationReason;
}
