import React, { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  MenuItem,
  Select,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid
} from '@mui/material';


const AdminDashboard = () => {
  const [summary, setSummary] = useState<any>(null);
  const [orders, setOrders] = useState<any[]>([]);
  const [status, setStatus] = useState('');

  const token = localStorage.getItem('token');

  const fetchSummary = async () => {
    const res = await fetch('http://localhost:2045/api/admin/dashboard', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await res.json();
    setSummary(data);
  };

  const fetchOrders = async () => {
    const url = status
      ? `http://localhost:2045/api/admin/status?status=${status}`
      : 'http://localhost:2045/api/admin/status';

    const res = await fetch(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const data = await res.json();
    setOrders(data);
  };

  useEffect(() => {
    fetchSummary();
    fetchOrders();
  }, [status]);

  return (
      <Container>
      <Typography variant="h4" gutterBottom>
        Admin Dashboard
      </Typography>

      {/* Summary Cards */}
      <Grid container spacing={2} marginBottom={4}>
        {summary && (
          <>
            <Grid >
              <Paper sx={{ p: 2, textAlign: 'center' }}>
                <Typography variant="subtitle1">Total Orders</Typography>
                <Typography variant="h6">{summary.totalOrders}</Typography>
              </Paper>
            </Grid>
            <Grid size={{ xs: 6, sm: 3 }} >
              <Paper sx={{ p: 2, textAlign: 'center' }}>
                <Typography variant="subtitle1">Failed Orders</Typography>
                <Typography variant="h6">{summary.failedOrders}</Typography>
              </Paper>
            </Grid>
            <Grid size={{ xs: 6, sm: 3 }}>
              <Paper sx={{ p: 2, textAlign: 'center' }}>
                <Typography variant="subtitle1">Agents</Typography>
                <Typography variant="h6">{summary.agents}</Typography>
              </Paper>
            </Grid>
            <Grid size={{ xs: 6, sm: 3 }}>
              <Paper sx={{ p: 2, textAlign: 'center' }}>
                <Typography variant="subtitle1">Zones</Typography>
                <Typography variant="h6">{summary.zones}</Typography>
              </Paper>
            </Grid>
          </>
        )}
      </Grid>

      {/* Filter */}
      <Select
        value={status}
        onChange={(e) => setStatus(e.target.value)}
        displayEmpty
        fullWidth
        sx={{ mb: 3 }}
      >
        <MenuItem value="">All</MenuItem>
        <MenuItem value="DELIVERED">Delivered</MenuItem>
        <MenuItem value="FAILED">Failed</MenuItem>
        <MenuItem value="IN_PROGRESS">In Progress</MenuItem>
      </Select>

    {/*  Orders Table */}
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Order ID</TableCell>
            <TableCell>Status</TableCell>
            <TableCell>Assigned Agent</TableCell>
            <TableCell>Zone</TableCell>
            <TableCell>Customer</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {orders.map((order: any) => (
            <TableRow key={order.id}>
              <TableCell>{order.id}</TableCell>
              <TableCell>{order.status}</TableCell>
              <TableCell>{order.agent?.name || 'Unassigned'}</TableCell>
              <TableCell>{order.agent?.zone || '-'}</TableCell>
              <TableCell>{order.customerName}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table> 
    </Container>
  );
};

export default AdminDashboard;
