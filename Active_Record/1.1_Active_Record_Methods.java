
Active Record is the Object-Relational Mapping (ORM) framework in Ruby on Rails that simplifies database interactions by representing database tables as Ruby objects.
  It provides an easy way to perform database operations and allows you to work with databases using Ruby code.

Active Record simplifies database operations and makes working with databases more Ruby-like.
  It abstracts away much of the SQL, allowing you to focus on the logic of your application.

  Here is an Example

Suppose you have a "students" table in your database, and you want to interact with it using Active Record. First, you need to create a corresponding "Student" model. In Rails, you can do this using a generator:

```bash
rails generate model Student name:string age:integer
```

This command generates a "Student" model with attributes "name" and "age." Then, you can create a migration to create the "students" table in the database:

```bash
rails db:migrate
```

Now, you can use Active Record to interact with the "students" table. For example, you can create a new student, save it to the database, and retrieve it:

```ruby
# Create a new student
student = Student.new(name: "John", age: 20)

# Save the student to the database
student.save

# Find a student by ID
found_student = Student.find(1)
```


-------------- WHAT ARE ACTIVE RECORD METHODS ---------------------

Active Record  methods for querying, associations, validations, and more explained with code samples




Certainly! Here are examples of Active Record methods for querying, associations, validations, and more:

  
  
Querying Records:
----------------------------------------------------------------------------------------------------

  1.where:  Find records that match specified conditions.
          
                  students = Student.where(name: 'John') 
  
  2.find:   Find a record by its primary key.

                  student = Student.find(1)
  
  3.first:  Find the first record that matches the conditions.

                  student = Student.first
  
  4.last:   Find the last record that matches the conditions.

                  student = Student.last
  
  5.order: Order records based on a specific attribute.

                  students = Student.order(:name)
  
  6.limit: Limit the number of records returned.

                  students = Student.limit(10)
  
  7.pluck: Retrieve a specific attribute from matching records.

                  student_names = Student.where(age: 20).pluck(:name)

  
Associations:
----------------------------------------------------------------------------------------------------

  **Associations:**

- `belongs_to`: Define a one-to-one association between models.

- `has_many`: Define a one-to-many association between models.

- `has_one`: Define a one-to-one association between models.

- `has_many :through`: Define a many-to-many association between models through a join model.

  

1. belongs_to:   Define a one-to-one association between models.


    class Enrollment < ActiveRecord::Base
      belongs_to :student
      belongs_to :course
    end
  
2. has_many:    Define a one-to-many association between models.


    class Student < ActiveRecord::Base
      has_many :enrollments
      has_many :courses, through: :enrollments
    end

  
3.has_one: Define a one-to-one association between models.

    class Profile < ActiveRecord::Base
      belongs_to :user
    end

    class User < ActiveRecord::Base
      has_one :profile
    end
  
4.has_many :through: Define a many-to-many association between models through a join model.


    class Student < ActiveRecord::Base
      has_many :enrollments
      has_many :courses, through: :enrollments
    end
    
    class Course < ActiveRecord::Base
      has_many :enrollments
      has_many :students, through: :enrollments
    end
    
    class Enrollment < ActiveRecord::Base
      belongs_to :student
      belongs_to :course
    end

  
Validations:
----------------------------------------------------------------------------------------------------

**Validations:**

- `validates_presence_of`: Ensure that a field is not blank.

- `validates_uniqueness_of`: Ensure that a field has a unique value.

- `validates_length_of`: Ensure that a field has a specific length.

- `validates_numericality_of`: Ensure that a field is a number.


  
  1.validates_presence_of: Ensure that a field is not blank.

      class Student < ActiveRecord::Base
        validates_presence_of :name, :email
      end
  
  2.validates_uniqueness_of: Ensure that a field has a unique value.


    class Student < ActiveRecord::Base
      validates_uniqueness_of :email
    end
  
 3.validates_length_of: Ensure that a field has a specific length.


    class Student < ActiveRecord::Base
      validates_length_of :name, minimum: 2, maximum: 50
    end
  
 4.validates_numericality_of: Ensure that a field is a number.

    class Student < ActiveRecord::Base
      validates_numericality_of :age
    end





  
  
Callbacks:
----------------------------------------------------------------------------------------------------

**Callbacks:**

- `before_save`: Perform actions before a record is saved.

- `after_create`: Perform actions after a record is created.

- `before_destroy`: Perform actions before a record is destroyed.


  

    1.before_save: Perform actions before a record is saved.
    
          class Student < ActiveRecord::Base
            before_save :generate_slug
          
            def generate_slug
              self.slug = name.parameterize
            end
          end
  
   2.after_create: Perform actions after a record is created.

          class Student < ActiveRecord::Base
            after_create :send_welcome_email
          
            def send_welcome_email
              # Code to send email
            end
          end

  
   3.before_destroy: Perform actions before a record is destroyed.


        class Student < ActiveRecord::Base
          before_destroy :cleanup_associated_data
        
          def cleanup_associated_data
            # Code to remove associated records or data
          end
        end

These examples demonstrate some commonly used Active Record methods for querying, associations, validations, and callbacks.
Active Record provides many more methods to handle various scenarios and relationships between models.
Refer to the Rails documentation for a comprehensive list of Active Record methods and their usage.

















Active Record is the Object-Relational Mapping (ORM) framework in Ruby on Rails that simplifies database interactions by representing database tables as Ruby objects. It provides an easy way to perform database operations and allows you to work with databases using Ruby code.

Here's a basic example of how Active Record works in Rails:



You can also query records based on specific conditions using Active Record:

```ruby
# Find students with a specific name
johns = Student.where(name: "John")

# Find the first student with a specific age
first_twenty_year_old = Student.where(age: 20).first
```

Active Record simplifies database operations and makes working with databases more Ruby-like. It abstracts away much of the SQL, allowing you to focus on the logic of your application.

Please note that this is just a basic introduction to Active Record in Rails. In a real application, you would perform more complex database operations and establish associations between models. The code samples provided should give you an idea of how to get started with Active Record in Rails.





  
















  
