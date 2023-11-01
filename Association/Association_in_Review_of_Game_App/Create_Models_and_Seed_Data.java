To implement the given data structure in a Ruby on Rails API application with a PostgreSQL database, you will need to define models for the `Game`, `Author`, and `Review` tables and set up associations between them. Here's how you can do it step by step:

1. **Generate Models**:
   First, generate the models using Rails generators.

   ```bash
   rails generate model Game title:string
   rails generate model Author name:string verified:boolean
   rails generate model Review rating:integer content:text
   ```

   This will create the model files and migration files for each table.

2. **Define Model Associations**:
   Update the model files to define associations between the tables. You can use `has_many`, `belongs_to`, and `has_and_belongs_to_many` associations as appropriate.

   - In the `Game` model:
     ```ruby
     class Game < ApplicationRecord
       has_many :reviews
     end
     ```

   - In the `Author` model:
     ```ruby
     class Author < ApplicationRecord
       has_many :reviews
     end
     ```

   - In the `Review` model:
     ```ruby
     class Review < ApplicationRecord
       belongs_to :author
       belongs_to :game
     end
     ```

3. **Run Migrations**:
   After defining the associations, run the migrations to create the database tables.

   ```bash
   rails db:migrate
   ```

4. **Seed Data**:
   Create a seed file to populate the database with the provided data. Create a `seeds.rb` file in the `db` directory and add the code to seed the database with the data:

   ```ruby
   # db/seeds.rb

   games = [
     { title: 'Zelda, Tears of the Kingdom' },
     { title: 'Final Fantasy 7 Remake' },
     { title: 'Elden Ring' },
     { title: 'Mario Kart' },
     { title: 'Pokemon Scarlet' },
   ]

   authors = [
     { name: 'mario', verified: true },
     { name: 'yoshi', verified: false },
     { name: 'peach', verified: true },
   ]

   reviews = [
     { rating: 9, content: 'lorem ipsum', author: Author.find_by(name: 'mario'), game: Game.find_by(title: 'Final Fantasy 7 Remake') },
     { rating: 10, content: 'lorem ipsum', author: Author.find_by(name: 'yoshi'), game: Game.find_by(title: 'Zelda, Tears of the Kingdom') },
     # Add more reviews here
   ]

   games.each { |game| Game.create(game) }
   authors.each { |author| Author.create(author) }
   reviews.each { |review| Review.create(review) }
   ```

   Run the seed task to populate the database.

   ```bash
   rails db:seed
   ```

Now, your Rails application has three tables (Games, Authors, and Reviews) with associations, and the database is seeded with the provided data.

You can access and manipulate this data through the Rails application's API endpoints using GraphQL or any other API endpoints you set up for these models.
