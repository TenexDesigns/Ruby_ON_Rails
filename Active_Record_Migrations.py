Active Record Migrations in Ruby on Rails provide a way to manage database schema changes and versioning 
in a structured and organized manner. Migrations use a Domain-Specific Language (DSL) to define the changes to the database schema.
Heres an explanation of ActiveRecord Migrations with code samples:

  
  
Creating a Migration:
To create a new migration, you can use the rails generate migration command followed by a descriptive name for the migration.
For example, to create a migration for adding a birthdate column to the students table, run the following command:


rails generate migration AddBirthdateToStudents birthdate:date
This command generates a new migration file in the db/migrate directory with a timestamp prefix
and a name indicating the purpose of the migration.



Migration DSL:
The migration DSL provides methods for defining the changes to the database schema.
Common methods used in migrations include:

  
  
  
  
create_table: Create a new table.

class CreateStudents < ActiveRecord::Migration[6.0]
  def change
    create_table :students do |t|
      t.string :name
      t.string :email

      t.timestamps
    end
  end
end







add_column: Add a new column to an existing table.

class AddBirthdateToStudents < ActiveRecord::Migration[6.0]
  def change
    add_column :students, :birthdate, :date
  end
end










remove_column: Remove a column from an existing table.

class RemoveEmailFromStudents < ActiveRecord::Migration[6.0]
  def change
    remove_column :students, :email, :string
  end
end








rename_column: Rename a column in an existing table.

class RenameNameColumnInStudents < ActiveRecord::Migration[6.0]
  def change
    rename_column :students, :name, :full_name
  end
end
These are just a few examples of the migration DSL methods.
The DSL provides a range of methods for modifying tables, adding indexes, creating associations, and more.







Running Migrations:
To apply pending migrations and update the database schema, run the rails db:migrate command.
This command will execute all the migrations that have not been run yet.


rails db:migrate
  
  
  
  
Rolling Back Migrations:
If you need to revert a migration, you can use the rails db:rollback command.
  This command reverts the last migration by undoing its changes.


rails db:rollback
You can also rollback multiple steps by specifying the STEP parameter.


rails db:rollback STEP=3
Note: Be cautious while rolling back migrations, especially if your application already has data in the database.
Data loss may occur if not handled properly.

Other Migration Tasks:

rails db:migrate:status: Shows the current status of migrations.
rails db:version: Shows the current schema version.
rails db:seed: Runs the seed data file (db/seeds.rb) to populate the database with initial data.
Active Record Migrations provide a structured and versioned approach to managing database schema changes in Ruby on Rails.
By using the migration DSL, you can define and modify your database schema in a consistent and organized manner.





MORE EXPLANATION
************************************************************************************************




Active Record Migrations in Ruby on Rails provide a convenient and structured way to manage database schema changes.
Heres an explanation of ActiveRecord Migrations with code samples, 
along with additional information about why manual migrations should be avoided,
how it helps work with different database management systems, reversible migrations, and running specific migrations.

ActiveRecord Migrations and DSL:
ActiveRecord Migrations use a Domain-Specific Language (DSL) to define database schema changes.
The DSL provides a set of methods that can be used to create, modify, or remove database tables and columns.
Here are some examples:

Creating a table:

class CreateStudents < ActiveRecord::Migration[6.0]
  def change
    create_table :students do |t|
      t.string :name
      t.string :email
      t.timestamps
    end
  end
end
Adding a column to an existing table:

class AddBirthdateToStudents < ActiveRecord::Migration[6.0]
  def change
    add_column :students, :birthdate, :date
  end
end
Removing a column from a table:

class RemoveEmailFromStudents < ActiveRecord::Migration[6.0]
  def change
    remove_column :students, :email, :string
  end
end
Renaming a column in a table:

class RenameNameColumnInStudents < ActiveRecord::Migration[6.0]
  def change
    rename_column :students, :name, :full_name
  end
end




Avoiding Manual Migrations:
  
Manual migrations involve making changes directly to the database schema without using ActiveRecord Migrations.
This approach is discouraged because it can lead to inconsistencies, difficulties in tracking changes,
and potential data loss. ActiveRecord Migrations provide a structured and versioned approach to manage
database schema changes, making it easier to collaborate with other developers and maintain a consistent 
and trackable history of changes.





Working with Different Database Management Systems:
  
ActiveRecord Migrations abstract the underlying SQL syntax and provide a database-agnostic way to define schema changes. 
The migration DSL handles the differences between database management systems, allowing you to write migrations that can 
be used across different databases without modification. This flexibility simplifies the process of switching or
supporting multiple databases within a Rails application.





Reversible Migrations:
  
  Reversible migrations allow you to define reversible changes, making it easier to roll back migrations.
The change method automatically generates the necessary up and down methods to reverse the migration.

class ChangeColumnTypeInStudents < ActiveRecord::Migration[6.0]
  def change
    change_column :students, :age, :integer
  end
end
The above migration will generate both up and down methods to change the column type to integer
and revert it back to the original type respectively.








Running Specific Migrations:
  
To run a specific migration, you can use the rails db:migrate command with the VERSION parameter:

Copy code
rails db:migrate VERSION=20220101000000
This will execute only the specified migration and update the database schema accordingly.

Alternatively, you can also use the up and down methods to run a specific migration:


class MyMigration < ActiveRecord::Migration[6.0]
  def up
    # Code for the migration
  end

  def down
    # Code to revert the migration
  end
end
Then, you can run the migration using the migrate method in the Rails console or a Rake task.

ActiveRecord Migrations provide a powerful and convenient way to manage database schema changes 
in Ruby on Rails applications. By using the migration DSL, you can define and modify your database
schema in a structured and versioned manner, ensuring consistency, ease of collaboration, and easy rollback of changes if necessary.






















































































































..
