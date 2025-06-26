// src/routers.tsx
import React, { useContext } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import VendorDashboard from "../components/pages/dashboard/VendorDashboard";
import AgentDashboard from "../components/pages/dashboard/AgentDashboard";
import AdminDashboard from "../components/pages/dashboard/AdminDashboard";
import Login from "../components/pages/Auth/Login";
import { AuthContext } from "../context/AuthContext";
import ProtectedRoute from "../components/pages/Auth/ProtectedRoute";
import Layout from "../components/Layout";


const AppRoutes = () => {
  const {role } = useContext(AuthContext);

  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Layout />}>
      <Route
        path="/vendor"
        element={
          <ProtectedRoute>
            {role === "VENDOR" ? <VendorDashboard /> : <Navigate to="/login" />}
          </ProtectedRoute>
        }
      />
      <Route
        path="/agent"
        element={
          <ProtectedRoute>
            {role === "AGENT" ? <AgentDashboard /> : <Navigate to="/login" />}
          </ProtectedRoute>
        }
      />
      <Route
        path="/admin"
        element={
          <ProtectedRoute>
            {role === "ADMIN" ? <AdminDashboard /> : <Navigate to="/login" />}
          </ProtectedRoute>
        }
      />
      <Route path="*" element={<Navigate to="/login" />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;
