import { useNavigate } from 'react-router-dom';
import IAlumno from '../../model/entity/IAlumno';

interface IProps {alumno: IAlumno | undefined}

export default function AlumnoDetailToolBar(props: IProps) {

    let navigate = useNavigate();

    const onEditClicked = () => {
        navigate("/alumnos/" + props.alumno?.dni + "/edit");
    }

    const onDeleteClicked = () => {
        navigate("/alumnos/" + props.alumno?.dni + "/delete");
    }

    return (
        <>
        <div className="bottom-tool-bar">
            <button id="blue-btn" className="tool-bar-button" onClick={onEditClicked}>*</button>
            <button id="red-btn" className="tool-bar-button" onClick={onDeleteClicked}>-</button>
        </div>
        </>
    );
}