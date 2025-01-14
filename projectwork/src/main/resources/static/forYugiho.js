
console.log("ciao ciao");



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
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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






//per quando si clicca sul Brand Pokemon, mostra card, accessori, .. riferiti a questo brand
const switchBrand2= document.querySelector('.logo-icon-puzzle');
console.log(switchBrand2);

switchBrand2.addEventListener('click', () => {
	
	brandSelezionato= localStorage.getItem('brandSelect');
	
	if(brandSelezionato !== switchBrand2.id){
		brandSelezionato= localStorage.setItem('brandSelect', switchBrand2.id);
		aperturaBrandYugiho();	
	}
	else{
		console.log("Sto già nel brand",switchBrand2.id);
	}
});




function aperturaBrandYugiho(){

	getCardNovita2();
	getCardSpeciale2();
	getAccessoriActionFigure2();
	getAccessoriGadget2();
}






