let oggettoDatiAdmin; // Dichiarazione globale

document.addEventListener('DOMContentLoaded', function () {
    const datiAdmin = sessionStorage.getItem("user");
    if (datiAdmin) {
        oggettoDatiAdmin = JSON.parse(datiAdmin);
        if (oggettoDatiAdmin?.email) {
            caricaProdotti(0);
        }
    }

    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('special-button')) {
            const prodottoId = e.target.id.replace('product-', '');
            modificaCategoriaProdotto(prodottoId);
        } else if (e.target.classList.contains('elimina-prodotto')) {
            const prodottoId = e.target.id.replace('delete-button-', '');
            eliminaProdotto(prodottoId);
        }
    });
});

function caricaProdotti(pagina) {
    if (!oggettoDatiAdmin?.email) {
        console.error('Dati admin non disponibili');
        return;
    }

    fetch(`/api/admin/${oggettoDatiAdmin.email}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Errore nella risposta del server');
            }
            return response.json();
        })
        .then(adminData => {
            const tuttiProdotti = [
                ...adminData.carte,
                ...adminData.bustine,
                ...adminData.box,
                ...(adminData.accessori || [])
            ];

            const prodottiPerPagina = 12;
            const inizio = pagina * prodottiPerPagina;
            const fine = inizio + prodottiPerPagina;
            const prodottiPagina = tuttiProdotti.slice(inizio, fine);
            const totalePagine = Math.ceil(tuttiProdotti.length / prodottiPerPagina);

            const container = document.querySelector('.prodotti-container');
            if (!container) {
                console.error('Container prodotti non trovato');
                return;
            }

            container.innerHTML = '';

            prodottiPagina.forEach(prodotto => {
                let imagePath = prodotto.immagine;
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
                        imagePath = `img/placeholder.jpg`;
                }
                console.log(prodotto);
                

                const prezzoOriginale = prodotto.prezzo.toFixed(2);
                const prezzoScontato = prodotto.prezzoScontato.toFixed(2);
                const mostraPrezzo = (prodotto.categoria === 'PREVENDITA' || prodotto.categoria === 'SPECIALE') ? `
                    <p class="product-price" style="margin-bottom: 0.3rem;">
                        <span style="text-decoration: line-through; color: red;">€${prezzoOriginale}</span>
                        <span style="font-weight: bold; color: green;">€${prezzoScontato}</span>
                    </p>
                ` : `
                    <p class="product-price" style="margin-bottom: 0.3rem;">€${prezzoOriginale}</p>
                `;

                // Sticker Offerta Speciale
                const sticker = (prodotto.categoria === 'PREVENDITA' || prodotto.categoria === 'SPECIALE') ? `
                    <div class="sticker" style="
                        background: red;
                        color: yellow;
                        font-weight: bold;
                        padding: 5px 10px;
                        border-radius: 5px;
                        z-index: 10;
                        margin-bottom: 5px;
                    ">
                        Offerta Speciale
                    </div>
                ` : '';

                container.innerHTML += `
                <div class="product-card text-center" style="position: relative;">
                ${sticker}
                    <div>
                        <a href="/DettagiProdotto.html?id=${prodotto.id}">
                            <img src="${imagePath}" style="max-width: 100%; " alt="${prodotto.nome}" onerror="this.src='img/placeholder.jpg'"> 
                        </a>
                    </div>
                    <div class="inser-select"></div>
                    <p class="product-category" id="categoria-${prodotto.id}" style="margin-bottom: 0.3rem; display: none;">${prodotto.categoria}</p>
                    <h3 class="product-name" style="margin-bottom: 0.3rem;">${prodotto.nome}</h3>
                    ${mostraPrezzo}
                    <div>
                        <button type="button" id="product-${prodotto.id}" class="btn btn-primary special-button mb-1">
                            ${prodotto.categoria === 'SPECIALE' ? 'Togli l\'offerta' : 'Metti in offerta'}
                        </button>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="button" id="delete-button-${prodotto.id}" class="btn btn-danger order-button mb-5 elimina-prodotto">
                            Elimina
                        </button>
                    </div>
                </div>
                `;
            });

            // Paginazione
            const paginationContainer = document.querySelector('.pagination');
            if (paginationContainer) {
                paginationContainer.innerHTML = '';
                for (let i = 0; i < totalePagine; i++) {
                    const button = document.createElement('button');
                    button.className = `btn ${i === pagina ? 'btn-primary' : 'btn-outline-primary'} mx-1`;
                    button.textContent = i + 1;
                    button.onclick = () => caricaProdotti(i);
                    paginationContainer.appendChild(button);
                }
            }
        })
        .catch(error => {
            console.error('Errore nel caricamento dei prodotti:', error);
            const container = document.querySelector('.prodotti-container');
            if (container) {
                container.innerHTML = '<p class="text-center text-danger">Si è verificato un errore nel caricamento dei prodotti.</p>';
            }
        });
}

function modificaCategoriaProdotto(prodottoId) {
    const categoriaElement = document.querySelector(`#categoria-${prodottoId}`);
    if (!categoriaElement) {
        console.error(`Elemento categoria per il prodotto con ID ${prodottoId} non trovato`);
        return;
    }

    const categoria = categoriaElement.textContent.trim();
    const nuovaCategoria = categoria === 'SPECIALE' ? 'DEFAULT' : 'SPECIALE';
    const url = `/api/admin/prodotti/${prodottoId}/categoria?categoria=${nuovaCategoria}`;

    console.log('Invio richiesta PUT a:', url);

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Errore nella risposta del server: ${response.status}`);
            }
            return response.json();
        })
        .then(() => {
            categoriaElement.textContent = nuovaCategoria;
            const button = document.querySelector(`#product-${prodottoId}`);
            if (button) {
                button.textContent = nuovaCategoria === 'SPECIALE' ? 'Togli l\'offerta' : 'Metti in offerta';
                location.reload();
            }
        })
        .catch(error => {
            console.error('Errore durante la modifica della categoria:', error);
            alert('Si è verificato un errore durante la modifica della categoria.');
        });
}

function eliminaProdotto(prodottoId) {
    console.log(`Eliminazione prodotto con ID: ${prodottoId}`);

}

function eliminaProdotto(productId) {
    if (!oggettoDatiAdmin || !oggettoDatiAdmin.email) {
        console.error('Dati admin non disponibili');
        return;
    }

    if (!confirm('Sei sicuro di voler eliminare questo prodotto?')) {
        return;
    }

    fetch(`/api/prodotto/elimina-prodotto?id=${productId}`, { 
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorText => {
                    throw new Error(`Errore ${response.status}: ${errorText}`);
                });
            }
            return response.json();
        })
        .then(() => {
            const productCard = document.querySelector(`#delete-button-${productId}`).closest('.product-card');
            if (productCard) {
                productCard.remove();
            }
            alert('Prodotto eliminato con successo');
            location.reload();
        })
        .catch(error => {
            console.error('Errore durante l\'eliminazione:', error.message);
            alert('Si è verificato un errore durante l\'eliminazione del prodotto. Riprova.');
        });
}


