/**
 * Created by szarazdenes on 2017. 05. 03..
 */
function cartAdder() {

    $(".add-to-cart").click(function insertToCart() {
        var idToCart = this.getAttribute("id");
        var pureId = idToCart.slice(8);
        var idProd =JSON.parse(pureId);

        toSession(idProd);

        })

}

function toSession(idProd) {
    $.ajax({
        url: '/addtocart/' + idProd,
        type: 'GET',
    })

}






$(document).ready(function () {

    cartAdder();


});