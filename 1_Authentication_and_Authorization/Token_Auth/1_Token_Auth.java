To implement token-based authentication in Rails, you can use third-party libraries or gems like `devise-jwt`, `knock`, or `jwt`. Here's an example of how to implement token-based authentication using the `devise-jwt` gem:

1. Add the `devise-jwt` gem to your Gemfile and run `bundle install`:

```ruby
gem 'devise-jwt'
```

2. Generate the necessary files for `devise-jwt`:

```bash
rails generate devise:install
rails generate devise User
rails generate devise-jwt:install
```

3. Configure `devise-jwt` in `config/initializers/devise.rb`:

```ruby
config.jwt do |jwt|
  jwt.secret = ENV['DEVISE_JWT_SECRET_KEY']
  jwt.dispatch_requests = [
    ['POST', %r{^/api/v1/auth/sign_in$}]
  ]
  jwt.revocation_requests = [
    ['DELETE', %r{^/api/v1/auth/sign_out$}]
  ]
end
```

4. Update the `User` model to include `:jwt_authenticatable`:

```ruby
class User < ApplicationRecord
  devise :database_authenticatable, :registerable,
         :jwt_authenticatable, jwt_revocation_strategy: Devise::JWT::RevocationStrategies::Null
end
```

5. Create an authentication controller to handle sign-in and sign-out requests:

```ruby
class Api::V1::AuthController < ApplicationController
  def sign_in
    user = User.find_by(email: params[:email])
    if user&.valid_password?(params[:password])
      render json: { token: user.generate_jwt }
    else
      render json: { error: 'Invalid email or password' }, status: :unauthorized
    end
  end

  def sign_out
    # Implement your sign-out logic here
  end
end
```

6. Create routes for the authentication endpoints in `config/routes.rb`:

```ruby
namespace :api do
  namespace :v1 do
    post 'auth/sign_in', to: 'auth#sign_in'
    delete 'auth/sign_out', to: 'auth#sign_out'
  end
end
```

7. Protect your API endpoints by adding an authentication check:

```ruby
class Api::V1::SomeController < ApplicationController
  before_action :authenticate_user!

  def index
    # Your protected action logic here
  end
end
```

With this setup, you can now use token-based authentication in your Rails application. Users can sign in by sending a POST request to `/api/v1/auth/sign_in` with their email and password. The response will include a token that can be used for subsequent authenticated requests.

Note: This is a basic example to get you started with token-based authentication using `devise-jwt`. You may need to customize it further based on your specific requirements.

Remember to secure your JWT secret key and consider additional security measures like token expiration, refresh tokens, and handling token revocation.

Sources:
- [devise-jwt Gem](https://github.com/waiting-for-dev/devise-jwt)
- [devise-jwt Documentation](https://github.com/waiting-for-dev/devise-jwt/blob/master/README.md)
- [Rails Devise Gem](https://github.com/heartcombo/devise)





Sign Up and Customizing Token Lifespan
-------------------------------


To implement the sign-up functionality in the context of token-based authentication using the `devise-jwt` gem, you can follow these steps:

1. Update the `User` model to include the necessary modules and configurations:

```ruby
class User < ApplicationRecord
  devise :database_authenticatable, :registerable,
         :jwt_authenticatable, jwt_revocation_strategy: Devise::JWT::RevocationStrategies::Null

  # Add any additional validations or attributes you need for user sign-up
end
```

2. Create a controller and route for user registration:

```ruby
class Api::V1::RegistrationsController < ApplicationController
  def create
    user = User.new(user_params)
    if user.save
      render json: { token: user.generate_jwt }
    else
      render json: { error: user.errors.full_messages }, status: :unprocessable_entity
    end
  end

  private

  def user_params
    params.require(:user).permit(:email, :password, :password_confirmation)
  end
end
```

```ruby
namespace :api do
  namespace :v1 do
    post 'auth/sign_up', to: 'registrations#create'
  end
end
```

3. Update the `config/routes.rb` file to include the new route:

```ruby
Rails.application.routes.draw do
  # Other routes...

  namespace :api do
    namespace :v1 do
      post 'auth/sign_in', to: 'auth#sign_in'
      delete 'auth/sign_out', to: 'auth#sign_out'
      post 'auth/sign_up', to: 'registrations#create'
    end
  end
end
```

With these changes, you can now send a POST request to `/api/v1/auth/sign_up` with the user's email, password, and password confirmation to create a new user. If the user is successfully created, the response will include a token that can be used for subsequent authenticated requests.

Regarding customizing the JWT lifespan, you can modify the `jwt` configuration in `config/initializers/devise.rb`:

```ruby
config.jwt do |jwt|
  jwt.secret = ENV['DEVISE_JWT_SECRET_KEY']
  jwt.expiration_time = 1.day.to_i # Set the expiration time to 1 day (adjust as needed)
  # Other configurations...
end
```

By default, the `expiration_time` is set to 1 day. You can adjust it to your desired lifespan, such as 1 hour (`1.hour.to_i`) or 30 minutes (`30.minutes.to_i`), depending on your application's requirements.

Remember to restart your Rails server after making changes to the configuration files.

Sources:
- [devise-jwt Gem](https://github.com/waiting-for-dev/devise-jwt)
- [devise-jwt Documentation](https://github.com/waiting-for-dev/devise-jwt/blob/master/README.md)
- [Rails Devise Gem](https://github.com/heartcombo/devise)























