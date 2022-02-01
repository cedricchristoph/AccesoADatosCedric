import React, { ChangeEvent, useState } from 'react';
import './App.css';
import { Persona } from './Persona';

interface IProps {persona: Persona, modificarPadre: any};
interface IState {persona: Persona, imc: number};

class PersonaCard extends React.Component<IProps, IState> {

    nombreRef: any;
    apellidosRef: any;
    alturaRef: any;
    edadRef: any;
    pesoRef: any;
    modificarPadre: any;


    constructor(props: IProps) {
        super(props);
        this.state = {persona: props.persona, imc: 0};
        this.nombreRef = React.createRef();
        this.apellidosRef = React.createRef();
        this.alturaRef = React.createRef();
        this.edadRef = React.createRef();
        this.pesoRef = React.createRef();
        this.modificarPadre = props.modificarPadre;
    }


    render () {

        const update = (event:ChangeEvent<HTMLInputElement>) => {
            this.setState(
                state => (
                    (state.persona as any)[event.target.id] = event.target.value, state
                ), () => {
                    this.setState({imc: this.state.persona.calcularImc()})
                }
            );
            this.modificarPadre(this.state.persona);
        }

        return (
            <div className='card'> 
                <p>Identificador: <span>{this.state.persona.id}</span></p>
                <div className='input-box'><label htmlFor='nombre'>Nombre:</label><br/>
                <input type="text" 
                placeholder='Nombre' 
                id='nombre' 
                ref={this.nombreRef} onChange={update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='apellidos'>Apellidos:</label><br/>
                <input type="text" placeholder='Apellidos' id='apellidos' ref={this.apellidosRef} onChange={update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='edad'>Edad:</label><br/>
                <input type="text" placeholder='Edad' id='edad' ref={this.edadRef} onChange={update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='altura'>Altura:</label><br/>
                <input type="text" placeholder='Altura' id='altura' ref={this.alturaRef} onChange={update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='peso'>Peso:</label><br/>
                <input type="text" placeholder='Peso' id='peso' ref={this.pesoRef} onChange={update}/>
                <br/></div>
                <input type="button" value="Calcular IMC"/>
                
                <h4>IMC: {isNaN(this.state.imc)? 0: this.state.imc.toFixed(2)}</h4>
            </div>
        );
    }
}

export default PersonaCard;