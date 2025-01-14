async function addItemToCart(item) {
    const userData = getUserDataFromSessionStorage();
    if (!userData || !userData.email) {
        alert('Per favore, effettua il login per aggiungere prodotti al carrello');
        window.location.href = '/login';
        return;
    }

    function determineProductType(item) {
        const typeMap = {
            'Carta': 'CARTA',
            'Box': 'BOX',
            'Bustina': 'BUSTINA',
            'Accessorio': 'ACCESSORIO'
        };
        return typeMap[item.categoria] || item.categoria.toUpperCase();
    }

    const baseUrl = 'http://localhost:8080/api/ordini';
    const tipoProdotto = determineProductType(item);

    try {
        // Create new order with product
        const creaOrdineRequest = {
            emailUtente: userData.email,
            tipoProdotto: tipoProdotto,
            idProdotto: item.id,
            quantita: 1
        };

        const response = await fetch(`${baseUrl}/crea-ordine-e-aggiungi-prodotto`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(creaOrdineRequest)
        });

        if (!response.ok) {
            // Se la creazione fallisce, proviamo ad aggiungere al carrello esistente
            const userId = await getUserId(userData.email);
            if (userId) {
                const dettaglioRequest = {
                    tipoProdotto: tipoProdotto,
                    productId: item.id,
                    quantita: 1
                };

                const addResponse = await fetch(`${baseUrl}/aggiungi-prodotto?userId=${userId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(dettaglioRequest)
                });

                if (!addResponse.ok) {
                    throw new Error('Errore durante l\'aggiunta del prodotto al carrello esistente');
                }
            } else {
                throw new Error('Errore durante l\'aggiunta del prodotto al carrello');
            }
        }

        await updateCartBadge();
        alert('Prodotto aggiunto al carrello con successo!');

    } catch (error) {
        console.error('Errore dettagliato:', error);
        alert('Si è verificato un errore durante l\'aggiunta del prodotto al carrello: ' + error.message);
    }
}

async function getUserId(email) {
    try {
        const ordineInCorso = await getOrdineInCorso(email);
        return ordineInCorso ? ordineInCorso.userId : null;
    } catch (error) {
        console.error('Errore nel recupero dell\'ID utente:', error);
        return null;
    }
}

async function getOrdineInCorso(email) {
    const baseUrl = 'http://localhost:8080/api/ordini';
    const encodedEmail = encodeURIComponent(email);
    
    try {
        const response = await fetch(`${baseUrl}/in-corso/${encodedEmail}`);
        if (response.ok) {
            return await response.json();
        }
        return null;
    } catch (error) {
        console.error('Errore nel recupero dell\'ordine in corso:', error);
        return null;
    }
}

async function updateCartBadge() {
    const userData = getUserDataFromSessionStorage();
    if (!userData || !userData.email) return;

    try {
        const ordineInCorso = await getOrdineInCorso(userData.email);
        const badge = document.getElementById('cart-badge');
        
        if (ordineInCorso && badge) {
            const numItems = ordineInCorso.dettagliOrdine?.length || 0;
            badge.textContent = numItems;
            badge.style.display = numItems > 0 ? 'flex' : 'none';
        } else if (badge) {
            badge.style.display = 'none';
        }
    } catch (error) {
        console.error('Errore nell\'aggiornamento del badge:', error);
        const badge = document.getElementById('cart-badge');
        if (badge) {
            badge.style.display = 'none';
        }
    }
}

// Inizializza il badge del carrello al caricamento della pagina
document.addEventListener('DOMContentLoaded', () => {
    updateCartBadge();
});