
console.log("ciao ciao");


//NOVITA
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
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/Yu-Gi-Oh/${data[i].immagine}></a>
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
async function getBoxNovita2(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/box/brand/yugiho-novita');

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
async function getBustineNovita2(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/bustina/brand/yugiho-novita');

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













/********************************************************************************************************************/

//SPECIALE
//chiamata fetch per recuperare le card con categoria speciale da inserire nello swiper Speciale
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
					<!-- <img src=img/${data[i].immagine}> -->
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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



//chiamata fetch per recuperare le box con categoria speciale da inserire nello swiper Speciale
async function getBoxSpeciale2(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/box/brand/yugiho-speciale');

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




//chiamata fetch per recuperare le bustine con categoria speciale da inserire nello swiper Speciale
async function getBustineSpeciale2(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/bustina/brand/yugiho-speciale');

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










/******************************************************************************************************************************/

//ACCESSORI
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
					<!-- <img src=img/${data[i].immagine}> -->
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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
					<!-- <img src=img/${data[i].immagine}> -->
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine}></a>
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
	getBoxNovita2();
	getBustineNovita2();

	getCardSpeciale2();
	getBoxSpeciale2();
	getBustineSpeciale2();

	getAccessoriActionFigure2();
	getAccessoriGadget2();
}

