function getUserDataFromSessionStorage() {
    const userData = sessionStorage.getItem('user');
    if (userData) {
        try {
            return JSON.parse(userData);
        } catch (e) {
            console.error("Errore nel parsing dei dati dell'utente.");
            return null;
        }
    }
    return null;
}

async function getUserProfileImage() {
    const userData = getUserDataFromSessionStorage();
    const userEmail = userData ? userData.email : null;

    if (!userEmail) {
        console.error("Email utente non trovata");
        window.location.href = '/login';
        return;
    }

    try {
        const response = await fetch(`/api/utente/${userEmail}`);
        const data = await response.json();
        const profileImage = data.immagine;

        const profileImageElement = document.getElementById("profile-img");
        if (profileImage) {
            profileImageElement.src = `img/profilo/${profileImage}`;
        } else {
            profileImageElement.src = "https://www.w3schools.com/w3images/avatar2.png";
        }
    } catch (error) {
        console.error("Errore nel recupero dell'immagine del profilo:", error);
    }
}

document.getElementById("change-image-btn").addEventListener("click", function() {
    document.getElementById("image-selection").style.display = "block";
});

function selectImage(element) {
    const selectedImg = element.getAttribute("data-img");

    document.getElementById("profile-img").src = `img/profilo/${selectedImg}`;

    document.getElementById("image-selection").style.display = "none";

    const userData = getUserDataFromSessionStorage();
    const userEmail = userData ? userData.email : null;

    if (!userEmail) {
        console.error("Email utente non trovata");
        window.location.href = '/login';
        return;
    }

    updateUserImage(userEmail, selectedImg);
}

async function updateUserImage(email, immagine) {
    const response = await fetch("/api/utente/aggiorna-immagine", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            email: email,
            immagine: immagine
        })
    });

    if (response.ok) {
        alert("Immagine profilo aggiornata!");
    } else {
        alert("Errore nell'aggiornamento dell'immagine.");
    }
}

window.onload = getUserProfileImage;