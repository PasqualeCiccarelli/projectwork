function getUserDataFromSessionStorage() {
    const userData = sessionStorage.getItem('user');
    if (userData) {
        try {
            return JSON.parse(userData);
        } catch (e) {
            console.error("Errore nel parsing dei dati dell'utente.", e);
        }
    }
    return null;
}

async function loadCart() {
    const userData = getUserDataFromSessionStorage();
    if (!userData || !userData.email) {
        alert('Utente non autenticato. Effettua il login.');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/ordini/in-corso/${userData.email}`);
        if (!response.ok) throw new Error('Errore nel recupero dell\'ordine in corso');

        const ordine = await response.json();
        renderCart(ordine);
        updateTotal(ordine.totale);
    } catch (error) {
        console.error('Errore:', error);
        alert('Impossibile caricare il carrello. Riprova più tardi.');
    }
}

function updateTotal(total) {
    const totalElement = document.getElementById('order-total');
    if (totalElement) {
        totalElement.textContent = total.toFixed(2);
    }
}

export { getUserDataFromSessionStorage, loadCart, updateTotal };