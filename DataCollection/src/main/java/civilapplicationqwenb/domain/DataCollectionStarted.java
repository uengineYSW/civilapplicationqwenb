package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DataCollectionStarted extends AbstractEvent {

    private Date startTime;
    private CollectionStatus status;

    public DataCollectionStarted(DataCollector aggregate) {
        super(aggregate);
    }

    public DataCollectionStarted() {
        super();
    }
}
//>>> DDD / Domain Event
