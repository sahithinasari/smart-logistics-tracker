// src/components/pages/Login.tsx
import React, { useState, useContext } from "react";
import { Button, TextField, Container, Typography } from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../../context/AuthContext";

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const res = await axios.post(
        "http://localhost:8082/realms/smart-logistics-tracker/protocol/openid-connect/token",
        new URLSearchParams({
          grant_type: "password",
          client_id: "logistics-api",
          username: email,
          password: password,
        }),
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      );

      const token = res.data.access_token;
      const payload = JSON.parse(atob(token.split(".")[1]));
      const role = payload.realm_access.roles.includes("ADMIN")
        ? "ADMIN"
        : payload.realm_access.roles.includes("VENDOR")
        ? "VENDOR"
        : "AGENT";
console.log("Token "+token+"Role "+role)
      login(token, role);
      navigate(`/${role.toLowerCase()}`);
    } catch (err) {
      alert("Invalid credentials");
    }
  };

  return (
    <Container maxWidth="sm" style={{ marginTop: '100px' }}>
      <Typography variant="h4">Login</Typography>
      <TextField label="Email" fullWidth margin="normal" value={email} onChange={(e) => setEmail(e.target.value)} />
      <TextField label="Password" type="password" fullWidth margin="normal" value={password} onChange={(e) => setPassword(e.target.value)} />
      <Button variant="contained" fullWidth onClick={handleLogin}>Login</Button>
    </Container>
  );
};

export default Login;
