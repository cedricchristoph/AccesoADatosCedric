import axios from "axios";
import { useNavigate } from "react-router-dom";
import ApiUtil from "../model/util/ApiUtil";
import { useState } from 'react';

interface IProps {setTokenState: Function}
interface IState {loginFailed: Boolean}
function Login(props: IProps) {

    let navigate = useNavigate();
    const [state, setState] = useState<IState>({loginFailed: false});

    function save (event:React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let usuarioElement: HTMLInputElement = formulario.username;
        let passwordElement: HTMLInputElement = formulario.password;

        const asynclogin = async () => {
            try {
                let urlParams = "username=" + usuarioElement.value.toString() + "&password=" + passwordElement.value.toString();
                let { data } = await axios.post(ApiUtil.getApiUrl() + "login?" + urlParams);
                let token: string = data;
                if (token.includes("Bearer")) {
                    localStorage.setItem("token", token);
                    props.setTokenState(token);
                    console.log(token);
                    navigate("/mesas");
                }
            } catch (error: any) {
                console.log(error)
                if (error.toString().includes("403")) {
                    setState({loginFailed: true});
                } else {
                    navigate("/connection_error");
                }
            }
        }
        asynclogin();
    }

    return (
        <>
            <div className="wrapper">
                <form className="form-signin" onSubmit={save}>
                    <h2 className="form-signin-heading">Iniciar sesión</h2>
                    <h4>Por favor inicia sesión para avanzar</h4>
                    <input type="text" className="form-control" name="username" placeholder="Usuario" />
                    <input type="password" className="form-control" name="password" placeholder="Contraseña" />
                    {state.loginFailed ? <h4 className="error-msg">Usuario y/o contraseña errónea</h4> : <></>}
                    <button className="submit-button" type="submit">Iniciar sesión</button><br /><br />
                </form>

            </div>

        </>);
}

export default Login;