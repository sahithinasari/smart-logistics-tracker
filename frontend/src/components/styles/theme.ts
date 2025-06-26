import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#edcf5d", // Golden Yellow (as the new primary)
      contrastText: "#010101", // Black text for contrast
    },
    secondary: {
      main: "#a4a4a4", // Gray as the secondary color
      contrastText: "#f2f0ea", // Light text for contrast
    },
    background: {
      default: "#f2f0ea", // Light cream background
      paper: "#ffffff", // White for cards/paper elements
    },
    text: {
      primary: "#010101", // Black text
      secondary: "#a4a4a4", // Grey text
    },
    action: {
      disabled: "#a4a4a4", // Grey for disabled elements
    },
  },
  typography: {
    fontFamily: "Calibri, sans-serif",
  },
  components: {
    MuiTypography: {
      styleOverrides: {
        h4: {
          color: "#212123",
          textAlign: "center",
          textTransform: "capitalize",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          height: "100%",
        },
        h6: {
          color: "#8f8f8f",
          textTransform: "capitalize",
          paddingTop: "0.7%",
        },
        body1: {
          fontSize: "15px",
          "&.error": {
            color: "red",
          },
        },
        body2: {
          fontSize: "12px",
        },
      },
    },
    MuiSvgIcon: {
      styleOverrides: {
        root: {
          color: "#212123", // Icon color
        },
      },
    },
    MuiInputLabel: {
      styleOverrides: {
        root: {
          color: "#212123",
          fontSize: "14px",
          "&.Mui-focused": {
            color: "#212123",
          },
        },
      },
    },
    MuiFormLabel: {
      styleOverrides: {
        root: {
          color: "#212123",
          fontSize: "14px",
          "&.Mui-focused": {
            color: "#212123",
          },
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          marginBottom: 5,
          color: "#212123",
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          fontSize: 17,
          backgroundColor: "#ffffff",
          color: "#212123",
          "&:hover": {
            backgroundColor: "#8f8f8f",
            color: "#ffffff",
          },
        },
      },
    },
    MuiTableContainer: {
      styleOverrides: {
        root: {
          width: "100%",
          height: "auto",
        },
      },
    },
    MuiTableRow: {
      styleOverrides: {
        root: {
          "&:nth-of-type(even)": {
            backgroundColor: "#f5f5f5",
          },
        },
      },
    },
    MuiTableCell: {
      styleOverrides: {
        root: {
          padding: "5px",
        },
      },
    },
  },
});

export default theme;
