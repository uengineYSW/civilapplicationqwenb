package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CompleteDataCollectionCommand {

    private List<Document> collectedData;
    private CollectionStatus status;
}
