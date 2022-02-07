const DOM = {
    n1 : document.getElementById("n1"),
    n2 : document.getElementById("n2"),
    selection : document.getElementById("selection"),
    pageBody : document.querySelector("body"),
    resultLabel : document.getElementById("result")
}

function calcular() {
    let operationResult = 0;
    let a = Number(DOM.n1.value);
    let b = Number(DOM.n2.value);
    setTimeout(
        () => {
            alert("Estoy calculando...")
        }, 0
    );
    switch(DOM.selection.value) {
        case "+":
            operationResult = a + b;
            break;
        case "-":
            operationResult = a - b;
            break;
        case "*":
            operationResult = a * b;
            break;
        case "/":
            operationResult = a / b;
            break;
    }
    setTimeout(
        () => {
            alert("Ya estoy listo");
        }, 3000
    );
    DOM.resultLabel.innerHTML = "Resultado: " + operationResult;
    setTimeout(
        () => {
            alert("Resultado: " + operationResult);
        }, 5000
    );
}