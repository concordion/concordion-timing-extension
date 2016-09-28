window.onload = function() {

    var toggleButton = document.getElementById("toggleImg");
    toggleButton.style.backgroundColor = "#F5F9FD";
    toggleButton.style.border = "1px solid #C3D9FF";
    toggleButton.style.padding = "4px"
    toggleButton.onclick = function() {

        var timings = document.getElementsByClassName("time-fig");

        for (var i = 0; i < timings.length; i++) {
            var timing = timings[i];

            if (timing.style.display === "none") {
                timing.style.display = "block";
                toggleButton.style.backgroundColor = "#F5F9FD";
                toggleButton.style.border = "1px solid #C3D9FF";
                toggleButton.style.padding = "4px"

            } else {
                timing.style.display = "none";
                toggleButton.style.backgroundColor = "white";
                toggleButton.style.border = "";
                toggleButton.style.padding = "5px";

            }
         }


        var timings = document.getElementsByClassName("time-fig-inline");

        for (var i = 0; i < timings.length; i++) {
            var timing = timings[i];

            if (timing.style.display === "none") {
                timing.style.display = "inline";
            } else {
                timing.style.display = "none";
            }
         }
    };


};