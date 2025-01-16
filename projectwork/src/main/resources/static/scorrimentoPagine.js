//inizializzazione swiper
const confSwiper= {
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
        renderBullet: function (index, className) {
          return '<span class="' + className + '">' + (index + 1) + "</span>";
        },
    }
};


function inizializzazioneSwiperAdmin(){
    return new Swiper(`.swiperScorrimentoProdotti`, confSwiper);
}


const numeroPagine= document.querySelector('.numero-pagine');
const numP= document.querySelector('.num-p');
const frecciaSinistra= document.querySelector('.bi-caret-left');
const frecciaDestra= document.querySelector('.bi-caret-right');

console.log(numP.children.length);
let dim= localStorage.setItem('dim', numP.children.length);








let cont_card= 0;
let numeroSlide= '';
let selNumeroSlide= '';
const slide= document.querySelector('.sw-prodotti');

function creazioneEinserimentoProdotti(data){

    for(let i=0; i<data.length; i++){
        let card= 
            `
            <div class="product-card col-3 text-center">
                <div>
                    <a href="/DettagiProdotto.html?id=${data[i].id}"><img src=img/${data[i].immagine} style="max-width: 100%;"></a>
                </div>
                <p class="product-category" style="margin-bottom: 0.3rem;">${data[i].categoria}</p>
                <h3 class="product-name" style="margin-bottom: 0.3rem;">${data[i].nome}</h3>
                <p class="product-price" style="margin-bottom: 0.3rem;">${data[i].prezzo}</p>
                <button type="button" class="btn btn-primary order-button mb-5">Ordina</button>
            </div>
            `;
        
        //slide.innerHTML += card; 
        cont_card++;

        if(cont_card > 10){
            cont_card= 1;
        }
        
        if(cont_card == 1){
            numeroSlide= data[i].nome;

            let contenitore= 
                `
                <div class="swiper-slide">
                    <div class="container" style="margin-top: 5rem;">
                        <div class="row ${numeroSlide}">
                        </div>
                    </div>
                </div>
                `;
            
            slide.innerHTML += contenitore;

            let s= '.'+numeroSlide;
            //console.log(s);    
            selNumeroSlide= document.querySelector(s);
            //console.log(selNumeroSlide);

            selNumeroSlide.innerHTML += card;
        }
        else if(cont_card > 1 && cont_card < 11){
            selNumeroSlide.innerHTML += card;
        }

        console.log(cont_card);
    }  

    inizializzazioneSwiperAdmin();
    inserimentoFrecce();
}





function inserimentoFrecce(){
    //console.log(cc.offsetWidth);
    
    let getDim= localStorage.getItem('dim');

    if(getDim != numP.children.length){
        let ms= frecciaSinistra.style.marginRight;
        let md= frecciaDestra.style.marginLeft;
        
        let nuovo= ms + '1.5rem';
        frecciaSinistra.style.marginRight= nuovo;
        frecciaSinistra.style.marginBottom= '0.5rem';
        nuovo= md + '1.5rem'
        frecciaDestra.style.marginLeft= nuovo;
        frecciaDestra.style.marginBottom= '0.5rem';
        console.log(ms, md);
    }
    
    // for(let i=0; i<numeroPagine.children.length; i++){
    //     if(numeroPagine.children[i].className != 'bi bi-caret-left'){
    //         let frecciaSinistra= document.createElement('i');
    //         frecciaSinistra.classList.add('bi', 'bi-caret-left');
            
    //         let frecciaDestra= document.createElement('i');
    //         frecciaDestra.classList.add('bi', 'bi-caret-right');

    //         numeroPagine.prepend(frecciaSinistra);
    //         numeroPagine.append(frecciaDestra);
    //     }
    //     break;   
    // }
}






//click freccia sinistra
const fs= document.querySelector('.bi-caret-left');

fs.addEventListener('click', e => {

     e.preventDefault();

     console.log(e);
    
});