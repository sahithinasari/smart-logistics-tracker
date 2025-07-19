import React, { useContext } from "react";
import { Button, Container, Typography } from "@mui/material";
import { AuthContext } from "../../../context/AuthContext";

const Login = () => {
  const { login } = useContext(AuthContext);

  return (
    <Container maxWidth="sm" style={{ marginTop: '100px', textAlign: 'center' }}>
      <Typography variant="h4" gutterBottom>
        Smart Logistics Tracker
      </Typography>
      <Typography variant="body1" gutterBottom>
        Please log in to continue
      </Typography>
      <Button variant="contained" onClick={login}>
        Login with Keycloak
      </Button>
    </Container>
  );
};

export default Login;
