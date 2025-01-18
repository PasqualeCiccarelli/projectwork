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
            <img src="${imagePath}" alt="${prodotto.nome}">
            <h3>${prodotto.nome}</h3>
            <p>Categoria: ${prodotto.categoria}</p>
            <p class="price">Prezzo: €${prodotto.prezzoEffettivo.toFixed(2)}</p>
            <button>Ordina</button>
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

document.addEventListener("DOMContentLoaded", function () {
    // Carichiamo i prodotti iniziali
    fetchProdotti({ brand: "POKEMON" });

    // Aggiorniamo il valore visivo del range di prezzo minimo
    const prezzoMinimo = document.getElementById("prezzoMinimo");
    const prezzoMassimo = document.getElementById("prezzoMassimo");

    prezzoMinimo.addEventListener("input", function () {
        const euroValue = (prezzoMinimo.value / 100).toFixed(2); // Converti in euro
        document.getElementById("prezzoMinimoValue").textContent = `€${euroValue}`;
    });

    // Aggiorniamo il valore visivo del range di prezzo massimo
    prezzoMassimo.addEventListener("input", function () {
        const euroValue = (prezzoMassimo.value / 100).toFixed(2); // Converti in euro
        document.getElementById("prezzoMassimoValue").textContent = `€${euroValue}`;
    });

    // Applichiamo i filtri quando i selettori cambiano
    const selectFilters = document.querySelectorAll(".form-select");
    selectFilters.forEach((select) => {
        select.addEventListener("change", function () {
            applyFilters();
        });
    });

    // Applichiamo i filtri sui range di prezzo
    document.getElementById("apply-prezzo-minimo").addEventListener("click", applyFilters);
    document.getElementById("apply-prezzo-massimo").addEventListener("click", applyFilters);
});

// Funzione per applicare i filtri
function applyFilters() {
    const filters = {};

    // Otteniamo i valori dei selettori
    const categoria = document.querySelector('select[name="categoria"]').value;
    if (categoria && categoria !== "Scegli Categoria") {
        filters.categoria = categoria;
    }

    const rarita = document.querySelector('select[name="rarita"]').value;
    if (rarita && rarita !== "Scegli Raritá") {
        console.log(rarita);
        filters.rarita = rarita;
    }

    const tipo = document.querySelector('select[name="tipo"]').value;
    if (tipo && tipo !== "Scegli Tipo") {
        filters.tipo = tipo;
    }

    const tipoCategoria = document.querySelector('select[name="tipoCategoria"]').value;
    if (tipoCategoria && tipoCategoria !== "Scegli Categoria") {
        filters.tipoCategoria = tipoCategoria;
    }

    // Otteniamo i valori dei range di prezzo in centesimi
    const prezzoMinimo = document.getElementById("prezzoMinimo").value;
    const prezzoMassimo = document.getElementById("prezzoMassimo").value;

    if (prezzoMinimo) {
        filters.prezzoMinimo = (prezzoMinimo / 100).toFixed(2); // Dividi per 100 per avere il valore in euro
    }
    if (prezzoMassimo) {
        filters.prezzoMassimo = prezzoMassimo; // In centesimi
    }

    // Aggiorniamo i prodotti con i filtri selezionati
    console.log(filters);
    fetchProdotti(filters);
}

// Funzione per ottenere i prodotti filtrati
async function fetchProdotti(filters = {}, page = 0) {
    const params = new URLSearchParams({
        ...filters,
        brand: "POKEMON",
        page,
        size: 12,
    });

    console.log(params.toString());

    const response = await fetch(`/api/prodotto?${params.toString()}`);
    const data = await response.json();
    renderProdotti(data.content);
    renderPagination(data.totalPages, data.number);
    console.log(data);
    console.log(data.content);
}

// Funzione per renderizzare i prodotti
function renderProdotti(prodotti) {
    const productList = document.getElementById("product-list");
    const noProductsMessage = document.getElementById("no-products-message");

    if (prodotti.length === 0) {
        noProductsMessage.style.display = "block";
        productList.innerHTML = ""; // Nessun prodotto da mostrare
    } else {
        noProductsMessage.style.display = "none"; // Nascondi il messaggio di "Nessun articolo trovato"
        productList.innerHTML = prodotti
            .map((prodotto) => {
                // Verifica se il tipoCategoria è "BUSTINA"
                const imgStyle = prodotto.tipoCategoria === "BUSTINA" ? 'style="width: 76%;"' : "";

                return `
                    <div class="col-lg-4 col-md-6">
                        <div class="product">
                            <a href="/DettagiProdotto.html?id=${prodotto.id}">
                                <img src="img/pokemon/${prodotto.immagine}" alt="${prodotto.nome}" ${imgStyle}>
                            </a>
                            <h3 class="h5">${prodotto.nome}</h3>
                            <p>${prodotto.descrizione}</p>
                            <p class="price">Prezzo: €${(prodotto.prezzo / 100).toFixed(2)}</p> <!-- Mostra il prezzo in euro -->
                            <div>
                                <button type="button" id="${prodotto.id}" class="btn btn-primary order-button mb-3 btn-carrello">Ordina</button>
                            </div>
                        </div>
                    </div>`;
            })
            .join("");
    }
}

// Funzione per renderizzare la paginazione
function renderPagination(totalPages, currentPage) {
    const pagination = document.getElementById("pagination-script");
    pagination.innerHTML = `
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item ${currentPage === 0 ? "disabled" : ""}">
                    <a class="page-link" href="#" onclick="handlePageClick(event, ${currentPage - 1})">Previous</a>
                </li>
                ${Array.from({ length: totalPages })
                    .map(
                        (_, i) => `
                        <li class="page-item ${i === currentPage ? "active" : ""}">
                            <a class="page-link" href="#" onclick="handlePageClick(event, ${i})">${i + 1}</a>
                        </li>`
                    )
                    .join("")}
                <li class="page-item ${currentPage === totalPages - 1 ? "disabled" : ""}">
                    <a class="page-link" href="#" onclick="handlePageClick(event, ${currentPage + 1})">Next</a>
                </li>
            </ul>
        </nav>`;
}

// Funzione per gestire il click sui pulsanti di paginazione
function handlePageClick(event, page) {
    // Blocca il comportamento predefinito del link
    event.preventDefault();

    // Esegui la logica per caricare i prodotti
    fetchProdotti({}, page);

    // Scroll alla sezione con id "store"
    const storeSection = document.getElementById("store");
    if (storeSection) {
        storeSection.scrollIntoView({ behavior: "smooth", block: "start" });
    }
}


