Active Model is a library in Rails that provides a known set of interfaces for usage in model classes. It includes methods for validations, callbacks, translations, and more. Active Model helps when creating classes that need to interact with Action Pack, Action View, or other Rails components. It's also useful when you want to include features that are present in Active Record models in non-Active Record models.

Here's a guide to using some of the main features of Active Model in a Rails API:

### ActiveModel::Attributes

ActiveModel::Attributes allows you to define attributes with a type in non-Active Record models. This can be useful when you want to use attribute-related methods in those models.

```ruby
class Person
  include ActiveModel::Model
  include ActiveModel::Attributes

  attribute :name, :string
  attribute :age, :integer
end

person = Person.new(name: 'John Doe', age: '30')
person.name # => 'John Doe'
person.age # => 30
```

### ActiveModel::Callbacks

ActiveModel::Callbacks provides the ability to define callbacks in your models. Callbacks are methods that get called at certain moments of an object's life cycle.

```ruby
class Person
  extend ActiveModel::Callbacks

  define_model_callbacks :create

  def create
    run_callbacks :create do
      # Your create action methods here
    end
  end
end
```

### ActiveModel::Dirty

ActiveModel::Dirty adds methods to track changes in your model. It's useful when you need to know whether an attribute has changed, what the previous value was, etc.

```ruby
class Person
  include ActiveModel::Model
  include ActiveModel::Dirty

  define_attribute_methods :name

  def name
    @name
  end

  def name=(val)
    name_will_change! unless val == @name
    @name = val
  end

  def save
    # This method should have the action to 'save' the model
    changes_applied
  end
end

person = Person.new
person.name = 'John Doe'
person.name_changed? # => true
person.save
person.name_changed? # => false
```

### ActiveModel::Validations

ActiveModel::Validations module provides a way to add validations to your models.

```ruby
class Person
  include ActiveModel::Model
  include ActiveModel::Validations

  attr_accessor :name, :email

  validates :name, presence: true
  validates :email, format: { with: URI::MailTo::EMAIL_REGEXP }
end

person = Person.new
person.valid? # => false
person.errors.messages # => {:name=>["can't be blank"], :email=>["is invalid"]}
```

### ActiveModel::Model

ActiveModel::Model includes methods for models that need to behave like Active Record objects, without the database functionality.

```ruby
class Person
  include ActiveModel::Model

  attr_accessor :name, :age
end

person = Person.new(name: 'John Doe', age: 30)
person.name # => 'John Doe'
person.age # => 30
```
In the above examples, the `Person` class includes different modules from ActiveModel, defining attributes, and implementing features like validations, callbacks, and dirty tracking.

Remember, these are simple examples. Depending on your requirements, you may need to add more complex logic to these methods [Source 0](https://guides.rubyonrails.org/active_model_basics.html), [Source 1](https://api.rubyonrails.org/classes/ActiveModel.html), [Source 3](https://api.rubyonrails.org/classes/ActiveModel/API.html).
                                                                                                                                        

                                                                                                                                        
                                                                                                                                        ..
