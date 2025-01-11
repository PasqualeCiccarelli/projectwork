const promoteForm = document.getElementById("promoteForm");

promoteForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const userId = localStorage.getItem("id");
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
            return response.text();
        })
        .then((data) => {
            console.log("Risposta dal server:", data); 
            localStorage.setItem("ruolo2", "ADMIN");
            alert("Promozione avvenuta con successo!");
        })
        .catch((error) => {
            console.error("Errore durante la promozione:", error);
            alert("Si è verificato un errore durante la promozione.");
        });
});