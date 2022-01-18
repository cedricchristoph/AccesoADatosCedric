// APP.js
// Cedric Christoph
const {escribir} = require("./utils/fileManager");
const {crearTabla} = require("./modelo/tabla");

let respuesta = process.argv[2];

if (respuesta != null) {
    escribir("tabla.txt", crearTabla(Number(respuesta)))
        .then(console.log("ok grabado"))
        .catch(err => console.log(err));
} else {
    console.log("No se han establecidos parámetros de entrada")
}