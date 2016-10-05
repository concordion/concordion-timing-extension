window.onload = function() {

    var toggleButton = document.getElementById("toggleImg");

    toggleButton.onclick = function() {

        var display = false;
        // check to see if the button has the class with the on styling
        if (toggleButton.classList.contains("time-toggle-button-on")) {
            // show it as off
            toggleButton.classList.remove("time-toggle-button-on");
        } else {
            // show it as on
            toggleButton.classList.add("time-toggle-button-on");
            display = true;
        }

        // update the time figures to reflect the state of the button
        toggleTiming(display);
    };
};

function toggleTiming(show) {

    var timings = document.getElementsByClassName("time-fig");
    var timing;
    for (var i = 0; timing = timings[i]; i++) {
        timing.style.display = show ? "block" : "none";
    }

    timings = document.getElementsByClassName("time-fig-inline");

    for (var i = 0; timing = timings[i]; i++) {
        timing.style.display = show ? "inline" : "none";
    }
}