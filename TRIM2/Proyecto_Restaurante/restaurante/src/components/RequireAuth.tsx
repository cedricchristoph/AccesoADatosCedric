import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom';
interface IProps {
    children: JSX.Element;
}
export const RequireAuth = ({ children }: IProps) => {
    let autorizado = localStorage.getItem("token");
    if (autorizado) {
        return children
    }
    return <Navigate to="/login" />
}