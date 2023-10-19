ActionController::Session is a module provided by Ruby on Rails that enables session management in ActionController.
    Sessions allow you to store data that persists across multiple requests for a particular user. 
    This allows you to maintain state and store user-specific information throughout their interaction with your application.

Heres an explanation of how ActionController::Session works:

Enabling Sessions:
To enable sessions in your Rails application, you need to include the ActionController::Base.session module 
    in your controller. This is usually done automatically when creating a new Rails application.

Session Storage:
By default, ActionController::Session uses cookies to store session data on the client-side.
    The session data is encrypted and signed to ensure security.
    Rails automatically manages the session cookies for you, so you dont have to manually handle the cookie storage.

Accessing Session Data:
You can access session data using the session object within your controllers.
The session object acts as a hash-like container, allowing you to store and retrieve values associated with a
specific users session. For example:


class UsersController < ApplicationController
  def create
    session[:user_id] = @user.id
    # ...
  end

  def show
    @user = User.find(session[:user_id])
    # ...
  end
end

In this example, the create action sets the user_id in the session, while the show action retrieves it.

Flash Messages:
ActionController::Session also includes support for flash messages,
    which are temporary messages that persist across a single redirect. 
    Flash messages are useful for displaying success or error messages to the user. 
    You can use the flash object within your controllers to set flash messages. For example:


class UsersController < ApplicationController
  def create
    flash[:success] = "User created successfully!"
    redirect_to user_path(@user)
  end
end
In this example, a success flash message is set, and the user is redirected to the show page.

Configuration:
You can configure various aspects of session management by modifying the
config.session_options hash in your Rails applications configuration
files (config/application.rb or environment-specific files like config/environments/development.rb).
Configuration options include session storage, expiration time, domain restriction, and more.

ActionController::Session provides a convenient way to manage session data in your Rails application. 
    It allows you to store user-specific information securely and retrieve it across multiple requests.
    By leveraging sessions, you can create personalized and stateful web applications.










































































































...
