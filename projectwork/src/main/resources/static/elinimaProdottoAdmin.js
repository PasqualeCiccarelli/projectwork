
function visualizzaBottoneElimina(){
    const bottoneElimina= document.querySelectorAll('.elimina-prodotto');

    bottoneElimina.forEach(bottone => {
        bottone.style.display= "flex";
    });
};


function clickBottoneElimina(){
    const bottoneElimina= document.querySelectorAll('.elimina-prodotto');

    bottoneElimina.forEach(bottone => {
        bottone.addEventListener('click', () => {
            //console.log(bottone.id);
            const popup= document.querySelector('.popup-conferma-eliminazione');
            const carosello= document.querySelector('.swiperScorrimentoProdotti');
            const conferma= document.querySelector('.conf-eliminazione');
            const nega= document.querySelector('.nega-eliminazione');
            console.log(carosello);
            

            carosello.classList.toggle("aggiungi-filtro");
            popup.style.display= "flex";

            conferma.addEventListener('click', () => {
                carosello.classList.add("aggiungi-filtro");
                popup.style.display= "none";
                funzioneEliminazione(bottone);
            });

            nega.addEventListener('click', () => {
                carosello.classList.remove("aggiungi-filtro")
                popup.style.display= "none"; 
            });
        })
    });
}



async function funzioneEliminazione(bottone){
    console.log(bottone.id);
    let response= await fetch(`/api/prodotto/elimina-prodotto?id=${bottone.id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if(response.status != 200){
        throw new Error("Al momento non è stato possibile eliminare il prodotto");
    }

    let data= await response.json();
    console.log("Codice di verifica eliminazione carta (1 a buon fine, 0 no)", data);
    location.reload();   
}