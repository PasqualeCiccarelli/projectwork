document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); 

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const loginData = {
        email: email,
        password: password
    };

    fetch("/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Credenziali non valide");
        }
        return response.json();
    })
    .then(data => {

        localStorage.setItem("nome", data.nome);
        
        if (data.ruolo1) {
            localStorage.setItem("ruolo1", data.ruolo1);
        }
        if (data.ruolo2) {
            localStorage.setItem("ruolo2", data.ruolo2);
        }

        if (data.email) {
            localStorage.setItem("email", data.email);
        }
        if (data.id) {
            localStorage.setItem("id", data.id);
        }

    })
    .catch(error => {
        document.getElementById("errorMessage").innerText = error.message;
    });
});