
// Cedric Christoph
console.log(getTabla(5));

function getTabla(tabla) {
    let limite = 10;
    let respuesta = "";
    for (let i = 1; i <= limite; i++) {
        respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
    }
    return respuesta;
}