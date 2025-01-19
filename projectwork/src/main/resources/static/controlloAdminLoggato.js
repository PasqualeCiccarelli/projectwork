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

function checkAdminStatus() {
    const userData = getUserDataFromSessionStorage();
  
    if (!userData) {
      console.log("Utente non autenticato o dati non trovati nel sessionStorage");
      window.location.href = '/errore';
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

checkAdminStatus();