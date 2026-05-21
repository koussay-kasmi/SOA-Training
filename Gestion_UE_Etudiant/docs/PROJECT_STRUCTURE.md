# Project Structure

## Backend (JAX-RS + Tomcat WAR)

- `backend/src/main/java/entities`
  - `UniteEnseignement.java`: UE model
  - `Module.java`: Module model with embedded UE
- `backend/src/main/java/metiers`
  - `UniteEnseignementBusiness.java`: UE in-memory business logic
  - `ModuleBusiness.java`: Module in-memory business logic
- `backend/src/main/java/webservices`
  - `UeWS.java`: UE REST endpoints
  - `ModuleWS.java`: Module REST endpoints
  - `CorsFilter.java`: CORS for Angular (`http://localhost:4200`)
- `backend/src/main/webapp/WEB-INF/web.xml`: Jersey servlet bootstrap
- `backend/src/main/webapp/swagger`
  - `openapi.yaml`: OpenAPI specification served by the app
  - `index.html`: Swagger UI page

## Frontend (Angular)

- `frontend/src/app`
  - `app.component.ts/html/css`: UI for UE + Module operations
  - `api.service.ts`: API calls to backend endpoints
  - `models.ts`: shared TypeScript models
  - `app.config.ts`: router + HttpClient providers

## API Base URLs

- Backend API: `http://localhost:8080/app/api`
- Swagger UI: `http://localhost:8080/app/swagger/`
- Swagger YAML: `http://localhost:8080/app/swagger/openapi.yaml`

## Run

1. Deploy backend WAR to Tomcat.
2. Open frontend folder:
   - `cd frontend`
3. Install dependencies:
   - `npm install`
4. Start Angular:
   - `npm start`
