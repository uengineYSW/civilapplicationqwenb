package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataCollectionCompleted extends AbstractEvent {

    private Date endTime;
    private CollectionStatus status;
    private Document collectedData;

    public DataCollectionCompleted(DataCollector aggregate) {
        super(aggregate);
    }

    public DataCollectionCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
