import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./navbar/Navbar"; // ✅ Add a common navbar

const Layout = () => {
  return (
    <>
      <Navbar /> 
      <Outlet /> {/* This will render the nested routes */}
    </>
  );
};

export default Layout;
