// async function loadCart() {
//     try {
//         // Recupera i dati dell'utente loggato
//         const userData = getUserDataFromSessionStorage();
//         if (!userData || !userData.email) {
//             console.error("Utente non autenticato.");
//             window.location.href = '/login';
//             return;
//         }

//         const userEmail = userData.email;

//         // Richiesta GET per recuperare il carrello
//         const response = await fetch(`/api/ordini/carrello?email=${userEmail}`);
//         const cart = await response.json();

//         // Se il carrello è vuoto, visualizza il messaggio
//         if (!cart || !cart.dettagliOrdine || cart.dettagliOrdine.length === 0) {
//             document.getElementById('cart-items').innerHTML = '<p>Il carrello è vuoto</p>';
//             return;
//         }

//         // Costruisci l'HTML per ciascun prodotto nel carrello
//         let totalPrice = 0;
//         const cartHtml = cart.dettagliOrdine.map(item => {
//             const product = item.carta || item.accessorio || item.box || item.bustina;
//             totalPrice += item.prezzo * item.quantita;

//             // HTML per ogni prodotto
//             return `
//             <div class="cart-item">
//                 <h3>${product.nome}</h3>
//                 <p>Prezzo: €${item.prezzo}</p>
//                 <p>Quantità: ${item.quantita}</p>
//                 <p>${product.specificDetails || ''}</p>
//             </div>
//         `;
//         }).join('');  // Unisci tutti gli articoli in un'unica stringa HTML

//         // Inserisci l'HTML generato nel div con id "cart-items"
//         const cartItemsDiv = document.getElementById('cart-items');
//         if (cartItemsDiv) {
//             cartItemsDiv.innerHTML = cartHtml;
//         }

//         // Inserisci il prezzo totale nel div appropriato
//         document.getElementById('total-price').textContent = totalPrice.toFixed(2);
//     } catch (error) {
//         console.error('Errore nel caricamento del carrello:', error);
//         alert('Errore nel caricamento del carrello');
//     }
// }

// function loadCarrello() {
//     const userData = checkUtenteStatus();
//     if (!userData) return;

//     // Recuperiamo l'email dell'utente
//     const email = userData.email;

//     // Chiamata API per ottenere l'ordine in corso
//     fetch(`/carrello?email=${email}`)
//         .then(response => response.json()) // Supponiamo che la risposta sia in formato JSON
//         .then(data => {
//             // Verifica se ci sono prodotti nell'ordine
//             if (data && data.prodotti && Array.isArray(data.prodotti)) {
//                 const cartItemsDiv = document.querySelector('.cart-items');
//                 cartItemsDiv.innerHTML = `
//                     <div class="cart-item ${data.id}">
//                         <h3>${data.nome}</h3>
//                         <p>Prezzo: €${data.prezzo}</p>
//                         <p>Quantità: ${data.quantita}</p>
//                         <p>${data.specificDetails || ''}</p>
//                     </div>
//         `; // Pulisce il contenuto precedente

//                 // Aggiungi i prodotti al div
//                 data.prodotti.forEach(product => {
//                     const productElement = document.createElement('div');
//                     productElement.classList.add('cart-item');
//                     productElement.innerHTML = `
                        
//                     `;
//                     cartItemsDiv.appendChild(productElement);
//                 });
//             } else {
//                 console.log("Nessun prodotto trovato nell'ordine.");
//                 const cartItemsDiv = document.querySelector('.cart-items');
//                 cartItemsDiv.innerHTML = '<p>Il tuo carrello è vuoto.</p>';
//             }
//         })
//         .catch(error => {
//             console.error('Errore nel recupero dell\'ordine:', error);
//             alert("Errore nel recupero dell'ordine.");
//         });
// }

// // Assicurati che il carrello venga caricato quando il DOM è pronto
// document.addEventListener('DOMContentLoaded', loadCart);

// function showRemoveModal(dettaglioId, currentQuantity) {
//     let quantityToRemove = 1;
//     if (currentQuantity > 1) {
//         quantityToRemove = prompt(`Quanti articoli vuoi rimuovere? (1-${currentQuantity})`, "1");
//         quantityToRemove = parseInt(quantityToRemove);

//         if (isNaN(quantityToRemove) || quantityToRemove < 1 || quantityToRemove > currentQuantity) {
//             alert('Quantità non valida');
//             return;
//         }
//     }

//     removeFromCart(dettaglioId, quantityToRemove);
// }

// async function removeFromCart(dettaglioId, quantity) {
//     try {
//         const userEmail = getLoggedUserEmail();
//         const response = await fetch('/api/ordini/rimuovi-prodotto', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({
//                 email: userEmail,
//                 dettaglioId: dettaglioId,
//                 quantita: quantity
//             })
//         });

//         if (response.ok) {
//             loadCart();
//         } else {
//             throw new Error('Errore nella rimozione del prodotto');
//         }
//     } catch (error) {
//         console.error('Errore:', error);
//         alert('Errore nella rimozione del prodotto');
//     }
// }

// async function deleteOrder() {
//     if (!confirm('Sei sicuro di voler eliminare l\'ordine?')) return;

//     try {
//         const userEmail = getLoggedUserEmail();
//         const response = await fetch('/api/ordini/elimina', {
//             method: 'DELETE',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ email: userEmail })
//         });

//         if (response.ok) {
//             alert('Ordine eliminato con successo');
//             location.reload();
//         } else {
//             throw new Error('Errore nell\'eliminazione dell\'ordine');
//         }
//     } catch (error) {
//         console.error('Errore:', error);
//         alert('Errore nell\'eliminazione dell\'ordine');
//     }
// }

// async function completeOrder() {
//     const address = document.getElementById('shipping-address').value;
//     if (!address) {
//         alert('Inserisci un indirizzo di spedizione');
//         return;
//     }

//     try {
//         const userEmail = getLoggedUserEmail();
//         const response = await fetch('/api/ordini/completa', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({
//                 email: userEmail,
//                 indirizzo: address
//             })
//         });

//         if (response.ok) {
//             alert('Ordine completato con successo!');
//             window.location.href = '/conferma-ordine';
//         } else {
//             throw new Error('Errore nel completamento dell\'ordine');
//         }
//     } catch (error) {
//         console.error('Errore:', error);
//         alert('Errore nel completamento dell\'ordine');
//     }
// }

// document.addEventListener('DOMContentLoaded', loadCarrello());

function fetchAndDisplayCartItems() {
    const userData = getUserDataFromSessionStorage();
    const email = userData.email;
  
    fetch(`/api/ordini/carrello?email=${email}`)
      .then(response => response.json())
      .then(data => {
        const cartItemsDiv = document.getElementById('cart-items');
        cartItemsDiv.innerHTML = ''; // Pulisci il div prima di ripopolarlo
  
        data.forEach(product => {
          const card = document.createElement('div');
          card.classList.add('card'); // Aggiungi una classe CSS per lo styling
          card.innerHTML = `
            <div class="card-body ${product.id}">
              <h5 class="card-title">${product.nome}</h5>
              <p class="card-text">${product.quantita}</p>
              <p class="card-text">Prezzo: ${product.prezzo}€</p>
              </div>
          `;
          cartItemsDiv.appendChild(card);
        });
      })
      .catch(error => {
        console.error('Errore durante il recupero dei dati del carrello:', error);
        // Gestisci l'errore, ad esempio mostrando un messaggio all'utente
      });
  }
  
  // Chiama la funzione all'avvio della pagina o in un evento specifico
  fetchAndDisplayCartItems();