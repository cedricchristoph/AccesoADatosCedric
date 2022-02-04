import axios from 'axios';
import React from 'react';
interface IProps { }
interface IState {
    monedas?: Array<any>;
}
class App extends React.Component<IProps, IState>{
    ip: string;
    puerto: number;
    rutaBase: string;
    rutaMonedas: string;
    constructor(props: IProps) {
        super(props);
        this.state = {
            monedas: []
        };
        this.ip = "localhost";
        this.puerto = 8080;
        this.rutaBase = "http://" + this.ip + ":" + this.puerto + "/api/v1";
        this.rutaMonedas = this.rutaBase + "/monedas";
    }
    render() {
        const { monedas } = this.state;
        return (
            <>
                <h3>Un componente sencillo para monedas</h3>
                <div>
                    Monedas: {JSON.stringify(monedas)}
                </div>
            </>
        );
    }
    public async componentDidMount() {
        let ruta = this.rutaMonedas;
        console.log(ruta);
        let respuesta = await axios.get(ruta);
        console.log(respuesta.data);
        this.setState({ monedas: respuesta.data });
    }
}
export default App;