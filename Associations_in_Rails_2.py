Active Record associations in Ruby on Rails allow you to define relationships between models. 
These associations enable you to perform operations on related data and simplify the process of
fetching and manipulating associated records. There are several types of associations available in Rails,
each serving a different purpose. Heres an overview of the most commonly used associations along with code samples:

belongs_to:
The belongs_to association defines a one-to-one relationship where the current model "belongs to" another model. 
It is typically used when a models table contains a foreign key referencing another tables primary key. For example:


class Comment < ApplicationRecord
  belongs_to :post
end

class Post < ApplicationRecord
  # has_many :comments
end
In this example, the Comment model belongs to the Post model. The comments table should have a post_id column,
which is used to establish the association.

has_one:
The has_one association defines a one-to-one relationship where the current model "has one" associated model. 
It is the inverse of the belongs_to association. For example:

class User < ApplicationRecord
  has_one :profile
end

class Profile < ApplicationRecord
  belongs_to :user
end
In this example, the User model has one associated Profile model, and the Profile model belongs to a User.

has_many:
The has_many association defines a one-to-many relationship where the current model "has many" associated models. 
It is commonly used when a model can have multiple related records. For example:


class Author < ApplicationRecord
  has_many :books
end

class Book < ApplicationRecord
  belongs_to :author
end
In this example, the Author model has many associated Book models, and each Book belongs to an Author.

has_and_belongs_to_many (HABTM):
The has_and_belongs_to_many association defines a many-to-many relationship between two models.
It is typically used when both models can have multiple associated records. For example:


class Student < ApplicationRecord
  has_and_belongs_to_many :courses
end

class Course < ApplicationRecord
  has_and_belongs_to_many :students
end


In this example, a Student can enroll in multiple Courses, and a Course can have multiple Students.
To establish this association, a join table courses_students is used, which contains the foreign keys of both models.

has_one :through and has_many :through:
These associations allow you to define indirect relationships between models through a third model.
It is used when a model is associated with another model through a join model. For example:

class Doctor < ApplicationRecord
  has_many :appointments
  has_many :patients, through: :appointments
end

class Patient < ApplicationRecord
  has_many :appointments
  has_many :doctors, through: :appointments
end

class Appointment < ApplicationRecord
  belongs_to :doctor
  belongs_to :patient
end

In this example, a Doctor can have many Patients through Appointments, and a Patient can have many Doctors through Appointments. 
The Appointments model acts as a join model that establishes the association.

These are the main types of associations available in Ruby on Rails.
By defining associations between models, you can easily navigate between related records, 
perform queries on associated data, and maintain the integrity of your database relationships.
Associations help streamline the code and provide a natural way to work with related data in Rails applications.









MORE EXPLANATION
***********************************************************************************************************************




Active Record Associations in Rails provide a way to declare the relationships between models in a database.
This helps to make common operations simpler and easier in your code.
There are six types of associations that Rails supports:

belongs_to: This association is used when one model belongs to another model.
  It creates a foreign key column in the table of the model that belongs to another model. For example:
    class Comment < ApplicationRecord
      belongs_to :post
    end

    class Post < ApplicationRecord
      has_many :comments
    end
    ```
has_one: This association is used when one model has one associated model. 
  It creates a foreign key column in the table of the associated model. For example:
    class User < ApplicationRecord
      has_one :profile
    end

    class Profile < ApplicationRecord
      belongs_to :user
    end
    ```
has_many: This association is used when one model has many associated models. 
  It creates a foreign key column in the table of the associated model. For example:
    
    class Author < ApplicationRecord
      has_many :books
    end

    class Book < ApplicationRecord
      belongs_to :author
    end
    ```
has_many :through: This association is used when one model has many associated models through another model. 
    It creates a join table that contains foreign keys from both models. For example:
      
    class Doctor < ApplicationRecord
      has_many :appointments
      has_many :patients, through: :appointments
    end

    class Patient < ApplicationRecord
      has_many :appointments
      has_many :doctors, through: :appointments
    end

    class Appointment < ApplicationRecord
      belongs_to :doctor
      belongs_to :patient
    end
    ```
has_one :through: This association is used when one model has one associated model through another model.
    It creates a join table that contains foreign keys from both models. For example:
      
    class Supplier < ApplicationRecord
      has_one :account
      has_one :account_history, through: :account
    end

    class Account < ApplicationRecord
      belongs_to :supplier
      has_one :account_history
    end

    class AccountHistory < ApplicationRecord
      belongs_to :account
    end
    ```
has_and_belongs_to_many: This association is used when two models have a many-to-many relationship.
  It creates a join table that contains foreign keys from both models. For example:
    class User < ApplicationRecord
      has_and_belongs_to_many :groups
    end

    class Group < ApplicationRecord
      has_and_belongs_to_many :users
    end
    ```
In summary, Active Record Associations in Rails provide a way to declare the relationships between models in a database. 
There are six types of associations that Rails supports: belongs_to, has_one, has_many, has_many :through, has_one :through,
      and has_and_belongs_to_many. These associations help to make common operations simpler and easier in your code.










































































































































...
