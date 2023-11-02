**GraphQL Mutations**

The following GraphQL mutations can be used for user sign-up, sign-in, and sign-out:

```ruby
# Sign up mutation
mutation RegisterMutation($email: String!, $password: String!) {
  register(email: $email, password: $password) {
    user {
      id
      name
      email
    }
    token
  }
}

# Sign in mutation
mutation SignInMutation($email: String!, $password: String!) {
  signIn(email: $email, password: $password) {
    token
  }
}

# Sign out mutation
mutation SignOutMutation {
  signOut
}
```

**GraphQL Resolvers**

The following GraphQL resolvers can be used to implement the above mutations:

```ruby
class Mutations::RegisterMutation < Mutations::BaseMutation
  argument :email, String, required: true
  argument :password, String, required: true

  field :user, UserType, null: false
  field :token, String, null: false

  def resolve
    user = User.create!(email: email, password: password)

    token = DeviseTokenAuth::Concerns::User.create_token(user)

    {
      user: user,
      token: token
    }
  end
end

class Mutations::SignInMutation < Mutations::BaseMutation
  argument :email, String, required: true
  argument :password, String, required: true

  field :token, String, null: false

  def resolve
    user = User.find_by_email(email)
    raise GraphQL::ExecutionError.new('Invalid email or password') unless user && user.valid_password?(password)

    token = DeviseTokenAuth::Concerns::User.create_token(user)

    {
      token: token
    }
  end
end

class Mutations::SignOutMutation < Mutations::BaseMutation
  field :success, Boolean, null: false

  def resolve
    DeviseTokenAuth::Concerns::User.invalidate_token(context[:current_user])

    {
      success: true
    }
  end
end
```

**Customization**

You can customize the above mutations and resolvers to meet your specific requirements. For example, you can add additional validation checks to the sign-up and sign-in mutations, or you can implement a different way to invalidate authentication tokens in the sign-out mutation.

**Usage**

To use the above mutations, you can send GraphQL queries to your GraphQL API. For example, to sign up a new user, you would send the following query:

```
mutation {
  register(email: "john.doe@example.com", password: "password123") {
    user {
      id
      name
      email
    }
    token
  }
}
```

To sign in a user, you would send the following query:

```
mutation {
  signIn(email: "john.doe@example.com", password: "password123") {
    token
  }
}
```

To sign out a user, you would send the following query:

```
mutation {
  signOut
}
```

Once you have signed in a user, you can use the authentication token to make authenticated requests to your GraphQL API. For example, to fetch all users, you would send the following query with the authentication token in the `Authorization` header:

```
query {
  users {
    id
    name
    email
  }
}

Authorization: Bearer YOUR_AUTHENTICATION_TOKEN
```

I hope this helps!


  ...
