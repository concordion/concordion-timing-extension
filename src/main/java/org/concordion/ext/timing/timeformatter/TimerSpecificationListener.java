package org.concordion.ext.timing.timeformatter;

import org.concordion.api.*;
import org.concordion.api.listener.*;

public class TimerSpecificationListener implements SpecificationProcessingListener   {

    private final Resource iconResource;
    private final boolean showByDefault;

    public TimerSpecificationListener(Resource icon, boolean showByDefault) {
        // Initialise Variables
        iconResource = icon;
        this.showByDefault = showByDefault;
    }

    @Override
    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
    }

    @Override
    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        // Add toggle button to specification sheet

        // creates new <div> container for styling the toggle button
        Element toggleContainer = new Element("div");
        toggleContainer.setId("toggle-button");

        // creates <img> tag for the clickable icon that toggles the timing data
        Element toggleIcon = new Element("img");
        toggleIcon.setId("toggleImg");
        toggleIcon.addStyleClass("time-toggle-button");

        // checks if timings should be shown by default
        if (showByDefault) {
            toggleIcon.addStyleClass("time-toggle-button-on");
        }

        toggleIcon.addAttribute("src", event.getResource().getRelativePath(iconResource));
        toggleIcon.addAttribute("height", "24");
        toggleIcon.addAttribute("width", "24");

        toggleContainer.appendChild(toggleIcon);

        // add it to the top of the concordion HTML
        event.getRootElement().getFirstDescendantNamed("body").prependChild(toggleContainer);
    }
}