package org.concordion.ext.timing.junit.timeformatter;

import org.concordion.api.Element;
import org.concordion.api.Resource;
import org.concordion.api.listener.AbstractRunEvent;
import org.concordion.api.listener.SpecificationProcessingEvent;
import org.concordion.ext.timing.timeformatter.TimerSpecificationListener;
import org.junit.Assert;
import org.junit.Test;

public class TimerSpecificationListenerTests {


    @Test
    public void ToggleButtonHasCorrectId() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource("/"), new Resource("/"), false, true);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingContainer = event.getRootElement()
                .getFirstChildElement("body")
                .getFirstChildElement("div");
        Assert.assertEquals("Container's id is 'toggle-button'", "toggle-button",
                timingContainer.getAttributeValue("id"));
    }

    @Test
    public void ButtonIsToggleOffWhenShownByDefault() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource("/"), new Resource("/"), false, true);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingImage = getFirstVisibleImage(event);
        Assert.assertTrue(timingImage.getAttributeValue("id").equals("toggle-off"));
    }

    @Test
    public void ButtonIsToggleOnWhenNotShownByDefault() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource("/"), new Resource("/"), false, false);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingImage = getFirstVisibleImage(event);
        Assert.assertTrue(timingImage.getAttributeValue("id").equals("toggle-on"));
    }

    @Test
    public void ToggleButtonIsOnWhenHighlightedAndShown() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource("/"), new Resource("/"), true, true);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingImage = getFirstVisibleImage(event);
        Assert.assertTrue(timingImage.getAttributeValue("class").contains("time-toggle-button-on"));
    }

    @Test
    public void ToggleButtonIsOffWhenHighlightedAndNotShown() {

        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));
        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource("/"), new Resource("/"), true, false);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource("/"), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element timingImage = getFirstVisibleImage(event);
        Assert.assertFalse(timingImage.getAttributeValue("class").contains("time-toggle-button-on"));
    }

    private Element getFirstVisibleImage(SpecificationProcessingEvent event) {
        Element[] images = event.getRootElement()
                .getFirstChildElement("body")
                .getFirstChildElement("div")
                .getChildElements("img");
        Element timingImage = null;
        for (Element image : images) {
            if (image.getAttributeValue("style") != "display:none") {
                timingImage = image;
            }
        }
        return timingImage;
    }

    private Element buildRunElement() {
        // Create Elements to emulate a HTML file.
        Element element = new Element("div");

        // A tag used parse the html name for use with the listener.
        Element aTag = new Element("a");
        aTag.addAttribute("href", "test.html");

        // A tag used to store the timing (no timing actually in it)
        Element spanTag = new Element("span");
        spanTag.addAttribute("class", "time-fig-inline");

        element.appendChild(aTag);

        return element;
    }

    private void assertTimeFigInline(AbstractRunEvent runEvent) {
        // Get the timing span added by the run event above
        Element timingContainer = runEvent.getElement().getParentElement().getFirstDescendantNamed("span");
        Assert.assertNotEquals(timingContainer, null);

        String timingSpanClassName = timingContainer.getAttributeValue("class");

        // Check if it actually timing span exists
        Assert.assertEquals("Container's class is 'time-fig-inline'", "time-fig-inline", timingSpanClassName);
    }

    @Test
    public void toggleIconAtSameLevel() {
        assertToggleIconsPath("/test.png", "/", "test.png");
    }

    @Test
    public void toggleIconBelowSpec() {
        assertToggleIconsPath("/img/test.png", "/", "img/test.png");
    }

    @Test
    public void toggleIconAboveSpec() {
        assertToggleIconsPath("/test.png", "/sub/", "../test.png");
    }

    @Test
    public void toggleIconSiblingPathSpec() {
        assertToggleIconsPath("/img/test.png", "/sub/", "../img/test.png");
    }

    private void assertToggleIconsPath(String iconPath, String specificationPath, String expectedIconPath) {
        // Arrange
        Element html = new Element("html");
        html.appendChild(new Element("body"));

        TimerSpecificationListener listener = new TimerSpecificationListener(new Resource(iconPath), new Resource("/"), false, true);
        SpecificationProcessingEvent event = new SpecificationProcessingEvent(new Resource(specificationPath), html);

        // Act
        listener.beforeProcessingSpecification(event);
        listener.afterProcessingSpecification(event);

        // Assert
        Element toggleTimingIcon = event.getRootElement()
                .getElementById("toggle-button")
                .getFirstChildElement("img");

        Assert.assertNotNull(toggleTimingIcon);
        Assert.assertEquals("Toggle button 'toggle-button'", expectedIconPath,
                toggleTimingIcon.getAttributeValue("src"));

    }

}
