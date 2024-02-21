window.addEventListener("DOMContentLoaded", function(){
    const swiper = new Swiper(".mySwiper", {
        loop:true,
            navigation: {
              nextEl: ".mySwiper .next",
              prevEl: ".mySwiper .prev",
            },
      pagination: {
        el: ".swiper-pagination",
      },
    });

});