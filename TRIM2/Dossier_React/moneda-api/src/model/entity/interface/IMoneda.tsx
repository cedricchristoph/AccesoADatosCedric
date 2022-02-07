import IHistorico from './IHistorico';

export default interface IMoneda {
  idmoneda: number | null;
  nombre: string;
  pais: string;
  historicos: IHistorico[];
}