//Configurazione degli swiper
// const confSwiper= {
//     centeredSlides: true,
//     loop: true,
//     slidesPerView: 6,
//     spaceBetween: 30,
//     autoplay: {
//         delay: 3000,
//         disableOnInteraction: false,
//     },
//     pagination: {
//         el: ".swiper-pagination",
//         clickable: true,
//     },
//     navigation: {
//         nextEl: ".swiper-button-next",
//         prevEl: ".swiper-button-prev",
//     },
//     breakpoints: {
//         // Quando la larghezza della finestra è >= 768px
//         768: {
//         slidesPerView: 3, // Aumenta il numero di immagini visibili su tablet
//         spaceBetween: 20,
//     },
//     // Quando la larghezza della finestra è >= 1024px
//         1024: {
//         slidesPerView: 6, // Aumenta il numero di immagini visibili su desktop
//         spaceBetween: 30,
//         }
//     },
// }

// function initializeSwiper() {
//     return new Swiper(`.mySwiper`, confSwiper);
// }

// function initializeSwiper2() {
//     return new Swiper(`.mySwiper2`, confSwiper);
// }

// function initializeSwiper3() {
//     return new Swiper(`.mySwiper3`, confSwiper);
// }

// initializeSwiper();
// initializeSwiper2();

console.log("ciao ciao");









// const gadget2= document.querySelector('.gadget');
// const swiperNovita2= document.querySelector('.swiper-novita');
// //const swiperPrevendita= document.querySelector('.swiper-prevendita');
// const swiperSpeciale2= document.querySelector('.swiper-speciale');
// let brandSelezionato2= '';


//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Novità
async function getCardNovita2(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/card/brand/yugiho-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<!-- <img src=img/${data[i].immagine}> -->
					<img src=${data[i].immagine}>
					<p class="product-category">${data[i].categoria}</p>
					<h3 class="product-name">${data[i].nome}</h3>
					<p class="product-price">${data[i].prezzo}</p>
					<button type="button" class="btn btn-primary order-button">Ordina</button>
				</div>
			</div>
				
			`;
		
		swiperNovita.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper2();
}

//getCardNovita();









//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Speciale
async function getCardSpeciale2(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/card/brand/yugiho-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
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
		
		swiperSpeciale.innerHTML += card;
	}

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper();
}

//getCardSpeciale();











//chiamata fetch per recuperare gli accessori (action-figure e gadget) da inserire nello swiper Gadget
async function getAccessoriActionFigure2(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/yugiho/action-figure');

	sollevamentoEccezione(response)

    const data= await response.json();

    console.log(data);
	
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

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper3();
}

//getAccessoriActionFigure();



async function getAccessoriGadget2(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/yugiho/gadget');

	sollevamentoEccezione(response);

    const data= await response.json();

    console.log(data);
	
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

	//reinizializzo gli swiper per farli ripartire dalla prima slide
	initializeSwiper3();
}

//getAccessoriGadget();




//per quando si clicca sul Brand Pokemon, mostra card, accessori, .. riferiti a questo brand
const switchBrand2= document.querySelector('.logo-icon-puzzle');
console.log(switchBrand2);

switchBrand2.addEventListener('click', () => {
	
	console.log("WWWWWWWWWWWWWWWWWWWWWWW");
	
	brandSelezionato= localStorage.getItem('brandSelect');
	
	if(brandSelezionato !== switchBrand2.id){
		brandSelezionato= localStorage.setItem('brandSelect', switchBrand2.id);
		aperturaHome();	
	}
	else{
		console.log("Sto già nel brand",switchBrand2.id);
	}
});


  


// function sollevamentoEccezione(response){
// 	if(response.status != 200){
// 		let placeholder=
// 			`
// 			<div class="swiper-slide mb-5">
// 				<div class="card" aria-hidden="true">
// 					<div style="width: 100%; height: 200px; background-color: #868E96;"></div>
// 					<div class="card-body">
// 						<h5 class="card-title placeholder-glow">
// 						<span class="placeholder col-6"></span>
// 						</h5>
// 						<p class="card-text placeholder-glow">
// 						<span class="placeholder col-7"></span>
// 						<span class="placeholder col-4"></span>
// 						<span class="placeholder col-4"></span>
// 						<span class="placeholder col-6"></span>
// 						<span class="placeholder col-8"></span>
// 						</p>
// 						<a class="btn btn-primary disabled placeholder col-6" aria-disabled="true"></a>
// 					</div>
// 				</div>
//         	</div>
// 			`;	
		
// 		swiperNovita.innerHTML+= placeholder;
// 		throw new Error("errore nel caricamento delle card");
// 	}
// };



