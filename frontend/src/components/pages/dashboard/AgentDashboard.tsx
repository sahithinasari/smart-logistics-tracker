// src/pages/AgentDashboard.tsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Typography, List, ListItem, Button } from '@mui/material';

const AgentDashboard = () => {
  const [orders, setOrders] = useState([]);

  const fetchOrders = async () => {
    const token = localStorage.getItem('token');
    const res = await axios.get('http://localhost:2045/api/orders', {
      headers: { Authorization: `Bearer ${token}` }
    });
    setOrders(res.data.filter((o: any) => o.status === 'ASSIGNED'));
  };

  const updateStatus = async (orderId: number, status: string) => {
    const token = localStorage.getItem('token');
    await axios.post(`http://localhost:2045/api/deliver/${orderId}/${status}`, null, {
      headers: { Authorization: `Bearer ${token}` }
    });
    fetchOrders();
  };

  useEffect(() => { fetchOrders(); }, []);

  return (
    <Container>
      <Typography variant="h5">Assigned Orders</Typography>
      <List>
        {orders.map((order: any) => (
          <ListItem key={order.id}>
            {order.customerName} - {order.deliveryAddress}
            <Button onClick={() => updateStatus(order.id, 'DELIVERED')}>Mark Delivered</Button>
          </ListItem>
        ))}
      </List>
    </Container>
  );
};

export default AgentDashboard;
