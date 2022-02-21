import IDetalleFactura from "./IDetalleFactura";

export default interface IServicio {
    idservicio: Number | undefined,
    fechacomienzo: String | undefined,
    fechafin: String | undefined,
    pagada: Number | undefined,
    reservada: String | null | undefined,
    detallefacturas: Array<IDetalleFactura> | null | undefined
}