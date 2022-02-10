import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IAsignatura from "../../model/entity/IAsignatura";

interface IProps {asignatura: IAsignatura}
interface IState {asignatura: IAsignatura}

export default function AsignaturaCard(props: IProps) {

    const [stasignatura, setStAsignatura] = useState<IState>({asignatura: props.asignatura});
    let navigate = useNavigate();

    const asignaturaClicked = () => {
        let route = "/asignaturas/" + stasignatura.asignatura.idasignatura;
        navigate(route);
    }

    const eliminarClicked = () => {
        navigate("/asignaturas/" + stasignatura.asignatura.idasignatura + "/delete");
    }

    return (
        <>
        <div className="asignatura-card">
            <div className="top-tool-bar">
                <button className="top-tool-button top-tool-delete" onClick={eliminarClicked}>✕</button>
            </div>
            <div onClick={asignaturaClicked}>
                <h3>{stasignatura.asignatura.nombre}</h3>
                <p>{stasignatura.asignatura.curso}</p>
            </div>
        </div>
        </>
    );

}