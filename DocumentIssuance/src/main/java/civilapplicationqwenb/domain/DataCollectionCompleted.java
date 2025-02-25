package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DataCollectionCompleted extends AbstractEvent {

    private Date endTime;
    private Object status;
    private Object collectedData;
}
