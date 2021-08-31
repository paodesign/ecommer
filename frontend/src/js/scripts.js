// This file is intentionally blank
// Use this file to add JavaScript to your project

const loginSection = document.querySelector("#viewLogin");
const basketSection = document.querySelector("#viewBasket");
const productsSection = document.querySelector("#viewProduct");
const checkoutSection = document.querySelector("#viewCheckout");


window.addEventListener('load', addToCart, false);



function viewByOperationType(operationType){
    if(operationType == "login"){
        basketSection.className='hide-div',
        productsSection.className='hide-div',
        checkoutSection.className='hide-div'
    }

    else if(operationType == "basket"){
        loginSection.className='hide-div',
        productsSection.className='hide-div',
        checkoutSection.className='hide-div'
    }

    else if(operationType == "products"){
        loginSection.className='hide-div',
        basketSection.className='hide-div',
        checkoutSection.className='hide-div'
    }

    else if(operationType == "checkout"){
        loginSection.className='hide-div',
        basketSection.className='hide-div',
        productsSection.className='hide-div'
    }
    else{
        document.write("soy el else")
        console.error("no funciona")
    }

}



const products = [ 
    {id:1, name:"product1", price: 40, rated: 5}, 
    {id:2, name:"product2", price: 15.5, isSale: true}, 
    {id:3, name:"product3", price: 70, isSale: true},
    {id:4, name:"product4", price: 123, rated: 3}]

function addToCart() {
    viewByOperationType("checkout")
    fetch('http://localhost:8080/products')
        .then(response => response.json())
        .then(prods => loadProductsList(prods))
}
    
function priceFormat(unitPrice) {
    // Create our number formatter.
    var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
    
        // These options are needed to round to whole numbers if that's what you want.
        //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
        //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
    });
    return formatter.format(unitPrice);    
}

function loadProductsList(products) {
    products.forEach(prd => {
        const div = document.createElement("div");
        div.className = "col mb-5";
        div.appendChild(createProductCard(prd));
        document.querySelector('#productsWrapper').append(div);

        const btnAdd = document.querySelector("#btnAdd"+prd.id);
        btnAdd.addEventListener("click", ev => {
            const basketSpan = document.querySelector("#basketCount");
            basketSpan.textContent = prd.id
        })
        
        
    })
}

function createProductCard(product) {

    const divCard = document.createElement("div");
    divCard.id = product.id;
    divCard.className = "card h-100";
    divCard.innerHTML = `<img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <div class="card-body p-4">
                            <div class="text-center">
                                <h5 class="fw-bolder">${product.name}</h5> 
                                ${priceFormat(product.unitPrice)}
                            </div>
                        </div>`

    const footer = document.createElement("div");
    footer.className="card-footer p-4 pt-0 border-top-0 bg-transparent";
    footer.innerHTML=`
    <div class="text-center">
        <a class="btn btn-outline-dark mt-auto" id="btnAdd${product.id}" href="#">Agregar</a>
    </div>`
    divCard.append(footer);


    const category = document.createElement("div");
    category.className="badge bg-dark text-white position-absolute";
    category.style="top: 0.5rem; right: 0.5rem"
    category.innerHTML= `${product.category}`
    divCard.append(category);        
   

    return divCard;
}


