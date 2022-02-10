import { useNavigate } from 'react-router-dom';
export default function AlumnosToolBar() {

    let naviagte = useNavigate();

    const onAddClicked = () => {
        naviagte("/alumnos/add");
    }

    return (
        <>
        <div className="bottom-tool-bar">
            <button id="green-btn" className="tool-bar-button" onClick={onAddClicked}>+</button>
        </div>
        </>
    );
}