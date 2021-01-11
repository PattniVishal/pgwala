// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  gatewayUrl: "http://localhost:8081/gateway-service",
  authServiceLoginUrl: "http://localhost:8081/gateway-service/auth-service/api/v1/auth/login",
  authServiceRegisterUrl: "http://localhost:8081/gateway-service/auth-service/api/v1/auth/register",
  getAllProperties: "http://localhost:8081/gateway-service/property-service/api/v1/properties",
  getPropertyById: "http://localhost:8081/gateway-service/property-service/api/v1/properties",
  getSeller: "http://localhost:8081/gateway-service/seller-service/api/v1/sellers",
  getBuyer: "http://localhost:8081/gateway-service/buyer-service/api/v1/buyers",
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
