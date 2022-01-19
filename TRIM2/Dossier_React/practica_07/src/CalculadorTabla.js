import React, { useState } from "react";

const CalculadorTabla = (props) => {
    const [contador, incrementar] = useState(1);
    let result = contador * 2;
    return (
        <>
            <h3>Tabla de 2</h3>
            <p>2 * {contador} = {result}</p>
            <button onClick={() => incrementar(contador + 1)}>
                2 * {contador + 1}
            </button>
        </>
    );
}

export default CalculadorTabla;