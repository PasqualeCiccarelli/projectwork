
const adminLoggato= document.querySelector('.admin-loggato-nome');
const adminLoggatoEmail= document.querySelector('.admin-loggato-email');
// const cardsAdmin= document.querySelector('.cards-admin');
// const bustineAdmin= document.querySelector('.bustine-admin');
// const boxAdmin= document.querySelector('.box-admin');
// const accessoriAdmin= document.querySelector('.accessori-admin');

let datiAdmin= sessionStorage.getItem("user");
let oggettoDatiAdmin= JSON.parse(datiAdmin);
console.log(oggettoDatiAdmin);



function inserDatiAdmin(){

    if(oggettoDatiAdmin != null){        
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

    clickBottoni();
    
}

document.addEventListener('DOMContentLoaded', loadData);







//click sul bottone del prodotto per modificare la categoria
function clickBottoni(){
    const bottoni= document.querySelectorAll('.btn-carrello');
    
    for(let i=0; i<bottoni.length; i++){
        bottoni[i].addEventListener('click', e => {
            console.log(bottoni[i]);
            
            let figlio= e.target.parentElement.parentElement.children[2];
            let inserSelect= document.querySelectorAll('.inser-select');

            let categoria1= '';
            let categoria2= '';
            if(figlio.textContent == 'DEFAULT'){
                categoria1= 'SPECIALE'
            }
            else if(figlio.textContent == 'SPECIALE'){
                categoria1= 'DEFAULT'
            }
            else if(figlio.textContent == 'NOVITA'){
                categoria1= 'SPECIALE';
                categoria2= 'DEFAULT';
            }
            
            let select=
                `
                <select id="categoria" class="attivo" onchange="scelta()">
                    <option value=${figlio.textContent}>${figlio.textContent}</option>
                    <option value=${categoria1}>${categoria1}</option>
                    <option value=${categoria2}>${categoria2}</option>
                </select>
                `;
            
            let categoriaScelta= '';
            for(let j=0; j<inserSelect.length; j++){
                if(i == j){
                    inserSelect[j].innerHTML= select;
                    figlio.style.display= 'none';
                }
            }
            console.log(categoriaScelta);
        });
    };
};




//function chiamata dal tag select creato sopra
function scelta(){
    let x= document.querySelector('.attivo');
    let applicaModifiche= document.querySelector('.applica-modifiche');
    
    //seleziono la categoria scelta dall'utente
    //nascondo il tag select e rimostro il tag che contiene la categoria
    let fratello= x.parentNode.nextElementSibling;
    console.log(fratello);
    fratello.textContent= x.value;
    x.style.display= "none";
    fratello.style.display= "block";

    //mostro il bottone per applicare le modifiche
    let bottone= fratello.nextElementSibling.nextElementSibling.nextElementSibling.firstChild;
    applicaModifiche.style.display= "block";

    x.className= "disattivo";
    
    //chiamata fetch per aggiornare il db
    console.log(fratello, bottone.id);
    applicaModifiche.addEventListener('click', () =>{
        chiamataFetch(x.value, bottone);
        location.reload();
    });
}


//aggiorna la categoria scelta anche nel db
async function chiamataFetch(categoria, bottone){
    try{
        let response= await fetch(`/api/prodotto/modifica-categoria?id=${bottone.id}&categoria=${categoria}`, {
            method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
            });
    
            let data= await response.json();
            console.log(data);
            //window.alert('Ricarica pagina per applicare la modifica');
        }
    catch(e){
        console.log(e);
    }
}