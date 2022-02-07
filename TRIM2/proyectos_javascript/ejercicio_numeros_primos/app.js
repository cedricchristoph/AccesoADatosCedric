const fs = require('fs');

let limit = 100000;
let primeNumbers = [];

const isPrime = (num) => {
    for (let i = 2; i < num; i++) {
        if (num % i == 0) {
            return false;
        }
    }
    return true;
}

for (let i = 2; i < limit; i++) {
    if (isPrime(i)) {
        console.log(i);
        primeNumbers.push(i + "\n");
    }
}

fs.writeFile(
    "resultados.txt",
    primeNumbers.toString(),
    (err) => {
        if (err)
            console.log("No se pudo guardar");
        else
            console.log("Se ha grabado");
    }
);
