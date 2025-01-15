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
    return new Swiper(`.mySwiper`, confSwiper);
}









const adminLoggato= document.querySelector('.admin-loggato-nome');
const adminLoggatoEmail= document.querySelector('.admin-loggato-email');
// const cardsAdmin= document.querySelector('.cards-admin');
// const bustineAdmin= document.querySelector('.bustine-admin');
// const boxAdmin= document.querySelector('.box-admin');
// const accessoriAdmin= document.querySelector('.accessori-admin');
const slide= document.querySelector('.swiper-wrapper');

let datiAdmin= sessionStorage.getItem("user");
let oggettoDatiAdmin= JSON.parse(datiAdmin);
console.log(oggettoDatiAdmin);



function inserDatiAdmin(){

    if(oggettoDatiAdmin != null){
        let admin=
            `
                <p>${oggettoDatiAdmin.nome}</p>
                <p>${oggettoDatiAdmin.email}</p>
            `;
        
        adminLoggato.textContent= oggettoDatiAdmin.nome;
        adminLoggatoEmail.textContent= oggettoDatiAdmin.email;
    }
    else{
        adminLoggato.textContent= "Login";
    }
}



// async function inserCards(){

//     let email= oggettoDatiAdmin.email;
//     let response= await fetch(`/api/admin/${email}/cards`);
//     let data= await response.json();

//     console.log(data);

// }



inserDatiAdmin();
//inserCards();














let cont_card= 0;
let numeroSlide= '';
let selNumeroSlide= '';
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

        //console.log(cont_card);
    }  

    inizializzazioneSwiperAdmin();
}






async function loadData() {
    try {

        const baseUrl = 'http://localhost:8080/api';
        let email= oggettoDatiAdmin.email;

        try {
            const cardResponse = await fetch(`${baseUrl}/admin/${email}/cards`);
            if (!cardResponse.ok) {
                throw new Error(`Errore nel recupero delle carte: ${cardResponse.statusText}`);
            }
            const cardData = await cardResponse.json();
            console.log('Card Data:', cardData);
            creazioneEinserimentoProdotti(cardData);
        } catch (error) {
            console.error('Errore carte:', error);
        }

        try {
            const bustineResponse = await fetch(`${baseUrl}/admin/${email}/bustine`);
            if (!bustineResponse.ok) {
                throw new Error(`Errore nel recupero delle bustine: ${bustineResponse.statusText}`);
            }
            const bustineData = await bustineResponse.json();
            console.log('Bustine Data:', bustineData);
            creazioneEinserimentoProdotti(bustineData);
        } catch (error) {
            console.error('Errore bustine:', error);
        }

        try {
            const boxResponse = await fetch(`${baseUrl}/admin/${email}/box`);
            if (!boxResponse.ok) {
                throw new Error(`Errore nel recupero dei box: ${boxResponse.statusText}`);
            }
            const boxData = await boxResponse.json();
            console.log('Box Data:', boxData);
            creazioneEinserimentoProdotti(boxData);
        } catch (error) {
            console.error('Errore box:', error);
        }

        try {
            const accessoriResponse = await fetch(`${baseUrl}/admin/${email}/accessori`);
            if (!accessoriResponse.ok) {
                throw new Error(`Errore nel recupero degli accessori: ${accessoriResponse.statusText}`);
            }
            const accessoriData = await accessoriResponse.json();
            console.log('Accessori Data:', accessoriData);
            creazioneEinserimentoProdotti(accessoriData);
        } catch (error) {
            console.error('Errore accessori:', error);
        }

    } catch (error) {
        console.error('Errore generale durante il caricamento dei dati:', error);
    }
}

document.addEventListener('DOMContentLoaded', loadData);






// Elementi della pagina
// const adminLoggato = document.querySelector('.admin-loggato-nome');
// const adminLoggatoEmail = document.querySelector('.admin-loggato-email');
// const cardsAdmin = document.querySelector('.cards-admin');
// const bustineAdmin = document.querySelector('.bustine-admin');
// const boxAdmin = document.querySelector('.box-admin');
// const accessoriAdmin = document.querySelector('.accessori-admin');

// let datiAdmin = sessionStorage.getItem("user");
// let oggettoDatiAdmin = JSON.parse(datiAdmin);

// // Variabili per la paginazione
// let currentPage = 1;
// const itemsPerPage = 5;

// function inserDatiAdmin() {
//     if (oggettoDatiAdmin != null) {
//         adminLoggato.textContent = oggettoDatiAdmin.nome;
//         adminLoggatoEmail.textContent = oggettoDatiAdmin.email;
//     } else {
//         adminLoggato.textContent = "Login";
//     }
// }

// inserDatiAdmin();

// function creazioneEinserimentoProdotti(data, classeHtml) {
//     classeHtml.innerHTML = ''; // Pulisce il contenuto corrente

//     // Calcola l'intervallo degli elementi da mostrare
//     const startIndex = (currentPage - 1) * itemsPerPage;
//     const endIndex = startIndex + itemsPerPage;

//     // Itera sugli elementi della pagina corrente
//     const paginatedItems = data.slice(startIndex, endIndex);
//     for (let i = 0; i < paginatedItems.length; i++) {
//         let card = `
//             <div class="product-card col-3 text-center">
//                 <div>
//                     <a href="/DettagiProdotto.html?id=${paginatedItems[i].id}"><img src=img/${paginatedItems[i].immagine} style="max-width: 100%;"></a>
//                 </div>
//                 <p class="product-category" style="margin-bottom: 0.3rem;">${paginatedItems[i].categoria}</p>
//                 <h3 class="product-name" style="margin-bottom: 0.3rem;">${paginatedItems[i].nome}</h3>
//                 <p class="product-price" style="margin-bottom: 0.3rem;">${paginatedItems[i].prezzo}</p>
//                 <button type="button" class="btn btn-primary order-button mb-5">Ordina</button>
//             </div>
//         `;
//         classeHtml.innerHTML += card;
//     }
// }

// function aggiungiEventiNavigazione(data, target) {
//     const btnPrev = document.querySelector('.btn-prev'); // Pulsante precedente
//     const btnNext = document.querySelector('.btn-next'); // Pulsante successivo

//     btnPrev?.addEventListener('click', () => {
//         if (currentPage > 1) {
//             currentPage--;
//             creazioneEinserimentoProdotti(data, target);
//         }
//     });

//     btnNext?.addEventListener('click', () => {
//         if (currentPage < Math.ceil(data.length / itemsPerPage)) {
//             currentPage++;
//             creazioneEinserimentoProdotti(data, target);
//         }
//     });
// }

// async function loadData() {
//     try {
//         const baseUrl = 'http://localhost:8080/api';
//         let email = oggettoDatiAdmin?.email;

//         if (!email) {
//             throw new Error('Nessuna email trovata per l'admin loggato.');
//         }

//         const endpoints = [
//             { url: `${baseUrl}/admin/${email}/cards`, target: cardsAdmin },
//             { url: `${baseUrl}/admin/${email}/bustine`, target: bustineAdmin },
//             { url: `${baseUrl}/admin/${email}/box`, target: boxAdmin },
//             { url: `${baseUrl}/admin/${email}/accessori`, target: accessoriAdmin }
//         ];

//         for (let endpoint of endpoints) {
//             const response = await fetch(endpoint.url);
//             if (!response.ok) {
//                 throw new Error(`Errore nel recupero dati: ${response.statusText}`);
//             }
//             const data = await response.json();
//             creazioneEinserimentoProdotti(data, endpoint.target);
//             aggiungiEventiNavigazione(data, endpoint.target);
//         }
//     } catch (error) {
//         console.error('Errore generale durante il caricamento dei dati:', error);
//     }
// }

// document.addEventListener('DOMContentLoaded', loadData);
