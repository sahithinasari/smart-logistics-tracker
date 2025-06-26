import React, { useContext } from "react";
import { Navigate } from "react-router-dom"
import { AuthContext } from "../../../context/AuthContext";

const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { isAuthenticated,token } = useContext(AuthContext);
console.log("Token in context:", token);
console.log("isAuthenticated:", isAuthenticated);

  return isAuthenticated ? <>{children}</> : <Navigate to="/login" />;
};

export default ProtectedRoute;