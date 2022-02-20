import IPlato from "./IPlato";

export default interface IDetalleFactura {
    iddetallefactura: Number | undefined,
    cantidad: Number,
    preciounidad: Number,
    plato: IPlato
}