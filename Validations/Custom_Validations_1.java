In Ruby on Rails, you can create custom validations for your models. This is useful when the built-in validators are not sufficient for your needs. Here's how you can do it:

1. **Define a custom validation method**: This method contains the logic of your validation. If the validation fails, you should add an error to the record's errors collection.

2. **Use the `validate` method to add the custom validation method**: The `validate` method tells Rails to use your custom validation method to validate the record.

Here's an example:

```ruby
class User < ApplicationRecord
  validate :email_must_be_company_email

  def email_must_be_company_email
    unless email.ends_with?('@mycompany.com')
      errors.add(:email, 'must be a company email')
    end
  end
end
```

In this example, the `email_must_be_company_email` method is a custom validation that checks if the user's email ends with '@mycompany.com'. If it doesn't, it adds an error to the `email` attribute.

You can use custom validations anywhere you need to enforce specific rules that are not covered by the built-in validators. For example, you might use a custom validation to check that a date attribute is not in the future, that a string attribute follows a specific format, or that a combination of attributes is unique.

Remember, the `errors.add` method in your custom validation method is what makes the record invalid. If the condition in your custom validation method is met and `errors.add` is called, the record will not be saved when you call `save` or `save!`.



  ....
