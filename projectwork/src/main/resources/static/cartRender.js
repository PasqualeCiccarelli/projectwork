import { addItem, removeOneItem } from 'cartActions.js';

function renderCart(ordine) {
    const orderDetailsContainer = document.getElementById('order-details');
    orderDetailsContainer.innerHTML = '';

    if (!ordine || ordine.dettagli.length === 0) {
        orderDetailsContainer.innerHTML = '<p>Il carrello è vuoto.</p>';
        return;
    }

    const groupedDetails = {};
    ordine.dettagli.forEach((dettaglio) => {
        const key = `${dettaglio.prodotto.tipo}-${dettaglio.prodotto.id}`;
        if (!groupedDetails[key]) {
            groupedDetails[key] = { ...dettaglio };
        } else {
            groupedDetails[key].quantita += dettaglio.quantita;
        }
    });

    Object.values(groupedDetails).forEach((dettaglio) => {
        const itemCard = document.createElement('div');
        itemCard.className = 'order-card';

        itemCard.innerHTML = `
            <img src="${dettaglio.prodotto.immagineUrl || 'placeholder.jpg'}" alt="${dettaglio.prodotto.nome}" class="order-card-image">
            <div class="order-card-info">
                <h4>${dettaglio.prodotto.nome}</h4>
                <p>Prezzo unitario: €${dettaglio.prezzo.toFixed(2)}</p>
                <p>Quantità: <span id="quantita-${dettaglio.prodotto.id}">${dettaglio.quantita}</span></p>
                <div class="order-card-buttons">
                    <button onclick="addItem(${dettaglio.prodotto.id}, '${dettaglio.prodotto.tipo}')">Aggiungi articolo</button>
                    <button onclick="removeOneItem(${dettaglio.id}, ${dettaglio.prodotto.id}, '${dettaglio.prodotto.tipo}')">Elimina articolo</button>
                </div>
            </div>
        `;

        orderDetailsContainer.appendChild(itemCard);
    });
}

export { renderCart };