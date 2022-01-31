import React from "react";
interface IProps { }
interface IState { texto: number }

class MostrarInput extends React.Component<IProps, IState> {

    input: any;

    constructor(props: IProps) {
        super(props);
        this.state.texto = "";
        input: React.createRef();

    }

    componentDidMount() {

    }

    componentWillUnmount() {
        
    }

    tick() { 
        if (this.state.count == 1)
            clearInterval(this.timerID);
        let {count} = this.state;
        this.setState({ count: count - 1 }); 
    }

    function handleChange(event:ChangeEvent<HTMLInputElement>){
        this.setState({texto: this.input});
    }


    render() {
        return (
            <div>
                <h1>Introducir texto</h1>
                <input type="text" ref={this.input} onChange={handleChange}/>
                <h2>Texto:&nbsp;{this.state.texto}</h2>
            </div>
        );
    }
}
export default MostrarInput;