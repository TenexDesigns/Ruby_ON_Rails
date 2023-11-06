You can create custom validations in Ruby on Rails by defining a method in your model and then using the `validate` method to specify when and where the custom validation should be executed. To add a custom error message, you can include the error message in the `errors.add` method within your custom validation method. Here's how to do it:

1. Create a Custom Validation Method:
   In your model, define a method that represents your custom validation logic. This method will be called during the validation process.

   For example, let's say you want to validate that a user's age is above 18:

   ```ruby
   class User < ApplicationRecord
     def custom_age_validation
       if age.present? && age < 18
         errors.add(:age, "must be 18 or older")
       end
     end
   end
   ```

   In this example, we've defined a custom validation method `custom_age_validation` that checks if the `age` attribute is less than 18. If the condition is not met, we add an error message to the `:age` attribute.

2. Specify When and Where to Run the Custom Validation:
   To specify when and where the custom validation should be run, you can use the `validate` method in your model. You can specify the method name as a symbol, and Rails will call this method during the validation process.

   ```ruby
   class User < ApplicationRecord
     validate :custom_age_validation
   end
   ```

   By calling `validate :custom_age_validation`, the `custom_age_validation` method will be executed during the validation process of the `User` model.

3. Use the Custom Validation:
   Now, when you attempt to save or update a `User` record, the `custom_age_validation` method will be called, and if the age condition is not met, an error message will be added to the `errors` object.

   For example:

   ```ruby
   user = User.new(age: 16)
   user.save  # This will fail validation, and "Age must be 18 or older" will be in errors.full_messages
   ```

Custom validations are useful for enforcing business rules and constraints that are specific to your application. They allow you to add custom error messages that provide meaningful feedback to users when validation fails. Make sure to add appropriate error messages that clearly indicate the nature of the validation failure.



  ...
