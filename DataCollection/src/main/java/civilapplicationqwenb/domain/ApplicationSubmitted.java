package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ApplicationSubmitted extends AbstractEvent {

    private String applicationNumber;
    private Object status;
    private Date submissionDate;
}
