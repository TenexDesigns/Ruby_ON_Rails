In Ruby, both `lambda` and `proc` are used to define anonymous functions, also known as closures or blocks of code that can be assigned to variables and passed around as objects. They are similar in many ways but have some differences in behavior. Let's explore `lambda` and `proc` in Ruby and how they are used in Rails.

**Lambda:**

A `lambda` in Ruby is a type of anonymous function that is created using the `lambda` keyword or the `->` (stabby lambda) syntax. Here are some key characteristics of `lambda`:

1. **Strict Argument Checking**: Lambdas perform strict argument checking. If the number of arguments passed to a lambda does not match the expected number, a `ArgumentError` is raised.

2. **Return Behavior**: When a `lambda` encounters a `return` statement, it only returns from the lambda itself, not from the enclosing method or block.

3. **Scoping**: Lambdas have a closure-like behavior, meaning they can access variables from the surrounding scope in which they are defined.

Here's an example of using a `lambda` in Ruby:

```ruby
my_lambda = lambda { |x, y| x + y }
result = my_lambda.call(3, 4)
puts result # Output: 7
```

**Proc:**

A `proc` in Ruby is another type of anonymous function that is created using the `Proc.new` method or the `proc` method. Here are some key characteristics of `proc`:

1. **Flexible Argument Checking**: Procs have flexible argument checking. If the number of arguments passed to a proc does not match the expected number, the extra arguments are ignored, and missing arguments are assigned `nil`.

2. **Return Behavior**: When a `proc` encounters a `return` statement, it immediately returns from the enclosing method or block, similar to a regular method.

3. **Scoping**: Procs have a closure-like behavior and can access variables from the surrounding scope in which they are defined.

Here's an example of using a `proc` in Ruby:

```ruby
my_proc = Proc.new { |x, y| x + y }
result = my_proc.call(3, 4)
puts result # Output: 7
```

**Usage in Rails:**

In Rails, both `lambda` and `proc` can be used in various scenarios, such as:

1. **Callbacks**: Lambdas and procs can be used as callbacks in Rails models or controllers to execute custom logic before or after certain actions.

2. **Filters**: Lambdas and procs can be used as filters in Rails controllers to conditionally apply certain actions based on specific conditions.

3. **Custom Methods**: Lambdas and procs can be assigned to variables and used as custom methods within Rails models or controllers.

Here's an example of using a `lambda` as a callback in a Rails model:

```ruby
class User < ApplicationRecord
  before_save :normalize_name

  private

  normalize_name = lambda do
    self.name = name.downcase.capitalize
  end
end
```

In the above example, the `normalize_name` lambda is used as a `before_save` callback to modify the `name` attribute of a `User` model before it is saved to the database.

Both `lambda` and `proc` have their own use cases, and the choice between them depends on the specific requirements of your code. It's important to consider the differences in argument checking and return behavior when deciding which one to use.
