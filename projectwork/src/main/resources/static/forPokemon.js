//Configurazione degli swiper
const configurazioneSwiper= {
    centeredSlides: true,
    loop: true,
    slidesPerView: 6,
    spaceBetween: 30,
    // autoplay: {
    //     delay: 3000,
    //     disableOnInteraction: false,
    // },
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
        320: {
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

function initializeSwiper3() {
    return new Swiper(`.mySwiper3`, configurazioneSwiper);
}

// initializeSwiper();
// initializeSwiper2();

console.log("ciao");









const gadget= document.querySelector('.gadget');
const swiperNovita= document.querySelector('.swiper-novita');
//const swiperPrevendita= document.querySelector('.swiper-prevendita');
const swiperSpeciale= document.querySelector('.swiper-speciale');
let brandSelezionato= '';


//NOVITA
//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Novità
async function getCardNovita(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/card/brand/pokemon-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperNovita.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper2();
}




//chiamata fetch per recuperare le box con categoria novità da inserire nello swiper Novità
async function getBoxNovita(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/box/brand/pokemon-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperNovita.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper2();
}




//chiamata fetch per recuperare le bustine con categoria novità da inserire nello swiper Novità
async function getBustineNovita(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/bustina/brand/pokemon-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperNovita.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper2();
}













/*******************************************************************************************************************************************************************/

//SPECIALE
//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Speciale
async function getCardSpeciale(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/card/brand/pokemon-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperSpeciale.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper();
}




//chiamata fetch per recuperare le box con categoria novità da inserire nello swiper Speciale
async function getBoxSpeciale(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/box/brand/pokemon-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperSpeciale.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper();
}



//chiamata fetch per recuperare le bustine con categoria novità da inserire nello swiper Speciale
async function getBustineSpeciale(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/bustina/brand/pokemon-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		swiperSpeciale.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper();
}










/***************************************************************************************************************************/

//ACCESSORI
//chiamata fetch per recuperare gli accessori (action-figure e gadget) da inserire nello swiper Gadget
async function getAccessoriActionFigure(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/action-figure');

	sollevamentoEccezione(response)

    const data= await response.json();

    console.log(data);
	
	for(let i=0; i<data.length; i++){
		
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
			
			`;
		
		gadget.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper3();
}





async function getAccessoriGadget(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/pokemon/gadget');

	sollevamentoEccezione(response);

    const data= await response.json();

    console.log(data);
	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/pokemon/${data[i].immagine}></a>
					<p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
					<h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
					<p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
					<div><button type="button" id="${data[i].id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button></div>
				</div>
			</div>
				
			`;
		
		gadget.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper3();
}






//per quando si clicca sul Brand Pokemon, mostra card, accessori, .. riferiti a questo brand
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
	
	getCardNovita();
	getBoxNovita();
	getBustineNovita();
	
	getCardSpeciale();
	getBoxSpeciale();
	getBustineSpeciale();


	getAccessoriActionFigure();
	getAccessoriGadget();
	

	//prendo il brand che viene mostrato all'apertura della pagina web
	brandSelezionato= localStorage.setItem('brandSelect', switchBrand.id);
}

aperturaHome();


  


function sollevamentoEccezione(response){
	if(response.status != 200){
		let placeholder=
			`
			<div class="swiper-slide mb-5">
				<div class="card" aria-hidden="true">
					<div style="width: 100%; height: 200px; background-color: #868E96;"></div>
					<div class="card-body">
						<h5 class="card-title placeholder-glow">
						<span class="placeholder col-6"></span>
						</h5>
						<p class="card-text placeholder-glow">
						<span class="placeholder col-7"></span>
						<span class="placeholder col-4"></span>
						<span class="placeholder col-4"></span>
						<span class="placeholder col-6"></span>
						<span class="placeholder col-8"></span>
						</p>
						<a class="btn btn-primary disabled placeholder col-6 mb-5" aria-disabled="true"></a>
					</div>
				</div>
        	</div>
			`;	
		
		swiperNovita.innerHTML+= placeholder;
		throw new Error("errore nel caricamento delle card");
	}
};



