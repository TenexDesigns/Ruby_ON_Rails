The `class_name` option in Rails associations allows you to specify the name of the class (model) that is associated with the given association. This is important in scenarios where the actual class name differs from the association name.

Here's what happens when you use or don't use `class_name`:

1. When You Use `class_name`:

   Suppose you have a situation where the class name doesn't match the association name. For example, in a `Follow` model, you have two associations: `follower` and `followed`. The class name for both should be `User`. You can explicitly specify the class name like this:

   ```ruby
   class Follow < ApplicationRecord
     belongs_to :follower, class_name: 'User'
     belongs_to :followed, class_name: 'User'
   end
   ```

   By using `class_name`, you are telling Rails to associate `follower` and `followed` with the `User` model. This is necessary when the association names don't exactly match the class names.

2. When You Don't Use `class_name`:

   In most cases, when the association name matches the class name, you don't need to specify `class_name`. Rails will assume that the association corresponds to the class with the same name. For example:

   ```ruby
   class Blog < ApplicationRecord
     belongs_to :user
   end
   ```

   In this case, you don't need to use `class_name` because the association `user` corresponds to the `User` model by convention.

The `class_name` option specifies the model that the association refers to, not the table name. By default, Rails follows naming conventions to map the association name to the corresponding class name. If the class name and association name match, you don't need to specify `class_name`.

For example, if you have a `User` model, Rails will assume that the `belongs_to :user` association refers to the `User` class by convention. If you had a table name that didn't match the class name, you would use the `table_name` option to specify the table name, but this is a different concept from `class_name`.
