// Cedric Christoph
const fs = require('fs');

function getTabla(tabla) {
    let limite = 10;
    let respuesta = "";
    for (let i = 1; i <= limite; i++) {
        respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
    }
    return respuesta;
}

fs.writeFile(
    'tabla.txt',
    getTabla(4),
    (err) => {
        if (err)
            console.log("No se pudo guardar");
        else
            console.log("Se ha grabado");
    }
);