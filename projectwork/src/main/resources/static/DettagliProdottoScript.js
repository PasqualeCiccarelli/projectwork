async function fetchProductData() {
    try {

        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');

        if (!id) {
            throw new Error('ID prodotto non specificato');
        }

        const response = await fetch(`/api/prodotto/dettagli-prodotto?id=${id}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const prodotto = await response.json();
        
        const dettagliCartaDiv = document.querySelector('.dettagli-carta');
        if (!dettagliCartaDiv) {
            throw new Error('Elemento .dettagli-carta non trovato nella pagina');
        }

        dettagliCartaDiv.innerHTML = `
        <div class="prodotto-container">
            <div class="immagine-container">
                <img src="img/${prodotto.immagine}" 
                     alt="${prodotto.nome}" 
                     class="${isCard(prodotto) ? 'card-effect' : 'zoom-effect'}">
            </div>

            <div class="info-container">
                <h1 style="margin: 0 0 20px 0; color: #333;">${prodotto.nome}</h1>
                
                <p><strong>Categoria:</strong> ${prodotto.categoria}</p>
                <p><strong>Descrizione:</strong> ${prodotto.descrizione || 'Nessuna descrizione disponibile'}</p>
                <p><strong>Tipo:</strong> ${prodotto.tipo}</p>
                <p><strong>Brand:</strong> ${prodotto.brand}</p>
                <p><strong>Dettagli Specifici:</strong> ${prodotto.specificDetails || 'Non disponibile'}</p>

                <div class="prezzo-container">
                    ${prodotto.categoria === 'SPECIALE' ? `
                        <p style="text-decoration: line-through; color: #666;">
                            Prezzo originale: €${prodotto.prezzo.toFixed(2)}
                        </p>
                        <p style="color: #e53935; font-size: 1.3em; margin: 10px 0;">
                            <strong>Prezzo scontato: €${prodotto.prezzoScontato.toFixed(2)}</strong>
                        </p>
                    ` : `
                        <p style="font-size: 1.3em; margin: 10px 0;">
                            <strong>Prezzo: €${prodotto.prezzo.toFixed(2)}</strong>
                        </p>
                    `}
                    
                    ${prodotto.disponibilita ? `
                        <p style="color: #28a745; margin: 10px 0;">
                            Disponibile (${prodotto.rimanenza} pezzi rimanenti)
                        </p>
                        <button class="btn-carrello" onclick="aggiungiAlCarrello(${prodotto.id})">
                            Aggiungi al carrello
                        </button>
                    ` : `
                        <p style="color: #dc3545; margin: 10px 0;">Non disponibile</p>
                        <button class="btn-carrello" disabled style="background-color: #6c757d; cursor: not-allowed;">
                            Non disponibile
                        </button>
                    `}
                </div>
            </div>
        </div>
    `;

    if (isCard(prodotto)) {
        const cardImage = document.querySelector('.card-effect');
        if (cardImage) {
            addTiltEffect(cardImage);
        }
    }

    } catch (error) {
        console.error('Errore:', error);
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
           !prodotto.specificDetails.includes('Colore:') && 
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