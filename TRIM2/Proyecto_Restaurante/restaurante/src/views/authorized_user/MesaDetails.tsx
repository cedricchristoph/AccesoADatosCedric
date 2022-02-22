import { useState, useEffect } from "react";
import IServicio from "../../model/entity/IServicio";
import IMesa from "../../model/entity/IMesa";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import ApiUtil from "../../model/util/ApiUtil";

interface IState {
    mesa: IMesa | undefined;
    servicioActual: IServicio | null;
}

export default function MesaDetails() {
    const [state, setState] = useState<IState>();
    let { mesaid } = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const asyncLoadMesa = async () => {
            try {
                let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
                let url = "v2/mesas/" + mesaid;
                let respuestaMesa = await axios.get(ApiUtil.getApiUrl() + url);
                let respuestaServicios = await axios.get(
                    ApiUtil.getApiUrl() + url + "/servicios",
                    headers
                );
                let servicios: Array<IServicio> = respuestaServicios.data;
                let mesa: IMesa = respuestaMesa.data;
                mesa.servicios = servicios;
                let servicio: IServicio | null = null;
                try {
                    let respuestaServicio = await axios.get(
                        ApiUtil.getApiUrl() + url + "/servicio_actual",
                        headers
                    );
                    servicio = respuestaServicio.data;
                } catch { }
                setState({ mesa: mesa, servicioActual: servicio });
            } catch {
                navigate("/connection_error");
            }
        };
        asyncLoadMesa();
    }, []);

    function abrirServicioActual() {
        navigate(
            "/mesas/" +
            state?.mesa?.nummesa +
            "/servicios/" +
            state?.servicioActual?.idservicio
        );
    }

    function crearServicio() {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        let url = ApiUtil.getApiUrl() + "v2/mesas/" + mesaid;
        const asyncCreateServicio = async () => {
            console.log(url + "/servicios");
            try {
                let {data} = await axios.post(url + "/servicios", headers);
            } catch (error) {
                console.log(error);
                return;
            }
            let {data} = await axios.get(url + "/servicio_actual", headers);
            let servicio : IServicio = data;
            let mesa : IMesa | undefined = state?.mesa;
            setState({mesa: mesa, servicioActual: servicio});
        }
        asyncCreateServicio();
    }

    return (
        <>
            <div className="container">
                {state?.mesa ? 
                <>
                <h1>Mesa {state?.mesa?.nummesa}</h1>
                <p>Ocupación máxima: {state?.mesa?.ocupantesmax} personas</p>
                {state?.servicioActual != null ? (
                    <>
                        <p>Mesa está en uso actualmente</p>
                        <button onClick={abrirServicioActual}>
                            {" "}
                            Abrir servicio actual{" "}
                        </button>
                    </>
                ) : (
                    <button className="submit-button" onClick={crearServicio}>
                        Iniciar nuevo servicio
                    </button>
                )}
                <h3>Servicio anteriores</h3>
                <table>
                    <th>Fecha entrada</th>
                    <th>Fecha salida</th>
                    <th>Reservado a</th>
                    <tbody>
                        {state?.mesa?.servicios?.map((s) => {
                            return (
                                <>
                                    {s.idservicio === state.servicioActual?.idservicio ? (
                                        <tr className="clickable highlight">
                                            <td>{s.fechacomienzo}</td>
                                            <td>{s.fechafin}</td>
                                            <td>{s.reservada}</td>
                                        </tr>
                                    ) : (
                                        <tr className="clickable">
                                            <td>{s.fechacomienzo}</td>
                                            <td>{s.fechafin}</td>
                                            <td>{s.reservada}</td>
                                        </tr>
                                    )}
                                </>
                            );
                        })}
                    </tbody>
                </table>
                </>
                : 
                
                <span className="loader"/>

                }
                
            </div>
        </>
    );
}
