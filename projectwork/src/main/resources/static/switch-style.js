const background = document.querySelector("#background");

const btnPokemon = document.querySelector("#btn-pokemon");

const btnYugi = document.querySelector("#btn-yugi");

const btnMagic = document.querySelector("#btn-magic");
const novita = document.getElementById("Novita");
const gadgets = document.getElementById("Gadgets");

btnPokemon.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-yugi");
  background.classList.remove("background-magic");
  background.classList.add("background-pokemon");
  novita.src = "../static/img/immagini-font/Novita.png";
  gadgets.src = "../static/img/immagini-font/gadgets.png";
});

btnYugi.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-pokemon");
  background.classList.remove("background-magic");
  background.classList.add("background-yugi");
  novita.src = "../static/img/immagini-font/yugi-novita.png";
  gadgets.src = "../static/img/immagini-font/yugi-gadgets.png";
});

btnMagic.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-pokemon");
  background.classList.remove("background-yugi");
  background.classList.add("background-magic");
  novita.src = "../static/img/immagini-font/magic-novità.png";
  gadgets.src = "../static/img/immagini-font/magic-gadgets.png";
});
