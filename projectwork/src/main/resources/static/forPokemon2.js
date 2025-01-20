// Funzione per creare una card di prodotto con dimensioni uniformi
function createProductCard(prodotto) {
    const prezzoOriginale = prodotto.prezzo.toFixed(2);
    const prezzoScontato = prodotto.prezzoScontato && prodotto.prezzoScontato > 0 ? prodotto.prezzoScontato.toFixed(2) : null;

    let mostraPrezzo = `<p class="product-price">€${prezzoOriginale}</p>`;
    if (prodotto.categoria === "SPECIALE" || prodotto.categoria === "PREVENDITA") {
        mostraPrezzo = `
            <p class="product-price">
                <span class="original-price">€${prezzoOriginale}</span>
                <span class="discount-price">€${prezzoScontato}</span>
            </p>`;
    }

    const sticker = (prodotto.categoria === 'PREVENDITA' || prodotto.categoria === 'SPECIALE') ? `
        <div class="sticker animate__animated animate__heartBeat">
            Offerta Speciale
        </div>
    ` : '';

    return `
        <div class="swiper-slide">
            <div class="product-card">
                ${sticker}
                <div class="product-image-wrapper">
                    <a href="/DettagiProdotto.html?id=${prodotto.id}">
                        <img src="img/pokemon/${prodotto.immagine}" 
                             alt="${prodotto.nome}" 
                             class="img-fluid">
                    </a>
                </div>
                <div class="product-info">
                    <h3 class="h5 product-title text-truncate">${prodotto.nome}</h3>
                    ${mostraPrezzo}
                    <div>
                        <button type="button" id="${prodotto.id}" class="btn btn-carrello">
                            Aggiungi al carrello
                        </button>
                    </div>
                </div>
            </div>
        </div>`;
}

// Configurazione Swiper
const swiperConfig = {
    slidesPerView: 2,
    spaceBetween: 20,
    loop: true, // Abilita il loop infinito
    loopFillGroupWithBlank: true,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true
    },
    breakpoints: {
        480: {
            slidesPerView: 2,
            spaceBetween: 20
        },
        768: {
            slidesPerView: 3,
            spaceBetween: 30
        },
        1024: {
            slidesPerView: 4,
            spaceBetween: 30
        },
        1200: {
            slidesPerView: 5,
            spaceBetween: 30
        }
    }
};

// Creazione istanze Swiper
const swiper1 = new Swiper('.mySwiper', swiperConfig);
const swiper2 = new Swiper('.mySwiper2', swiperConfig);
const swiper3 = new Swiper('.mySwiper3', swiperConfig);
const swiper4 = new Swiper('.mySwiper4', swiperConfig);

// Funzioni per popolare i caroselli
async function loadPokemonNovita() {
    try {
        const response = await fetch('/api/prodotto/brand-categoria?brand=POKEMON&categoria=NOVITA');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper2 .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper2.update();
    } catch (error) {
        console.error('Errore nel caricamento dei prodotti Novità:', error);
    }
}

async function loadPokemonSpeciali() {
    try {
        const response = await fetch('/api/prodotto/brand-categoria?brand=POKEMON&categoria=SPECIALE');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper1.update();
    } catch (error) {
        console.error('Errore nel caricamento dei prodotti Speciali:', error);
    }
}

async function loadTopSellingPokemon() {
    try {
        const response = await fetch('/api/prodotto/top-venduti?brand=POKEMON');
        if (!response.ok) {
            throw new Error(`Errore nella risposta: ${response.status}`);
        }
        const pokemonProducts = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper4 .swiper-wrapper');

        swiperWrapper.innerHTML = pokemonProducts.map(createProductCard).join('');
        swiper4.update();
    } catch (error) {
        console.error('Errore nel caricamento dei prodotti più venduti:', error);
    }
}

async function loadPokemonAccessori() {
    try {
        const response = await fetch('/api/prodotto/brand-tipoCategoria-tipi?brand=POKEMON&tipoCategoria=ACCESSORIO&tipi=ACTION_FIGURE,GADGET');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper3 .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper3.update();
    } catch (error) {
        console.error('Errore nel caricamento degli accessori:', error);
    }
}

// Evento per cambio brand
const switchBrand= document.querySelector('.logo-icon-pokeball');

switchBrand.addEventListener('click', () => {
	
	brandSelezionato= localStorage.getItem('brandSelect');
	
	if(brandSelezionato !== switchBrand.id){
		brandSelezionato= localStorage.setItem('brandSelect', switchBrand.id);
		aperturaHome();	
	}
	else{
		console.log("Sto già nel brand",switchBrand.id);
	}
});


//per quando apro la home
function aperturaHome(){
    loadTopSellingPokemon();
    loadPokemonNovita();
    loadPokemonSpeciali();
    loadPokemonAccessori();
    brandSelezionato= localStorage.setItem('brandSelect', switchBrand.id);
}

aperturaHome();
