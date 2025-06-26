// src/utils/storage.ts
const TOKEN_KEY = "token";
const ROLE_KEY = "role";

const storage = {
  getToken: () => localStorage.getItem(TOKEN_KEY),
  setToken: (token: string) => localStorage.setItem(TOKEN_KEY, token),
  removeToken: () => localStorage.removeItem(TOKEN_KEY),

  getRole: () => localStorage.getItem(ROLE_KEY),
  setRole: (role: string) => localStorage.setItem(ROLE_KEY, role),
  removeRole: () => localStorage.removeItem(ROLE_KEY),
};

export default storage;
