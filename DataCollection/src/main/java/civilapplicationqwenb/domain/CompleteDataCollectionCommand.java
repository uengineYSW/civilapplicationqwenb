package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CompleteDataCollectionCommand {

    private Document collectedData;
    private CollectionStatus status;
}
