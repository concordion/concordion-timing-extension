package org.concordion.ext;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.footer.TimerSpecificationListener;

public class TimerExtension implements ConcordionExtension {

    @Override
    public void addTo(ConcordionExtender extender) {
        extender.withSpecificationProcessingListener(new TimerSpecificationListener());
    }
}
