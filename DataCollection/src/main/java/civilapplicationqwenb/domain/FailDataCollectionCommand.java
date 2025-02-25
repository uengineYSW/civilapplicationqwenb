package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class FailDataCollectionCommand {

    private String errorDetails;
    private CollectionStatus status;
}
