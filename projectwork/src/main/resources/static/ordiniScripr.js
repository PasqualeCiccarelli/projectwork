const userData = getUserDataFromSessionStorage();
const userEmail = userData ? userData.email : null;

if (!userEmail) {
    console.error("Email utente non trovata");
    window.location.href = '/login';
}

document.getElementById('btn-miei-ordini').addEventListener('click', () => {
    document.getElementById('miei-ordini-section').classList.remove('d-none');
    document.getElementById('ordini-in-arrivo-section').classList.add('d-none');
    loadOrdiniConsegnati(userEmail);
});

document.getElementById('btn-ordini-in-arrivo').addEventListener('click', () => {
    document.getElementById('ordini-in-arrivo-section').classList.remove('d-none');
    document.getElementById('miei-ordini-section').classList.add('d-none');
    loadOrdiniInArrivo(userEmail);
});

// function loadOrdiniConsegnati(email) {
//     if (!email) {
//         console.error("Email non valida per caricare gli ordini consegnati.");
//         return;
//     }

//     fetch(`/api/ordini/consegnati/${email}`)
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error(`Errore durante il caricamento: ${response.status}`);
//             }
//             return response.json();
//         })
//         .then(data => {
//             const container = document.getElementById('miei-ordini-container');
//             container.innerHTML = '';

//             if (data.length === 0) {
//                 container.innerHTML = `<p class="text-muted">Nessun ordine consegnato trovato.</p>`;
//                 return;
//             }

//             let imagePath = dettaglio.prodottoDto.immagine;
//             switch (dettaglio.prodottoDto.brand) {
//                 case "POKEMON":
//                     imagePath = `img/pokemon/${dettaglio.prodottoDto.immagine}`;
//                     break;
//                 case "YUGIHO":
//                     imagePath = `img/Yu-Gi-Oh/${dettaglio.prodottoDto.immagine}`;
//                     break;
//                 case "MAGIC":
//                     imagePath = `img/magic/${dettaglio.prodottoDto.immagine}`;
//                     break;
//                 default:
//                     imagePath = `img/placeholder.jpg`;
//                 }

            
            

//             data.forEach(ordine => {
//                 const orderCard = document.createElement('div');
//                 orderCard.classList.add('card', 'order-card');
//                 orderCard.innerHTML = `
//                     <h5>Ordine del ${ordine.data}</h5>
//                     <p><strong>Prezzo Totale:</strong> €${ordine.prezzoTotale.toFixed(2)}</p>
//                     <div class="product-list">
//                         ${ordine.dettagliOrdine.map(dettaglio => `
//                             <div class="product-item">
//                                 <img src="${imagePath || 'placeholder.png'}" alt="${dettaglio.prodottoDto.nome || 'Prodotto'}" />
//                                 <p>${dettaglio.prodottoDto.nome || 'Prodotto senza nome'}</p>
//                                 <p>Quantità: ${dettaglio.quantita}</p>
//                             </div>
//                         `).join('')}
//                     </div>
//                 `;
//                 container.appendChild(orderCard);
                
//             });
//         })
//         .catch(error => {
//             console.error("Errore durante il caricamento degli ordini consegnati:", error);
//             document.getElementById('miei-ordini-container').innerHTML = `<p class="text-danger">Errore durante il caricamento degli ordini. Riprova più tardi.</p>`;
//         });
// }

// function loadOrdiniInArrivo(email) {
//     fetch(`/api/ordini/in-arrivo/${email}`)
//         .then(response => response.json())
//         .then(data => {
//             const container = document.getElementById('ordini-in-arrivo-container');
//             container.innerHTML = '';
//             const stati = ['ORDINATO', 'SPEDITO', 'IN_CONSEGNA'];

            
//             stati.forEach(stato => {
//                 const ordiniByStato = data.filter(ordine => ordine.stato === stato);
//                 if (ordiniByStato.length > 0) {
//                     const statoSection = document.createElement('div');
//                     statoSection.innerHTML = `
//                         <h5>${stato}</h5>
//                         <div class="product-list">
//                             ${ordiniByStato.map(ordine => `
//                                 <div class="card order-card">
//                                     <h5>Ordine - ${ordine.data}</h5>
//                                     <div class="product-list">
//                                         ${ordine.dettagliOrdine.map(dettaglio => `
//                                             <div class="product-item">
//                                                 <img src="${dettaglio.prodottoDto.immagine}" alt="${dettaglio.prodottoDto.nome}" />
//                                                 <p>${dettaglio.prodottoDto.nome}</p>
//                                             </div>
//                                         `).join('')}
//                                     </div>
//                                     ${stato === 'ORDINATO' ? `
//                                         <button class="btn btn-danger mt-3" onclick="eliminaOrdine('${email}', ${ordine.id})">Annulla Ordine</button>
//                                     ` : ''}
//                                 </div>
//                             `).join('')}
//                         </div>
//                     `;
//                     container.appendChild(statoSection);
//                 }
//             });
//         });
// }

function eliminaOrdine(email, ordineId) {
    if (confirm("Sei sicuro di voler annullare questo ordine?")) {
        fetch(`/api/ordini/elimina-ordine?email=${encodeURIComponent(email)}&ordineId=${ordineId}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert("Ordine annullato con successo.");
                loadOrdiniInArrivo(email); // Ricarica gli ordini
            } else {
                alert("Errore durante l'annullamento dell'ordine.");
            }
        })
        .catch(error => {
            console.error("Errore durante la richiesta di annullamento:", error);
            alert("Errore durante l'annullamento dell'ordine.");
        });
    }
}















function loadOrdiniInArrivo(email) {
    if (!email) {
        console.error("Email non valida per caricare gli ordini.");
        return;
    }

    // Carica ordini consegnati
    fetch(`/api/ordini/consegnati/${email}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Errore durante il caricamento degli ordini consegnati: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const container = document.getElementById('miei-ordini-container');
            container.innerHTML = '';

            if (data.length === 0) {
                container.innerHTML = `<p class="text-muted">Nessun ordine consegnato trovato.</p>`;
                return;
            }

            data.forEach(ordine => {
                const ordineDiv = document.createElement('div');
                ordineDiv.className = 'order-card';

                ordineDiv.innerHTML = `
                    <h5>Ordine consegnato - ${ordine.data}</h5>
                    <div class="product-list">
                        ${ordine.dettagliOrdine.map(dettaglio => `
                            <div class="product-item">
                                <img src="${getImagePath(dettaglio.prodottoDto)}" alt="${dettaglio.prodottoDto.nome}" />
                                <p style="color: black;">${dettaglio.prodottoDto.nome}</p>
                            </div>
                        `).join('')}
                    </div>
                `;

                container.appendChild(ordineDiv);
            });
        })
        .catch(error => console.error("Errore nel caricamento degli ordini consegnati:", error));

    // Carica ordini in arrivo
    fetch(`/api/ordini/in-arrivo/${email}`)
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('ordini-in-arrivo-container');
            container.innerHTML = '';
            const stati = ['ORDINATO', 'SPEDITO', 'IN_CONSEGNA'];

            if (data.length === 0) {
                container.innerHTML = `<p class="text-muted">Nessun ordine consegnato trovato.</p>`;
                return;
            }

            stati.forEach(stato => {
                const ordiniByStato = data.filter(ordine => ordine.stato === stato);
                if (ordiniByStato.length > 0) {
                    const statoSection = document.createElement('div');
                    statoSection.innerHTML = `
                        <h5>${stato}</h5>
                        <div class="product-list">
                            ${ordiniByStato.map(ordine => `
                                <div class="card order-card">
                                    <h5>Ordine - ${ordine.data}</h5>
                                    <div class="product-list">
                                        ${ordine.dettagliOrdine.map(dettaglio => `
                                            <div class="product-item">
                                                <img src="${getImagePath(dettaglio.prodottoDto)}" alt="${dettaglio.prodottoDto.nome}" />
                                                <p style="color:black;">${dettaglio.prodottoDto.nome}</p>
                                            </div>
                                        `).join('')}
                                    </div>
                                    ${stato === 'ORDINATO' ? `
                                        <button class="btn btn-danger mt-3" onclick="eliminaOrdine('${email}', ${ordine.id})">Annulla Ordine</button>
                                    ` : ''}
                                </div>
                            `).join('')}
                        </div>
                    `;
                    container.appendChild(statoSection);
                }
            });
        })
        .catch(error => console.error("Errore nel caricamento degli ordini in arrivo:", error));
}

// Funzione per determinare il percorso dell'immagine in base al brand
function getImagePath(prodottoDto) {
    let imagePath = prodottoDto.immagine;

    switch (prodottoDto.brand) {
        case "POKEMON":
            imagePath = `img/pokemon/${prodottoDto.immagine}`;
            break;
        case "YUGIHO":
            imagePath = `img/Yu-Gi-Oh/${prodottoDto.immagine}`;
            break;
        case "MAGIC":
            imagePath = `img/magic/${prodottoDto.immagine}`;
            break;
        default:
            imagePath = `img/placeholder.jpg`;
            break;
    }

    return imagePath;
}
