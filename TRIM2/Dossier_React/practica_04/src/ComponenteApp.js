import React from "react";
import PropTypes from 'prop-types';

const ComponenteApp = (props) => {

    let result = props.num1 + props.num2;

    return (
        <>
        <h1>Valores recibidos:</h1>
        <h4>{JSON.stringify(props)}</h4>
        <br/>
        <h4>La suma de {props.num1} y {props.num2} es {result}</h4>
        </>
    )
}

ComponenteApp.propTypes = {
    num1: PropTypes.number.isRequired,
    num2: PropTypes.number.isRequired
}

export default ComponenteApp;