package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ApplicationCreated extends AbstractEvent {

    private String applicationNumber;
    private Object serviceType;
    private Date applicationDate;
    private Object status;
    private Object userData;
}
