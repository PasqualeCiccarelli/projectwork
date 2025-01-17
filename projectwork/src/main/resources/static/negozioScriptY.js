document.addEventListener("DOMContentLoaded", function() {

    fetchProdotti({ brand: "YUGIHO" });

    const applyFiltersButton = document.getElementById("apply-filters");
    if (applyFiltersButton) {
        applyFiltersButton.addEventListener("click", function() {
            applyFilters();
        });
    }

    const prezzoMinimo = document.getElementById("prezzoMinimo");
    const prezzoMassimo = document.getElementById("prezzoMassimo");

    prezzoMinimo.addEventListener("input", function() {
        document.getElementById("prezzoMinimoValue").textContent = `€${prezzoMinimo.value}`;
    });

    prezzoMassimo.addEventListener("input", function() {
        document.getElementById("prezzoMassimoValue").textContent = `€${prezzoMassimo.value}`;
    });

    const filterButtons = document.querySelectorAll(".filter-button");
    filterButtons.forEach(button => {
        button.addEventListener("click", function() {
            const parentFilter = this.parentElement;
            parentFilter.classList.toggle("open");
            fetchProdotti({ brand: "YUGIHO" });
        });
    });

    const filterRadioButtons = document.querySelectorAll(".filter-radio");
    filterRadioButtons.forEach(radio => {
        radio.addEventListener("change", function() {
            applyFilters();
        });
    });
});

function applyFilters() {
    const filters = {};

    const categoria = document.querySelector('input[name="categoria"]:checked')?.value;
    if (categoria && categoria !== "tutti") {
        filters.categoria = categoria;
    }

    const rarita = document.querySelector('input[name="rarita"]:checked')?.value;
    if (rarita && rarita !== "tutti") {
        filters.rarita = rarita;
    }

    const tipo = document.querySelector('input[name="tipo"]:checked')?.value;
    if (tipo && tipo !== "tutti") {
        filters.tipo = tipo;
    }

    const tipoCategoria = document.querySelector('input[name="tipoCategoria"]:checked')?.value;
    if (tipoCategoria && tipoCategoria !== "tutti") {
        filters.tipoCategoria = tipoCategoria;
    }

    const prezzoMinimo = document.getElementById("prezzoMinimo").value;
    const prezzoMassimo = document.getElementById("prezzoMassimo").value;

    if (prezzoMinimo) {
        filters.prezzoMinimo = prezzoMinimo;
    }
    if (prezzoMassimo) {
        filters.prezzoMassimo = prezzoMassimo;
    }

    fetchProdotti(filters);
}

async function fetchProdotti(filters = {}, page = 0) {
    const params = new URLSearchParams({
        ...filters,     
        brand: "YUGIHO", 
        page,
        size: 12,
    });

    const response = await fetch(`/api/prodotto?${params.toString()}`);
    const data = await response.json();
    renderProdotti(data.content);
    renderPagination(data.totalPages, data.number);
}

function renderProdotti(prodotti) {
    const productList = document.getElementById("product-list");
    const noProductsMessage = document.getElementById("no-products-message");

    if (prodotti.length === 0) {
        noProductsMessage.style.display = "block";
        productList.innerHTML = ""; 
    } else {
        noProductsMessage.style.display = "none"; 
        productList.innerHTML = prodotti
            .map(
                (prodotto) => `  
            <div class="product">
                <a href="/DettagiProdotto.html?id=${prodotto.id}"><img src="img/Yu-Gi-Oh/${prodotto.immagine}" alt="${prodotto.nome}"></a>
                <h3>${prodotto.nome}</h3>
                <p>${prodotto.descrizione}</p>
                <p class="price">Prezzo: €${prodotto.prezzo.toFixed(2)}</p>
                <div>
                    <button type="button" id="${prodotto.id}" class="btn btn-primary order-button mb-5 btn-carrello">Ordina</button>
                </div>
            </div>` 
            )
            .join("");
    }
}

function renderPagination(totalPages, currentPage) {
    const pagination = document.getElementById("pagination");
    pagination.innerHTML = Array.from({ length: totalPages })
        .map(
            (_, i) => `
        <button ${i === currentPage ? "disabled" : ""} onclick="fetchProdotti({}, ${i})">
            ${i + 1}
        </button>` 
        )
        .join("");
}