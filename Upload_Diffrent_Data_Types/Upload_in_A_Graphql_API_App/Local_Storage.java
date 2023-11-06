Storing uploaded files in the local file system, instead of a cloud storage service like Amazon S3, is possible in Ruby on Rails using Active Storage. However, it's generally not recommended for production applications because it can lead to scalability, performance, and backup issues. Storing files in a cloud service like S3 is typically a better approach, as it ensures reliability, scalability, and durability. Nonetheless, if you still want to use local storage for development or specific use cases, you can configure Active Storage accordingly.

Here's how you can configure Active Storage to use local storage in your Rails application:

1. **Configure the Storage Service**:

   In your Rails application, you can specify the storage service in your `config/environments/development.rb` (for development) and `config/environments/test.rb` (for testing). Add the following configuration:

   ```ruby
   config.active_storage.service = :local
   ```

   This tells Rails to use local storage for these environments.

2. **Specify the Storage Path**:

   You can also specify the local storage path where the files will be stored. In your `config/environments/development.rb` and `config/environments/test.rb`, add the following configuration:

   ```ruby
   config.active_storage.service = :local
   config.active_storage.local_root = Rails.root.join("storage")
   ```

   This will configure Active Storage to store files in the `storage` directory within your application's root directory.

3. **Create the Storage Directory**:

   Create the local storage directory if it doesn't exist. Run the following command to create the directory:

   ```bash
   mkdir -p storage
   ```

4. **Migrate the Database**:

   Run the database migrations to create the necessary tables for Active Storage:

   ```bash
   rails active_storage:install
   rails db:migrate
   ```

Now, your Rails application is configured to use local storage for uploaded files in development and testing environments. Keep in mind that this approach is not recommended for production because local storage may not be as reliable and scalable as cloud-based storage solutions like Amazon S3. Using cloud storage services is typically a better choice for production applications, as they provide better durability, performance, and scalability.

To use local storage in production, you would specify `:local` as the service in your `config/environments/production.rb` file. However, it's essential to be aware of the potential limitations and challenges associated with local storage in a production environment.


  ...
