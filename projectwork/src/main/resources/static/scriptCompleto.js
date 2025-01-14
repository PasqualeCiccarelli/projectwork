async function loadData() {
    try {
        const baseUrl = 'http://localhost:8080/api';

        // Carica le carte
        try {
            const cardResponse = await fetch(`${baseUrl}/card/brand/magic`);
            if (!cardResponse.ok) {
                throw new Error(`Errore nel recupero delle carte: ${cardResponse.statusText}`);
            }
            const cardData = await cardResponse.json();
            console.log('Card Data:', cardData);
            createCarousel('card-container', cardData);
        } catch (error) {
            console.error('Errore carte:', error);
        }

        // Carica le bustine
        try {
            const bustineResponse = await fetch(`${baseUrl}/bustina/brand/magic`);
            if (!bustineResponse.ok) {
                throw new Error(`Errore nel recupero delle bustine: ${bustineResponse.statusText}`);
            }
            const bustineData = await bustineResponse.json();
            console.log('Bustine Data:', bustineData);
            createCarousel('bustine-container', bustineData);
        } catch (error) {
            console.error('Errore bustine:', error);
        }

        // Carica i box
        try {
            const boxResponse = await fetch(`${baseUrl}/box/brand/magic`);
            if (!boxResponse.ok) {
                throw new Error(`Errore nel recupero dei box: ${boxResponse.statusText}`);
            }
            const boxData = await boxResponse.json();
            console.log('Box Data:', boxData);
            createCarousel('box-container', boxData);
        } catch (error) {
            console.error('Errore box:', error);
        }

        // Carica gli accessori
        try {
            const accessoriResponse = await fetch(`${baseUrl}/accessori/brand/magic`);
            if (!accessoriResponse.ok) {
                throw new Error(`Errore nel recupero degli accessori: ${accessoriResponse.statusText}`);
            }
            const accessoriData = await accessoriResponse.json();
            console.log('Accessori Data:', accessoriData);
            createCarousel('accessori-container', accessoriData);
        } catch (error) {
            console.error('Errore accessori:', error);
        }

    } catch (error) {
        console.error('Errore generale durante il caricamento dei dati:', error);
    }
}

// Aggiungi l'articolo al carrello

// Funzione per creare il carousel dei prodotti
function createCarousel(containerId, data) {
    const container = document.getElementById(containerId);
    if (!container) {
        console.error(`Container ${containerId} non trovato`);
        return;
    }

    container.innerHTML = '';

    if (!Array.isArray(data) || data.length === 0) {
        const emptySlide = document.createElement('div');
        emptySlide.classList.add('swiper-slide');
        emptySlide.textContent = 'Nessun elemento disponibile';
        container.appendChild(emptySlide);
        return;
    }

    data.forEach(item => {
        const slide = document.createElement('div');
        slide.classList.add('swiper-slide');

        const card = document.createElement('div');
        card.classList.add('product-card');

        // Immagine con percorso corretto
        const img = document.createElement('img');
        img.src = `img/magic/${item.immagine}`;
        img.alt = item.nome;

        const category = document.createElement('p');
        category.classList.add('product-category');
        category.textContent = item.categoria;

        const name = document.createElement('h3');
        name.classList.add('product-name');
        name.textContent = item.nome;

        const price = document.createElement('p');
        price.classList.add('product-price');
        price.textContent = `€ ${item.prezzo.toFixed(2)}`;

        const button = document.createElement('button');
        button.classList.add('order-button');
        button.textContent = 'Ordina';

        // Aggiungi il listener per il click sul bottone
        button.addEventListener('click', () => {
            console.log('Bottone "Ordina" cliccato');
            addItemToCart(item);
        });

        card.appendChild(img);
        card.appendChild(category);
        card.appendChild(name);
        card.appendChild(price);
        card.appendChild(button);
        slide.appendChild(card);
        container.appendChild(slide);
    });

    // Inizializza swiper se lo stai usando per il carousel
    initializeSwiper();
}
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