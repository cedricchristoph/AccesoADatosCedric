import React from "react";
interface IProps { }
interface IState { result: number }

class OperarBotones extends React.Component<IProps, IState> {

    divideBtn: any;
    multiplyBtn: any;

    constructor(props: IProps) {
        super(props);
        this.state.result = 100;
        this.divideBtn = "";
        this.multiplyBtn = "";
        reloadButtonTexts();
    }

    function reloadButtonTexts() {
        this.divideBtn = {this.state.result} + " / 2";
        this.multiplyBtn = {this.state.result} + " * 2";
    }

    componentDidMount() {

    }

    componentWillUnmount() {
        
    }

    function divideHandler(event:ChangeEvent<HTMLInputElement>){
        this.setState({result: this.state.result/2});
    }

    function multiplyHandler(event:ChangeEvent<HTMLInputElement>){
        this.setState({result: this.state.result*2});
    }


    render() {
        return (
            <div>
                <h1>Operar Botones</h1>
                <h3>Pulsa los botones para dividir o multiplicar</h3>
                <h2>Resultado:&nbsp;{this.state.result}</h2>
                <input type="button" onChange={divideHandler} 
                value={this.divideBtn}/>
                <input type="button" onChange={multiplyHandler} 
                value={this.multiplyBtn}/>
            </div>
        );
    }
}
export default OperarBotones;