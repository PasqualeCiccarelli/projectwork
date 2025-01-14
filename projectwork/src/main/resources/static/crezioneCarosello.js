function createCarousel(containerId, data) {
    const container = document.getElementById(containerId);
    if (!container) {
        console.error(`Container ${containerId} non trovato`);
        return;
    }
    
    container.innerHTML = '';
 
    if (!Array.isArray(data) || data.length === 0) {
        const emptySlide = document.createElement('div');
        emptySlide.classList.add('swiper-slide');
        emptySlide.textContent = 'Nessun elemento disponibile';
        container.appendChild(emptySlide);
        return;
    }
 
    data.forEach(item => {
        const slide = document.createElement('div');
        slide.classList.add('swiper-slide');
 
        const card = document.createElement('div');
        card.classList.add('product-card');
        
        // Immagine con percorso corretto
        const a = document.createElement('a');
        a.href = `/DettagiProdotto.html?id=${item.id}`;  


        const img = document.createElement('img');
        img.src = `img/${item.immagine}`;
        img.alt = item.nome;


        a.appendChild(img);
        /*img.onerror = () => {
            img.src = 'img/placeholder.jpg';
        };*/
 

        const category = document.createElement('p');
        category.classList.add('product-category');
        category.textContent = item.categoria;
 

        const name = document.createElement('h3');
        name.classList.add('product-name');
        name.textContent = item.nome;
 

        const price = document.createElement('p');
        price.classList.add('product-price');
        price.textContent = `€ ${item.prezzo.toFixed(2)}`;
 

        const button = document.createElement('button');
        button.classList.add('order-button');
        button.textContent = 'Ordina';
        

        card.appendChild(a);
        card.appendChild(category);
        card.appendChild(name);
        card.appendChild(price);
        card.appendChild(button);
        slide.appendChild(card);
        container.appendChild(slide);
    });
	
	initializeSwiper();
}