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
                        <img src="img/magic/${prodotto.immagine}" 
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

// Inizializzazione degli Swiper
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
    // autoplay: {
    //     delay: 3000,  // Autoplay opzionale, ogni 3 secondi
    //     disableOnInteraction: false
    // },
    breakpoints: {
        // quando la viewport è >= 480px
        480: {
            slidesPerView: 2,
            spaceBetween: 20
        },
        // quando la viewport è >= 768px
        768: {
            slidesPerView: 3,
            spaceBetween: 30
        },
        // quando la viewport è >= 1024px
        1024: {
            slidesPerView: 4,
            spaceBetween: 30
        },
        // quando la viewport è >= 1200px
        1200: {
            slidesPerView: 5,
            spaceBetween: 30
        }
    }
};

const swiper1 = new Swiper('.mySwiper', swiperConfig);
const swiper2 = new Swiper('.mySwiper2', swiperConfig);
const swiper3 = new Swiper('.mySwiper3', swiperConfig);
const swiper4 = new Swiper('.mySwiper4', swiperConfig);

// Funzioni per popolare i caroselli
async function loadMagicNovita() {
    try {
        const response = await fetch('/api/prodotto/brand-categoria?brand=MAGIC&categoria=NOVITA');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper2 .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper1.update();
    } catch (error) {
        console.error('Errore nel caricamento dei prodotti Novità:', error);
    }
}

async function loadMagicSpeciali() {
    try {
        const response = await fetch('/api/prodotto/brand-categoria?brand=MAGIC&categoria=SPECIALE');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper2.update();
    } catch (error) {
        console.error('Errore nel caricamento dei prodotti Speciali:', error);
    }
}

async function loadTopSellingMagic() {
    try {
        const response = await fetch('/api/prodotto/top-venduti?brand=MAGIC');
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

async function loadMagicAccessori() {
    try {
        const response = await fetch('/api/prodotto/brand-tipoCategoria-tipi?brand=MAGIC&tipoCategoria=ACCESSORIO&tipi=ACTION_FIGURE,GADGET');
        const products = await response.json();
        const swiperWrapper = document.querySelector('.mySwiper3 .swiper-wrapper');
        swiperWrapper.innerHTML = products.map(createProductCard).join('');
        swiper4.update();
    } catch (error) {
        console.error('Errore nel caricamento degli accessori:', error);
    }
}

// Stili CSS aggiuntivi
// const styles = `
//     <style>
//         .product-card {
//             border: 1px solid #ddd;
//             border-radius: 8px;
//             box-shadow: 0 2px 4px rgba(0,0,0,0.1);
//         }
        
//         .sticker {
//             position: absolute;
//             top: 10px;
//             right: 10px;
//             background: red;
//             color: white;
//             padding: 5px 10px;
//             border-radius: 5px;
//             z-index: 1;
//         }
        
//         .product-info {
//             display: flex;
//             flex-direction: column;
//             height: calc(100% - 200px);
//         }
        
//         .product-title {
//             margin-bottom: 0.5rem;
//             overflow: hidden;
//             display: -webkit-box;
//             -webkit-line-clamp: 2;
//             -webkit-box-orient: vertical;
//         }
//     </style>
// `;

// // Aggiunta degli stili al documento
// document.head.insertAdjacentHTML('beforeend', styles);

// Inizializzazione al caricamento della pagina
const switchBrand3= document.querySelector('.logo-icon-magic');
console.log(switchBrand3);

switchBrand3.addEventListener('click', () => {
	
	brandSelezionato= localStorage.getItem('brandSelect');
	
	if(brandSelezionato !== switchBrand3.id){
		brandSelezionato= localStorage.setItem('brandSelect', switchBrand3.id);
		aperturaBrandMagic();	
	}
	else{
		console.log("Sto già nel brand",switchBrand3.id);
	}
});




function aperturaBrandMagic(){
    loadMagicNovita();
    loadMagicSpeciali();
    loadTopSellingMagic();
    loadMagicAccessori();

}
