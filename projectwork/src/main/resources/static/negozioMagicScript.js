async function loadData() {
    try {
        const baseUrl = 'http://localhost:8080/api';

        try {
            const cardResponse = await fetch(`${baseUrl}/card/brand/magic`);
            if (!cardResponse.ok) {
                throw new Error(`Errore nel recupero delle carte: ${cardResponse.statusText}`);
            }
            const cardData = await cardResponse.json();
            console.log('Card Data:', cardData);
            createCarousel('card-container', cardData);
        } catch (error) {
            console.error('Errore carte:', error);
        }

        try {
            const bustineResponse = await fetch(`${baseUrl}/bustina/brand/magic`);
            if (!bustineResponse.ok) {
                throw new Error(`Errore nel recupero delle bustine: ${bustineResponse.statusText}`);
            }
            const bustineData = await bustineResponse.json();
            console.log('Bustine Data:', bustineData);
            createCarousel('bustine-container', bustineData);
        } catch (error) {
            console.error('Errore bustine:', error);
        }

        try {
            const boxResponse = await fetch(`${baseUrl}/box/brand/magic`);
            if (!boxResponse.ok) {
                throw new Error(`Errore nel recupero dei box: ${boxResponse.statusText}`);
            }
            const boxData = await boxResponse.json();
            console.log('Box Data:', boxData);
            createCarousel('box-container', boxData);
        } catch (error) {
            console.error('Errore box:', error);
        }

        try {
            const accessoriResponse = await fetch(`${baseUrl}/accessori/brand/magic`);
            if (!accessoriResponse.ok) {
                throw new Error(`Errore nel recupero degli accessori: ${accessoriResponse.statusText}`);
            }
            const accessoriData = await accessoriResponse.json();
            console.log('Accessori Data:', accessoriData);
            createCarousel('accessori-container', accessoriData);
        } catch (error) {
            console.error('Errore accessori:', error);
        }

    } catch (error) {
        console.error('Errore generale durante il caricamento dei dati:', error);
    }
}

document.addEventListener('DOMContentLoaded', loadData);
