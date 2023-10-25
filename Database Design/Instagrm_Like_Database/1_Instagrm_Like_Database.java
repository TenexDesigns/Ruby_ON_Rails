To design database tables and relationships for a Rails application, you should first identify the different entities in your application and the relationships between them. In your case, the entities are:

* User
* Post
* Comment
* Picture
* Video

The relationships between these entities are:

* A user can have many posts.
* A post can have many comments.
* A comment can be associated with one post.
* A post can have many pictures and videos.
* A picture or video can be associated with one post.

Based on these relationships, we can design the following database tables:

```sql
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE posts (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
  id INT NOT NULL AUTO_INCREMENT,
  post_id INT NOT NULL,
  body TEXT NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE pictures (
  id INT NOT NULL AUTO_INCREMENT,
  post_id INT NOT NULL,
  image BLOB NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE videos (
  id INT NOT NULL AUTO_INCREMENT,
  post_id INT NOT NULL,
  video BLOB NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES posts(id)
);
```

Once you have created the database tables, you can use Rails to generate the corresponding models. To do this, run the following command in your Rails application directory:

```
rails generate model user username first_name last_name email password
rails generate model post user_id
rails generate model comment post_id body
rails generate model picture post_id image
rails generate model video post_id video
```

This will generate the following models:

```ruby
class User < ApplicationRecord
  has_many :posts
end

class Post < ApplicationRecord
  belongs_to :user
  has_many :comments
  has_many :pictures
  has_many :videos
end

class Comment < ApplicationRecord
  belongs_to :post
end

class Picture < ApplicationRecord
  belongs_to :post
end

class Video < ApplicationRecord
  belongs_to :post
end
```

Rails will automatically create the database relationships based on the model definitions. For example, the `User` model has a `has_many` relationship with the `Post` model, so Rails will create a foreign key column called `user_id` in the `posts` table.

You can then use the models to interact with the database. For example, the following code will create a new user:

```ruby
user = User.create(username: 'johndoe', first_name: 'John', last_name: 'Doe', email: 'john.doe@example.com', password: 'password123')
```

The following code will create a new post for the user:

```ruby
post = Post.create(user: user, body: 'This is my first post!')
```

And the following code will get all of the posts for the user:

```ruby
posts = user.posts
```

You can use the same approach to create, read, update, and delete comments, pictures, and videos.
