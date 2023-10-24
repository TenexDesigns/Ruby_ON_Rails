The safe navigation operator `&.` is a feature introduced in Ruby 2.3 that allows you to safely navigate through a chain of method calls without raising an error if any intermediate method returns `nil`. It is also known as the "lonely operator" or "nil-safe operator". Here's how you can use it in Ruby:

1. Basic Usage:
```ruby
# Without safe navigation operator
if user && user.profile && user.profile.address
  puts user.profile.address
end

# With safe navigation operator
puts user&.profile&.address
```
In the above example, the safe navigation operator `&.` is used to safely navigate through the chain of method calls `user.profile.address`. If any intermediate method returns `nil`, the chain is short-circuited and `nil` is returned instead of raising an error. This helps to avoid `NoMethodError` when dealing with potentially `nil` values.

2. Method Invocation:
```ruby
# Without safe navigation operator
if user && user.profile && user.profile.address
  user.profile.address.save
end

# With safe navigation operator
user&.profile&.address&.save
```
In this example, the safe navigation operator is used to invoke the `save` method on the `address` object only if all the previous methods (`user`, `profile`, and `address`) return non-`nil` values. This prevents calling methods on `nil` objects and avoids `NoMethodError`.

3. Array and Hash Access:
```ruby
# Without safe navigation operator
if params && params[:user] && params[:user][:name]
  puts params[:user][:name]
end

# With safe navigation operator
puts params&.dig(:user, :name)
```
The safe navigation operator can also be used to safely access elements in arrays and hashes. The `dig` method is used to access nested elements. If any intermediate key or index returns `nil`, the chain is short-circuited and `nil` is returned.

4. Conditional Assignment:
```ruby
# Without safe navigation operator
if user && user.profile
  profile = user.profile
else
  profile = nil
end

# With safe navigation operator
profile = user&.profile
```
The safe navigation operator can be used for conditional assignment. If the left-hand side of the assignment returns `nil`, the right-hand side is assigned to the variable. This can help simplify code and avoid repetitive `nil` checks.

The safe navigation operator `&.` is a powerful feature in Ruby that allows for concise and safe navigation through method chains, array and hash access, and conditional assignments. It helps to write more readable and robust code by handling `nil` values gracefully.
