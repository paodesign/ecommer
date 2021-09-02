// This file is intentionally blank
// Use this file to add JavaScript to your project

const loginSection = document.querySelector("#viewLogin");
const basketSection = document.querySelector("#viewBasket");
const productsSection = document.querySelector("#viewProduct");
const checkoutSection = document.querySelector("#viewCheckout");
const btnLogin = document.querySelector("#btnLogin");
const sessionStorageKey = "userKey";
const apiUrl = 'http://localhost:8080/';
var listProducts = [];
const headerTitle = document.querySelector("#headerTitle");
const headerSubTitle = document.querySelector("#headerSubTitle");
const btnBasket = document.querySelector("#btnBasket");
const btnHome = document.querySelector("#btnHome");


window.addEventListener('load', () =>{
    viewByOperationType("products");
    loadProductsList();
}, false);

btnLogin.addEventListener('click', ()=>{
    viewByOperationType("login");
})

function storeUser(user){
    sessionStorage.setItem(sessionStorageKey,user);
}

function getStoredUser(){
    sessionStorage.getItem(sessionStorageKey);
}

btnBasket.addEventListener('click', ()=>{
    viewByOperationType("basket");
})

btnHome.addEventListener('click', ()=>{
    viewByOperationType("products");
    loadProductsList();
})


function viewByOperationType(operationType){
    if(operationType == "login"){
        basketSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        loginSection.classList.remove("hide-div");
        renderHeader("Login","Bienvenidos a esta web.")
    }

    else if(operationType == "basket"){
        loginSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        basketSection.classList.remove("hide-div");
        renderHeader("Basket","Este sera tu carrito de compras.")
    }

    else if(operationType == "products"){
        loginSection.classList.add('hide-div');
        basketSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        productsSection.classList.remove("hide-div");
        renderHeader("Products","Estos son los productos disponibles.")
    }

    else if(operationType == "checkout"){
        loginSection.classList.add('hide-div');
        basketSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.remove("hide-div");
        renderHeader("Products","Realiza tu checkout.")
    }
    else{
        document.write("soy el else")
        console.error("no funciona")
    }

}


function loadProductsList() {
    fetch(apiUrl + "products")
        .then(response => response.json())
        .then(prods => listProducts = prods)
        .then(()=>renderProductsList())

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

function renderProductsList() {
    listProducts.forEach(prd => {
        const div = document.createElement("div");
        div.className = "col mb-5";
        div.appendChild(createProductCard(prd));
        document.querySelector('#viewProduct').append(div);

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
                                <div class= "d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                </div>
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

function renderHeader(title, subtitle){
    if(title){
        headerTitle.textContent = title;
    }
    if(subtitle){
        headerSubTitle.textContent = subtitle;
    }
}


