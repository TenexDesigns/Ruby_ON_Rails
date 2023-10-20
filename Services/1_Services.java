In a Rails application, the business logic can be placed in several places. However, the most common places are the models, services, or concerns, depending on the complexity and requirements of your application.

### Models

The traditional Rails way is to put the business logic into the models, following the "fat model, skinny controller" principle. This principle suggests that models should handle all the business operations related to the database, while controllers should only be responsible for handling the user's request and sending the response [Source 1](https://blog.appsignal.com/2023/05/10/organize-business-logic-in-your-ruby-on-rails-application.html).

```ruby
class User < ApplicationRecord
  def deactivate_account
    self.active = false
    save!
  end
end
```

However, if a model becomes too complex or starts handling business logic not directly related to its function as a model, it might be time to move some of this logic elsewhere.

### Services

Another option is to use service objects. A service object is a Plain Old Ruby Object (PORO) that encapsulates a set of related operations. You can use services to extract complex business logic from your models and controllers, making your code easier to read and maintain [Source 3](https://www.aha.io/engineering/articles/organizing-code-in-a-rails-monolith).

```ruby
class AccountDeactivationService
  def initialize(user)
    @user = user
  end

  def deactivate
    @user.active = false
    @user.save!
    # More business logic here...
  end
end
```

And you can call this service in your controller:

```ruby
def deactivate
  service = AccountDeactivationService.new(@user)
  service.deactivate
end
```

### Concerns

Concerns are a Rails way to share code among multiple models or controllers. They are modules that you can include in your models or controllers. If you have business logic that's used in multiple models or controllers, you can put it into a concern [Source 2](https://www.reddit.com/r/rails/comments/8s5pa2/where_do_i_put_business_logic/).

```ruby
module Deactivatable
  extend ActiveSupport::Concern

  def deactivate
    self.active = false
    save!
  end
end

class User < ApplicationRecord
  include Deactivatable
end
```

### Final Thoughts

Where to put the business logic in a Rails application depends on the specific needs of your application. If the logic is simple and directly related to a model, it's fine to put it in the model. If the logic is complex or not directly related to a single model, consider using service objects or concerns.

Remember, the goal is to keep your code maintainable, testable, and easy to understand. Choose the approach that best achieves these goals for your application [Source 3](https://www.aha.io/engineering/articles/organizing-code-in-a-rails-monolith).
                                                                                                                                                                           
                                                                                                                                                                           
                                                                                                                                                                           
                                                                                                                                                                           
                                                                                                                                                                           
                                                                                                                                                                           ....
