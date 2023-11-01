In your database design, you have the following relationships:

1. A `Game` can have many `Reviews`. This is a one-to-many relationship.
2. An `Author` can have many `Reviews`. This is also a one-to-many relationship.
3. A `Review` belongs to both a `Game` and an `Author`. This creates a many-to-one relationship between `Review`, `Game`, and `Author`.

To represent these relationships in your Rails models and migrations, you can follow these steps:

**1. Generate the models:**

You've already generated the models for `Game`, `Author`, and `Review`. You dont need to specify the associations in the generator command because you'll add them manually.

**2. Add associations in the models:**

In the `Review` model, specify the `belongs_to` associations to `Game` and `Author`:

  rails generate model Review rating:integer content:text author:references game:references


```ruby
class Review < ApplicationRecord
  belongs_to :game
  belongs_to :author
  # Other attributes and validations
end
```

In the `Game` and `Author` models, specify the `has_many` associations to `Review`:


  rails generate model Game title:string

```ruby
class Game < ApplicationRecord
  has_many :reviews
  # Other attributes and validations
end







rails generate model Author name:string verified:boolean

  
class Author < ApplicationRecord
  has_many :reviews
  # Other attributes and validations
end
```

**3. Create migrations:**

You don't need to modify the migrations for the `Game` and `Author` models because Rails handles the foreign keys automatically when you specify the associations in the models.

For the `Review` model, the migration might look like this:

```ruby
class CreateReviews < ActiveRecord::Migration[7.0]
  def change
    create_table :reviews do |t|
      t.integer :rating
      t.text :content
      t.references :game, foreign_key: true, null: false
      t.references :author, foreign_key: true, null: false

      t.timestamps
    end
  end
end
```

In the `t.references` line, you specify that a `Review` must belong to a `Game` and an `Author`, and these relationships are non-nullable (null: false).

**4. Run the migrations:**

After defining the associations and creating the migrations, run the migrations to create the tables in the database:

```bash
rails db:migrate
```

With these changes in place, your database design will reflect the relationships you described, and Rails will handle the associations between the tables and the code. The `has_many` and `belongs_to` associations are essential for defining the relationships in your Rails application and will impact how you access and interact with the data in your database.




Game.create(title: 'Elden Ring')
Author.create(name: 'mario', verified: true)



author = Author.find(1)  # Find the author with ID 1
game = Game.find(1)      # Find the game with ID 1

# Create a review associated with the author and game
review = Review.create(
  rating: 3,
  content: 'lorem ipsum',
  author_id: author.id,  # Use 'author_id' instead of 'authors_id'
  game_id: game.id       # Use 'game_id' instead of 'games_id'
)






















  
