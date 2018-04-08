var todoform = document.getElementById("todo-form");

todoform.addEventListener("submit", function checkcard(e) {
    var card = document.getElementById("card");
    var card_number = parseFloat(card.value);
    var cardcredit = document.getElementById("card-credit");
    var node = document.createElement("P");
    var textnode = document.createTextNode(
        "Credit card should be written correct!"
    );
    node.appendChild(textnode);

    if (card_number.toString().length == 16) {
        alert("Good");
    } else {
        card.classList.add("red-border");
        cardcredit.appendChild(node);
        e.preventDefault();
    }
});


