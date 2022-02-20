import IServicio from "./IServicio";

export default interface IMesa {
    nummesa: Number | undefined,
    ocupantesmax: Number,
    servicios: Array<IServicio> | null
}