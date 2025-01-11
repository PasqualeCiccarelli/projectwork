const background = document.querySelector("#background");

const btnPokemon = document.querySelector("#btn-pokemon");

const btnYugi = document.querySelector("#btn-yugi");

const btnMagic = document.querySelector("#btn-magic");

btnPokemon.addEventListener("click", () => {
    background.classList.remove("background-base");
    background.classList.remove("background-yugi");
    background.classList.remove("background-magic");
    background.classList.add("background-pokemon");
});

btnYugi.addEventListener("click", () => {
    background.classList.remove("background-base");
    background.classList.remove("background-pokemon");
    background.classList.remove("background-magic");
    background.classList.add("background-yugi");
});

btnMagic.addEventListener("click", () => {
    background.classList.remove("background-base");
    background.classList.remove("background-pokemon");
    background.classList.remove("background-yugi");
    background.classList.add("background-magic");
});

