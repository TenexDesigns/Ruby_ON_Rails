"In a GraphQL Rails API app, the controllers provided by Devise and Devise Token Auth are not used directly.
  Instead, you will primarily use mutations and resolvers to handle authentication and user management."




-------------------------------------------------------------------
To implement token-based authentication in a GraphQL Rails API app with Devise and Devise Token Auth, as well as apply custom validations to email and password, follow these steps:

**Step 1: Add Devise and Devise Token Auth to Your Gemfile**
Add the `devise` and `devise_token_auth` gems to your Rails application's Gemfile:

```ruby
gem 'devise'
gem 'devise_token_auth'
```

Then, run `bundle install` to install the gems.

**Step 2: Set Up Devise and Devise Token Auth**

Run the Devise and Devise Token Auth install generators:

```bash
rails generate devise:install
rails generate devise_token_auth:install User auth
```

The above commands will create configuration files, migrations, and route setup for Devise and Devise Token Auth. The second command uses the `User` model and mounts the authentication routes at `/auth`.

**Step 3: Customize Your User Model**

Edit your `User` model (`app/models/user.rb`) to include custom validations and any additional fields. For example, to add a custom email validation:

```ruby
class User < ActiveRecord::Base
  # Include default Devise modules and any custom fields
  devise :database_authenticatable, :registerable, :validatable

  # Devise Token Auth modules
  include DeviseTokenAuth::Concerns::User

  # Custom validations
  validates :email, presence: true, uniqueness: true, format: { with: /\A[^@\s]+@example\.com\z/, message: "Email must be from example.com domain" }
end
```

**Step 4: Run Migrations**

Run the migrations to create the necessary database tables:

```bash
rails db:migrate
```

**Step 5: Define Custom Mutations for Registration and Sign-In**

Define custom mutations in your GraphQL schema to handle user registration and sign-in. In your GraphQL schema file (e.g., `app/graphql/types/mutation_type.rb`), add your custom mutations. You can use the `devise_token_auth` gem's controller methods in your mutations.

Here's an example of custom mutations for user registration and sign-in:

```ruby
module Types
  class MutationType < Types::BaseObject
    # Custom registration mutation
    field :register, mutation: Mutations::RegisterUser

    # Custom sign-in mutation
    field :signIn, mutation: Mutations::SignInUser
  end
end
```

**Step 6: Implement Custom Mutations and Resolvers**

Create custom mutations and resolvers for user registration and sign-in in your GraphQL app. You can use the Devise Token Auth gem's controller methods within your custom mutations.

Here's an example of a custom mutation for user registration (`app/graphql/mutations/register_user.rb`):

```ruby
module Mutations
  class RegisterUser < BaseMutation
    argument :email, String, required: true
    argument :password, String, required: true

    field :user, Types::UserType, null: true
    field :errors, [String], null: false

    def resolve(email:, password:)
      user = User.new(email: email, password: password)
      if user.save
        {
          user: user,
          errors: [],
        }
      else
        {
          user: nil,
          errors: user.errors.full_messages,
        }
      end
    end
  end
end
```

**Step 7: Configure Authentication Routes**

In your `config/routes.rb`, you may need to configure authentication routes to handle registration and sign-in via GraphQL. Here's an example of how to set up custom routes for these actions:

```ruby
mount_devise_token_auth_for 'User', at: 'graphql', controllers: {
  registrations: 'auth/registrations', # Replace with your custom registrations controller
  sessions: 'auth/sessions', # Replace with your custom sessions controller
}
```

**Step 8: Use Authentication in Your GraphQL API**

Now, you can use the API endpoints provided by `devise_token_auth` to allow users to sign up, sign in, and sign out. Here are some common API endpoints:

- Sign-up: POST to `/graphql/auth` with the user's email, password, and other required information.
- Sign-in: POST to `/graphql/auth/sign_in` with the user's email and password to obtain a token.
- Sign-out: DELETE to `/graphql/auth/sign_out` with the user's token to log the user out.

You can also use the endpoints for account confirmation, password reset, and other authentication-related actions as needed.

**Step 8: Secure Your GraphQL API**

Implement proper security measures, such as using HTTPS, handling token expiration, and handling token refresh, to enhance the security of your GraphQL API.

Regarding the use of namespacing and versioning, you can update the `mount_devise_token_auth_for` line as demonstrated above to accommodate your GraphQL structure and naming conventions. Make sure the endpoint paths align with your GraphQL schema and routes.


**Step 9.2: Secure Your GraphQL Mutations**

To secure your GraphQL mutations, you can implement authorization checks within your resolvers or custom mutations to ensure that only authenticated users can perform registration and sign-in actions.

**Step 9: Test Your Custom Mutations**

Test your custom mutations by using a GraphQL client like Postman, Insomnia, or a GraphQL client library. Verify that user registration and sign-in work as expected, and that custom validations are applied.

By following these steps, you can implement token-based authentication, custom validations, and user registration/sign-in in your GraphQL Rails API app using Devise and Devise Token Auth. This approach allows you to handle authentication and user management in a GraphQL-specific manner, bypassing the traditional Devise controller actions used in RESTful applications.








the project set up is here
------------------------------------------------------------------------------------------------



  
1_ Create The rails API app
  
  rails new graphql --api -d postgresql 


  

2_ Create User model

  rails g model User email:string name:string  

The above genarator brings this 

  user_model.rb
  ------------------------
        class User < ApplicationRecord
              has_many :posts
        end


3_ Create Post model

   rails g model Post user:belongs_to title:string body:text

The above genarator brings this 

  post_model.rb
  ------------------
        class Post < ApplicationRecord
              belongs_to :user
        end




  4_ Then you need the follwing gems

gem.file
  -------------------

  
  gem 'graphql', '~> 2.1', '>= 2.1.5'

  This is nneded for devlopment reasons

  group :development, :test do
  # See https://guides.rubyonrails.org/debugging_rails_applications.html#debugging-with-the-debug-gem
      gem 'debug', platforms: %i[mri mingw x64_mingw]
      gem 'faker'
      gem 'graphiql-rails'
end




5_ This is to create fake data 

  seed.rb
  ---------

  require 'faker'

# Clear existing data
User.destroy_all
Post.destroy_all

# Generate users
10.times do
  User.create(
    name: Faker::Name.name,
    email: Faker::Internet.email
  )
end

# Generate posts associated with users
User.all.each do |user|
  5.times do
    user.posts.create(
      title: Faker::Lorem.sentence,
      body: Faker::Lorem.paragraph
    )
  end
end










6_  Use  graphql gem

  this will genrate the  files and folders used by grapql

  rails g graphql:install




  7_ List og graphql commands you can run
  
  rails generate


  this such commands include the follwing

  Graphql:
        graphql:enum
        graphql:input
        graphql:install
        graphql:install:mutation_root
        graphql:interface
        graphql:loader
        graphql:mutation
        graphql:mutation_create
        graphql:mutation_delete
        graphql:mutation_update
        graphql:object
        graphql:relay
        graphql:scalar
        graphql:union








8_  Types   -->  They enable you to query your data, and determine which data types will be retured thap parten to the requesrted data including the associations
  Types (Schema):

PostType and UserType are GraphQL object types representing the structure of the data you can query in your API.
  These types define the fields you can request when querying posts and users.



User Type
  
  rails g graphql:object user



  this will be genrated but I have addded and removed some fields

user_type.rb
------------------------
  module Types
  class UserType < Types::BaseObject
    field :id, ID, null: false
    field :name, String, null: true
    field :email, String, null: true
    field :posts, [Types::PostType], null: true

  end
end





Post Type

    rails g graphql:object post

  this will be genrated but I have addded and removed some fields

  post_type.rb
------------------------
  module Types
  class PostType < Types::BaseObject
    field :id, Integer, null: false
    field :title, String, null: false
    field :body, String, null: false
  end
end





  query_type.rb                --->This is where the database calls occure
------------------------------
  module Types
  class QueryType < Types::BaseObject
    # /users
    field :users, [Types::UserType], null: false

    def users
      User.all
    end

    # /user/:id
    field :user, Types::UserType, null: false do
      argument :id, ID, required: true
    end

    def user(id:)
      User.find(id)
    end
  end
end



