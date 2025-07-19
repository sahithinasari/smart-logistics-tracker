import Keycloak from 'keycloak-js' 

const keycloak = new Keycloak({
  url: "http://localhost:8082",
  realm: "smart-logistics-tracker",
  clientId: "logistics-api",
});
export default keycloak;

