
import IAsignatura from "./IAsignatura";

export default interface IMatricula {

    idmatricula: Number | undefined;
    year: Number;
    asignaturas: Array<IAsignatura>

}