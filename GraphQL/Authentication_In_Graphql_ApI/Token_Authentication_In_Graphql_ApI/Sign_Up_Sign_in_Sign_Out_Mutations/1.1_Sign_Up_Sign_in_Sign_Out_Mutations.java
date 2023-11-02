Certainly! Below, Ill provide you with examples of how to create custom mutations and resolvers for user sign-up, sign-in, and sign-out in a GraphQL Rails API. We'll assume that you have a `User` model and that you've already set up Devise and Devise Token Auth.

**1. Sign-Up Mutation:**

First, let's create a custom mutation for user sign-up. This mutation will take user information like `email`, `password`, and any additional fields you want to include (e.g., `name`). It will create a new user and return an authentication token upon successful registration.

```ruby
# app/graphql/mutations/user_mutations.rb

module Mutations
  class SignUpUser < BaseMutation
    argument :email, String, required: true
    argument :password, String, required: true
    argument :name, String, required: false

    field :user, Types::UserType, null: true
    field :token, String, null: true
    field :errors, [String], null: true

    def resolve(email:, password:, name: nil)
      user = User.new(email: email, password: password, name: name)

      if user.save
        token = user.create_new_auth_token
        context[:session][:token] = token # Store token in session for future requests
        {
          user: user,
          token: token['access-token'],
          errors: [],
        }
      else
        {
          user: nil,
          token: nil,
          errors: user.errors.full_messages,
        }
      end
    end
  end
end
```

In the code above:

- We create a custom mutation called `SignUpUser` with arguments for `email`, `password`, and an optional `name`.
- In the `resolve` method, we create a new user with the provided data and save it. If the user is successfully saved, we generate an authentication token using `user.create_new_auth_token` and return it along with the user object.
- If there are any validation errors or the user can't be saved, we return the errors.

**2. Sign-In Mutation:**

Next, let's create a custom mutation for user sign-in. This mutation will take the user's `email` and `password`, and it will return an authentication token upon successful sign-in.

```ruby
# app/graphql/mutations/user_mutations.rb

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
        context[:session][:token] = token # Store token in session for future requests
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

In this code:

- We create a custom mutation called `SignInUser` with arguments for `email` and `password`.
- In the `resolve` method, we attempt to find the user by email, and then we check if the provided password is valid. If the user is found and the password is valid, we generate an authentication token and return it along with the user object.
- If the email or password is invalid, we return an error message.

**3. Sign-Out Mutation:**

To create a custom mutation for user sign-out (i.e., logging out), you can use a mutation that clears the stored token in the session. Here's a basic example:

```ruby
# app/graphql/mutations/user_mutations.rb

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

In this mutation, we simply clear the stored token in the session, effectively logging the user out.

Now that we've created these custom mutations, you can use them in your GraphQL queries. For example, a sign-up query might look like this:

```graphql
mutation {
  signUpUser(input: { email: "user@example.com", password: "password", name: "User Name" }) {
    user {
      id
      email
      name
    }
    token
    errors
  }
}
```

Similarly, you can use the `signInUser` mutation for user sign-in and the `signOutUser` mutation for user sign-out in your GraphQL queries.




  To use these mutations in your GraphQL API, you need to add them to your schema and define the corresponding resolvers.

Schema:
   # app/graphql/types/mutation_type.rb
   module Types
     class MutationType < Types::BaseObject
       field :signUpUser, mutation: Mutations::SignUpUser
       field :signInUser, mutation: Mutations::SignInUser
       field :signOutUser, mutation: Mutations::SignOutUser
     end
   end
In this example, we add the signUpUser, signInUser, and signOutUser mutations to the MutationType in your schema.

Resolvers:
You don't need to define explicit resolvers for these mutations since they use the default resolver behavior. However, make sure you have the corresponding Types::UserType defined for the user field in the mutations.

That's it! You have now created mutations and resolvers for user sign-up, sign-in, and sign-out in your GraphQL Rails API. You can test these mutations using a GraphQL client or tool like GraphiQL or GraphQL Playground.


  ..
  ..
