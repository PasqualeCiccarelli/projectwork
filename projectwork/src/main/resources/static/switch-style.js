const background = document.querySelector("#background");

const btnPokemon = document.querySelector("#btn-pokemon");

const btnYugi = document.querySelector("#btn-yugi");

const btnMagic = document.querySelector("#btn-magic");
const novita = document.getElementById("Novita");
const gadgets = document.getElementById("Gadgets");
const pokemonImg = document.getElementById("pokemon-img");
const yugiImg = document.getElementById("yugi-img");
const magicImg = document.getElementById("magic-img");
const offerteImg = document.getElementById("offerte");
const navbarVideo = document.querySelector("#navbar-video"); 
const navbarVideo2 = document.querySelector("#navbar-video2"); 
const videoSource = document.querySelector("#video"); 
const videoSource2 = document.querySelector("#video2"); 

btnPokemon.addEventListener("click", () => {
  offerteImg.src = "img/immagini-font/Offerte-pokemong.png";
  novita.src = "img/immagini-font/Novita.png";
  gadgets.src = "img/immagini-font/gadgets.png";
  videoSource.src = "img/sfondi/pokemon-video.mp4"; 
  videoSource2.src ="img/sfondi/pikac.mp4" 
  navbarVideo.load(); 
  navbarVideo2.load(); 
  navbarVideo.play(); 
  navbarVideo2.play(); 
});

btnYugi.addEventListener("click", () => {
  offerteImg.src = "img/immagini-font/yugi-offerte.png";
  novita.src = "img/immagini-font/yugi-novita.png";
  gadgets.src = "img/immagini-font/yugi-gadgets.png";
  videoSource.src = "img/sfondi/yugioh.mp4"; 
  videoSource2.src ="img/sfondi/yugifinale.mp4" 
  navbarVideo.load(); 
  navbarVideo.play(); 
  navbarVideo2.load(); 
  navbarVideo2.play(); 
});

btnMagic.addEventListener("click", () => {
  offerteImg.src = "img/immagini-font/magic-offerte.png";
  novita.src = "img/immagini-font/magic-novita.png";
  gadgets.src = "img/immagini-font/magic-gadgets.png";
  videoSource.src = "img/sfondi/magic.mp4"; 
  videoSource2.src = "img/sfondi/magic-back.mp4"; 
  gadgets.classList.add("magic-font");
  navbarVideo.load(); 
  navbarVideo.play(); 
  navbarVideo2.load(); 
  navbarVideo2.play(); 
});

function checkResolutionIcons() {
  if (window.innerWidth>=1024) {
    pokemonImg.src="img/icone-tgc/pokemon-logo.png";
    yugiImg.src="img/icone-tgc/yugioh-logo.png";
    magicImg.src="img/icone-tgc/magic-logo.png";
  }
  else{
    pokemonImg.src="img/icone-tgc/pokeball.png";
    yugiImg.src="img/icone-tgc/millennium-puzzle.png";
    magicImg.src="img/icone-tgc/magic-symbol.png";
  }
}

checkResolutionIcons();

window.addEventListener("resize", ()=> checkResolutionIcons());
