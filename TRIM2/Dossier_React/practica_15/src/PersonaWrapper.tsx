import React, { useState } from 'react';
import './App.css';
import { Persona } from './Persona';
import PersonaCard from './PersonaCard';

interface IProps {};
interface IState {personas: Array<Persona>};

export default class PersonaWrapper extends React.Component<IProps, IState> {

    count: number;

    constructor (props: IProps) {
        super(props);
        this.state = {personas: new Array};
        this.count = 0;
    }

    addNewPersona = () => {
        this.count++;
        let arr = this.state.personas;
        arr.push(new Persona(this.count));
        this.setState({personas: arr});
    }


    render() {
        const actualizar = (personaActualizada: Persona) => {
            let personas = this.state.personas;
            let index = personas.findIndex((p) => p.id === personaActualizada.id);
            personas[index] = personaActualizada;
            this.setState({personas: personas});
        }
        
        let arr = this.state.personas;
        return (
            <>
            <h1>Lista de personas</h1>
            <div className='wrapper'>
                {arr.map((elemento) => <PersonaCard persona={elemento} modificarPadre={actualizar}/>)}
            </div>   
            <input type="button" onClick={this.addNewPersona} value="Añadir"/>
            </>
        );
    }

}