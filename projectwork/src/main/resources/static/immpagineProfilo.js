
const omino= document.querySelector('.omino');
const immagineProfilo= document.querySelector('.immagine-profilo');
const InserNomeUtente= document.querySelector('.nome-utente');
const logOut= document.querySelector('.sign-out');




function getUserDataFromSessionStorage() {
    const userData = sessionStorage.getItem('user');
    if (userData) {
      try {
        if (typeof userData === 'string') {
          return JSON.parse(userData);
        }
        return userData;
      } catch (e) {
        alert("Errore nel parsing dei dati dell'utente.");
        console.error(e);
      }
    }
    return null;
}





function inserisciLogoeNomeUtente(){
    let nomeUtente= getUserDataFromSessionStorage().nome;
    let immagine= recuparaImmagineUtente(getUserDataFromSessionStorage().id)
        .then((immagine) => {
            console.log(immagine);
            
            if(nomeUtente != ""){
                let nomeInMaiuscolo= nomeUtente.toUpperCase();
                InserNomeUtente.textContent= nomeInMaiuscolo;
            }
        
            if(immagine != ""){        
                let img=
                    `
                    <img src="img/profilo/${immagine}" alt="">
                    `;
                
                immagineProfilo.innerHTML= img;
            }
            else{
                omino.style.display= "in line";
            }
        })
        .catch((errore) => {
            console.log(errore);
        })
}

inserisciLogoeNomeUtente();




async function recuparaImmagineUtente(id){
    console.log(id);
    
    let response= await fetch(`/api/utente/recupera-immagine-utente?id=${id}`);

    if(response.status != 200){
        throw new Error("Non è stato possibile recuperare l'immagine dell'utente loggato");
    }

    let data= await response.text();   
    return data;
}



function signOut(){
    logOut.addEventListener('click', () => {
        sessionStorage.clear();
        location.reload();
    });
}

signOut();