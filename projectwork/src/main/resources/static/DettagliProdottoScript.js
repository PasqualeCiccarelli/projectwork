async function fetchProductData() {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');

        if (!id) {
            throw new Error('ID prodotto non specificato');
        }

        const response = await fetch(`/api/prodotto/dettagli-prodotto?id=${id}`);

        if (!response.ok) {
            throw new Error(`Errore HTTP: status ${response.status}`);
        }

        const prodotto = await response.json();

        if (!prodotto || prodotto.length === 0) {
            throw new Error('Nessun prodotto trovato');
        }

        // Elemento della pagina dove mostrare i dettagli
        const dettagliCartaDiv = document.querySelector('.dettagli-carta');
        if (!dettagliCartaDiv) {
            throw new Error('Elemento .dettagli-carta non trovato nella pagina');
        }

        // Se prodotto è un array, usa il primo elemento (o iteralo se necessario)
        const prodottoDettagli = Array.isArray(prodotto) ? prodotto[0] : prodotto;

        // Determina il percorso immagine
        let imagePath = prodottoDettagli.immagine;
        switch (prodottoDettagli.brand.toUpperCase()) {
            case "POKEMON":
                imagePath = `img/pokemon/${prodottoDettagli.immagine}`;
                break;
            case "YUGIHO":
                imagePath = `img/Yu-Gi-Oh/${prodottoDettagli.immagine}`;
                break;
            case "MAGIC":
                imagePath = `img/magic/${prodottoDettagli.immagine}`;
                break;
            default:
                console.warn(`Brand non riconosciuto: ${prodottoDettagli.brand}`);
        }
        console.log(prodotto);
        
        let classe;

        if (window.innerWidth >= 1024) {
            classe = "d-flex";
        } else {
            classe = "d-flex flex-column";
        }

        // Aggiungi un listener per il resize della finestra
        window.addEventListener('resize', () => {
            const newClasse = window.innerWidth >= 1024 ? "d-flex" : "row";
            if (newClasse !== classe) {
            location.reload();
            }
        });

        // Aggiorna il contenuto HTML
        dettagliCartaDiv.innerHTML = `
            <div class="prodotto-container ${classe}">
                <div class="immagine-container col-12 col-md-8">
                    <img src="${imagePath}" 
                         alt="${prodottoDettagli.nome}" 
                         class="${isCard(prodottoDettagli) ? 'card-effect' : 'zoom-effect'}">
                </div>

                <div class="info-container col-12 col-md-6">
                    <h1>${prodottoDettagli.nome}</h1>
                    <p><strong>Categoria:</strong> ${prodottoDettagli.categoria}</p>
                    <p><strong>Descrizione:</strong> ${prodottoDettagli.descrizione || 'Nessuna descrizione disponibile'}</p>
                    <p><strong>Tipo:</strong> ${prodottoDettagli.tipo}</p>
                    <p><strong>Brand:</strong> ${prodottoDettagli.brand}</p>
                    <p><strong>Dettagli Specifici:</strong> ${prodottoDettagli.specificDetails || 'Non disponibile'}</p>

                    <div class="prezzo-container">
                        ${prodottoDettagli.categoria === 'SPECIALE' ? `
                            <p style="text-decoration: line-through;">Prezzo originale: €${prodottoDettagli.prezzo.toFixed(2)}</p>
                            <p style="color: #e53935;">Prezzo scontato: €${prodottoDettagli.prezzoScontato.toFixed(2)}</p>
                        ` : `
                            <p>Prezzo: €${prodottoDettagli.prezzo.toFixed(2)}</p>
                        `}

                        ${prodottoDettagli.disponibilita ? `
                            <p style="color: #28a745;">Disponibile (${prodottoDettagli.rimanenza} pezzi rimanenti)</p>
                            <button class="btn-carrello" onclick="aggiungiAlCarrello(${prodottoDettagli.id})">Aggiungi al carrello</button>
                        ` : `
                            <p style="color: #dc3545;">Non disponibile</p>
                            <button class="btn-carrello" disabled>Non disponibile</button>
                        `}
                    </div>
                </div>
            </div>
        `;

        // Aggiungi effetto tilt se è una card
        if (isCard(prodottoDettagli)) {
            const cardImage = document.querySelector('.card-effect');
            if (cardImage) {
                addTiltEffect(cardImage);
            }
        }

    } catch (error) {
        console.error('Errore:', error.message);
        const dettagliCartaDiv = document.querySelector('.dettagli-carta');
        if (dettagliCartaDiv) {
            dettagliCartaDiv.innerHTML = `
                <div style="text-align: center; padding: 20px; color: #dc3545;">
                    <h3>Si è verificato un errore</h3>
                    <p>${error.message}</p>
                </div>
            `;
        }
    }
}

console.log(typeof prodotto);





function isCard(prodotto) {
    return prodotto.specificDetails && 
           !prodotto.specificDetails.includes('Peso:') && 
           !prodotto.specificDetails.includes('Numero Bustine:');
}

function addTiltEffect(element) {
    element.addEventListener('mousemove', (e) => {
        const rect = element.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;
        
        const centerX = rect.width / 2;
        const centerY = rect.height / 2;
        
        const rotateX = (y - centerY) / 20;
        const rotateY = (centerX - x) / 20;
        
        element.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
    });

    element.addEventListener('mouseleave', () => {
        element.style.transform = 'rotateX(0) rotateY(0)';
    });
}


document.addEventListener('DOMContentLoaded', fetchProductData);