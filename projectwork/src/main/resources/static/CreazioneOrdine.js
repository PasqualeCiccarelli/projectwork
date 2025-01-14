function handleOrder(item) {
    const existingContainer = document.getElementById('order-form-container');
    if (existingContainer) {
        existingContainer.remove();
    }
 
    const formContainer = document.createElement('div');
    formContainer.id = 'order-form-container';
    Object.assign(formContainer.style, {
        position: 'fixed',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        backgroundColor: '#fff',
        padding: '20px',
        border: '1px solid #ccc',
        boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.2)',
        zIndex: '1000',
        width: '300px',
        textAlign: 'center',
        borderRadius: '8px'
    });

    const title = document.createElement('h3');
    title.textContent = `Ordina ${item.nome}`;
    title.style.marginBottom = '15px';
    formContainer.appendChild(title);

    const quantityLabel = document.createElement('label');
    quantityLabel.textContent = 'Quantità: ';
    quantityLabel.htmlFor = 'quantity-input';
    formContainer.appendChild(quantityLabel);

    const quantityInput = document.createElement('input');
    quantityInput.type = 'number';
    quantityInput.id = 'quantity-input';
    quantityInput.min = 1;
    quantityInput.max = item.rimanenza;
    quantityInput.value = 1;
    Object.assign(quantityInput.style, {
        margin: '10px 0',
        width: '100%',
        padding: '5px',
        boxSizing: 'border-box'
    });
    formContainer.appendChild(quantityInput);

    const confirmButton = document.createElement('button');
    confirmButton.textContent = 'Conferma Ordine';
    Object.assign(confirmButton.style, {
        marginRight: '10px',
        padding: '8px 16px',
        backgroundColor: '#4CAF50',
        color: '#fff',
        border: 'none',
        cursor: 'pointer',
        borderRadius: '4px'
    });

    confirmButton.onclick = async () => {
        const quantity = parseInt(quantityInput.value);
        if (isNaN(quantity) || quantity < 1 || quantity > item.rimanenza) {
            alert(`Inserisci un valore valido tra 1 e ${item.rimanenza}`);
            return;
        }

        const userData = getUserDataFromSessionStorage();
        if (!userData || !userData.email) {
            alert('Errore: utente non autenticato. Riprova.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/ordini/crea-ordine-e-aggiungi-prodotto', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    emailUtente: userData.email,
                    tipoProdotto: item.tipoProdotto,
                    idProdotto: item.id,
                    quantita: quantity
                })
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                throw new Error(`Errore durante la creazione dell'ordine: ${errorMessage}`);
            }

            alert('Ordine creato con successo!');
            formContainer.remove();
        } catch (error) {
            console.error('Errore durante la creazione dell\'ordine:', error);
            alert(`Errore durante la creazione dell'ordine: ${error.message}`);
        }
    };
    formContainer.appendChild(confirmButton);

    const cancelButton = document.createElement('button');
    cancelButton.textContent = 'Annulla';
    Object.assign(cancelButton.style, {
        padding: '8px 16px',
        backgroundColor: '#f44336',
        color: '#fff',
        border: 'none',
        cursor: 'pointer',
        borderRadius: '4px'
    });
    cancelButton.onclick = () => formContainer.remove();
    formContainer.appendChild(cancelButton);

    const buttonContainer = document.createElement('div');
    Object.assign(buttonContainer.style, { marginTop: '15px' });
    buttonContainer.appendChild(confirmButton);
    buttonContainer.appendChild(cancelButton);
    formContainer.appendChild(buttonContainer);

    document.body.appendChild(formContainer);
}