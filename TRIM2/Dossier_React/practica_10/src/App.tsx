import React from 'react';
import './App.css';
import Cronometro from './Cronometro';

function App() {

  return (
    <div className="App">
      <header className="App-header">
        
      </header>

      <body>
        <div>
          <h1>Cronómetro</h1>
          Cantidad segundos: <input type="text" />
          <br />
          <button onClick={iniciarCronometro} > </button>
        </div>
      </body>

    </div>
  );
}

const iniciarCronometro = () => {

}


export default App;
