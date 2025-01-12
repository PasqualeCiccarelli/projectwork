function getUserDataFromSessionStorage() {
  const userData = sessionStorage.getItem('user'); // Recupera il valore 'user' da SessionStorage
  if (userData) {
    try {
      // Verifica se userData è una stringa JSON
      if (typeof userData === 'string') {
        return JSON.parse(userData);  // Fai il parsing solo se è una stringa
      }
      return userData;  // Se è già un oggetto, restituiscilo così com'è
    } catch (e) {
      alert("Errore nel parsing dei dati dell'utente.");
      console.error(e);
    }
  }
  return null; // Restituisce null se non ci sono dati nel sessionStorage
}

function checkLoginStatus() {
  // Recupera i dati dell'utente da SessionStorage
  const userData = getUserDataFromSessionStorage();

  if (!userData) {
    console.log("Utente non autenticato o dati non trovati nel sessionStorage");
    window.location.href = '/login'; // Reindirizza alla pagina di login
    return;
  }

  console.log('Dati dell\'utente:', userData);

  // Verifica se i dati necessari sono presenti
  if (!userData.email || !userData.ruolo2) {
    console.log("Dati mancanti nell'utente: email o ruolo");
    window.location.href = '/login'; // Reindirizza se i dati non sono completi
    return;
  }

  // Controlla se l'utente ha il ruolo richiesto
  if (userData.ruolo2 !== 'ADMIN') {
    console.log("Utente non ha il ruolo ADMIN");
    window.location.href = '/login'; // Reindirizza se il ruolo non è ADMIN
    return;
  }

  // L'utente ha il ruolo richiesto
  console.log("Accesso consentito. L'utente è un ADMIN.");
}

// Esegui il controllo all'avvio della pagina
checkLoginStatus();

function getEmailFromCookie() {
 
  const userData = getUserDataFromSessionStorage();
  console.log(userData.email);
  
  return userData.email;

  // Restituisce l'email se trovata, altrimenti null
}

console.log(getEmailFromCookie());


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

document.getElementById("cardForm").addEventListener("submit", async function (event) {
  event.preventDefault();

  const formData = new FormData(event.target);  // Usa FormData per gestire i dati del form
  const data = Object.fromEntries(formData.entries());
  data.disponibilita = data.disponibilita === "true";
  data.prezzo_scontato = (data.prezzo * 0.8).toFixed(2);

  const userData = getUserDataFromSessionStorage();

  if (!userData) {
    alert("Non sei autenticato. Effettua il login.");
    window.location.href = '/login';  // Reindirizza se l'utente non è autenticato
    return;
  }

  const emailAdmin = getEmailFromCookie();  // Recupera l'email dal cookie

  if (!emailAdmin) {
      alert("Email dell'utente non trovata nel cookie.");
      console.error("Email non trovata nel cookie.");
      return;
  }

  data.emailAdmin = emailAdmin;

  // Gestione disponibilità
  if (data.disponibilita) {
    const today = new Date().toISOString().split("T")[0]; 
    data.data_inizio = today;
    data.categoria = "DEFAULT";
  } else {
    data.categoria = "PREVENDITA";
  }

  // Gestione della data di inizio
  if (data.data_inizio) {
    data.data_inizio = new Date(data.data_inizio).toISOString().split("T")[0];
  }

  // Gestione dell'immagine (se presente)
  const immagineFile = immagineInput.files[0]; 
  if (immagineFile) {
    data.immagine = immagineFile.name;  // Usa solo il nome del file, non l'intero file
  }

  console.log(data);  // Log dei dati da inviare

  try {
    const response = await fetch("/api/card/crea", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // Se invii JSON, questa intestazione è importante
      },
      credentials: 'include',  // Importante per inviare i cookie di sessione (JSESSIONID)
      body: JSON.stringify(data) // Invia i dati in formato JSON
    });

    const responseText = await response.text();
    if (response.ok) {
      alert("Carta creata con successo!");
    } else {
      alert("Errore durante la creazione della carta: " + responseText);
    }
  } catch (err) {
    alert("Errore durante la creazione della carta: " + err.message);
  }
});