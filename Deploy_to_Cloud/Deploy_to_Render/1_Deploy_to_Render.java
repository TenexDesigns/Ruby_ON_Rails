

Here is the orginal article -----------> https://medium.com/@michaelmunavu83/deploying-rails-only-api-with-render-97b39ef4aaf8






To deploy a Rails API on Render, you can follow the steps outlined in the provided article. Here's a summary of the key steps to deploy your Rails API to Render:

1. **Set Up Your Repository with PostgreSQL**:
   - Ensure that your Rails application is using PostgreSQL as the database. You can create a new Rails project with PostgreSQL by using the following command:
     ```bash
     rails new myfirstsite --database=postgresql
     ```
   - If you're deploying an existing repository, update your Gemfile to use the 'pg' gem instead of 'sqlite3' and run `bundle install`.

2. **Configure Your Database**:
   - Make sure your `config/database.yml` file is correctly configured to use PostgreSQL as the adapter and set up the production section to use environment variables, including `DATABASE_URL`.

3. **Modify Project Configurations**:
   - Adjust the configuration files for Puma and production environments to match the settings necessary for your production deployment.

4. **Enable CORS**:
   - To handle Cross-Origin Resource Sharing (CORS), add the 'rack-cors' gem to your Gemfile and configure it in `config/initializers/cors.rb`.

5. **Create a PostgreSQL Database on Render**:
   - Log in to Render or create an account if you haven't already.
   - Create a new PostgreSQL database. You can use the free tier.
   - Once created, copy the Internal Database URL for later use.

6. **Create a Build Script**:
   - Create a build script called `render-build.sh` in the `bin` folder of your Rails application. This script should include commands to install gems, run database migrations, and optionally seed data.

7. **Create a Web Service on Render**:
   - Log in to Render.
   - Create a new Web Service.
   - Configure your GitHub account and select the repository to deploy.
   - Set the environment to Ruby.
   - Clear the Build Command and set it to `./bin/render-build.sh`.
   - Clear the Start Command and set it to `bundle exec puma -C config/puma.rb`.
   - Add the required environment variables, including `DATABASE_URL` and `RAILS_MASTER_KEY`.
   - Choose the Free plan.
   - Click the Create Web Service button.

8. **Monitor the Deployment**:
   - Check the progress of the deployment and review the logs to ensure that everything is working correctly.

Following these steps should help you successfully deploy your Rails API on Render. Please note that Render is a cloud platform that simplifies deployment and hosting, and it offers a free tier for PostgreSQL databases, which can be helpful for development and small-scale projects.
