package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ElectronicSignatureHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<ElectronicSignature>> {

    @Override
    public EntityModel<ElectronicSignature> process(
        EntityModel<ElectronicSignature> model
    ) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/verifyelectronicsignature"
                )
                .withRel("verifyelectronicsignature")
        );

        return model;
    }
}
