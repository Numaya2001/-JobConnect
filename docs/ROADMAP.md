# JobConnect implementation roadmap

## Included in this ZIP (working MVP)
- Account registration and login using JWT
- Role-aware backend authorization
- Job CRUD and public job browsing
- Job seeker applications
- Employer application status updates
- Saved jobs
- React login/register, job list, dashboards and forms

## Recommended next increments
1. Add profile editing, education, experience and skills.
2. Add secure PDF resume upload with size/type validation and private storage.
3. Add employer company verification and admin approval of job posts.
4. Add notification persistence and email delivery.
5. Add admin management screens and audit logging.
6. Add pagination, advanced filters and analytics.
7. Add unit/integration tests and CI.
8. Deploy with HTTPS, managed database, secrets manager and backups.

Build and verify one increment at a time. Never deploy with the development JWT secret.
