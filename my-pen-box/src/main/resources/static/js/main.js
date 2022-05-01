
// SettingsBar animation (mobile view)

$(".settings").css("display", "flex").hide();

$(".set-icon").click(function () {
    if ($(".settings").is(":hidden")) {
        $(".settings").slideDown(700);
        $(".settings-background").css("display", "block");
        $(".main-nav").css("pointer-events", "none");
        $(".content-window").css("pointer-events", "none");
        $("header").css("box-shadow", "0 0.35rem 0.35rem rgba(60, 10, 41, .7)");
    } else {
        $(".settings").slideUp(200);
        $(".settings-background").css("display", "none");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    }
});

window.onclick = function settings(event) {
    if (!event.target.matches(".set-icon") && !event.target.matches(".settings") && !event.target.matches(".settings a")) {
        $(".settings").slideUp(200);
        $(".settings-background").css("display", "none");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    }
};

function mQuery() {
    if (window.matchMedia("(min-width: 1024px)").matches) {
        $(".settings").slideUp(1);
        $(".settings-background").css("display", "none");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    }
};

mQuery();
window.matchMedia("(min-width: 1024px)").addListener(mQuery);

// ManageButtonMenu animation

$(".manage-btn").on("mouseover", manageButtonOver);
$(".manage-menu ul li a").on("click", manageButtonOut);

function manageButtonOver(event) {
    if (event.target.matches(".manage-btn")) {
        $(".manage-menu").slideDown();
        $(".manage-arrows-icon").css("transform", "rotate(180deg)");
        $(".manage-arrows-icon").css("transition-duration", "0.4s");
    }
}

function manageButtonOut(event) {
    if (event.target.matches(".manage-menu ul li a")) {
        $(".manage-menu").slideUp();
        $(".manage-arrows-icon").css("transform", "rotate(0deg)");
        $(".manage-arrows-icon").css("transition-duration", "0.4s");
    }
}

$(".manage-menu").mouseleave(function () {
    $(".manage-menu").slideUp();
    $(".manage-arrows-icon").css("transform", "rotate(0deg)");
    $(".manage-arrows-icon").css("transition-duration", "0.4s");
});

// Рабочая версия ManageButtonMenu animation, но только по кликам:
// $(".manage-btn").click(function () {
//   if ($(".manage-menu").is(":hidden")) {
//     $(".manage-menu").show(200);
//     $(".manage-arrows-icon").css("transform", "rotate(180deg)")
//     $(".manage-arrows-icon").css("transition-duration", "0.4s");
//   } else {
//     $(".manage-menu").hide(200);
//     $(".manage-arrows-icon").css("transform", "rotate(0deg)");
//     $(".manage-arrows-icon").css("transition-duration", "0.4s");
//   }
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

// Modal animation

let rangePercent = $('[type="range"]').val();
$('[type="range"]').on('change input', function () {
    rangePercent = $('[type="range"]').val();
    $('span').html(rangePercent);
});