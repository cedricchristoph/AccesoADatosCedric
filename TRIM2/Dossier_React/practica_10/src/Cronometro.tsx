import React from "react";
interface IProps { }
interface IState { count: number }

class Cronometro extends React.Component<IProps, IState> {
    
    timerID: any;
    tiempo: any;
   

    constructor(props: IProps) {
        super(props);
        tiempo: React.createRef();
        this.state = {count: 0};
    }

    componentDidMount() {

    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    tick() { 
        if (this.state.count == 1)
            clearInterval(this.timerID);
        let {count} = this.state;
        this.setState({ count: count - 1 }); 
    
    }




    render() {
        const iniciarCronometro =()=> {
            //let value = this.tiempo.current.value;
            this.setState({count: 10});
            this.timerID = setInterval(
                () => this.tick(),
                1000
            );
            
        }


        return (
            <div>
                <h1>Cronómetro</h1>
                <h3>Introducir segundos: </h3>
                <input type="text" ref={this.tiempo}/>
                <button onClick={iniciarCronometro}> Start/Stop </button> 
                <h2>Quedan&nbsp;{this.state.count}&nbsp;segundos</h2>
            </div>
        );
    }
}
export default Cronometro;