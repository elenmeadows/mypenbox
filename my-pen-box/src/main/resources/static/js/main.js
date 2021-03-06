
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
    let productId = $(this).attr("id").replace(/\D/g, "");
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
    let productId = parseInt($("#product-info").attr("class"));
    let totalItems = parseInt($("#total-items").text().replace(/\D/g, ""));
    if (productId <= totalItems && productId != 1) {
        console.log("first prev-btn");
        $.ajax({
            url:'/modal?productId=' + (productId - 1),
            success: function (data) {
                $('#product-info').load('/modal?productId=' + (productId - 1));
                productId -= 1;
                $('#product-info').removeAttr('class').addClass(productId.toString());
            }
        });
    } else if (productId == 1 || productId > totalItems) {
        console.log("first prev-btn");
        $.ajax({
            url:'/modal?productId=' + (productId),
            success: function (data) {
                $('#product-info').load('/modal?productId=' + (productId));
            }
        });
    }
    $("#overlay").show();
    $(".modal").show();
});

$(".next-button").click(function () {
    let productId = parseInt($("#product-info").attr("class"));
    let totalItems = parseInt($("#total-items").text().replace(/\D/g, ""));
    if (productId < totalItems && productId != totalItems) {
        console.log("first next-btn");
        $.ajax({
            url:'/modal?productId=' + (productId + 1),
            success: function (data) {
                $('#product-info').load('/modal?productId=' + (productId + 1));
                productId += 1;
                $('#product-info').removeAttr('class').addClass(productId.toString());
            }
        });
    } else if (productId == totalItems || productId > totalItems) {
        console.log("second next-btn");
        $.ajax({
            url:'/modal?productId=' + (productId),
            success: function (data) {
                $('#product-info').load('/modal?productId=' + (productId));
            }
        });
    }
    $("#overlay").show();
    $(".modal").show();
});

// Auto-resize of feedback message container

$(".feedback-container").keyup(function () {
    $(this).css("height", "auto");
    let scHeight = $(this).prop('scrollHeight');
    $(this).css("height", `${scHeight}px`);
});