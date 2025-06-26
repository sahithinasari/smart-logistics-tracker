// src/context/AuthContext.tsx
import React, { createContext, useState, useEffect } from "react";
import storage from "../utils/storage";

interface AuthContextProps {
  isAuthenticated: boolean;
  token: string | null;
  role: string | null;
  login: (token: string, role: string) => void;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextProps>({
  isAuthenticated: false,
  token: null,
  role: null,
  login: () => {},
  logout: () => {},
});

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [token, setToken] = useState<string | null>(storage.getToken());
  const [role, setRole] = useState<string | null>(storage.getRole());

  const login = (token: string, role: string) => {
    storage.setToken(token);
    storage.setRole(role);
    setToken(token);
    setRole(role);
  };

  const logout = () => {
    storage.removeToken();
    storage.removeRole();
    setToken(null);
    setRole(null);
  };

  const isAuthenticated = !!token;

  return (
    <AuthContext.Provider value={{ isAuthenticated, token, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
