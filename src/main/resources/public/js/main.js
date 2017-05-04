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
        async: true,
        data: 'json',
        success: function (data) {
            var obj = data["numberOfProductsInCart"];
            $("#cart-counter").html(obj);
        },
        error: function() {
            alert("Error")
        }
    });
}


$(document).ready(function () {

    cartAdder();


});