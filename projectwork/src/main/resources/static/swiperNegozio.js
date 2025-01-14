const swiperConfig = {
    loop: false,
    slidesPerView: 3,
    spaceBetween: 30,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        dynamicBullets: true,
    },
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    breakpoints: {
        1024: {
            slidesPerView: 2,
            spaceBetween: 20,
        },
        768: {
            slidesPerView: 1,
            spaceBetween: 10,
        }
    },
    effect: 'fade',
    fadeEffect: {
        crossFade: true,
    },
    lazy: true,
    speed: 600,
    preloadImages: false,
    watchSlidesVisibility: true,
    watchSlidesProgress: true,
};

function initializeSwiper() {
    return new Swiper(`.mySwiper`, swiperConfig);
}