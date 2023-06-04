To reverse a migration in Rails, you can use the rails db:migrate:down command followed by the version number of
    the migration you want to revert. Here are the steps to reverse a migration:

Find the version number of the migration you want to revert. This can be found in the schema_migrations table
in the database or by looking at the filename of the migration in the db/migrate directory.
Run the rails db:migrate:down command followed by the version number of the migration.
    For example, to revert a migration with version number 20230604123456, you would run the following command:
   rails db:migrate:down VERSION=20230604123456
Alternatively, you can use the rails db:rollback command to revert the last migration that was applied.
  This is useful if you want to quickly undo the most recent change to the database schema.

It's important to note that not all migrations can be reversed automatically by Rails.
If you make a change to the database schema that can't be reversed, you'll need to manually write the code to undo that change. 
One way to do this is to use the reversible method in your migration, which allows you to specify custom code for both the up and
down methods. [5]

In summary, reversing a migration in Rails is simple and can be done using the rails db:migrate:down command followed by the 
    version number of the migration you want to revert. Alternatively, you can use the rails db:rollback command
      to quickly undo the most recent change to the database schema. However, not all migrations can be reversed
      automatically by Rails, so you may need to write custom code to undo certain changes.






MORE EXPLANATION
*******************************************************************************************************************


When you encounter the error message "This migration uses change_column, which is not automatically reversible,"
it means that the migration youre trying to run contains a change_column method that cannot be automatically reversed by Rails.

To make the migration reversible, you have two options:

Define up and down methods:
Instead of using the change method, you can define the up and down methods explicitly.
The up method should contain the changes you want to make to the database schema, 
while the down method should define how to rollback those changes. Here's an example:

class ChangeColumnTypeInTable < ActiveRecord::Migration[6.0]
  def up
    change_column :table_name, :column_name, :new_column_type
  end

  def down
    change_column :table_name, :column_name, :old_column_type
  end
end
In this example, the up method changes the column type to new_column_type, and the down method rolls it back to the old_column_type.
This allows Rails to reverse the migration if necessary.

Use the reversible method:
Alternatively, you can use the reversible method provided by Rails to define reversible behavior. 
The reversible method takes two blocks, one for the migrations "up" direction and the other for the "down" direction.
Inside each block, you can use the change method to define the desired schema changes. Here's an example:


class ChangeColumnTypeInTable < ActiveRecord::Migration[6.0]
  def change
    reversible do |dir|
      change_table :table_name do |t|
        dir.up   { t.change :column_name, :new_column_type }
        dir.down { t.change :column_name, :old_column_type }
      end
    end
  end
end

In this example, the reversible method wraps the change_table block, allowing you to specify the schema changes for
both the "up" and "down" directions using the change method. This ensures that the migration can be reversed properly.

By using either the up and down methods or the reversible method, you provide explicit instructions to Rails
on how to revert the changes made by the migration. This ensures that the migration is reversible and can be rolled back if needed.



































