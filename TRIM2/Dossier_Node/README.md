# Dossier Node
Esta página contiene todas las prácticas propuestas.

## Practica 01
Crear una aplicación que al ejecutar:
```ruby
node app.js
```
nos muestre en pantalla nuestro nombre completo.

**app.js**
```ruby
console.log("Cedric Christoph");
```

## Practica 03
Crear la aplicación descrita
```ruby
console.log(getTabla(5));

function getTabla(tabla) {
    let limite = 10;
    let respuesta = "";
    for (let i = 1; i <= limite; i++) {
        respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
    }
    return respuesta;
}
```

## Practica 04
Crear la aplicación descrita, pero si hay un error no mostrarlo sino mostrar un mensaje que diga que no se pudo grabar.
```ruby
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
```

## Practica 05
Crear la aplicación descrita. El fichero: manejarTabla.js debe exportar un método: escribir(nombreDelFichero, textoEscribir ) que tiene que devolver una promesa. En el interior del método escribir() se llama a la función: fs.writeFileSync()

**model > tabla.js**
```ruby
function getTabla(tabla) {
    let limite = 10;
    let respuesta = "";
    for (let i = 1; i <= limite; i++) {
        respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
    }
    return respuesta;
}
exports.crearTabla = getTabla;
```

**utils > fileManager.js**
```ruby
const fs = require('fs');

async function escribir(fichero, contenido) {
    fs.writeFileSync(fichero, contenido)
}
exports.escribir = escribir;
```

**app.js**
```ruby
const {escribir} = require("./utils/fileManager");
const {crearTabla} = require("./modelo/tabla");

escribir("tabla.txt", crearTabla(7))
    .then(console.log("ok grabado"))
    .catch(err => console.log(err));
```

## Practica 06
Variar la aplicación que tenemos para grabar una tabla en un fichero para que le pasemos como parámetro el número del que queremos que nos haga la tabla.
```ruby
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
```

## Practica 07
Variar el ejercicio de la tabla con yargs y nos construya la tabla de un valor ( en el ejemplo vemos el 8 ) mediante:
```ruby
node app --tabla=8
```
**app.js**
```ruby
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
```

## Practica 08
Crear el ejercicio descrito. Modificar el puerto al 8000 comprobar que arranca correctamente y pasarle diferentes parámetros. Tomar captura de pantalla de lo obtenido.

**app.js**
```ruby
const http = require('http');
const url = require('url');

const port = 9000;
http.createServer( (req, res) => {
    res.write(`Server running on port ${port}`);
    const queryObject = url.parse(req.url, true).query;
    console.log(queryObject);
    let text = "";
    Object.entries(queryObject)
        .forEach( (par) => text += (par[0] + ": " + par[1]));
    res.write(` Received parameter ${text}`);
    res.end();
}).listen(port);
```

