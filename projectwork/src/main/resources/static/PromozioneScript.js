function getUserDataFromSessionStorage() {
    const userData = sessionStorage.getItem('user');
    if (userData) {
        try {
            return JSON.parse(userData);
        } catch (e) {
            alert("Errore nel parsing dei dati dell'utente.");
            console.error("Errore nel parsing:", e);
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

    if (!userData.email || !userData.ruolo1) {
        console.log("Dati mancanti nell'utente: email o ruolo");
        window.location.href = '/login';
        return;
    }

    console.log("Accesso consentito. Utente autenticato.");
}

function getIdFromSessionStorage() {
    const userData = getUserDataFromSessionStorage();
    if (userData && userData.id) {
        return userData.id;
    }
    console.error("ID utente non trovato nei dati memorizzati.");
    return null;
}

function promuoviUtenteAdmin(roleData) {
    const userData = getUserDataFromSessionStorage();

    if (userData) {
        try {

            userData.ruolo2 = roleData.ruolo2;
            sessionStorage.setItem('user', JSON.stringify(userData));

            console.log("Ruolo aggiornato con successo:", userData);
        } catch (error) {
            console.error("Errore durante l'aggiornamento dei dati dell'utente:", error);
        }
    } else {
        console.error("Nessun dato utente trovato nel sessionStorage.");
    }
}

function handlePromoteFormSubmit(event) {
    event.preventDefault();

    const userId = getIdFromSessionStorage();

    if (!userId) {
        alert("Errore: Utente non identificato. Effettua il login.");
        return;
    }

    const acceptPolicies = document.getElementById("acceptPolicies").checked;

    if (!acceptPolicies) {
        alert("Devi accettare le politiche aziendali.");
        return;
    }

    const url = `http://localhost:8080/api/promote/${userId}?acceptedPolicies=on`;

    fetch(url, {
        method: "POST",
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`Errore: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then((data) => {
            console.log("Risposta dal server:", data);

            promuoviUtenteAdmin(data);

            alert("Promozione avvenuta con successo!");
        })
        .catch((error) => {
            console.error("Errore durante la promozione:", error);
            alert("Si è verificato un errore durante la promozione.");
        });
}

document.addEventListener("DOMContentLoaded", () => {
    checkLoginStatus();

    const promoteForm = document.getElementById("promoteForm");

    if (promoteForm) {
        promoteForm.addEventListener("submit", handlePromoteFormSubmit);
    } else {
        console.error("Form di promozione non trovato.");
    }
});