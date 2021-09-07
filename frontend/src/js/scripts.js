/*!
* Start Bootstrap - Shop Homepage v5.0.3 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

const loginSection = document.querySelector("#viewLogin");
const basketSection = document.querySelector("#viewBasket");
const productsSection = document.querySelector("#viewProduct");
const checkoutSection = document.querySelector("#viewCheckout");
const btnLogin = document.querySelector("#btnLogin");
const btnLogout = document.querySelector("#btnLogout");
const headerTitle = document.querySelector("#headerTitle");
const headerSubTitle = document.querySelector("#headerSubTitle");
const btnBasket = document.querySelector("#btnBasket");
const btnHome = document.querySelector("#btnHome");
const username = document.querySelector("#username");
const password = document.querySelector("#password");
const btnBasketRemove = document.querySelector("#btnBasketRemove");
const btnBasketCheckout = document.querySelector("#btnBasketCheckout");
const sessionStorageKey = "userKey";
const apiUrl = 'http://localhost:8080/';
var listProducts = [];
var basketItems = [];

window.addEventListener('load', () => {
    if (getStoredUser()) {
        viewNavBarAsLoggedin();
        fetch(apiUrl+"/basket/"+getStoredUser().basket)
            .then(resp => resp.json())
            .then(basket =>{
                basketItems = basket.items;
                updateNavBasketCount(basket.products);
            })
    }
    viewByOperationType("products");
}, false);

btnLogin.addEventListener('click', () => {
    viewByOperationType("login");
})

function storeUser(user) {
    let json = JSON.stringify(user);
    sessionStorage.setItem(sessionStorageKey, json);
}

function getStoredUser() {
    let json = sessionStorage.getItem(sessionStorageKey);
    return JSON.parse(json);
}

btnHome.addEventListener('click', () => {
    viewByOperationType("products");
})

btnLogout.addEventListener('click', () => {
    sessionStorage.removeItem(sessionStorageKey);
    location.reload();
})

btnBasketRemove.addEventListener('click', () => {
    basketSection.remove()
    if (basketSection) {
        viewByOperationType("products")
    }

})
btnBasketCheckout.addEventListener('click', ()=>{
    viewByOperationType("checkout")
})

function viewNavBarAsLoggedin() {
    btnLogin.classList.add("btn-check");
    btnLogout.classList.remove("btn-check");
    btnBasket.classList.remove("btn-check");
}

function login() {

    const login = {
        username: username.value,
        password: password.value
    }
    const options = {
        method: "POST",
        body: JSON.stringify(login),
        headers: { 'Content-Type': 'application/json' }
    }

    fetch(apiUrl + "users/login", options)
        .then(response => response.json())
        .then(userLogged => {
            if (userLogged) {
                username.value = "";
                password.value = "";
                storeUser(userLogged);
                viewNavBarAsLoggedin();
                viewByOperationType("products");
            }
        })
        .catch(err => alert("Usuario o contaseña incorrecto."));
}

function addProductToBasket(id) {
    const options = {
        method: "POST",
        //mode: "no-cors",
        headers: { 'Content-Type': 'application/json' }
    }

    fetch(apiUrl + "basket/" + getStoredUser().basket + "/product/" + id, options)
    .then(response => response.json())
    .then(data => updateNavBasketCount(data.products));
};

function updateNavBasketCount(count){
    const basketSpan = document.querySelector("#basketCount");
    basketSpan.textContent = count;
}

function loadBasket() {
    viewByOperationType("basket");
}

function viewByOperationType(operationType) {
    if (operationType == "login") {
        basketSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        loginSection.classList.remove("hide-div");
        renderHeader("Login", "Bienvenidos a esta web.");
    }

    else if (operationType == "basket") {
        loginSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        basketSection.classList.remove("hide-div");
        renderHeader("Basket", "Este sera tu carrito de compras.")
    }

    else if (operationType == "products") {
        loginSection.classList.add('hide-div');
        basketSection.classList.add('hide-div');
        checkoutSection.classList.add('hide-div');
        productsSection.classList.remove("hide-div");
        renderHeader("Products", "Estos son los productos disponibles.");
        loadProductsList();
    }

    else if (operationType == "checkout") {
        loginSection.classList.add('hide-div');
        basketSection.classList.add('hide-div');
        productsSection.classList.add('hide-div');
        checkoutSection.classList.remove("hide-div");
        renderHeader("Checkout", "Realiza tu checkout.")
    }
    else {
        document.write("soy el else")
        console.error("no funciona")
    }
}

function loadProductsList() {
    productsSection.innerHTML = "";
    fetch(apiUrl + "products")
        .then(response => response.json())
        .then(prods => listProducts = prods)
        .then(() => renderProductsList());
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


    const btnAdd = document.createElement("button");
    btnAdd.id = "btnAdd" + product.id;
    btnAdd.className = "btn btn-outline-dark mt-auto";
    btnAdd.disabled = getStoredUser() == undefined;
    btnAdd.textContent = "Agregar";
    btnAdd.addEventListener("click", ev => addProductToBasket(product.id));

    const btnDiv = document.createElement("div");
    btnDiv.className = "text-center";
    btnDiv.append(btnAdd);

    const footer = document.createElement("div");
    footer.className = "card-footer p-4 pt-0 border-top-0 bg-transparent";
    footer.append(btnDiv);

    divCard.append(footer);

    //Adding category badget
    const category = document.createElement("div");
    category.className = "badge bg-dark text-white position-absolute";
    category.style = "top: 0.5rem; right: 0.5rem"
    category.innerHTML = `${product.category}`

    divCard.append(category);

    return divCard;
}

function renderHeader(title, subtitle) {
    if (title) {
        headerTitle.textContent = title;
    }
    if (subtitle) {
        headerSubTitle.textContent = subtitle;
    }
}