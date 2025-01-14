import { updateCartBadge } from 'cartUtils.js';

export async function addItem(productId, productType) {
    const userData = getUserDataFromSessionStorage();
    if (!userData || !userData.email) {
        alert('Utente non autenticato. Effettua il login.');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/ordini/aggiungi-prodotto`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                userId: userData.id,
                tipoProdotto: productType,
                productId: productId,
                quantita: 1
            })
        });

        if (!response.ok) throw new Error('Errore durante l\'aggiunta dell\'articolo');

        alert('Articolo aggiunto con successo!');

        const cartCount = await getCartCount();
        updateCartBadge(cartCount);
    } catch (error) {
        console.error('Errore:', error);
        alert('Impossibile aggiungere l\'articolo. Riprova più tardi.');
    }
}

export async function getCartCount() {
    const userData = getUserDataFromSessionStorage();
    if (!userData || !userData.email) return 0;

    try {
        const response = await fetch(`http://localhost:8080/api/ordini/in-corso/${userData.email}`);
        if (!response.ok) throw new Error('Errore durante il recupero del carrello');

        const ordine = await response.json();
        return ordine.dettagli.reduce((acc, dettaglio) => acc + dettaglio.quantita, 0);
    } catch (error) {
        console.error('Errore:', error);
        return 0;
    }
}

async function removeOneItem(dettaglioId, productId, productType) {
    const quantitaElement = document.getElementById(`quantita-${productId}`);
    const currentQuantita = parseInt(quantitaElement.textContent, 10);

    if (currentQuantita > 1) {
        try {
            const response = await fetch(`http://localhost:8080/api/ordini/aggiungi-prodotto`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    userId: getUserDataFromSessionStorage().id,
                    tipoProdotto: productType,
                    productId: productId,
                    quantita: -1
                })
            });

            if (!response.ok) throw new Error('Errore durante l\'aggiornamento dell\'articolo');
            quantitaElement.textContent = currentQuantita - 1;
        } catch (error) {
            console.error('Errore:', error);
            alert('Impossibile ridurre la quantità. Riprova più tardi.');
        }
    } else {
        if (!confirm('Sei sicuro di voler rimuovere completamente questo articolo dal carrello?')) return;

        try {
            const response = await fetch(`http://localhost:8080/api/ordini/rimuovi/dettaglio/${dettaglioId}`, {
                method: 'DELETE'
            });

            if (!response.ok) throw new Error('Errore durante la rimozione dell\'articolo');
            loadCart();
        } catch (error) {
            console.error('Errore:', error);
            alert('Impossibile rimuovere l\'articolo. Riprova più tardi.');
        }
    }
}

export { addItem, removeOneItem };