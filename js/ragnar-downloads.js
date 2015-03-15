$(document).ready(function() {
    $("#more-desktop").click(function() {
        $(".platform-pill").removeClass("active");
        $("#desktop-pill").addClass("active"); 
    });
    $("#more-mobile").click(function() {
        $(".platform-pill").removeClass("active");
        $("#mobile-pill").addClass("active");
        
    });
});