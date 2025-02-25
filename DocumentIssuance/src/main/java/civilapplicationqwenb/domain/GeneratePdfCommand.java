package civilapplicationqwenb.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class GeneratePdfCommand {

    private String content;
    private UserData userData;
    private Document documents;
}
