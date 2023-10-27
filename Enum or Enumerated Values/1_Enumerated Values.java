
Enum in Rails or Ruby

An enum, short for enumeration, is a data type that defines a set of named constants. Enums are useful for representing a limited set of values, such as the status of an order (placed, shipped, delivered, canceled) or the type of product (book, movie, music).

Enums can be used in both Rails and Ruby. In Rails, enums can be used to define enum columns on database tables. This makes it easy to store and validate enum values in the database.





  In Ruby on Rails, the `enum` feature allows you to define enumerations for model attributes, mapping attribute values to symbolic names. This makes your code more expressive and readable, as you can refer to attribute values using descriptive labels rather than raw integers or strings. Enums are typically used in models to represent attributes with predefined, discrete values.

Here's what `enum` does and how to use it in Rails:

**What `enum` Does**:
- `enum` creates a set of named values for a model attribute, making it easier to work with attribute values.
- It allows you to map database values to human-readable labels.
- It provides methods for querying and setting attribute values using the enum labels.

**Where to Use `enum`**:
- You can use `enum` in Rails models to define enumerations for model attributes that have a finite set of possible values. Common use cases include defining status, role, or category attributes.

**Using `enum` in Rails**:

Let's say you have a `User` model with a `role` attribute that can take one of three values: "admin," "user," or "guest." You can use `enum` to define this enumeration in your model:

```ruby
class User < ApplicationRecord
  enum role: {
    admin: 'admin',
    user: 'user',
    guest: 'guest'
  }
end
```

In this example:
- The `enum` method is called on the `role` attribute.
- The `role` attribute is mapped to a hash where keys are the symbolic names and values are the corresponding database values.

Now, you can work with the `role` attribute using the symbolic names:

```ruby
user = User.new(role: :admin)

# Querying by enum value
admins = User.where(role: :admin)

# Changing the enum value
user.role = :user

# Getting the enum value as a string
role = user.role
```

`enum` provides several helpful methods, such as `<attribute>_values` to get a hash of symbolic names and their corresponding values and `<attribute>?` to check the attribute's value.

```ruby
# Get the hash of symbolic names and values
User.roles # { "admin" => "admin", "user" => "user", "guest" => "guest" }

# Check if a user is an admin
user.admin?  # true
```

By using `enum`, you make your code more readable and maintainable because you use meaningful labels instead of hard-coded strings or integers. It also helps enforce data integrity because it limits attribute values to the defined set of options.















  ......









  
