package civilapplicationqwenb.domain;

import civilapplicationqwenb.domain.*;
import civilapplicationqwenb.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class SignatureVerified extends AbstractEvent {

    private String signatureId;
    private Boolean validity;

    public SignatureVerified(ElectronicSignature aggregate) {
        super(aggregate);
    }

    public SignatureVerified() {
        super();
    }
}
//>>> DDD / Domain Event
