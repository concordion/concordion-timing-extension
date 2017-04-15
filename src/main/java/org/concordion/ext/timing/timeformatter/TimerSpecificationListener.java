package org.concordion.ext.timing.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.Resource;
import org.concordion.api.listener.SpecificationProcessingEvent;
import org.concordion.api.listener.SpecificationProcessingListener;

public class TimerSpecificationListener implements SpecificationProcessingListener   {

    private final Resource onIconResource;
    private final Resource offIconResource;
    private final boolean highlightIcon;
    private final boolean showByDefault;

    public TimerSpecificationListener(Resource onIcon, Resource offIcon, boolean highlightIcon, boolean showByDefault) {
        onIconResource = onIcon;
        offIconResource = offIcon;
        this.highlightIcon = highlightIcon;
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
//        Element toggleIcon = new Element("img");
//        toggleIcon.setId("toggleImg");

        Element toggleOnIcon = new Element("img");
        toggleOnIcon.setId("toggle-on");
        toggleOnIcon.addAttribute("src", event.getResource().getRelativePath(onIconResource));
        toggleOnIcon.addAttribute("height", "24");
        toggleOnIcon.addAttribute("width", "24");
        if (highlightIcon) {
            toggleOnIcon.addStyleClass("time-toggle-button");
        }

        Element toggleOffIcon = new Element("img");
        toggleOffIcon.setId("toggle-off");
        toggleOffIcon.addAttribute("src", event.getResource().getRelativePath(offIconResource));
        toggleOffIcon.addAttribute("height", "24");
        toggleOffIcon.addAttribute("width", "24");
        if (highlightIcon) {
            toggleOffIcon.addStyleClass("time-toggle-button");
            toggleOffIcon.addStyleClass("time-toggle-button-on");
        }

        // checks if timings should be shown by default
        if (showByDefault) {
            toggleOnIcon.addAttribute("style", "display:none");
        } else {
            toggleOffIcon.addAttribute("style", "display:none");
        }

//        toggleIcon.addAttribute("src", event.getResource().getRelativePath(iconResource));
//        toggleIcon.addAttribute("height", "24");
//        toggleIcon.addAttribute("width", "24");
//
//        toggleContainer.appendChild(toggleIcon);
        toggleContainer.appendChild(toggleOnIcon);
        toggleContainer.appendChild(toggleOffIcon);

        // add it to the top of the concordion HTML
        event.getRootElement().getFirstDescendantNamed("body").prependChild(toggleContainer);
    }
}