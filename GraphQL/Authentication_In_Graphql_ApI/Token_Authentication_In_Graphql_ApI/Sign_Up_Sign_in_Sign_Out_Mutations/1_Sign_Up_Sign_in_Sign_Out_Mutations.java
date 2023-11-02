Sure! Here's an example of how you can create the mutations and resolvers for user sign-up, sign-in, and sign-out in your GraphQL Rails API:

1. **User Sign-Up:**

   ```ruby
   # app/graphql/mutations/sign_up_user.rb
   module Mutations
     class SignUpUser < BaseMutation
       argument :email, String, required: true
       argument :password, String, required: true

       field :user, Types::UserType, null: true
       field :token, String, null: true
       field :errors, [String], null: false

       def resolve(email:, password:)
         user = User.new(email: email, password: password)

         if user.save
           token = user.create_new_auth_token
           { user: user, token: token['access-token'], errors: [] }
         else
           { user: nil, token: nil, errors: user.errors.full_messages }
         end
       end
     end
   end
   ```

   In this example, we define the `SignUpUser` mutation with the `email` and `password` arguments. Inside the `resolve` method, we create a new user with the provided email and password. If the user is successfully saved, we generate an authentication token using `create_new_auth_token` and return the user and token. If there are any validation errors, we return the errors.

2. **User Sign-In:**

   ```ruby
   # app/graphql/mutations/sign_in_user.rb
   module Mutations
     class SignInUser < BaseMutation
       argument :email, String, required: true
       argument :password, String, required: true

       field :user, Types::UserType, null: true
       field :token, String, null: true
       field :errors, [String], null: false

       def resolve(email:, password:)
         user = User.find_by(email: email)

         if user&.valid_password?(password)
           token = user.create_new_auth_token
           { user: user, token: token['access-token'], errors: [] }
         else
           { user: nil, token: nil, errors: ['Invalid email or password'] }
         end
       end
     end
   end
   ```

   In this example, we define the `SignInUser` mutation with the `email` and `password` arguments. Inside the `resolve` method, we find the user with the provided email and check if the password is valid using `valid_password?`. If the authentication is successful, we generate a new authentication token and return the user and token. If the authentication fails, we return an error message.

3. **User Sign-Out:**

   ```ruby
   # app/graphql/mutations/sign_out_user.rb
   module Mutations
     class SignOutUser < BaseMutation
       argument :token, String, required: true

       field :success, Boolean, null: false

       def resolve(token:)
         user = User.find_by(authentication_token: token)

         if user
           user.tokens = {}
           user.save
           { success: true }
         else
           { success: false }
         end
       end
     end
   end
   ```

   In this example, we define the `SignOutUser` mutation with the `token` argument. Inside the `resolve` method, we find the user with the provided token and clear the `tokens` field to invalidate the token. We return a `success` field indicating whether the sign-out was successful.

To use these mutations in your GraphQL API, you need to add them to your schema and define the corresponding resolvers.

1. **Schema:**

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

   In this example, we add the `signUpUser`, `signInUser`, and `signOutUser` mutations to the `MutationType` in your schema.

2. **Resolvers:**

   You don't need to define explicit resolvers for these mutations since they use the default resolver behavior. However, make sure you have the corresponding `Types::UserType` defined for the `user` field in the mutations.

That's it! You have now created mutations and resolvers for user sign-up, sign-in, and sign-out in your GraphQL Rails API. You can test these mutations using a GraphQL client or tool like GraphiQL or GraphQL Playground.
