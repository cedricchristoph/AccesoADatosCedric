export class Persona {

    id: number;
    nombre: string;
    apellidos: string;
    altura: number;
    edad: number;
    peso: number;

    constructor(id: number) {
        this.id = id;
        this.nombre = "";
        this.apellidos = "";
        this.altura = 0;
        this.edad = 0;
        this.peso = 0;
    }

    actualizar(nombre: string, apellidos: string, altura: number, edad: number, peso:number) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
    }

    calcularImc() {
        return (this.peso / (this.altura*this.altura));
    }

}