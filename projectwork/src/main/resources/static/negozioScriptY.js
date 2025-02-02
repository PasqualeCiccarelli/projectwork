document.addEventListener("DOMContentLoaded", function () {
    // Carichiamo i prodotti iniziali
    fetchProdotti({ brand: "YUGIHO" });

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
        brand: "YUGIHO",
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

                // Calcolo dei prezzi
                const prezzoOriginale = prodotto.prezzo.toFixed(2);
                const prezzoScontato = prodotto.prezzoScontato ? prodotto.prezzoScontato.toFixed(2) : null;

                // Logica per mostrare il prezzo
                const mostraPrezzo = (prodotto.categoria === 'PREVENDITA' || prodotto.categoria === 'SPECIALE') && prezzoScontato ? `
                    <p class="product-price" style="margin-bottom: 0.3rem;">
                        <span style="text-decoration: line-through; color: red;">€${prezzoOriginale}</span>
                        <span style="font-weight: bold; color: green;">€${prezzoScontato}</span>
                    </p>
                ` : `
                    <p class="product-price" style="margin-bottom: 0.3rem;">€${prezzoOriginale}</p>
                `;

                // Sticker Offerta Speciale
                const sticker = (prodotto.categoria === 'PREVENDITA' || prodotto.categoria === 'SPECIALE') ? `
                    <div class="sticker animate__animated animate__heartBeat">
                        Offerta Speciale
                    </div>
                ` : '';

                return `
                    <div class="col-lg-4 col-md-6">
                        <div class="product" style="position: relative;">
                            ${sticker} <!-- Sticker solo per categorie PREVENDITA o SPECIALE -->
                            <a href="/DettagiProdotto.html?id=${prodotto.id}">
                                <img src="img/Yu-Gi-Oh/${prodotto.immagine}" alt="${prodotto.nome}" ${imgStyle}>
                            </a>
                            <h3 class="h5">${prodotto.nome}</h3>
                            <p>${prodotto.descrizione}</p>
                            ${mostraPrezzo} <!-- Inclusione del prezzo -->
                            <div>
                                <button type="button" id="${prodotto.id}" class="btn btn-primary order-button mb-3 btn-carrello">Ordina</button>
                            </div>
                        </div>
                    </div>`;
            })
            .join("");
    }
}

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
