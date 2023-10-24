The validation error you're encountering is due to the combination of validations in your `Book` model. You have validations for both creating (`on: :create`) and updating (`if` condition) records, and this combination is causing conflicts when you partially update a book.

To address this issue, you should restructure your validations to make it more clear and concise. You can have separate sets of validations for creating and updating records. Here's an updated model:

```ruby
class Book < ApplicationRecord
  validates :author, presence: true, length: { in: 8..15 }, on: :create
  validates :title, presence: true, length: { in: 8..15 }, on: :create

  validates :author, length: { in: 8..15 }, if: -> { author.present? }
  validates :title, length: { in: 8..15 }, if: -> { title.present? }
end
```

In this updated model, the validations for `presence: true` are retained only for the `create` action. When updating a record, we focus on the length validation without requiring the attributes to be present. This separation ensures that you can perform partial updates without encountering the "too short" validation error for attributes that aren't provided in the update parameters.

By making this change, you maintain the integrity of your data when creating new records and, at the same time, allow for partial updates without validation issues when updating existing records.


  ....
