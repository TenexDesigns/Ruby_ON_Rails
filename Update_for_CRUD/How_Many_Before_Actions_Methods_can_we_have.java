In Ruby on Rails, you can define multiple `before_action` methods for different controller actions. You can use `before_action` to set up common tasks, perform authentication, or prepare data before executing specific actions. You can specify one or more actions to trigger each `before_action`. Here's an example of how you can define multiple `before_action` methods for different actions:

```ruby
class MyController < ApplicationController
  before_action :common_setup, only: [:show, :edit]
  before_action :authenticate_user, only: [:edit, :update, :destroy]
  before_action :load_resource, only: [:show, :edit, :update, :destroy]

  # Define controller actions
  def show
    # ...
  end

  def edit
    # ...
  end

  def update
    # ...
  end

  def destroy
    # ...
  end

  private

  def common_setup
    # Perform common setup for the show and edit actions
  end

  def authenticate_user
    # Perform user authentication for the edit, update, and destroy actions
  end

  def load_resource
    # Load the resource (e.g., a book) for the specified actions
  end
end
```

In this example, we have three `before_action` methods: `common_setup`, `authenticate_user`, and `load_resource`. Each method is executed before specific actions. 

- `common_setup` runs before the `show` and `edit` actions.
- `authenticate_user` runs before the `edit`, `update`, and `destroy` actions.
- `load_resource` runs before the `show`, `edit`, `update`, and `destroy` actions.

You can mix and match different `before_action` methods and specify different actions for each one, depending on your application's requirements. This allows you to keep your code organized and ensures that necessary tasks are executed before the intended controller actions.


  ...
