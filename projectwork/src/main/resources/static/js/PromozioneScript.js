document.getElementById("promoteForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const acceptPolicies = document.getElementById("acceptPolicies").checked;

    const userId = 123;

    fetch(`/api/promote/${userId}?acceptedPolicies=${acceptPolicies}`, {
        method: "POST"
    })
    .then(response => response.json())
    .then(data => alert("Utente promosso a partner con successo"))
    .catch(error => alert("Errore: " + error.message));
});