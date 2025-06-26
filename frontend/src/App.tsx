// src/App.tsx
import React from "react";
import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routings/AppRoutes";
import { AuthProvider } from "./context/AuthContext";
import { ThemeProvider } from "@emotion/react";
import theme from "./components/styles/theme";

const App = () => (
  <AuthProvider>
     <ThemeProvider theme={theme}>
  <Router>
    <AppRoutes />
  </Router>
  </ThemeProvider>
  </AuthProvider>
);

export default App;
