To implement token-based authentication in a GraphQL Rails API, you can use the `devise` and `devise_token_auth` gems for user authentication, and JSON Web Tokens (JWT) for token generation and validation.

Here's a step-by-step guide:

**1. User Registration and Login**

Firstly, you'll need to create mutations for user registration and login. These mutations will handle the processes of user registration and authentication. Here's an example of how you can define these mutations:

```ruby
module Mutations
  class Register < BaseMutation
    argument :email, String, required: true
    argument :password, String, required: true

    field :user, Types::UserType, null: true
    field :token, String, null: true
    field :errors, [String], null: true

    def resolve(email:, password:)
      user = User.new(email: email, password: password)

      if user.save
        token = user.create_new_auth_token
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
  
  class Login < BaseMutation
    argument :email, String, required: true
    argument :password, String, required: true

    field :user, Types::UserType, null: true
    field :token, String, null: true
    field :errors, [String], null: true

    def resolve(email:, password:)
      user = User.find_for_database_authentication(email: email)

      if user && user.valid_password?(password)
        token = user.create_new_auth_token
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

In the `Register` mutation, a new user is created with the provided email and password. If the user is saved successfully, a new token is generated and returned along with the user data.

In the `Login` mutation, we find the user by email and check if the provided password is valid. If it is, a new token is generated and returned along with the user data [Source 4](https://www.makeuseof.com/graphql-apis-secure-user-authentication-expressjs-jwts/).

**2. Token Generation and Verification**

For token generation and verification, you can use the `JWT` gem. Here's an example of how you can generate and verify tokens:

```ruby
class JsonWebToken
  SECRET_KEY = Rails.application.secrets.secret_key_base.to_s

  def self.encode(payload, exp = 24.hours.from_now)
    payload[:exp] = exp.to_i
    JWT.encode(payload, SECRET_KEY)
  end

  def self.decode(token)
    decoded = JWT.decode(token, SECRET_KEY)[0]
    HashWithIndifferentAccess.new decoded
  rescue
    nil
  end
end
```

The `encode` method generates a new token with an expiration time of 24 hours from now. The `decode` method validates the token and returns its decoded content [Source 6](https://www.pluralsight.com/guides/token-based-authentication-with-ruby-on-rails-5-api).

**3. Token Authentication**

Next, you'll need to authenticate the token in each request. This can be done in the `ApplicationController`:

```ruby
class ApplicationController < ActionController::API
  before_action :authenticate_request

  attr_reader :current_user

  private

  def authenticate_request
    @current_user = AuthorizeApiRequest.call(request.headers).result
    render json: { error: 'Not Authorized' }, status: 401 unless @current_user
  end
end
```

In the `authenticate_request` method, we authenticate the token from the request headers. If the token is valid, we set `@current_user` to the user associated with the token. If the token is invalid, we respond with a 401 status code [Source 6](https://www.pluralsight.com/guides/token-based-authentication-with-ruby-on-rails-5-api).

**4. Use the Token in GraphQL**

Finally, you'll need to use the token in your GraphQL context. This can be done in your `GraphqlController`:

```ruby
class GraphqlController < ApplicationController
  def execute
    variables = ensure_hash(params[:variables])
    query = params[:query]
    operation_name = params[:operationName]
    context = { current_user: current_user }
    result = GraphqlSchema.execute(query, variables: variables, context: context, operation_name: operation_name)
    render json:


          ....
