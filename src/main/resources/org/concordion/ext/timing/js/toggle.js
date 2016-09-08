window.onload = function() {
    var toggleButton = document.getElementById("toggle-button");
    toggleButton.onclick = function() {

        var timings = document.getElementsByClassName("time-fig");

        for (var i = 0; i < timings.length; i++) {
            var timing = timings[i];
            console.log(timing);

            if (timing.style.display === "none") {
                timing.style.display = "block";
            } else {
                timing.style.display = "none";
            }
         }


        var timings = document.getElementsByClassName("time-fig-inline");

        for (var i = 0; i < timings.length; i++) {
            var timing = timings[i];
            console.log(timing);

            if (timing.style.display === "none") {
                timing.style.display = "inline";
            } else {
                timing.style.display = "none";
            }
         }
    };
};