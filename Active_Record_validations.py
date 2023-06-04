Active Record validations in Ruby on Rails are used to ensure that the data being saved into the database meets 
certain criteria or constraints. Validations are defined within the model class and are triggered before the model is saved. 
They provide a way to enforce data integrity and maintain the consistency of your applications data.

Heres an explanation of how validations work and how to use them in Rails with code samples:

  
  
  
  
Defining Validations:
Validations are typically defined using validation macros provided by Rails. 
These macros are added within the model class, and each validation checks a specific condition on one or more attributes.


class User < ApplicationRecord
  validates :name, presence: true
  validates :email, presence: true, uniqueness: true, format: { with: /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i }
end

In this example, the User model has two validations.
The first validation validates :name, presence: true ensures that the name attribute is present.
    The second validation checks the email attribute for presence, uniqueness, and a valid email format.

    
    
    
    
Triggering Validations:
Validations are automatically triggered when you call save or create on a model instance.
Rails checks each validation and halts the saving process if any validation fails. For example:


user = User.new(name: 'John', email: 'invalid_email')
user.save # returns false
user.errors.full_messages # returns ["Email is invalid"]

In this example, the user.save call returns false because the email format is invalid.
The error message can be accessed through the errors object.






Custom Validations:
You can also define custom validation methods within your model. 
These methods should return true if the validation passes or add an error to the errors object if it fails. For example:


class User < ApplicationRecord
  validate :custom_validation

  private

  def custom_validation
    if attribute_value_invalid?
      errors.add(:attribute, 'is invalid')
    end
  end
end



In this example, the custom_validation method is a custom validation that checks if a certain attribute value is invalid.
If its invalid, an error is added to the errors object.

Validations in Rails are powerful tools for ensuring data integrity.
They allow you to define rules and constraints on your model attributes, such as presence, 
uniqueness, length, format, and more. By using validations, you can validate user input, 
protect against invalid data, and provide meaningful error messages to users.








MORE EXPLANATION
*****************************************************************************************************************88




Active Record Validations in Rails allow you to ensure that only valid data is stored in your database.
They provide a way to validate the data that is being saved to the database and add custom validation rules.
Here are some examples of validations that can be used in Rails:

  
  
  
Presence:
  
  This validation ensures that a field is not empty when the record is saved. For example:
    class User < ApplicationRecord
      validates :username, presence: true
    end
    ```
    
    
    
    
Length:
  
  This validation ensures that a field has a minimum or maximum length. For example:
    class Post < ApplicationRecord
      validates :title, length: { minimum: 5 }
    end
    ```
    
    
    
    
Uniqueness:
  
  This validation ensures that a field is unique in the database. For example:
    class User < ApplicationRecord
      validates :email, uniqueness: true
    end
    ```
    
    
    
    
    
    
Custom: 
  
  This validation allows you to define a custom validation rule. For example:
    class User < ApplicationRecord
      validate :must_have_valid_email

      private

      def must_have_valid_email
        unless email.include?('@')
          errors.add(:email, 'must be a valid email address')
        end
      end
    end
    ```
    
    
    
In these examples, the methods in the User model have access to the data being validated and the fields in the User table.
The validates method is used to specify the validation rule,
and the errors object is used to add an error message if the validation fails.

In summary, Active Record Validations in Rails provide a way to validate the data that is being
saved to the database and add custom validation rules. There are several built-in validation rules,
such as presence, length, and uniqueness, and custom validation rules can also be defined.
The methods in the User model have access to the data being validated and the fields in the User table.






















































































































...
