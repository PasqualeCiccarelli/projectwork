// Funzione debounce per limitare le chiamate API durante la digitazione
function debounce(func, delay) {
    let timeoutId;
    return function (...args) {
        if (timeoutId) clearTimeout(timeoutId);
        timeoutId = setTimeout(() => func.apply(this, args), delay);
    };
}

// Funzione per gestire la chiamata API
async function fetchProdotti(query) {
    try {
        // Esegui una chiamata GET al server con la query
        const response = await fetch(`/api/prodotto/search?query=${encodeURIComponent(query)}`);
        if (!response.ok) {
            throw new Error(`Errore nella chiamata: ${response.statusText}`);
        }
        const prodotti = await response.json();
        // Passa i dati ricevuti alla funzione che gestirà la visualizzazione
        handleProdottiResponse(prodotti);
    } catch (error) {
        console.error("Errore durante la chiamata API:", error);
    }
}

// Funzione da chiamare quando l'utente digita nella barra di ricerca
const handleSearchInput = debounce((event) => {
    const query = event.target.value.trim();
    if (query) {
        fetchProdotti(query); // Chiamata API solo se la query non è vuota
    } else {
        handleProdottiResponse([]); // Se la query è vuota, resetta i risultati
    }
}, 300); // Ritardo di 300 ms per limitare le chiamate

// Assegna l'evento di input alla barra di ricerca
document.getElementById("floatingInput").addEventListener("input", handleSearchInput);

// Funzione per mostrare la popup con i risultati
function showPopup() {
    document.getElementById("overlay").style.display = "block";
    document.getElementById("searchPopup").style.display = "block";
}

// Funzione per nascondere la popup
function hidePopup() {
    document.getElementById("overlay").style.display = "none";
    document.getElementById("searchPopup").style.display = "none";
}

// Gestire la chiusura della popup
document.getElementById("closePopup").addEventListener("click", function () {
    hidePopup();
});

// Funzione per gestire la risposta della ricerca e popolare la popup
function handleProdottiResponse(prodotti) {
    const resultsContainer = document.getElementById("popupResults");
    resultsContainer.innerHTML = ""; // Pulisce i risultati precedenti

    if (prodotti.length === 0) {
        resultsContainer.innerHTML = "<p>Nessun prodotto trovato.</p>";
        return;
    }

    prodotti.forEach((prodotto) => {
        const productDiv = document.createElement("div");
        productDiv.className = "product-2";

        // Determina il percorso dell'immagine in base al brand
        let imagePath = prodotto.immagine; // Percorso di default
        switch (prodotto.brand) {
            case "POKEMON":
                imagePath = `img/pokemon/${prodotto.immagine}`;
                break;
            case "YUGIHO":
                imagePath = `img/Yu-Gi-Oh/${prodotto.immagine}`;
                break;
            case "MAGIC":
                imagePath = `img/magic/${prodotto.immagine}`;
                break;
            default:
                console.warn(`Brand non riconosciuto: ${prodotto.brand}`);
        }

        // Costruzione del contenuto del prodotto
        productDiv.innerHTML = `
            <a class="d-flex justify-content-center" href="/DettagiProdotto.html?id=${prodotto.id}"><img src="${imagePath}" alt="${prodotto.nome}"></a>
            <h3>${prodotto.nome}</h3>
            <p>Categoria: ${prodotto.categoria}</p>
            <p class="price">Prezzo: €${prodotto.prezzoEffettivo.toFixed(2)}</p>
            
        `;

        resultsContainer.appendChild(productDiv);
    });

    showPopup(); // Mostra la popup
}

// Gestire la ricerca nella barra di ricerca della popup (opzionale, per migliorare l'esperienza dell'utente)
document.getElementById("popupSearchBar").addEventListener("input", (event) => {
    const query = event.target.value.trim();
    if (query) {
        fetch(`/api/prodotto/search?query=${encodeURIComponent(query)}`)
            .then((response) => response.json())
            .then((data) => handleProdottiResponse(data))
            .catch((error) => console.error("Errore durante la ricerca:", error));
    }
});
