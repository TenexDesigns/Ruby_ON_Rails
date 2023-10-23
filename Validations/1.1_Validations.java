Validations in Rails are used to ensure that only valid data is saved into your database. They are used within Active Record models and can be used to ensure the presence, length, format, and uniqueness of attributes.

Here are some common types of validations and how to use them in Rails models:

1. **Presence Validation**: Ensures that a specific attribute is not empty or null.

    ```ruby
    class User < ApplicationRecord
      validates :name, presence: true
    end
    ```

2. **Length Validation**: Ensures that a string attribute has a certain length.

    ```ruby
    class User < ApplicationRecord
      validates :password, length: { minimum: 8 }
    end
    ```

3. **Format Validation**: Ensures that a string attribute matches a regular expression.

    ```ruby
    class User < ApplicationRecord
      validates :email, format: { with: URI::MailTo::EMAIL_REGEXP }
    end
    ```

4. **Uniqueness Validation**: Ensures that a specific attribute is unique before the object is saved.

    ```ruby
    class User < ApplicationRecord
      validates :email, uniqueness: true
    end
    ```

5. **Numericality Validation**: Ensures that a specific attribute is a number, and optionally that it is greater than, less than, or equal to a specific value.

    ```ruby
    class Product < ApplicationRecord
      validates :price, numericality: { greater_than_or_equal_to: 0 }
    end
    ```

6. **Inclusion/Exclusion Validation**: Ensures that a specific attribute's value is included or excluded from a given set.

    ```ruby
    class Coffee < ApplicationRecord
      validates :size, inclusion: { in: %w(small medium large),
        message: "%{value} is not a valid size" }
    end
    ```

7. **Confirmation Validation**: Commonly used when requiring a user to confirm a password or email address. This type of validation creates a virtual attribute whose name is the name of the field that has to be confirmed with "_confirmation" appended.

    ```ruby
    class User < ApplicationRecord
      validates :email, confirmation: true
    end
    ```

8. **Acceptance Validation**: Commonly used when requiring a user to accept terms of service. This validation is non-database related and usually used with a boolean field.

    ```ruby
    class User < ApplicationRecord
      validates :terms_of_service, acceptance: true
    end
    ```

You can also create custom validations. For example, if you wanted to ensure that a user's password includes at least one uppercase letter, one lowercase letter, and one number, you could write a custom validation like this:

```ruby
class User < ApplicationRecord
  validate :password_complexity
 
  def password_complexity
    if password.present? and not password.match(/\A(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+\z/)
      errors.add :password, "must include at least one lowercase letter, one uppercase letter, and one digit"
    end
  end
end
```

When a validation fails, the errors method will return an instance of ActiveModel::Errors containing all errors encountered while running validations. For example:

```ruby
user = User.new
user.valid? # => false
user.errors.messages # => {:name=>["can't be blank"]}
```

You can also use conditional validations that will only validate under certain conditions. For example, you might want to ensure that a user's email is unique, but only validate the email field if it's been changed:

```ruby
class User < ApplicationRecord
  validates :email, uniqueness: true, if: :email_changed?
end
```

In the view layer, you can use the `errors.full_messages` method to access the errors and display them to the user.

```ruby
<% if @user.errors.any? %>
  <ul>
    <% @user.errors.full_messages.each do |message| %>
      <li><%= message %></li>
    <% end %>
  </ul>
<% end %>
```

This will display a list of all errors for the user object [Source 1](https://guides.rubyonrails.org/active_record_validations.html), [Source 0](https://dev.to/daviducolo/rails-model-validation-a-comprehensive-guide-with-code-examples-21mh).


....
