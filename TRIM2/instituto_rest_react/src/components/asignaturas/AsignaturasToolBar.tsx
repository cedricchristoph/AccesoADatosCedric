import { useNavigate } from 'react-router-dom';
export default function AsignaturaToolBar() {

    let naviagte = useNavigate();

    const onAddClicked = () => {
        naviagte("/asignaturas/add");
    }

    return (
        <>
        <div className="bottom-tool-bar">
            <button id="green-btn" className="tool-bar-button" onClick={onAddClicked}>+</button>
        </div>
        </>
    );
}