document.addEventListener("DOMContentLoaded", () => {
    const bustina = document.getElementById("bustina");
    const form = document.getElementById("cardForm");

    bustina.addEventListener("click", () => {
        // Aggiungi la classe "open" per aprire la bustina
        bustina.classList.add("open");

        // Dopo l'animazione di apertura, rimuovi la bustina e mostra il form
        setTimeout(() => {
            bustina.classList.add("hidden");
            form.classList.remove("d-none");
            form.classList.add("fadeIn");
        }, 1000); // Durata dell'animazione in ms
    });
});