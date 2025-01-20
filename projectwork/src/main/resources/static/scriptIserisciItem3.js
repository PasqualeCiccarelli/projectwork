document.addEventListener("click", (event) => {
    if (event.target.classList.contains("btn-carrello")) {
        
        const prodottoId = event.target.id;

        const parentDiv = event.target.closest(".prodotto-container");

        if (!parentDiv) {
            console.error("Div genitore del prodotto non trovato.");
            return;
        }

        const existingForm = parentDiv.querySelector(".form-carrello");

        if (existingForm) {
            existingForm.remove();
        } else {
            
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
                <button class="btn-aggiungi">Conferma qualcosa</button>
            `;

            parentDiv.querySelector(".info-container").appendChild(form);

            form.querySelector(".btn-aggiungi").addEventListener("click", () => {
                const quantita = form.querySelector(`#quantita-${prodottoId}`).value;
                if (quantita < 1) {
                    alert("Inserisci una quantità valida.");
                    return;
                }

                aggiungiProdottoAlCarrello(prodottoId, quantita);

        //  form.remove();
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

        // Aggiorna il numero di prodotti nel carrello e visualizza lo sticker
        aggiornaStickerCarrello(quantita);
    } catch (error) {
        console.error("Errore nell'aggiunta del prodotto al carrello:", error);
        alert("Errore nell'aggiunta del prodotto al carrello.");
    }
}

// Funzione per aggiornare il numero di prodotti nel carrello
function aggiornaStickerCarrello(quantita) {
    // Recupera il numero di prodotti già presenti nel carrello
    const currentCount = parseInt(localStorage.getItem("cartCount")) || 0;
    const newCount = currentCount + parseInt(quantita);

    // Salva il nuovo conteggio nel localStorage
    localStorage.setItem("cartCount", newCount);

    // Aggiorna la visualizzazione dello sticker
    aggiornaStickerVisuale();
}

// Funzione per aggiornare la visualizzazione dello sticker del carrello
function aggiornaStickerVisuale() {
    const cartSticker = document.getElementById("cart-sticker");
    const cartCount = localStorage.getItem("cartCount");

    if (cartCount && parseInt(cartCount) > 0) {
        // Mostra lo sticker con il numero di prodotti
        cartSticker.style.display = "block";
        cartSticker.textContent = cartCount;
    } else {
        // Nasconde lo sticker se non ci sono prodotti nel carrello
        cartSticker.style.display = "none";
    }
}

// Inizializza la visualizzazione dello sticker al caricamento della pagina
document.addEventListener("DOMContentLoaded", () => {
    aggiornaStickerVisuale();
});



