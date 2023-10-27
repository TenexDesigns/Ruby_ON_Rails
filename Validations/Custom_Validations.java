In Rails, you can create custom validations for your models when the built-in validators are not enough. You can use the `validate` method to add custom validation methods. Here are some examples based on the code you provided:

```ruby
class User < ApplicationRecord
  validate :must_not_be_underage

  def must_not_be_underage
    age = Time.current.year - birthday.year
    errors.add(:base, "must be at least 18 years old") if age < 18
  end
end
```

In this example, the `must_not_be_underage` method is a custom validation that checks if the user is underage. If the user is underage, it adds an error to the `base` attribute.

Here's another example:

```ruby
class User < ApplicationRecord
  validate :password_never_be_used_before

  def password_never_be_used_before
    if PastPassword.where(user: self, hashed_password: Digest::MD5.hexdigest(password)).exists?
      errors.add(:password, "must be a new combination")
    end
  end
end
```

In this example, the `password_never_be_used_before` method is a custom validation that checks if the password has been used before. If the password has been used before, it adds an error to the `password` attribute.

You can also specify when the validation should happen with the `on:` option:

```ruby
class User < ApplicationRecord
  validates :first_name, presence: true, on: :create
  validates :last_name, presence: true, on: :update
end
```

In this example, the `first_name` is validated for presence only when the user is created, and the `last_name` is validated for presence only when the user is updated.

Remember, the `errors.add` method in your custom validation method is what makes the record invalid. If the condition in your custom validation method is met and `errors.add` is called, the record will not be saved when you call `save` or `save!`.


  ...
