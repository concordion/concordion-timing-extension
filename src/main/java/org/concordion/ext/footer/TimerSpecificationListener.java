package org.concordion.ext.footer;

import org.concordion.api.listener.*;

// FIXME: currently only gets the total time for the entire spec file not individual runs/examples
public class TimerSpecificationListener implements SpecificationProcessingListener {

    private long startTime;

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime + "ms");
    }
}
