package civilapplicationqwenb.infra;

import civilapplicationqwenb.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PdfDocumentHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<PdfDocument>> {

    @Override
    public EntityModel<PdfDocument> process(EntityModel<PdfDocument> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/generatepdf")
                .withRel("generatepdf")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/confirmpdf")
                .withRel("confirmpdf")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/addelectronicsignature"
                )
                .withRel("addelectronicsignature")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/finalizepdf")
                .withRel("finalizepdf")
        );

        return model;
    }
}
