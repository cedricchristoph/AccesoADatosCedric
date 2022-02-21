import IMesa from '../model/entity/IMesa';
import { useNavigate } from 'react-router-dom';

interface IProps {mesa: IMesa}
export default function MesaCard (props: IProps) {

    let navigate = useNavigate();

    function onClick () {
        let ruta = "/mesas/" + props.mesa.nummesa;
        navigate(ruta);
    }

    return (
        <>
        <div className='basic-card' onClick={onClick}>
                <h2>Mesa {props.mesa.nummesa}</h2>
                <p>Ocupación max. {props.mesa.ocupantesmax}</p>
        </div>
        </>
    );
}