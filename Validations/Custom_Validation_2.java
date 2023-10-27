1

2
How to do custom validations in Rails

There are two main ways to create custom validations in Rails:

Using a custom method: This is the simplest way to create a custom validation. You simply define a method on your model that performs the validation and adds an error to the model if the validation fails.

Creating a custom validator class: This is a more complex way to create a custom validation, but it allows you to encapsulate the validation logic in a separate class, which can be reused across multiple models.

Example using a custom method:

Ruby
class Post < ApplicationRecord
  validate :validate_content

  private

  def validate_content
    if content.blank?
      errors.add(:content, "can't be blank")
    end
  end
end
Use code with caution. Learn more
Example using a custom validator class:

Ruby
class ContentValidator < ActiveModel::EachValidator

  
def
 
validate_each(record, attribute, value)

    
if value.blank?
      record.errors.add(attribute, "can't be blank")
    end
  end
end

class Post < ApplicationRecord
  validates :content, content: true
end
Use code with caution. Learn more
Where and when to use custom validations

You can use custom validations to validate any attribute on any model. You can also use them to validate relationships between models.

Some common use cases for custom validations include:

Validating the format of an attribute, such as an email address or phone number.
Validating the uniqueness of an attribute, such as a username or email address.
Validating that an attribute is within a certain range, such as a price or age.
Validating that an attribute is present and not blank.
Validating that an attribute matches a certain pattern, such as a password or credit card number.
Example:

Ruby
class
 
User < ApplicationRecord
  validates :name, presence:
 
true
  validates :email, presence:
 
true, uniqueness:
 
true
  validates :password, presence: true, length: { minimum: 6 }
end
Use code with caution. Learn more
This user model uses custom validations to ensure that the name, email, and password attributes are all present and valid.

Testing custom validations

You can test your custom validations using Rails' built-in test framework. To do this, you create a test case for each validation that you want to test. In the test case, you set up the necessary data and then call the validation method. If the validation fails, the test case will fail.

Example:

Ruby
require 'test_helper'

class UserTest < ActiveSupport::TestCase
  test "validates name presence" do
    user = User.new
    refute user.valid?
    assert_includes user.errors[:name], "can't be blank"
  end
end
Use code with caution. Learn more
This test case tests the validation for the name attribute on the User model. The test case creates a new user object and then calls the valid? method. If the validation fails, the test case asserts that the errors object contains the error message "can't be blank".

Conclusion

Custom validations are a powerful tool that can be used to ensure that the data in your Rails application is valid. By using custom validations, you can prevent users from entering invalid data and improve the quality of your application.


  ....
