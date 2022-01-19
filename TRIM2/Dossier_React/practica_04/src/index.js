import React from "react";
import ReactDOM from 'react-dom';
import ComponenteApp from './ComponenteApp';

const divRoot = document.getElementById("root");
ReactDOM.render( <ComponenteApp num1={5} num2={3} />, divRoot);

