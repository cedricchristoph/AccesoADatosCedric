import http from "./http-common";
import IMoneda from "../model/entity/interface/IMoneda";

const selectAll = () => {
    return http.get<Array<IMoneda>>("");
}

const selectById = (id?: string) => {
    return http.get<IMoneda>(`${id}`);
}

const insert = (data: IMoneda) => {
    return http.post<IMoneda>("", data);
}

const update = (id: string, data: IMoneda) => {
    return http.put<any>(`${id}`, data);
}

const remove = (id: string) => {
    return http.delete<any>(`${id}`);
}

const MonedaService = {
    selectAll,
    selectById,
    insert,
    update,
    remove
}

export default MonedaService;