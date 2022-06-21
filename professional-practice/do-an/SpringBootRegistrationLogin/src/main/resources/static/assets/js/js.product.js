const PRODUCT = {
    clickMenu: (productType) => {
        document.getElementById("ProductType").innerHTML = productType;
    }
}

// { <li class="sub-nav__item">
// <a class="sub-nav__link" onclick="PRODUCT.clickMenu(PRODUCT_TYPE.one)">Chăm sóc toàn thân vvv</a>
// </li>
// <li class="sub-nav__item">
// <a class="sub-nav__link" onclick="PRODUCT.clickMenu(PRODUCT_TYPE.two)">Khuyến mãi</a>
// </li> }