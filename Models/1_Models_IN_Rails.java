models explained in rails with code samples,what they are responsible for,what happens inside them,
waht the ApplicationRecord does why,it inerits from ActiveRecord::Base and why do other model inreti from ApplicationRecord ,
    Naming conventions in model,model class and tables, are model responsible for crud oprations, validations done in models,
    callbacks in models, what is object lifecyle in this context,
    what are migrations in this context and how can they be done in model






Models Are Responsible for
1.Naming Convention - The model class name is in singular and the Table ame is in plural
  Model Class               Table name
 Class Project               projects
 Class OneAge                one_ages
 Class Person                peope


2. They are responsible for crud operations
    Create  -  Does not reuire existing object in database
    Read    -   requires existing object in database
    Update  -  requires existing object in database
    Delete  -   requires existing object in database


3.They inherit or extend the ApplicationRecord class
Applcation record class can not be instanciated , it can only be inherted by the  model created
e.g  Class Student < ApplicationRecorde
     end


4. Overiding_nameing Coventions

Incase you want to overide the naming conventions , where e.g for Model Project we get Table name Projects

class Project <ApplicationRecord
      self.table_name = "my_projects"
 end 


5. Valiadations
 Is used to ensure that requered fields are present e.g
  This ensures that name is alaways presnet and not nil. Or user is more than 18
  class User < ApplicationRecored
      valuaadtes :name , presence: true
  end



7. Callbacksa
Are associated =with an objects life cycle
callbacks can be called when an object is read, saved, updated, or destroyed


8.Migrations
These modefy the schema or the status of the database






MORE EXPLANATION
*********************************************************************************************************************************


In Rails, models are responsible for representing the data structure, handling business logic, 
and interacting with the database. Lets explore the key aspects of models in Rails:

1.Model Responsibilities:

Define the structure and relationships of the data stored in the database.
Perform CRUD (Create, Read, Update, Delete) operations on the database.
Implement business logic and data manipulation methods.
Validate data integrity and enforce constraints.
Interact with other models to establish associations and perform complex operations.
Define callbacks to trigger actions at specific points in the object lifecycle.


2.Model Implementation:
  
In Rails, models are typically created by subclassing ApplicationRecord, which itself inherits
from ActiveRecord::Base. The ApplicationRecord class serves as the base model for your application 
and includes functionality provided by ActiveRecord, such as database connectivity and ORM (Object-Relational Mapping) capabilities.

Heres an example of a model in Rails:


# app/models/user.rb
class User < ApplicationRecord
  validates :name, presence: true
  validates :email, presence: true, uniqueness: true
  has_many :posts
  
  before_save :normalize_name

  def normalize_name
    self.name = name.capitalize
  end
end

In this example, we have a User model that inherits from ApplicationRecord. 
The model includes validations (validates method) to ensure the presence of the name and uniqueness of the email.
It also establishes a one-to-many association with the Post model (has_many :posts).

Additionally, a before_save callback is defined, which invokes the normalize_name method before saving the user object to the database.
This callback capitalizes the user's name.




3.Naming Conventions:
  
Rails follows naming conventions to establish connections between models and database tables.
By convention, a model named User will be associated with a database table called users.
Rails automatically pluralizes the model name to determine the table name.





4.CRUD Operations:
  
Models in Rails provide methods for performing CRUD operations on the associated database table. For example:
In the controllers, There is an ORM, thatallows abstraction of these crud opreations, The orm e.g(def new  @project = Project.new end )
in cotrollers abstacts this creation of objects in the model ,but the crut oprations themsseves occur here in the models
  
Create: User.create(name: 'John', email: 'john@example.com')
Read: User.find(id), User.where(condition), etc.
Update: user.update(name: 'New Name'), user.save
Delete: user.destroy
  
  
  
  
5.Validations:
  
Models include validation rules to ensure data integrity. Rails provides a range of built-in validators
(e.g., presence, uniqueness, format) that can be used to validate model attributes.
Custom validation methods can also be defined within the model.





6.Callbacks:
  
Callbacks allow you to trigger actions at specific points in the object lifecycle,
such as before or after creating, updating, or deleting a record.
Rails provides a variety of callbacks (e.g., before_save, after_create) that can be utilized 
to perform additional actions or modify data.







7.Object Lifecycle:
  
The object lifecycle in Rails refers to the sequence of events that occur during the lifespan of a model object. 
It includes events like initialization, validation, saving, updating, and destroying an object.
Callbacks can be used to intercept these events and execute custom logic.







8.Migrations:
  
Migrations in Rails are a way to manage database schema changes over time.
They allow you to create and modify database tables, columns, indexes, and other schema-related elements. 
Migrations are written as Ruby scripts and can be executed to apply changes to the database schema.
Models are closely tied to migrations as they define the structure and relationships of the data.

Migrations can be generated using the rails generate migration command and executed using the rails db:migrate command. 
  For example, to create a migration for adding a username column to the users table, you can run:


rails generate migration AddUsernameToUsers username:string
rails db:migrate
This will generate a migration file that you can edit to define the changes to the database schema,
and then applying the migration will update the database accordingly.

In summary, models in Rails are responsible for managing data, implementing business logic,
validating data integrity, establishing relationships with other models, 
and interacting with the database using ActiveRecord.
They play a vital role in the MVC architecture, providing a representation of the applications data and behavior.








MORE EXPLANATION ---> HOW TO CREATE TABLES IN RAILS
***********************************************************************************************



To create a table in Rails, you can use database migrations. 
Migrations allow you to define and modify the database schema using Ruby code. 
Heres an example of creating a projects table:

Generate a migration: Open a terminal or command prompt, navigate to your Rails project directory,
  and run the following command:


rails generate migration CreateProjects
This will generate a migration file with a timestamp and the name you specified.

Edit the migration file: Open the generated migration file (located in the db/migrate directory) and
define the table structure using the create_table method:


class CreateProjects < ActiveRecord::Migration[6.1]
  def change
    create_table :projects do |t|
      t.string :name
      t.text :description
      t.timestamps
    end
  end
end
In this example, were creating a projects table with name and description columns.
The t.timestamps method adds created_at and updated_at columns, automatically tracking the creation and update timestamps.

Run the migration: Execute the migration using the following command:


rails db:migrate
  
This will create the projects table in your database.

