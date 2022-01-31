import React, { ChangeEvent, useState } from 'react';
import './App.css';
import { Persona } from './Persona';

interface IProps {persona: Persona};
interface IState {persona: Persona};

class PersonaCard extends React.Component<IProps, IState> {

    nombreRef: any;
    apellidosRef: any;
    alturaRef: any;
    edadRef: any;
    pesoRef: any;


    constructor(props: IProps) {
        super(props);
        this.state = {persona: props.persona};
        this.nombreRef = React.createRef();
        this.apellidosRef = React.createRef();
        this.alturaRef = React.createRef();
        this.edadRef = React.createRef();
        this.pesoRef = React.createRef();
    }

    update(event:ChangeEvent<HTMLInputElement>) {
        let nombre = this.nombreRef.value;
        let apellidos = this.apellidosRef.value;
        let altura = this.alturaRef.value;
        let peso = this.pesoRef.value;
        let edad = this.edadRef.value;
        this.state.persona.actualizar(nombre, apellidos, altura, edad, peso);
    }

    render () {
        return (
            <div className='card'> 
                <p>Identificador: <span>{this.state.persona.id}</span></p>
                <div className='input-box'><label htmlFor='nombre'>Nombre:</label><br/>
                <input type="text" placeholder='Nombre' id='nombre' ref={this.nombreRef} onChange={this.update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='apellidos'>Apellidos:</label><br/>
                <input type="text" placeholder='Apellidos' id='apellidos' ref={this.apellidosRef} onChange={this.update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='edad'>Edad:</label><br/>
                <input type="text" placeholder='Edad' id='edad' ref={this.edadRef} onChange={this.update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='altura'>Altura:</label><br/>
                <input type="text" placeholder='Altura' id='altura' ref={this.alturaRef} onChange={this.update}/>
                <br/></div>
                <div className='input-box'><label htmlFor='peso'>Peso:</label><br/>
                <input type="text" placeholder='Peso' id='peso' ref={this.pesoRef} onChange={this.update}/>
                <br/></div>
                <input type="button" value="Calcular IMC"/>
                <h4>IMC: {this.state.persona.calcularImc}</h4>
            </div>
        );
    }
}

export default PersonaCard;