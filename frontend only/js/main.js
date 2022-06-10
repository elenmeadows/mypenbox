
// SettingsBar animation (mobile view)

$(".settings").css("display", "flex").hide();

$(".set-icon").click(function () {
  if ($(".settings").is(":hidden")) {
    $(".settings").slideDown(700);
    $(".set-icon").addClass("open");
    $(".settings-background").css("display", "block");
    $(".main-nav").css("pointer-events", "none");
    $(".content-window").css("pointer-events", "none");
    $("header").css("box-shadow", "0 0.35rem 0.35rem rgba(38, 36, 36, .7)");
  } else {
    $(".settings").slideUp(200);
    $(".set-icon").removeClass("open");
    $(".settings-background").css("display", "none");
    $(".main-nav").css("pointer-events", "");
    $(".content-window").css("pointer-events", "");
    $("header").css("box-shadow", "");
  }
});

function settings() {
  window.onclick = function (event) {
    if (!event.target.matches(".set-icon") && !event.target.matches(".set-icon__burger") && !event.target.matches(".settings") && !event.target.matches(".settings a")) {
      $(".settings").slideUp(200);
      $(".set-icon").removeClass("open");
      $(".settings-background").css("display", "none");
      $(".main-nav").css("pointer-events", "");
      $(".content-window").css("pointer-events", "");
      $("header").css("box-shadow", "");
    }
  };
};

settings();

function mQuery() {
  let windowWidth = $(window).width();
  if (windowWidth >= 1024) {
    $(".settings").slideUp(1);
    $(".set-icon").removeClass("open");
    $(".settings-background").css("display", "none");
    $(".main-nav").css("pointer-events", "");
    $(".content-window").css("pointer-events", "");
    $("header").css("box-shadow", "");
  };

  if (windowWidth <= 692) {
    $(".manage-btn span").text("");
    $(".manage-btn svg").css("margin-left", "0");
  } else {
    $(".manage-btn span").text("Manage");
    $(".manage-btn svg").css("margin-left", "1rem");
  }
}

mQuery();
window.matchMedia("(min-width: 1024px)").addListener(mQuery);
window.matchMedia("(min-width: 692px)").addListener(mQuery);
window.matchMedia("(max-width: 692px)").addListener(mQuery);


// ManageButtonMenu animation

$(".manage-btn").click(function () {
  if ($(".manage-menu").is(":hidden")) {
    $(".manage-menu").show(200);
    $(".manage-btn svg").css("transform", "rotate(180deg)")
    $(".manage-btn svg").css("transition-duration", "0.4s");
  } else {
    $(".manage-menu").hide(200);
    $(".manage-btn svg").css("transform", "rotate(0deg)");
    $(".manage-btn svg").css("transition-duration", "0.4s");
  }
});

// Рабочая версия ManageButtonMenu animation, но тут hover effect:
// $(".manage-btn").on("mouseover", manageButtonOver);
// $(".manage-menu ul li a").on("click", manageButtonOut);

// function manageButtonOver(event) {
//   if (event.target.matches(".manage-btn")) {
//     $(".manage-menu").slideDown();
//     $(".manage-arrows-icon").css("transform", "rotate(180deg)");
//     $(".manage-arrows-icon").css("transition-duration", "0.4s");
//   }
// }

// function manageButtonOut(event) {
//   if (event.target.matches(".manage-menu ul li a")) {
//     $(".manage-menu").slideUp();
//     $(".manage-arrows-icon").css("transform", "rotate(0deg)");
//     $(".manage-arrows-icon").css("transition-duration", "0.4s");
//   }
// }

// $(".manage-menu").mouseleave(function () {
//   $(".manage-menu").slideUp();
//   $(".manage-arrows-icon").css("transform", "rotate(0deg)");
//   $(".manage-arrows-icon").css("transition-duration", "0.4s");
// });

// SearchBar animation

input.oninput = function () {
  let searchInput = $(".search-input").val();
  if (!searchInput == "") {
    $(".reset-icon").css("display", "inline-block");
  } else if (searchInput == "") {
    $(".reset-icon").css("display", "none");
  }
}

$(".reset-icon").click(function () {
  $(".reset-icon").css("display", "none");
});

// Modal window pop-up/collapse on click

$(".modal-window").click(function () {
  $("#overlay").show();
  $(".modal").show();
});

$(".close-button").click(function () {
  $("#overlay").hide();
  $(".modal").hide();
});

$("#overlay").click(function () {
  $("#overlay").hide();
  $(".modal").hide();
});

// Product indicator animation (modal window)

let rangePercent = $('[type="range"]').val();
$('[type="range"]').on('change input', function () {
  rangePercent = $('[type="range"]').val();
  $('#value').html(rangePercent);
});

// Favourite button animation (modal window)

$(".heart-btn").click(function () {
  $(this).toggleClass("liked");
});

// Auto-resize of feedback message container

$(".feedback-container").keyup(function (e) {
  $(this).css("height", "auto");
  let scHeight = $(this).prop('scrollHeight');
  $(this).css("height", `${scHeight}px`);
});
