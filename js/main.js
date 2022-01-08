
$(".set-icon").click(function() {
  if ( $( ".settings" ).is( ":hidden" ) ) {
    $(".settings").slideDown(100);
    $(".settings").css("display", "flex");
    $("body").css("background-color", "rgba(0,0,0,0.6)");
    $("body").css("z-index", 1000);
    $(".main-nav").css("pointer-events", "none");
    $(".content-window").css("pointer-events", "none");
  } else {
    $(".settings").slideUp(200);
    $("body").css("background-color", "white");
    $(".main-nav").css("pointer-events", "");
    $(".content-window").css("pointer-events", "");
  }
});

window.onclick = function(event) {
  if (!event.target.matches(".set-icon", ".settings", ".settings a") && !event.target.matches(".settings") && !event.target.matches(".settings a")) {
      $(".settings").slideUp(200);
      $("body").css("background-color", "white");
      $(".main-nav").css("pointer-events", "");
      $(".content-window").css("pointer-events", "");
  }
};

function mQuery() {
    if (window.matchMedia("(min-width: 1024px)").matches) {
        $(".settings").slideUp(1);
        $("body").css("background-color", "white");
        $(".main-nav").css("pointer-events", "");
        $(".content-window").css("pointer-events", "");
    }
};

mQuery();
window.matchMedia("(min-width: 1024px)").addListener(mQuery);


/* function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}


window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
} */


/* function myFunction() {
  document.querySelector(".settings").classList.toggle("show-settings");
  let openedDropdown =
  if () {

  }
}

window.onclick = function(event) {
  if (!event.target.matches('.set-icon')) {
    let dropdowns = document.getElementsByClassName("settings");
    let i;
    for (i = 0; i < dropdowns.length; i++) {
      let openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show-settings')) {
        openDropdown.classList.remove('show-settings');
      }
    }
  }
}
*/

/* let toggleNavStatus = false;

let toggleNav = function() {
    let getSidebar = document.querySelector(".nav-sidebar");
    let getSidebarUl = document.querySelector(".nav-sidebar ul");
    let getSidebarTitle = document.querySelector(".nav-sidebar span");
    let getSidebarLinks = document.querySelectorAll(".nav-sidebar a");

    if (toggleNavStatus === false) {
        getSidebarUl.style.visibility = "visible";
        getSidebar.style.width = "290px";
        getSidebarTitle.style.opacity = "0.5";
        document.body.style.backgroundColor = "rgba(0,0,0,0.4)";

        let arrayLength = getSidebarLinks.length;
        for (let i = 0; i < arrayLength; i++) {
            getSidebarLinks[i].style.opacity = "1";
        }

        toggleNavStatus = true;
    }

    else if (toggleNavStatus === true) {
        getSidebar.style.width = "60px";
        getSidebarTitle.style.opacity = "0";
        document.body.style.backgroundColor = "white";

        let arrayLength = getSidebarLinks.length;
        for (let i = 0; i < arrayLength; i++) {
            getSidebarLinks[i].style.opacity = "0";
        }

        getSidebarUl.style.visibility = "hidden";

        toggleNavStatus = false;
    }
} */
