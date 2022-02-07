
import IAsignatura from "./IAsignatura";

export default interface IMatricula {

    idmatricula: Number;
    year: Number;
    asignaturas: Array<IAsignatura>

}