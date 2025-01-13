
//Configurazione degli swiper
const configurazioneSwiper= {
    centeredSlides: true,
    loop: true,
    slidesPerView: 6,
    spaceBetween: 30,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    breakpoints: {
        // Quando la larghezza della finestra è >= 768px
        768: {
        slidesPerView: 3, // Aumenta il numero di immagini visibili su tablet
        spaceBetween: 20,
    },
    // Quando la larghezza della finestra è >= 1024px
        1024: {
        slidesPerView: 6, // Aumenta il numero di immagini visibili su desktop
        spaceBetween: 30,
        }
    },
}

function initializeSwiper() {
    return new Swiper(`.mySwiper`, configurazioneSwiper);
}

function initializeSwiper2() {
    return new Swiper(`.mySwiper2`, configurazioneSwiper);
}

initializeSwiper();
initializeSwiper2();




//chiamata fetch per recuperare gli accessori (action-figure e gadget)
async function getAccessoriActionFigure(){

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/action-figure');
    const data= await response.json();

    console.log(data);
}

getAccessoriActionFigure();



async function getAccessoriGadget(){

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/gadget');
    const data= await response.json();

    console.log(data);
}

getAccessoriGadget();









