// fileManager.js
// Cedric Christoph
const fs = require('fs');

async function escribir(fichero, contenido) {
    fs.writeFileSync(fichero, contenido)
}
exports.escribir = escribir;