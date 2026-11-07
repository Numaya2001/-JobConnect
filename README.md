# JobConnect – Job Portal & Recruitment Management System

A full-stack starter project built with React, Spring Boot, Spring Security, JWT, and MySQL.

## Included
- Role-based registration/login (JOB_SEEKER, EMPLOYER, ADMIN)
- JWT-protected backend APIs
- Job listing, search, create/update/delete
- Job applications and application-status updates
- Saved jobs
- Basic role-specific React dashboards
- MySQL schema/configuration guide

## Requirements
- Java 17+
- Maven 3.9+
- Node.js 20+
- MySQL 8+

## 1. Database
Create a database:
```sql
CREATE DATABASE jobconnect_db;
```
The application uses `jobconnect_db` and creates tables with Hibernate (`ddl-auto=update`).

## 2. Backend
Open a terminal in `backend`:
```powershell
Copy-Item .env.example .env
```
Set your MySQL username/password in `src/main/resources/application.properties` (or environment variables).
Then run:
```powershell
mvn spring-boot:run
```
Backend: http://localhost:8080

A default admin is not created automatically. Register a user normally, then promote that account to ADMIN directly in MySQL:
```sql
UPDATE users SET role='ADMIN' WHERE email='your-email@example.com';
```
Restart or sign in again to receive the ADMIN role in the JWT.

## 3. Frontend
Open another terminal in `frontend`:
```powershell
npm install
Copy-Item .env.example .env
npm run dev
```
Frontend: http://localhost:5173

## Demo flow
1. Register a Job Seeker account and sign in.
2. Register a second account as Employer.
3. Sign in as Employer and create a job.
4. Sign in as Job Seeker and apply.
5. Employer can update the application status.

## Important notes
- This is a complete runnable foundation/MVP, not a production-audited recruitment platform.
- Resume upload, email delivery, company verification workflow, admin analytics, audit history, and automated deployment are listed as future extensions in `docs/ROADMAP.md`.
- Before public deployment, configure HTTPS, strong secrets, database backups, rate limiting, file scanning/storage, and production CORS.
