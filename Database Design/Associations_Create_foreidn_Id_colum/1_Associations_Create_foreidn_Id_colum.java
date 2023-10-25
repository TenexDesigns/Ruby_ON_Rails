Yes, you are correct. In Rails associations, when you create associations between models, you are essentially creating relationships between the models by defining foreign keys in the database tables. These foreign keys are used to establish relationships between records in different tables. The foreign keys in Rails are typically named after the association names, but you can customize them if needed.

In your example with the `Follow` model, you have the following associations:

```ruby
class Follow < ApplicationRecord
  belongs_to :follower, class_name: 'User'
  belongs_to :followed, class_name: 'User'
end
```

This means that the `Follow` model has two foreign keys:

1. `follower_id`: This column references the `User` who is the follower in the relationship.
2. `followed_id`: This column references the `User` who is being followed in the relationship.

So, these foreign key columns establish the connections between `Follow` records and `User` records in your database, allowing you to track which users are following other users.
