// APP.js
// Cedric Christoph
const {escribir} = require("./utils/fileManager");
const {crearTabla} = require("./modelo/tabla");

escribir("tabla.txt", crearTabla(7))
    .then(console.log("ok grabado"))
    .catch(err => console.log(err));