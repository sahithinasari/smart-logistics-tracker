// src/App.tsx
import React from "react";
import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routings/AppRoutes";
import { AuthProvider } from "./context/AuthContext";
import { ThemeProvider } from "@emotion/react";
import theme from "./components/styles/theme";

const App = () => (
  <Router>
    <AuthProvider>
      <ThemeProvider theme={theme}>
        <AppRoutes />
      </ThemeProvider>
    </AuthProvider>
  </Router>
);

export default App;
