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







9_ Mutations - The enable you to make changes to the data,     to create ,edit update and delet the data



  This one Emables you to create a user



  class Mutations::CreateUser < Mutations::BaseMutation
  argument :name, String, required: true
  argument :email, String, required: true

  field :user, Types::UserType, null: false
  field :errors, [String], null: false

  def resolve(name:, email:)
    user = User.new(name: name, email: email)
		if user.save
      {
       	user: user,
       	errors: []
      }
    else
      {
      	user: nil,
        errors: user.errors.full_messages
      }
    end
  end
end























  



  
