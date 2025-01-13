document.getElementById("registerForm").addEventListener("submit", function (event) {
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

    const dataNascitaDate = new Date(dataNascita).toISOString().split("T")[0];

    const registerData = {
        nome: nome,
        cognome: cognome,
        email: email,
        password: password,
        dataNascita: dataNascitaDate,
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
        sessionStorage.setItem("user", JSON.stringify(data));
    
        alert("Registrazione completata!");
        window.location.href = "/";
    })
    .catch(error => {
        document.getElementById("errorMessage").innerText = error.message;
        console.log("Errore:", error);
    });
});