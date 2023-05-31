Step 1 - Write a descriptive migration of what you wish to do
   e.g   rails generate migration AddColumnNameToTableName column_name:data_type
    or you can use sanke case
    
    rails generate migration add_column_name_to_tablename column_name:data_type

      
      Practical --> rails g migration add_stage_to_projects stage:integer

step 2---> Run rake db:Migrate


This will automaticaly add a cloumn of the above name and data type






--------------------------------------------

But When changing the the colum, you have to make this changes manually
To do that manualy
-----

Step 1 - Write a descriptive migration of what you wish to do
   e.g   rails generate migration ChangeStatgeDataType
  
    AS you can see , we dint specify the column name and its data type,We will have to do that in the generated file in the migrations file
    e.g 
    gemerated  without column name
class ChangeDataTypeForStage < ActiveRecord::Migration[7.0]
  def change
  end
end

Here we put the column name we wish to change and to what data type
Here we change stage from integer to string

class ChangeDataTypeForStage < ActiveRecord::Migration[7.0]
  def change
    change_column :projects ,:stage , :string

  end
end





Removing is same as adding and it is done automaticaly for you
--------------------------------------------











MORE EXPLANTION
********************************************************************************************************


In Rails, there are several ways to add, change, and remove columns in a database.
Let's explore the different methods available for each operation:

Adding Columns:

Migration Method: You can use Rails migrations to add columns to a database table.
  Migrations are Ruby classes that allow you to make changes to the database schema.

Example:

ruby
Copy code
rails generate migration AddColumnNameToTableName column_name:data_type
This command generates a new migration file with the specified column name and data type.
You can then define the change in the migration file using add_column method.

ruby
Copy code
class AddColumnNameToTableName < ActiveRecord::Migration[6.1]
  def change
    add_column :table_name, :column_name, :data_type
  end
end
Once the migration file is created, you can run rails db:migrate to apply the migration and add the new column to the database table.

Changing Columns:

Migration Method: To change an existing column in the database table, you can create a migration and use the change_column method.

Example:

ruby
Copy code
rails generate migration ChangeColumnNameInTableName
This command generates a new migration file. In the migration file, you can define the change using change_column method.

ruby
Copy code
class ChangeColumnNameInTableName < ActiveRecord::Migration[6.1]
  def change
    change_column :table_name, :column_name, :new_data_type
  end
end
Running rails db:migrate will apply the migration and modify the specified column in the database table.

Removing Columns:

Migration Method: To remove a column from a database table, you can create a migration and use the remove_column method.

Example:

ruby
Copy code
rails generate migration RemoveColumnNameFromTableName
This command generates a new migration file. In the migration file, you can define the change using remove_column method.

ruby
Copy code
class RemoveColumnNameFromTableName < ActiveRecord::Migration[6.1]
  def change
    remove_column :table_name, :column_name
  end
end
Running rails db:migrate will apply the migration and remove the specified column from the database table.

These are the common methods for adding, changing, and removing columns in a database using Rails migrations
. By using migrations, you can easily manage and version your database schema changes. 
Additionally, migrations provide the ability to rollback changes if needed.

Its important to note that when making changes to a production database, 
careful consideration should be given to data migration and any potential impacts on the application. 
Its recommended to perform thorough testing and make backups before applying database changes.










MORE EXPLANTION
********************************************************************************************************




There are a few ways to add, change, or remove columns in a database in Rails.

Using the rails g migration command
The rails g migration command can be used to generate a new migration file.
This file will contain the SQL statements needed to add, change, or remove columns in the database.
For example, to add a new column named age to the users table, you would use the following command:

Code snippet
rails g migration add_age_to_users age:integer
Use code with caution. Learn more
This would generate a new migration file named 20230601123456_add_age_to_users.rb. 
The add_age_to_users method in this file would contain the following SQL statement:

Code snippet
def change
  add_column :users, :age, :integer
end
Use code with caution. Learn more
Once you have generated the migration file, you can run it using the rake db:migrate command. 
  This will execute the SQL statements in the migration file and add the new column to the database.

Using the rails db:schema:load command
The rails db:schema:load command can be used to load the database schema from the schema.rb file.
    This file contains the definition of all of the tables and columns in the database. 
    If you add a new column to the schema.rb file, you can load the schema using 
    the rails db:schema:load command and the new column will be added to the database.

Using the ActiveRecord API
The ActiveRecord API can be used to add, change, or remove columns in a database.
To add a new column, you can use the add_column method. To change the definition of an existing column,
you can use the change_column method. To remove a column, you can use the remove_column method.

For example, to add a new column named age to the users table, you would use the following code:

Code snippet
User.connection.add_column :users, :age, :integer
Use code with caution. Learn more
To change the definition of the age column to a string, you would use the following code:

Code snippet
User.connection.change_column :users, :age, :string
Use code with caution. Learn more
To remove the age column from the users table, you would use the following code:

Code snippet
User.connection.remove_column :users, :age
Use code with caution. Learn more
Which method you use to add, change, or remove columns in a database depends on your specific needs.
If you only need to make a small change to the database schema, you can use the rails g migration command.
If you need to make a larger change to the database schema, you can use the rails db:schema:load command.
    If you need to make a change to the database schema dynamically, you can use the ActiveRecord API.












































































...
