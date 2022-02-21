import { useNavigate } from 'react-router-dom';

interface IProps {setTokenState: Function}
export default function Logout(props: IProps) {

    let navigate = useNavigate();

    return (
    <>
    {localStorage.removeItem("token")}
    {props.setTokenState("")}
    {navigate("/")}
    </>
    );

}