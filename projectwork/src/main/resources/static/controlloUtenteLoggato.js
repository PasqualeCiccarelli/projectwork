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

function checkUtenteStatus() {
    const userData = getUserDataFromSessionStorage();

    if (!userData) {
        console.log("Utente non autenticato o dati non trovati nel sessionStorage");
        window.location.href = '/login';
        return;
    }

    console.log('Dati dell\'utente:', userData);

    if (!userData.email || !userData.ruolo1) {
        console.log("Dati mancanti nell'utente: email o ruolo");
        window.location.href = '/login';
        return;
    }

    console.log("Accesso consentito. Utente autenticato.");
}

checkUtenteStatus();