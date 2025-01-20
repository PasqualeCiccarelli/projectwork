document.addEventListener("click", event => {
    if (event.target.classList.contains("btn-carrello")) {
        // Recupera l'ID del prodotto dal bottone
        const prodottoId = event.target.id;

        // Trova il div genitore che contiene il prodotto
        const parentDiv = event.target.closest(".swiper-slide");

        if (!parentDiv) {
            console.error("Div genitore non trovato.");
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

            parentDiv.appendChild(form);

            // Aggiungi un event listener al secondo pulsante per aggiungere il prodotto al carrello
            form.querySelector(".btn-aggiungi").addEventListener("click", () => {
                const quantita = form.querySelector(`#quantita-${prodottoId}`).value;
                if (quantita < 1) {
                    alert("Inserisci una quantità valida.");
                    return;
                }
                // Aggiungi il prodotto al carrello
                aggiungiProdottoAlCarrello(prodottoId, quantita);

                // Rimuovi il form dopo aver aggiunto il prodotto
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

        // Aggiorna il numero di prodotti nel localStorage
        aggiornaStickerCarrello(quantita);

    } catch (error) {
        console.error("Errore nell'aggiunta del prodotto al carrello:", error);
        alert("Errore nell'aggiunta del prodotto al carrello.");
    }
}

// Inizializza la visualizzazione dello sticker al caricamento della pagina
document.addEventListener("DOMContentLoaded", () => {
    aggiornaStickerVisuale();
});

// Funzione per aggiornare il numero di prodotti nel carrello e visualizzare lo sticker
function aggiornaStickerCarrello(quantita) {
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
        cartSticker.style.display = "flex";
        cartSticker.textContent = cartCount;

        // Aggiungi animazione heartbeat per far pulsare lo sticker ogni pochi secondi
        if (!cartSticker.classList.contains("animate__heartBeat")) {
            cartSticker.classList.add("animate__heartBeat");
        }
    } else {
        // Nascondi lo sticker se non ci sono prodotti nel carrello
        cartSticker.style.display = "none";
    }
}
