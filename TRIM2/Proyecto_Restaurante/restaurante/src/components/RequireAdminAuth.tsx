import React from 'react'
import { Navigate, useNavigate } from 'react-router-dom';
interface IProps {
    children: JSX.Element;
}
export const RequireAdminAuth = ({ children }: IProps) => {
    let token = localStorage.getItem("token");
    if (token == null) {return <Navigate to="/login" />}
    const isAdmin = JSON.stringify(JSON.parse(atob(token.split('.')[1])));
    console.log(isAdmin);
    if (isAdmin.includes("ROLE_ADMIN")) {
        return children
    }
    return <Navigate to="/no_authorization" />
}