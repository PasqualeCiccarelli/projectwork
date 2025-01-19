document.addEventListener("click", event => {
    if (event.target.classList.contains("btn-carrello")) {
        // Recupera l'ID del prodotto dal bottone
        const prodottoId = event.target.id;

        // Trova il div del prodotto in cui il bottone è stato cliccato (il suo genitore)
        const parentDiv = event.target.closest(".product");

        if (!parentDiv) {
            console.error("Div genitore del prodotto non trovato.");
            return;
        }

        // Trova il form esistente (se presente) nel div genitore
        const existingForm = parentDiv.querySelector(".form-carrello");

        // Se il form esiste, rimuovilo (toggle)
        if (existingForm) {
            existingForm.remove();
        } else {
            // Se il form non esiste, crealo e aggiungilo
            const form = document.createElement("div");
            form.className = "form-carrello";
            form.innerHTML = `
                <label for="quantita-${prodottoId}">Quantità:</label>
                <input 
                    id="quantita-${prodottoId}" 
                    type="number" 
                    min="1" 
                    max="10" 
                    value="1"
                />
                <button class="btn-aggiungi">Aggiungi al carrello</button>
            `;

            // Aggiungiamo il form nel div prodotto
            parentDiv.appendChild(form);

            // Aggiungi l'evento per il pulsante di aggiunta al carrello
            form.querySelector(".btn-aggiungi").addEventListener("click", () => {
                const quantita = form.querySelector(`#quantita-${prodottoId}`).value;
                if (quantita < 1) {
                    alert("Inserisci una quantità valida.");
                    return;
                }

                // Aggiungi il prodotto al carrello
                aggiungiProdottoAlCarrello(prodottoId, quantita);

                // Rimuovi il form dopo che il prodotto è stato aggiunto al carrello
                form.remove();
            });
        }
    }
});

async function aggiungiProdottoAlCarrello(prodottoId, quantita) {
    const userData = getUserDataFromSessionStorage();

    if (!userData || !userData.email) {
        console.error("Dati utente mancanti. Impossibile aggiungere il prodotto al carrello.");
        return;
    }

    const requestBody = {
        email: userData.email,
        prodotto: {
            id: prodottoId
        },
        quantita: quantita
    };

    try {
        const response = await fetch('http://localhost:8080/api/ordini/aggiungi-prodotto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody),
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            console.error("Errore durante la richiesta al server:", errorMessage);
            throw new Error(errorMessage);
        }

        const data = await response.json();
        console.log("Prodotto aggiunto al carrello con successo:", data);
        alert("Prodotto aggiunto al carrello!");
    } catch (error) {
        console.error("Errore nell'aggiunta del prodotto al carrello:", error);
        alert("Errore nell'aggiunta del prodotto al carrello.");
    }
}
