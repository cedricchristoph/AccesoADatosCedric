import React, { useState } from "react";

const CalculadorTabla = (props) => {
    const [contador, incrementar] = useState(1);
    const [up, cambiarUp] = useState(true);
    let result = contador * 2;
    let text = `2 * ${contador}`;


    const calcular = () => {
        if (contador == 9)
            cambiarUp(false)
        if (contador == 2)
            cambiarUp(true)

        if (up == true) {
            incrementar(contador + 1);
            text = `2 * ${contador}`;
        } else {
            incrementar(contador - 1);
            text = `2 * ${contador}`;
        }
    }

    return (
        <>
            <h3>Tabla de 2</h3>
            <p>2 * {contador} = {result}</p>
            <button onClick={calcular}>
                {text}
            </button>
        </>
    );
}

export default CalculadorTabla;