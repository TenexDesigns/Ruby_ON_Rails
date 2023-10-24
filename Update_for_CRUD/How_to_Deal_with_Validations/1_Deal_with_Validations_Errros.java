Yes, you can have multiple validations for the same fields that apply under different conditions. In your case, you can have one set of validations for the `create` action and another set for the `update` action.

Here's how you can set it up:

```ruby
class Book < ApplicationRecord
  validates :author, presence: true, length: { in: 8..15 }, on: :create
  validates :title, presence: true, length: { in: 8..15 }, on: :create

  validates :author,  length: { in: 8..15 }, if: :author_provided?, on: :update  // Remove the resnce: true 
  validates :title,  length: { in: 8..15 }, if: :title_provided?, on: :update   // Remove the resnce: true 

  def author_provided?
    author.present?
  end

  def title_provided?
    title.present?
  end
end
```

In the above code:

- The `on: :create` option makes the validation rules apply only when a new book is being created. This means that both the `author` and `title` fields must be present and have a length between 8 and 15 characters when creating a new book.
- The `if: :author_provided?` and `if: :title_provided?` conditions with `on: :update` make the validation rules apply only when the `author` and `title` fields are included in the update parameters and the book is being updated. This allows you to partially update a book without having to include both the `title` and `author` fields in the update parameters, but still validates them when they are provided.

This setup allows you to have different validation rules for the `create` and `update` actions, providing the flexibility you need for your application.



  .....
