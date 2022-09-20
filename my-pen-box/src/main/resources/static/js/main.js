
// Close menus if clicked outside

function hideIfOutside() {
    window.onclick = function (event) {
        if (!event.target.matches(".settings-icon") && !event.target.matches(".settings-icon__burger") && !event.target.matches(".settings") && !event.target.matches(".settings *")) {
            $(".settings").slideUp(200);
            $(".settings-icon").removeClass("open");
            $(".settings-background").css("display", "none");
            $(".main-nav").css("pointer-events", "");
            $(".content-window").css("pointer-events", "");
            $("header").css("box-shadow", "");
        }

        if (!event.target.matches(".sortby-button") && !event.target.matches(".sortby-button *") && !event.target.matches(".sortby-filter-submenu") && !event.target.matches(".sortby-filter-submenu *")) {
            $(".sortby-filter-submenu").slideUp(200);
            $(".sortby-button .double-arrow").css("transform", "rotate(0deg)");
            $(".sortby-button .double-arrow").css("transition-duration", "0.4s");
        }

        if (!event.target.matches(".search-input")) {
            $(".reset-icon").css("display", "none");
        }
    }
};

hideIfOutside();

// MediaQuery animations

function mQuery() {
    let windowWidth = $(window).width();
    if (windowWidth >= 1024) {
        $(".settings").slideUp(1);
        $(".settings-icon").removeClass("open");
        $(".settings-background").css("display", "none");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    };

    if (windowWidth <= 692) {
        $(".manage-button span").text("");
        $(".manage-button svg").css("margin-left", "0");
    } else {
        $(".manage-button span").text("Manage");
        $(".manage-button svg").css("margin-left", "1rem");
    }
}

mQuery();
window.matchMedia("(min-width: 1024px)").addListener(mQuery);
window.matchMedia("(min-width: 692px)").addListener(mQuery);
window.matchMedia("(max-width: 692px)").addListener(mQuery);

// SettingsBar animation (mobile view)

$(".settings").css("display", "flex").hide();

$(".settings-icon").click(function () {
    if ($(".settings").is(":hidden")) {
        $(".settings").slideDown(700);
        $(".settings-icon").addClass("open");
        $(".settings-background").css("display", "block");
        $(".main-nav").css("pointer-events", "none");
        $(".content-window").css("pointer-events", "none");
        $("header").css("box-shadow", "0 0.35rem 0.35rem rgba(38, 36, 36, .7)");
    } else {
        $(".settings").slideUp(200);
        $(".settings-icon").removeClass("open");
        $(".settings-background").css("display", "none");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
        $("header").css("box-shadow", "");
    }
});

// SearchBar animation

$(".search-input").on("input focus", function () {
    let searchInput = $(".search-input").val();
    if (!searchInput == "") {
        $(".reset-icon").css("display", "inline-block");
    } else if (searchInput == "") {
        $(".reset-icon").css("display", "none");
    }
});

$(".reset-icon").click(function () {
    $('.search-input').attr('value', '')
    $(".reset-icon").css("display", "none");
    window.location = "http://localhost:8080/catalog";
});

// SortByFilterMenu animation

function toggleFilterButton() {
    let searchInput = $(".search-input").val();
    if (!searchInput == "") {
        $('.filter-toggle').text("on");
    } else if (searchInput == "") {
        $('.filter-toggle').text("off");
    }
}

toggleFilterButton();

$(".sortby-button").click(function () {
    if ($(".sortby-filter-submenu").is(":hidden")) {
        $(".sortby-filter-submenu").slideDown(700);
        $(".sortby-button .double-arrow").css("transform", "rotate(180deg)")
        $(".sortby-button .double-arrow").css("transition-duration", "0.4s");
    } else {
        $(".sortby-filter-submenu").slideUp(200);
        $(".sortby-button .double-arrow").css("transform", "rotate(0deg)");
        $(".sortby-button .double-arrow").css("transition-duration", "0.4s");
    }
});

// ManageButtonMenu animation

$("td").click(function () {
    if ($(this).find(".submenu__manage").is(":hidden")) {
        $(this).find(".submenu__manage").slideDown(500);
        $(this).find(".manage-button .double-arrow").css("transform", "rotate(180deg)")
        $(this).find(".manage-button .double-arrow").css("transition-duration", "0.4s");
    } else {
        $(this).find(".submenu__manage").slideUp(200);
        $(this).find(".manage-button .double-arrow").css("transform", "rotate(0deg)");
        $(this).find(".manage-button .double-arrow").css("transition-duration", "0.4s");
    }
});

// Multiselection animation

$('#select-all').click(function(){
    $('table input:checkbox').prop('checked', true);
    $('.multselection__counter span').html($('table input:checkbox:checked').length);
});

$('#remove-selection').click(function(){
    $('table input:checkbox').prop('checked', false);
    $('.multselection__counter span').html($('table input:checkbox:checked').length);
    $(".multselection").slideUp(400);
});

$(".multselection").css("display", "flex").hide();

$("table input:checkbox").click(function() {
    if ($('table input:checkbox:checked').length >= 1) {
        $(".multselection").slideDown(400);
    } else if ($('table input:checkbox:checked').length <= 2) {
        $(".multselection").slideUp(400);
    }
    $('.multselection__counter span').html($('table input:checkbox:checked').length);
});

// Modal window pop-up/collapse on click

$(".modal-open").click(function () {
    let productId = $(this).attr("id").replace(/\D/g, ""); // Getting ID-number of product in row
    $.ajax({
        url:'/modal?productId=' + productId,
        success: function (data) {
            $('#product-info').load('/modal?productId=' + productId);
            $('#product-info').removeAttr('class').addClass(productId);
        }
    });
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

$(".fav-button").click(function () {
    $(this).toggleClass("liked");
});

// Previous and Next buttons (modal window)

$(".prev-button").click(function () {
    let productId;
    if($(".product-info__modal[id]").length){
        productId = $(".product-info__modal").attr("id");
    } else {
        productId = parseInt($("#product-info").attr("class"));
    }
    $.ajax({
        url:'/modal-prev?productId=' + productId,
        success: function (data) {
            $('#product-info').load('/modal-prev?productId=' + productId);
        }
        });
    $("#overlay").show();
    $(".modal").show();
});

$(".next-button").click(function () {
    let productId;
    if($(".product-info__modal[id]").length){
        productId = $(".product-info__modal").attr("id");
    } else {
        productId = parseInt($("#product-info").attr("class"));
    }
    $.ajax({
        url:'/modal-next?productId=' + productId,
        success: function (data) {
            $('#product-info').load('/modal-next?productId=' + productId);
        }
    });
    $("#overlay").show();
    $(".modal").show();
});

// Sign-up & Login fields

$("#email").blur(function () {
    checkInputs($(this));
});

$("#firstname").blur(function () {
    checkInputs($(this));
});

$("#lastname").blur(function () {
    checkInputs($(this));
});

$("#nickname").blur(function () {
    checkInputs($(this));
});

$("#password").blur(function () {
    checkInputs($(this));
});

function checkInputs(inputValue) {
    const trimmedInput = $.trim(inputValue.val());

    if (trimmedInput === '') {
        $(".info-block").text("field(s) can't be blank!");
        inputValue.removeAttr("class").addClass("error");
    } else if (!isValid(trimmedInput)) {
        inputValue.removeAttr("class").addClass("error");
    } else {
        $(".info-block").text("");
        inputValue.removeAttr("class");
    }
}

function isValid(trimmedInput) {
    const email = $.trim($("#email").val());
    const firstName = $.trim($("#firstname").val());
    const lastName = $.trim($("#lastname").val());
    const nickname = $.trim($("#nickname").val());
    const password = $.trim($("#password").val());
    let result;

    switch (trimmedInput) {
        case email:
            result = /([\w\.\-_]+)?\w+@[\w-_]+(\.\w+){1,}$/.test(trimmedInput);
            break;
        case firstName:
            result = /^[a-zA-ZЁёА-я][A-Za-z-'ЁёА-я]*$/.test(trimmedInput);
            break;
        case lastName:
            result = /^[a-zA-ZЁёА-я][A-Za-z-'ЁёА-я]*$/.test(trimmedInput);
            break;
        case nickname:
            result = /^(?=.{3,})[A-Za-z0-9-._]*$/.test(trimmedInput);
            break;
        case password:
            result =  /^(?=.{8,})(?=.*\d)(?=.*[a-zёа-я])(?=.*[A-ZЁА-я])(?!.*\s).*$/.test(trimmedInput);
            break;
    }

    return result;

}

let passwordInput = false;
$(".show-password").click(function() {
    if (passwordInput) {
        $("#password").attr("type", "password");
        $(".show-pswd-icon .st0").css("stroke", "rgba(57, 54, 54, 0.45)");
        $(".show-pswd-icon .st1").css("fill", "rgba(57, 54, 54, 0.45)");
        $(".show-pswd-icon .st1").css("stroke", "rgba(57, 54, 54, 0.45)");
        passwordInput = false;
    } else {
        $("#password").attr("type", "text");
        $(".show-pswd-icon .st0").css("stroke", "#393636");
        $(".show-pswd-icon .st1").css("fill", "#393636");
        $(".show-pswd-icon .st1").css("stroke", "#393636");
        passwordInput = true;
    }
});

// Forgot password


// Auto-resize of feedback message container

$(".feedback-container").keyup(function () {
    $(this).css("height", "auto");
    let scHeight = $(this).prop('scrollHeight');
    $(this).css("height", `${scHeight}px`);
});