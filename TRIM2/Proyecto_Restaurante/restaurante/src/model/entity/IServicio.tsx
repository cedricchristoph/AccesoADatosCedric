import IDetalleFactura from "./IDetalleFactura";

export default interface IServicio {
    idservicio: Number | undefined,
    fechacomienzo: String,
    fechafin: String | undefined,
    pagada: Number,
    reservada: String | null,
    detallefacturas: Array<IDetalleFactura> | null
}