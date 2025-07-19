// src/context/AuthContext.tsx
import React, { createContext, useState, useEffect, useCallback, useRef } from "react";
import keycloak from "../keycloak";
import { useNavigate } from "react-router-dom";

interface AuthContextProps {
  isAuthenticated: boolean;
  role: string | null;
  login: () => void;
  logout: () => void;
  getToken: () => Promise<string | undefined>;
}

export const AuthContext = createContext<AuthContextProps>({
  isAuthenticated: false,
  role: null,
  login: () => {},
  logout: () => {},
  getToken: async () => undefined,
});

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [role, setRole] = useState<string | null>(null);
  const initialized = useRef(false);

  const login = useCallback(() => keycloak.login(), []);
  const logout = useCallback(() => keycloak.logout(), []);

  const getToken = async () => {
    try {
      await keycloak.updateToken(30);
      return keycloak.token;
    } catch {
      keycloak.login();
    }
  };
const navigate = useNavigate();
 
  useEffect(() => {
    if (initialized.current) return;
    initialized.current = true;

    keycloak.init({ onLoad: "login-required", pkceMethod: "S256" }).then((authenticated) => {
      setIsAuthenticated(authenticated);

      if (authenticated && keycloak.tokenParsed) {
        const parsed: any = keycloak.tokenParsed;
        const roles: string[] = parsed.realm_access?.roles || [];

        let userRole: string | null = null;
        if (roles.includes("ADMIN")) userRole = "ADMIN";
        else if (roles.includes("VENDOR")) userRole = "VENDOR";
        else if (roles.includes("AGENT")) userRole = "AGENT";

        setRole(userRole);

        // Redirect to dashboard based on role
        if (userRole) {
          navigate(`/${userRole.toLowerCase()}`);
        }

        // Setup token refresh
        const refreshInterval = setInterval(() => {
          keycloak.updateToken(60).catch(() => keycloak.logout());
        }, 60000);
        return () => clearInterval(refreshInterval);
      }
    });
  }, [navigate]);
  return (
    <AuthContext.Provider value={{ isAuthenticated, role, login, logout, getToken }}>
      {children}
    </AuthContext.Provider>
  );
};
