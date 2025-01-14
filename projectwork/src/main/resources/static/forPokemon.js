
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




const gadget= document.querySelector('.gadget');
const novita= document.querySelector('.novita');



//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Novità
async function getCardNovita(){
	
	let response= await fetch('/api/card/brand/pokemon-novita');
	let data= await response.json();
	
	console.log(data);
	
	novita.innerHTML= '';
	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<img src=${data[i].immagine}>
					<p class="product-category">${data[i].categoria}</p>
					<h3 class="product-name">${data[i].nome}</h3>
					<p class="product-price">${data[i].prezzo}</p>
					<button type="button" class="btn btn-primary order-button">Ordina</button>
				</div>
			</div>
				
			`;
		
		novita.innerHTML += card;
	}
}

//getCardNovita();






//chiamata fetch per recuperare gli accessori (action-figure e gadget) da inserire nello swiper Gadget
async function getAccessoriActionFigure(){

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/action-figure');
    const data= await response.json();

    console.log(data);
	
	gadget.innerHTML = '';
	
	for(let i=0; i<data.length; i++){
		
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<img src=${data[i].immagine}>
					<p class="product-category">${data[i].categoria}</p>
					<h3 class="product-name">${data[i].nome}</h3>
					<p class="product-price">${data[i].prezzo}</p>
					<button type="button" class="btn btn-primary order-button">Ordina</button>
				</div>
			</div>
			
			`;
		
		gadget.innerHTML += card;
	}
}

//getAccessoriActionFigure();



async function getAccessoriGadget(){

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/gadget');
    const data= await response.json();

    console.log(data);
	
	gadget.innerHTML = '';
	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<img src=${data[i].immagine}>
					<p class="product-category">${data[i].categoria}</p>
					<h3 class="product-name">${data[i].nome}</h3>
					<p class="product-price">${data[i].prezzo}</p>
					<button type="button" class="btn btn-primary order-button">Ordina</button>
				</div>
			</div>
				
			`;
		
		gadget.innerHTML += card;
	}
}

//getAccessoriGadget();




//per quando si clicca sul Brand Pokemon, mostra card, accessori, .. riferiti a questo brand
const switchBrand= document.querySelector('.logo-icon-pokeball');

switchBrand.addEventListener('click', () => {
	
	getCardNovita();
	getAccessoriActionFigure();
	getAccessoriGadget();	
	
	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper();
	initializeSwiper2();
});


  




