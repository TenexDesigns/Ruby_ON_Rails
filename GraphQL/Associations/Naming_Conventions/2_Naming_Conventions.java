Here are the naming conventions in Rails for associations:

**Associations referencing other models in the models folder**

Association names should be singular and in snake_case. For example, the association name for a `Game` model that has many `Review` models associated with it would be `reviews`.

**Class name**

The class name for an association should be the name of the model that it is associated with. For example, the class name for the `reviews` association in the `Game` model would be `Review`.

**Foreign key**

The foreign key for an association should be the name of the association, followed by `_id`. For example, the foreign key for the `reviews` association in the `Game` model would be `reviews_id`.

**Referencing of other models in the migrations**

When referencing other models in migrations, you should use the table name, followed by `_id`. For example, to add the `reviews` foreign key to the `games` table, you would use the following migration code:

```ruby
class CreateGames < ActiveRecord::Migration[7.0]
  def change
    create_table :games do |t|
      t.string :name
      t.integer :reviews_id
      t.timestamps
    end
  end
end
```

**Custom names**

If you need to use a custom name for an association, you can do so by specifying the class name and foreign key in the association definition. For example, to define an association called `my_reviews` that is associated with the `Review` model, you would use the following code:

```ruby
class Game < ApplicationRecord
  has_many :my_reviews, class_name: "Review", foreign_key: "reviews_id"
end
```

**Ensuring custom association names are linked to the models or tables we originally intend for them to be associated with in Rails API apps**

The best way to ensure that custom association names are linked to the models or tables that they are intended to be associated with is to use the `class_name` and `foreign_key` options in the association definition. This will explicitly tell Rails which model and table the association should be linked to.

You should also use descriptive names for custom associations. This will help you and other developers to understand what the association represents.

Here are some examples of well-named custom associations:

```ruby
class Game < ApplicationRecord
  has_many :user_reviews, class_name: "Review", foreign_key: "user_reviews_id"
  has_many :admin_reviews, class_name: "Review", foreign_key: "admin_reviews_id"
end
```

These association names clearly indicate what type of reviews they are associated with.

I hope this helps!




  ...
