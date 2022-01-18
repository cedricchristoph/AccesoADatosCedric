// APP.js
// Cedric Christoph
const {escribir} = require("./utils/fileManager");
const {crearTabla} = require("./modelo/tabla");

const yargs = require('yargs/yargs')
const { hideBin } = require('yargs/helpers')
const argv = yargs(hideBin(process.argv)).argv

let respuesta = argv.tabla;

if (respuesta != null) {
    escribir("tabla.txt", crearTabla(Number(respuesta)))
        .then(console.log("ok grabado"))
        .catch(err => console.log(err));
} else {
    console.log("No se ha establecido el parámetro de " +
    "entrada\n --tabla");
}