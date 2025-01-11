document.getElementById("registerForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const cognome = document.getElementById("cognome").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const dataNascita = document.getElementById("dataNascita").value;
    const accettaPolitiche = document.getElementById("accettaPolitiche").checked;

    if (!accettaPolitiche) {
        alert("Devi accettare le politiche aziendali per registrarti.");
        return;
    }
    

    const registerData = {
        nome: nome,
        cognome: cognome,
        email: email,
        password: password,
        dataNascita: dataNascita,
        accettaPolitiche: accettaPolitiche
    };

    fetch("/api/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(registerData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Errore durante la registrazione");
        }
    })
    .then(data => {
        const { id, nome, email, ruolo1, ruolo2 } = data;

        localStorage.setItem("id", id);
        localStorage.setItem("nome", nome);
        localStorage.setItem("email", email);

        if (ruolo1) {
            localStorage.setItem("ruolo1", ruolo1);
        }

        if (ruolo2) {
            localStorage.setItem("ruolo2", ruolo2);
        }

        alert("Registrazione completata! Puoi ora effettuare il login.");
        window.location.href = "/login.html";
    })
    .catch(error => {
        document.getElementById("errorMessage").innerText = error.message;
    });
})