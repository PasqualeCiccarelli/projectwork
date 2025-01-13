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

        sessionStorage.setItem("user", JSON.stringify(data));
        alert("Login completato!");
        window.location.href = '/';

    })
    .catch(error => {
        document.getElementById("errorMessage").innerText = error.message;
    });
});