
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