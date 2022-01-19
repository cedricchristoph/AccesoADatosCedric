import React from "react";
const ComponenteApp = () => {
    const datos = ['Cedric', 'Christoph', '2ºDAM'];
    return (
        <>
        <h1>mis datos:</h1>
        <h4>{JSON.stringify(datos)}</h4>
        </>
    )
}
export default ComponenteApp;