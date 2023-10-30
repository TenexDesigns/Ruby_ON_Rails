To create a new Rails API application with PostgreSQL as the database, follow these steps:

1. First, make sure you have Rails installed. You can install it using the following command:
   ```bash
   gem install rails
   ```

2. Now, you can create a new Rails API application with PostgreSQL as the database. Use the following command:
   ```bash
   rails new my_api --api -d postgresql
   ```
   Replace `my_api` with the name you want for your application.

3. This will create a new Rails API application with a configuration to use PostgreSQL. You will need to update the `config/database.yml` file with your PostgreSQL credentials.

4. After updating the `config/database.yml` file, you can create the database using the following command:
   ```bash
   rails db:create
   ```
   
5. Now, your Rails API application is set up with PostgreSQL. You can start building your API endpoints.

Remember, when creating models and migrations, you can use Rails generators, for example:
```bash
rails generate model User name:string email:string
rails db:migrate
```
This will create a User model with name and email fields, and run the migration to update the database.

For handling requests, you will need to create controllers. You can use Rails generators for this as well, for example:
```bash
rails generate controller Users
```
This will create a Users controller where you can define actions like `index`, `show`, `create`, `update`, and `destroy` to handle API requests.


  
