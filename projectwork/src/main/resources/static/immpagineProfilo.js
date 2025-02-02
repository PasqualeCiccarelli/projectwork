
const omino= document.querySelector('.omino');
const immagineProfilo= document.querySelector('.immagine-profilo');
const InserNomeUtente= document.querySelector('.nome-utente');
const togliCarrello= document.querySelector('.togli-carrello');
const classeSignIn= document.querySelector('.classe-sign-in');
const classeLogin= document.querySelector('.classe-login');
const logoProfilo= document.querySelector('.logo-profilo');


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
    let controlloSessione= sessionStorage.getItem("user");
    console.log(controlloSessione);
    

    //utente loggato
    if(controlloSessione != null){
        let nomeUtente= getUserDataFromSessionStorage().nome;
        let immagine= recuparaImmagineUtente(getUserDataFromSessionStorage().id)
            .then((immagine) => {
                console.log(immagine);
                
                if(nomeUtente != ""){
                    let nomeInMaiuscolo= primaLetteraInMaiuscolo(nomeUtente);
                    console.log(nomeInMaiuscolo);
                    
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
    //utente non loggato
    else{
        if(logoProfilo != null)
            logoProfilo.classList.toggle('show-loghi');
        
        if(togliCarrello != null)
            togliCarrello.classList.toggle("show-loghi");
        
        if(classeSignIn != null)
            classeSignIn.classList.toggle("show-loghi");
        
        if(classeLogin != null)
            classeLogin.classList.toggle("show-loghi");
    }
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




function primaLetteraInMaiuscolo(stringa){
    let s= "";
    s += stringa[0].toUpperCase();

    for(let i=1; i<stringa.length; i++){
        s += stringa[i].toLowerCase();
    }

    return s;
}