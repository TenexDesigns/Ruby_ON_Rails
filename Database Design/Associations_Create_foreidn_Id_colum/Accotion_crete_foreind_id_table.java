Yes, you are correct. When you create associations in Rails, you are essentially setting up references between different models using their primary keys (usually the `id` column). 

In your `Follow` model, when you say `belongs_to :follower, class_name: 'User'` and `belongs_to :followed, class_name: 'User'`, Rails will expect there to be a `follower_id` and a `followed_id` column in the `follows` table. These columns will hold the `id` of the `User` instances involved in the "follow" relationship.

So, for example, if User 1 follows User 2, there would be an entry in the `follows` table where `follower_id` is 1 (the `id` of User 1) and `followed_id` is 2 (the `id` of User 2).

This is how Rails sets up and maintains relationships between different models. It's a powerful feature that allows you to easily navigate and manipulate related data
  in your application.




-------------------------------------------------------------------------------------------------------------------------------------------------------------








  Yes, you are correct. When you create associations in Rails, Rails will automatically create foreign key columns in the associated tables. The foreign key columns will reference the primary key of the parent model.

For example, in the `Follow` model you provided, Rails will automatically create two foreign key columns: `follower_id` and `followed_id`. These foreign key columns will reference the primary key of the `User` model.

This allows Rails to track the relationships between the models. For example, Rails can use the `follower_id` foreign key to find all of the users who are following a particular user.

Here is a diagram of the database tables for the `User` and `Follow` models:

```
Users table:
+----+----------------------------+
| id | email                     |
+----+----------------------------+
| 1  | user1@example.com         |
| 2  | user2@example.com         |
+----+----------------------------+

Follows table:
+----+------------+------------+
| id | follower_id | followed_id |
+----+------------+------------+
| 1  | 1          | 2          |
| 2  | 2          | 1          |
+----+------------+------------+
```

As you can see, the `follower_id` and `followed_id` foreign key columns in the `Follows` table reference the `id` primary key column in the `Users` table. This allows Rails to track which users are following which other users.

I hope this helps!
