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
  
function checkLoginStatus() {

    const userData = getUserDataFromSessionStorage();
  
    if (!userData) {
      console.log("Utente non autenticato o dati non trovati nel sessionStorage");
      window.location.href = '/login';
      return;
    }
  
    console.log('Dati dell\'utente:', userData);
  
    if (!userData.email || !userData.ruolo2) {
      console.log("Dati mancanti nell'utente: email o ruolo");
      window.location.href = '/login';
      return;
    }
  
    if (userData.ruolo2 !== 'ADMIN') {
      console.log("Utente non ha il ruolo ADMIN");
      window.location.href = '/login';
      return;
    }

    console.log("Accesso consentito. L'utente è un ADMIN.");
}
  
checkLoginStatus();
  
function getEmailFromCookie() {
   
    const userData = getUserDataFromSessionStorage();
    console.log(userData.email);
    
    return userData.email;
}

const disponibilita = document.getElementById("disponibilita");
const dataInizioWrapper = document.getElementById("dataInizioWrapper");
const dataInizioInput = document.getElementById("data_inizio");
const immagineInput = document.getElementById("immagine");

disponibilita.addEventListener("change", () => {
  if (disponibilita.value === "false") {
    dataInizioWrapper.classList.remove("hidden");
  } else {
    dataInizioWrapper.classList.add("hidden"); 
    dataInizioInput.value = "";
  }
});

document.getElementById("accessorioForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());
    data.disponibilita = data.disponibilita === "true";
    data.prezzo_scontato = (data.prezzo * 0.8).toFixed(2);
  
    const userData = getUserDataFromSessionStorage();
  
    if (!userData) {
      alert("Non sei autenticato. Effettua il login.");
      window.location.href = '/login';
      return;
    }
  
    const emailAdmin = getEmailFromCookie();
  
    if (!emailAdmin) {
        alert("Email dell'utente non trovata nel cookie.");
        console.error("Email non trovata nel cookie.");
        return;
    }
  
    data.emailAdmin = emailAdmin;

    if (data.disponibilita) {
      const today = new Date().toISOString().split("T")[0]; 
      data.data_inizio = today;
      data.categoria = "DEFAULT";
    } else {
      data.categoria = "PREVENDITA";
    }
  
    if (data.data_inizio) {
      data.data_inizio = new Date(data.data_inizio).toISOString().split("T")[0];
    }

    const immagineFile = immagineInput.files[0]; 
    if (immagineFile) {
      data.immagine = immagineFile.name;
    }
  
    console.log(data);
  
    try {
      const response = await fetch("/api/accessori/crea", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        credentials: 'include',
        body: JSON.stringify(data)
      });
  
      const responseText = await response.text();
      if (response.ok) {
        alert("Accessorio creato con successo!");
      } else {
        alert("Errore durante la creazione dell'accessorio: " + responseText);
      }
    } catch (err) {
      alert("Errore durante la creazione dell'accessorio: " + err.message);
    }
  });