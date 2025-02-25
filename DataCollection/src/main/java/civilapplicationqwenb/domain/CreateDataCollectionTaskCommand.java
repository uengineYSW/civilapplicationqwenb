package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateDataCollectionTaskCommand {

    private String sourceSystem;
    private String collectionSchedule;
    private CollectionStatus status;
}
