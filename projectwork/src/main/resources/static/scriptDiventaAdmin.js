
const diventaAdmin= document.querySelector('.click-diventa-admin');
const bloccoAcc= document.querySelector('.blocco-accettazione');

diventaAdmin.addEventListener('click', () => {
    bloccoAcc.classList.toggle("mostra");
});