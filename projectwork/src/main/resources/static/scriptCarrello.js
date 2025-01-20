async function loadCart() {
  try {
      const userData = getUserDataFromSessionStorage();
      if (!userData || !userData.email) {
          console.error("Utente non autenticato.");
          window.location.href = '/login';
          return;
      }

      const userEmail = userData.email;
      const baseUrl = 'http://localhost:8080/api';

      const response = await fetch(`${baseUrl}/ordini/${userEmail}/carrello`);
      const cart = await response.json();

      const cartItemsDiv = document.getElementById('cart-items');
      if (!cartItemsDiv) {
          console.error('Elemento cart-items non trovato');
          return;
      }

      if (!cart || !cart.dettagliOrdine || cart.dettagliOrdine.length === 0) {
          cartItemsDiv.innerHTML = '<p>Il carrello è vuoto</p>';
          const cartActions = document.getElementById('cart-actions');
          if (cartActions) {
              cartActions.style.display = 'none';
          }
          return;
      }

      // Raggruppa i prodotti per ID
      const groupedProducts = {};
      cart.dettagliOrdine.forEach(item => {
          const prodId = item.prodottoDto.id;
          if (groupedProducts[prodId]) {
              groupedProducts[prodId].quantita += item.quantita;
          } else {
              groupedProducts[prodId] = {
                  ...item,
                  dettaglioId: item.id
              };
          }
      });

      let totalPrice = 0;
      const cartHtml = Object.values(groupedProducts).map(item => {
      const prodotto = item.prodottoDto;

      let priceHtml = '';
      let prevenditaInfo = '';
      if (item.quantita > 1) {
        
        priceHtml = `
            <p>Prezzo singolo prodotto: €${prodotto.prezzoEffettivo.toFixed(2)}</p>
            <p>Prezzo totale: €${(prodotto.prezzoEffettivo * item.quantita).toFixed(2)}</p>
        `;
      } else {
        // Prezzo per singolo prodotto
        priceHtml = `<p>Prezzo: €${prodotto.prezzoEffettivo.toFixed(2)}</p>`;
      }


      if (prodotto.prezzoEffettivo !== prodotto.prezzo) {
          priceHtml = `
            <p style="text-decoration: line-through; color: gray;">€${prodotto.prezzo.toFixed(2)}</p>
            <p style="font-size: larger;">€${prodotto.prezzoEffettivo.toFixed(2)}</p>
          `;
          }

      if (prodotto.categoria === 'PREVENDITA') {
        prevenditaInfo = `<p>Fino a: ${prodotto.dataInizio}</p>`;
      }

      totalPrice += prodotto.prezzoEffettivo * item.quantita;

      
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

        let styleBustina = "height: 100%; width: auto;";

        if (prodotto.tipoCategoria === "BUSTINA") {
            styleBustina = "height: 500px; width: 270px;";
        }

        if (prodotto.tipoCategoria === "BOX") {
            styleBustina = "height: 500px; width: 400px;";
        }

        if (prodotto.tipoCategoria === "ACCESSORIO") {
            styleBustina = "height: 700px; width: 550px;";
        }

        if (prodotto.brand === "YUGIHO" || prodotto.brand === "MAGIC" && prodotto.tipoCategoria === "BUSTINA") {
            styleBustina = "height: 500px; width: 350px;";
        }

        // Media query for mobile
        const mediaQueryMobile = window.matchMedia("(max-width: 468px)");

        if (mediaQueryMobile.matches) {
            if (prodotto.tipoCategoria === "BUSTINA") {
            styleBustina = "height: 250px; width: 135px;";
            }

            if (prodotto.tipoCategoria === "BOX") {
            styleBustina = "height: 250px; width: 200px;";
            }

            if (prodotto.tipoCategoria === "ACCESSORIO") {
            styleBustina = "height: 350px; width: 275px;";
            }

            if (prodotto.brand === "YUGIHO" || prodotto.brand === "MAGIC" && prodotto.tipoCategoria === "BUSTINA") {
            styleBustina = "height: 250px; width: 175px;";
            }
        }

          return `
          <div class="cart-item d-flex" id="product-${prodotto.id}" style="position:relative;">
              <img src="${imagePath}" alt="${prodotto.nome}" style="${styleBustina}" class="product-image" />
              <div class="puzzettone">
                  <h3>${prodotto.nome}</h3>
                  <p>Categoria: ${prodotto.categoria}</p>
                  ${prevenditaInfo}
                  <p>Rimanenza: ${prodotto.rimanenza}</p>
                  <p>Dettagli specifici: ${prodotto.specificDetails}</p>
                  <p>Quantità: ${item.quantita}</p>
                  ${priceHtml}
                  <button onclick="showRemoveModal(${item.dettaglioId}, ${item.quantita})" class="remove-button">
                      Rimuovi dal carrello
                  </button>
              </div>
          </div>
          `;
      }).join('');

      cartItemsDiv.innerHTML = cartHtml;

      const totalPriceElement = document.getElementById('total-price');
      if (totalPriceElement) {
          totalPriceElement.textContent = `Totale: €${totalPrice.toFixed(2)}`;
      }

      const cartActions = document.getElementById('cart-actions');
      if (cartActions) {
          cartActions.style.display = 'block';
      }
  } catch (error) {
      console.error('Errore nel caricamento del carrello:', error);
      alert('Errore nel caricamento del carrello');
  }
}

function showRemoveModal(dettaglioId, currentQuantity) {
    if (currentQuantity === 1) {
        if (confirm('Vuoi rimuovere questo articolo dal carrello?')) {
            removeFromCart(dettaglioId, 1);
        }
    } else {
        const quantityToRemove = prompt(`Quanti articoli vuoi rimuovere? (1-${currentQuantity})`, "1");
        const quantity = parseInt(quantityToRemove);
  
        if (!isNaN(quantity) && quantity > 0 && quantity <= currentQuantity) {
            removeFromCart(dettaglioId, quantity);
        } else if (quantityToRemove !== null) { // Se l'utente non ha premuto "Annulla"
            alert('Quantità non valida');
        }
    }
  }
  
  async function removeFromCart(dettaglioId, quantity) {
      try {
          const userData = getUserDataFromSessionStorage();
          const response = await fetch('/api/ordini/rimuovi-prodotto', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                  email: userData.email,
                  dettaglioId: dettaglioId,
                  quantita: quantity
              })
          });
    
          if (response.ok) {
              // Decrementa la quantità nel carrello
              aggiornaStickerCarrello(-quantity); // Riduce il conteggio per la quantità rimossa
              loadCart(); // Ricarica il carrello
          } else {
              throw new Error('Errore nella rimozione del prodotto');
          }
      } catch (error) {
          console.error('Errore:', error);
          alert('Errore nella rimozione del prodotto');
      }
  }
  
  async function deleteOrder() {
      if (!confirm('Sei sicuro di voler eliminare l\'intero ordine?')) {
          return;
      }
    
      try {
          const userData = getUserDataFromSessionStorage();
          const response = await fetch('/api/ordini/elimina', {
              method: 'DELETE',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({ email: userData.email })
          });
    
          if (response.ok) {
              alert('Ordine eliminato con successo');
              
              // Resetta il conteggio del carrello nel localStorage
              localStorage.removeItem("cartCount"); // Rimuove il conteggio dal localStorage
              aggiornaStickerCarrello(0); // Rimuove il conteggio e aggiorna il carrello
              loadCart(); // Ricarica il carrello
          } else {
              throw new Error('Errore nell\'eliminazione dell\'ordine');
          }
      } catch (error) {
          console.error('Errore:', error);
          alert('Errore nell\'eliminazione dell\'ordine');
      }
  }
  
  async function completeOrder() {
      const addressInput = document.getElementById('shipping-address');
      if (!addressInput || !addressInput.value.trim()) {
          alert('Inserisci un indirizzo di spedizione valido');
          return;
      }
  
      try {
          const userData = getUserDataFromSessionStorage();
          const response = await fetch('/api/ordini/completa', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                  email: userData.email,
                  indirizzo: addressInput.value.trim()
              })
          });
  
          if (response.ok) {
              alert('Ordine completato con successo!');
              
              // Rimuove il conteggio dal localStorage e aggiorna la visualizzazione
              localStorage.removeItem("cartCount");
              aggiornaStickerCarrello(0); // Rimuove il conteggio e aggiorna il carrello
  
              window.location.href = '/'; // Torna alla pagina principale
          } else {
              throw new Error('Errore nel completamento dell\'ordine');
          }
      } catch (error) {
          console.error('Errore:', error);
          alert('Errore nel completamento dell\'ordine');
      }
  }
  
  function aggiornaStickerCarrello(quantita) {
      const currentCount = parseInt(localStorage.getItem("cartCount")) || 0;
      const newCount = currentCount + parseInt(quantita);
  
      // Se il nuovo conteggio è inferiore a 0, lo settiamo a 0 (non possiamo avere numeri negativi)
      if (newCount < 0) {
          localStorage.setItem("cartCount", 0);
      } else {
          localStorage.setItem("cartCount", newCount);
      }
  
      // Non è necessario più aggiornare uno sticker visibile, quindi non viene chiamata una funzione visiva
  }
  
  function aggiornaStickerVisuale() {
      const cartCount = localStorage.getItem("cartCount");
  
      if (cartCount && parseInt(cartCount) > 0) {
          // Lo sticker non è visibile in questa pagina, ma possiamo gestire il conteggio nel localStorage
          console.log(`Prodotti nel carrello: ${cartCount}`);
      } else {
          console.log("Carrello vuoto");
      }
  }
  
  // Inizializza la pagina
  document.addEventListener('DOMContentLoaded', () => {
      loadCart();
      
      // Aggiungi event listener per i pulsanti principali
      const deleteOrderBtn = document.getElementById('delete-order-btn');
      if (deleteOrderBtn) {
          deleteOrderBtn.addEventListener('click', deleteOrder);
      }
      
      const completeOrderBtn = document.getElementById('complete-order-btn');
      if (completeOrderBtn) {
          completeOrderBtn.addEventListener('click', completeOrder);
      }
  });
  