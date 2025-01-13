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

function getUserDataFromSessionStorage() {
    const userData = sessionStorage.getItem('user');
    if (userData) {
        try {
            return JSON.parse(userData);
        } catch (e) {
            alert("Errore nel parsing dei dati dell'utente.");
            console.error("Errore nel parsing:", e);
        }
    }
    return null;
}

function checkLoginStatus() {
    const userData = getUserDataFromSessionStorage();

    if (!userData) {
        console.log("Utente non autenticato o dati non trovati nel sessionStorage");
        window.location.href = '/login';
        return;
    }

    console.log('Dati dell\'utente:', userData);

    if (!userData.email || !userData.ruolo1) {
        console.log("Dati mancanti nell'utente: email o ruolo");
        window.location.href = '/login';
        return;
    }

    console.log("Accesso consentito. Utente autenticato.");
}

async function loadData() {
    try {

        const baseUrl = 'http://localhost:8080/api';

        try {
            const cardResponse = await fetch(`${baseUrl}/card/brand/pokemon`);
            if (!cardResponse.ok) {
                throw new Error(`Errore nel recupero delle carte: ${cardResponse.statusText}`);
            }
            const cardData = await cardResponse.json();
            console.log('Card Data:', cardData);
            createCarousel('card-container', cardData);
        } catch (error) {
            console.error('Errore carte:', error);
        }

        try {
            const bustineResponse = await fetch(`${baseUrl}/bustina/brand/pokemon`);
            if (!bustineResponse.ok) {
                throw new Error(`Errore nel recupero delle bustine: ${bustineResponse.statusText}`);
            }
            const bustineData = await bustineResponse.json();
            console.log('Bustine Data:', bustineData);
            createCarousel('bustine-container', bustineData);
        } catch (error) {
            console.error('Errore bustine:', error);
        }

        try {
            const boxResponse = await fetch(`${baseUrl}/box/brand/pokemon`);
            if (!boxResponse.ok) {
                throw new Error(`Errore nel recupero dei box: ${boxResponse.statusText}`);
            }
            const boxData = await boxResponse.json();
            console.log('Box Data:', boxData);
            createCarousel('box-container', boxData);
        } catch (error) {
            console.error('Errore box:', error);
        }

        try {
            const accessoriResponse = await fetch(`${baseUrl}/accessori/brand/pokemon`);
            if (!accessoriResponse.ok) {
                throw new Error(`Errore nel recupero degli accessori: ${accessoriResponse.statusText}`);
            }
            const accessoriData = await accessoriResponse.json();
            console.log('Accessori Data:', accessoriData);
            createCarousel('accessori-container', accessoriData);
        } catch (error) {
            console.error('Errore accessori:', error);
        }

    } catch (error) {
        console.error('Errore generale durante il caricamento dei dati:', error);
    }
}

function createCarousel(containerId, data) {
    const container = document.getElementById(containerId);
    if (!container) {
        console.error(`Container ${containerId} non trovato`);
        return;
    }
    
    container.innerHTML = '';
 
    if (!Array.isArray(data) || data.length === 0) {
        const emptySlide = document.createElement('div');
        emptySlide.classList.add('swiper-slide');
        emptySlide.textContent = 'Nessun elemento disponibile';
        container.appendChild(emptySlide);
		console.log("DENTRO");
        return;
    }
	
	console.log(container.children);
	console.log(data);
	 
    data.forEach(item => {
        const slide = document.createElement('div');
        slide.classList.add('swiper-slide');
 
        const card = document.createElement('div');
        card.classList.add('product-card');
        
        // Immagine con percorso corretto
        const img = document.createElement('img');
        img.src = `img/pokemon/${item.immagine}`;
        img.alt = item.nome;
        img.onerror = () => {
            img.src = 'img/placeholder.jpg';
        };
 

        const category = document.createElement('p');
        category.classList.add('product-category');
        category.textContent = item.categoria;
 

        const name = document.createElement('h3');
        name.classList.add('product-name');
        name.textContent = item.nome;
 

        const price = document.createElement('p');
        price.classList.add('product-price');
        price.textContent = `€ ${item.prezzo.toFixed(2)}`;
 

        const button = document.createElement('button');
        button.classList.add('order-button');
        button.textContent = 'Ordina';
        button.onclick = () => handleOrder(item);

        card.appendChild(img);
        card.appendChild(category);
        card.appendChild(name);
        card.appendChild(price);
        card.appendChild(button);
        slide.appendChild(card);
        container.appendChild(slide);
    });
	
	initializeSwiper();
}

document.addEventListener('DOMContentLoaded', loadData);
