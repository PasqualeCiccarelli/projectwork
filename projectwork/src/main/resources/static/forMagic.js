console.log("ciao ciao ciao");


//NOVITA
//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Novità
async function getCardNovita3(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/card/brand/magic-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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
async function getBoxNovita3(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/box/brand/magic-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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
async function getBustineNovita3(){

	swiperNovita.innerHTML= '';

	let response= await fetch('/api/bustina/brand/magic-novita');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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








/****************************************************************************************************************************/

//SPECIALE
//chiamata fetch per recuperare le card con categoria novità da inserire nello swiper Speciale
async function getCardSpeciale3(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/card/brand/magic-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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
async function getBoxSpeciale3(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/box/brand/magic-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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
async function getBustineSpeciale3(){
	
	swiperSpeciale.innerHTML= '';

	let response= await fetch('/api/bustina/brand/magic-speciale');

	sollevamentoEccezione(response);

	let data= await response.json();
	
	console.log(data);

	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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











/*****************************************************************************************************************************/

//ACCESSORI
//chiamata fetch per recuperare gli accessori (action-figure e gadget) da inserire nello swiper Gadget
async function getAccessoriActionFigure3(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/magic/action-figure');

	sollevamentoEccezione(response)

    const data= await response.json();

    console.log(data);
	
	for(let i=0; i<data.length; i++){
		
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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





async function getAccessoriGadget3(){

	gadget.innerHTML = '';

    const response= await fetch('http://localhost:8080/api/accessori/brand/magic/gadget');

	sollevamentoEccezione(response);

    const data= await response.json();

    console.log(data);
	
	for(let i=0; i<data.length; i++){
			
		let card= 
			`
			<div class="swiper-slide">
				<div class="product-card">
					<a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/magic/${data[i].immagine}></a>
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

	getCardNovita3();
	getBoxNovita3();
	getBustineNovita3();

	getCardSpeciale3();
	getBoxSpeciale3();
	getBustineSpeciale3();

	getAccessoriActionFigure3();
	getAccessoriGadget3();
}


