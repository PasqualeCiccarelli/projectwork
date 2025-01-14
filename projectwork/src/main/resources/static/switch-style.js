const background = document.querySelector("#background");

const btnPokemon = document.querySelector("#btn-pokemon");

const btnYugi = document.querySelector("#btn-yugi");

const btnMagic = document.querySelector("#btn-magic");
const novita = document.getElementById("Novita");
const gadgets = document.getElementById("Gadgets");
const pokemonImg = document.getElementById("pokemon-img");
const yugiImg = document.getElementById("yugi-img");
const magicImg = document.getElementById("magic-img");

const navbarVideo = document.querySelector("#navbar-video"); 
const navbarVideo2 = document.querySelector("#navbar-video2"); 
const videoSource = document.querySelector("#video"); 
const videoSource2 = document.querySelector("#video2"); 

btnPokemon.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-yugi");
  background.classList.remove("background-magic");
  background.classList.add("background-pokemon");
  novita.src = "../static/img/immagini-font/Novita.png";
  gadgets.src = "../static/img/immagini-font/gadgets.png";
  videoSource.src = "../static/img/sfondi/pokemon-video.mp4"; 
  videoSource2.src ="../static/img/sfondi/pikac.mp4" 
  navbarVideo.load(); 
  navbarVideo2.load(); 
  navbarVideo.play(); 
  navbarVideo2.play(); 
});

btnYugi.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-pokemon");
  background.classList.remove("background-magic");
  background.classList.add("background-yugi");
  novita.src = "../static/img/immagini-font/yugi-novita.png";
  gadgets.src = "../static/img/immagini-font/yugi-gadgets.png";
  videoSource.src = "../static/img/sfondi/yugioh.mp4"; 
  videoSource2.src ="../static/img/sfondi/yugifinale.mp4" 
  navbarVideo.load(); 
  navbarVideo.play(); 
  navbarVideo2.load(); 
  navbarVideo2.play(); 
});

btnMagic.addEventListener("click", () => {
  background.classList.remove("background-base");
  background.classList.remove("background-pokemon");
  background.classList.remove("background-yugi");
  background.classList.add("background-magic");
  novita.src = "../static/img/immagini-font/magic-novità.png";
  gadgets.src = "../static/img/immagini-font/magic-gadgets.png";
  videoSource.src = "../static/img/sfondi/magic.mp4";
  navbarVideo.load(); 
  navbarVideo.play(); 
});

function checkResolutionIcons() {
  if (window.innerWidth>=1024) {
    pokemonImg.src="../static/img/icone-tgc/pokemon-logo.png";
    yugiImg.src="../static/img/icone-tgc/yugioh-logo.png";
    magicImg.src="../static/img/icone-tgc/magic-logo.png";
  }
  else{
    pokemonImg.src="../static/img/icone-tgc/pokeball.png";
    yugiImg.src="../static/img/icone-tgc/millennium-puzzle.png";
    magicImg.src="../static/img/icone-tgc/magic-symbol.png";
  }
}

checkResolutionIcons();

window.addEventListener("resize", ()=> checkResolutionIcons());
