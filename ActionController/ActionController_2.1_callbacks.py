In ActionController, callbacks are methods that are executed at specific points during the processing of a request. 
They allow you to perform certain actions before or after an action method is executed in a controller.
The two commonly used callbacks in ActionController are before_action and after_action.

1. before_action:
The before_action callback is executed before the action method is called. 
It allows you to define a set of actions or operations that should be performed before executing the main action.
Here's an example:


class UsersController < ApplicationController
  before_action :authenticate_user, except: [:index, :show]

  def index
    # ...
  end

  def show
    # ...
  end

  def edit
    # ...
  end

  private

  def authenticate_user
    # Logic to authenticate the user
    redirect_to login_path unless current_user
  end
end


In this example, the before_action callback is used to authenticate the user before executing any action except for
index and show. The authenticate_user method performs the authentication logic and redirects to the 
login page if the user is not authenticated.

2. after_action:
The after_action callback is executed after the action method has been called. 
It allows you to define actions or operations that should be performed after the main action has completed. Here's an example:

class UsersController < ApplicationController
  after_action :log_action, only: [:create, :update]

  def create
    # ...
  end

  def update
    # ...
  end

  private

  def log_action
    # Logic to log the action
    Rails.logger.info("User action performed")
  end
end
In this example, the after_action callback is used to log the action performed by the user 
after the create and update actions. The log_action method logs the action using the Rails logger.

Both before_action and after_action callbacks can be defined at the controller level or
within individual controller actions. They allow you to perform common operations,
such as authentication, authorization, logging, or setting up instance variables,
before or after the execution of controller actions.

Callbacks are an essential part of ActionController and provide a way to encapsulate 
common behavior that needs to be executed before or after specific actions.
They help in keeping the controller code clean, modular, 
and reusable by separating concerns and promoting DRY (Dont Repeat Yourself) principles.


