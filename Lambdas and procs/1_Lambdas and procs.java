In Ruby, both lambdas and procs are objects of the Proc class, but they have some differences in how they handle certain aspects of function execution. Here's an overview of lambdas and procs in Ruby and their use in Rails:

**Lambdas:**

1. **Syntax:** You define a lambda using the `lambda` keyword or the `->` (stabby lambda) syntax.
   ```ruby
   lambda { |args| # code }
   # or
   ->(args) { # code }
   ```

2. **Strict Argument Count:** Lambdas have strict argument count checking. If you pass the wrong number of arguments to a lambda, it will raise an exception.

3. **Returning from a Lambda:** When a `return` statement is executed within a lambda, it returns from the lambda itself, not the enclosing method.

4. **Scope:** Lambdas capture the values of local variables in the surrounding scope when they are created.

5. **Example:**
   ```ruby
   my_lambda = lambda { |x, y| x + y }
   result = my_lambda.call(1, 2) # result is 3
   ```

**Procs:**

1. **Syntax:** You define a proc using the `Proc.new` constructor or the `%w{}` literal.
   ```ruby
   Proc.new { |args| # code }
   # or
   proc { |args| # code }
   ```

2. **Loose Argument Count:** Procs do not perform strict argument count checking. If you pass the wrong number of arguments to a proc, it will not raise an exception.

3. **Returning from a Proc:** When a `return` statement is executed within a proc, it returns from the enclosing method (the method where the proc is defined).

4. **Scope:** Procs capture the values of local variables in the surrounding scope when they are created, similar to lambdas.

5. **Example:**
   ```ruby
   my_proc = proc { |x, y| x + y }
   result = my_proc.call(1, 2) # result is 3
   ```

**Use in Rails:**

In Rails, lambdas and procs are often used in various scenarios, such as:

1. **Callbacks:** Lambdas and procs can be used as callbacks in ActiveRecord models to perform actions before or after certain database operations.

2. **Custom Validation Rules:** You can define custom validation rules using lambdas or procs in Rails models.

3. **Scopes:** Lambdas and procs can be used in ActiveRecord scopes to create custom queries.

4. **Custom Helper Methods:** Lambdas and procs can be used to define custom helper methods for use in views or controllers.

5. **Controller Filters:** Lambdas and procs can be used in Rails controller filters to customize controller behavior.

Here's a simple example of using a lambda as a custom validation rule in a Rails model:

```ruby
class User < ApplicationRecord
  validates :email, format: { with: ->(email) { email =~ /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i } }
end
```

In this example, the lambda is used to define a custom email format validation rule for the `email` attribute of the `User` model.

Lambdas and procs provide flexibility and expressiveness when defining custom behavior in your Ruby on Rails applications.



  ....
