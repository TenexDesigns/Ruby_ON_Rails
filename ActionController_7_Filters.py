ActionController filters are a way to execute code before, after, or around certain controller actions. 
Filters allow you to perform common tasks such as authentication, authorization, logging,
and data manipulation in a reusable and centralized manner.
They help in organizing and encapsulating controller logic.

Here are the different types of filters available in ActionController:

Before Filters:
Before filters are executed before the controller action is performed.
They are commonly used for tasks like authentication and authorization.
You can define a before filter using the before_action method in your controller. 
For example:


class UsersController < ApplicationController
  before_action :authenticate_user, only: [:edit, :update]

  def edit
    # ...
  end

  def update
    # ...
  end

  private

  def authenticate_user
    # Logic for authentication goes here
    # Redirect or perform some action if not authenticated
  end
end
In this example, the authenticate_user method is executed before the edit and update actions.

After Filters:
After filters are executed after the controller action is performed. 
They are commonly used for tasks like logging or cleanup operations. 
You can define an after filter using the after_action method in your controller. For example:


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
    # Logic for logging goes here
  end
end
In this example, the log_action method is executed after the create and update actions.

Around Filters:
Around filters allow you to execute code both before and after the controller action.
They are commonly used for tasks like setting up and tearing down resources. 
You can define an around filter using the around_action method in your controller. For example:


class UsersController < ApplicationController
  around_action :wrap_action, only: [:show]

  def show
    # ...
  end

  private

  def wrap_action
    # Code executed before the action
    yield
    # Code executed after the action
  end
end

In this example, the wrap_action method wraps the show action, executing code before and after the action.

Filters help in keeping the code DRY (Dont Repeat Yourself) by centralizing common logic.
They provide a way to encapsulate cross-cutting concerns and apply them to multiple controller actions.
By using filters, you can improve the maintainability and readability of your controller code.






























































































,,,
