// src/pages/VendorDashboard.tsx
import React, { useState } from 'react';
import { Container, TextField, Button, Typography } from '@mui/material';
import axios from 'axios';

const VendorDashboard = () => {
  const [form, setForm] = useState({ customerName: '', deliveryAddress: '', vendorId: '' });

  const handleChange = (e: any) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleOrder = async () => {
    const token = localStorage.getItem('token');
    try {
      await axios.post('http://localhost:2045/api/orders', form, {
        headers: { Authorization: `Bearer ${token}` }
      });
      alert('Order placed');
    } catch (err) {
      alert('Failed to place order');
    }
  };

  return (
    <Container>
      <Typography variant="h5">Place Order</Typography>
      <TextField name="vendorId" label="Vendor ID" fullWidth margin="normal" onChange={handleChange} />
      <TextField name="customerName" label="Customer Name" fullWidth margin="normal" onChange={handleChange} />
      <TextField name="deliveryAddress" label="Delivery Address" fullWidth margin="normal" onChange={handleChange} />
      <Button variant="contained" onClick={handleOrder}>Submit Order</Button>
    </Container>
  );
};

export default VendorDashboard;
