Implementing token-based authentication in a GraphQL Rails API application typically involves using Devise and Devise Token Auth to manage user authentication and token generation. Below are the steps to implement token-based authentication with code samples:

1. **Set Up Devise and Devise Token Auth**:
   First, make sure you have Devise and Devise Token Auth set up in your Rails application. You can add them to your `Gemfile` and run `bundle install`:

   ```ruby
   gem 'devise'
   gem 'devise_token_auth'
   ```

   Then, run the generator to set up Devise:

   ```bash
   rails generate devise:install
   rails generate devise User
   ```

   Set up Devise Token Auth:

   ```bash
   rails generate devise_token_auth:install User auth
   ```

   Run the migrations to apply the changes:

   ```bash
   rails db:migrate
   ```

   Make sure to configure Devise and Devise Token Auth according to your application's needs, such as specifying the authentication headers and configuring user fields.

2. **Set Up GraphQL**:
   Configure GraphQL in your application if you haven't already. You can use the 'graphql' gem to set up GraphQL endpoints and types.

3. **Create Custom Mutations for Authentication**:
   In your GraphQL application, you can create custom mutations for sign-up, sign-in, and sign-out.

   For example, create a `User` type and mutations for sign-up and sign-in:

   ```ruby
   # app/graphql/types/user_type.rb
   module Types
     class UserType < Types::BaseObject
       field :id, ID, null: false
       field :email, String, null: true
       # Add other user fields as needed
     end
   end
   ```

   ```ruby
   # app/graphql/mutations/sign_up_user.rb
   module Mutations
     class SignUpUser < BaseMutation
       argument :email, String, required: true
       argument :password, String, required: true

       field :user, Types::UserType, null: true
       field :token, String, null: true
       field :errors, [String], null: true

       def resolve(email:, password:)
         user = User.create!(
           email: email,
           password: password
         )

         # Generate an authentication token and set it in the response
         token = user.create_new_auth_token
         context[:session][:token] = token

         {
           user: user,
           token: token['access-token'],
           errors: [],
         }
       rescue ActiveRecord::RecordInvalid => e
         {
           user: nil,
           token: nil,
           errors: e.record.errors.full_messages
         }
       end
     end
   end
   ```

   Similarly, create a mutation for signing in and storing the authentication token in the session:

   ```ruby
   # app/graphql/mutations/sign_in_user.rb
   module Mutations
     class SignInUser < BaseMutation
       argument :email, String, required: true
       argument :password, String, required: true

       field :user, Types::UserType, null: true
       field :token, String, null: true
       field :errors, [String], null: true

       def resolve(email:, password:)
         user = User.find_for_database_authentication(email: email)

         if user && user.valid_password?(password)
           token = user.create_new_auth_token
           context[:session][:token] = token

           {
             user: user,
             token: token['access-token'],
             errors: [],
           }
         else
           {
             user: nil,
             token: nil,
             errors: ['Invalid email or password'],
           }
         end
       end
     end
   end
   ```

4. **Create Sign-Out Mutation**:
   To implement sign-out, create a mutation to clear the authentication token from the session:

   ```ruby
   # app/graphql/mutations/sign_out_user.rb
   module Mutations
     class SignOutUser < BaseMutation
       field :success, Boolean, null: true

       def resolve
         context[:session][:token] = nil # Clear the stored token
         { success: true }
       end
     end
   end
   ```

5. **Update GraphQL Schema and Context**:
   In your GraphQL schema, update it to include these mutations and properly configure the context:

   ```ruby
   # app/graphql/types/mutation_type.rb
   module Types
     class MutationType < Types::BaseObject
       field :signUpUser, mutation: Mutations::SignUpUser
       field :signInUser, mutation: Mutations::SignInUser
       field :signOutUser, mutation: Mutations::SignOutUser
     end
   end
   ```

   Make sure to configure your context in the GraphQL controller to store and retrieve the authentication token as needed.

6. **Authentication in Resolvers**:
   In your resolvers, you can use the authentication token stored in the session to authenticate the user and perform actions based on the user's authorization.

7. **Testing**:
   Test your mutations and resolvers to ensure authentication and authorization work correctly.

These steps should help you implement token-based authentication in a GraphQL Rails API application using Devise and Devise Token Auth. Ensure that you adjust the code to match your specific application requirements and security considerations.
