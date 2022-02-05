$(".settings").css("display", "flex").hide();

$(".set-icon").click(function() {
  if ( $( ".settings" ).is( ":hidden" ) ) {
    $(".settings").slideDown(700);
    $("body").css("background-color", "rgba(0,0,0,0.7)");
    $(".main-nav").css("pointer-events", "none");
    $(".content-window").css("pointer-events", "none");
    $("header").css("box-shadow", "0 0.35rem 0.35rem rgba(60, 10, 41, .7)");
  } else {
    $(".settings").slideUp(200);
    $("body").css("background-color", "white");
    $(".main-nav").css("pointer-events", "");
    $(".content-window").css("pointer-events", "");
    $("header").css("box-shadow", "");
  }
});

window.onclick = function(event) {
  if (!event.target.matches(".set-icon") && !event.target.matches(".settings") && !event.target.matches(".settings a")) {
      $(".settings").slideUp(200);
      $("body").css("background-color", "white");
      $(".main-nav").css("pointer-events", "");
      $(".content-window").css("pointer-events", "");
      $("header").css("box-shadow", "");
  }
};

function mQuery() {
    if (window.matchMedia("(min-width: 1024px)").matches) {
        $(".settings").slideUp(1);
        $("body").css("background-color", "white");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    }
};

mQuery();
window.matchMedia("(min-width: 1024px)").addListener(mQuery);
