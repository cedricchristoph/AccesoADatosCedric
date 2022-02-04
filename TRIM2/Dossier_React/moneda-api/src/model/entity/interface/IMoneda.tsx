import IHistorico from './IHistorico';

export default interface IMoneda {
  idmoneda: number;
  nombre: string;
  pais: string;
  historicos: IHistorico[];
}