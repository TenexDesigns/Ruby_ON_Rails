discusss call backs in rails with code samples inclusding                                                       
1. Creating an Object
before_validation
after_validation
before_save
around_save
before_create
around_create
after_create
after_save
after_commit / after_rollback

2. Updating an Object
before_validation
after_validation
before_save
around_save
before_update
around_update
after_update
after_save
after_commit / after_rollback

3. Destroying an Object
before_destroy
around_destroy
after_destroy
after_commit / after_rollback aslo include these 

4 after_initialize and after_find  and after_touch









HERE IS THE EXPLANTION
************************************************************************************************************************





Callbacks in Rails provide hooks for executing custom code at specific points in the lifecycle of an object.
Heres an overview of the commonly used callbacks in Rails, categorized by object operations:

Creating an Object:

before_validation: Executed before validations are run.
after_validation: Executed after validations are run.
before_save: Executed before the object is saved.
around_save: Wraps the save operation, allowing code to run before and after the save.
before_create: Executed before a new object is created and saved.
around_create: Wraps the create operation, allowing code to run before and after the creation.
after_create: Executed after a new object is created and saved.
after_save: Executed after the object is saved, whether it's a new record or an existing one.
after_commit / after_rollback: Executed after the transaction is committed or rolled back.
  
  
  
Updating an Object:

before_validation: Executed before validations are run.
after_validation: Executed after validations are run.
before_save: Executed before the object is saved.
around_save: Wraps the save operation, allowing code to run before and after the save.
before_update: Executed before an existing object is updated and saved.
around_update: Wraps the update operation, allowing code to run before and after the update.
after_update: Executed after an existing object is updated and saved.
after_save: Executed after the object is saved.
after_commit / after_rollback: Executed after the transaction is committed or rolled back.
  
  
  
Destroying an Object:

before_destroy: Executed before the object is destroyed.
around_destroy: Wraps the destroy operation, allowing code to run before and after the destruction.
after_destroy: Executed after the object is destroyed.
after_commit / after_rollback: Executed after the transaction is committed or rolled back.
  
  
  
  
Other Callbacks:

after_initialize: Executed after a new object is instantiated.
after_find: Executed after an object is fetched from the database.
after_touch: Executed after an object has been touched (update of the updated_at timestamp).
Heres an example that demonstrates the usage of some of these callbacks:


class User < ApplicationRecord
  before_save :normalize_name
  after_create :send_welcome_email
  before_update :encrypt_password, if: :password_changed?
  after_destroy :log_deletion

  private

  def normalize_name
    self.name = name.downcase.capitalize
  end

  def send_welcome_email
    UserMailer.welcome_email(self).deliver_now
  end

  def encrypt_password
    self.password = BCrypt::Password.create(password)
  end

  def password_changed?
    encrypted_password_changed?
  end

  def log_deletion
    Rails.logger.info("User #{id} was deleted at #{Time.current}")
  end
end
In this example, we have defined several callbacks for the User model.
The before_save callback normalizes the name attribute before saving.
The after_create callback sends a welcome email to the user after creation.
The before_update callback encrypts the password if it has changed. 
The after_destroy callback logs the deletion of a user.

Callbacks allow you to encapsulate business logic and perform specific actions at different 
stages of an objects lifecycle in Rails.
They help maintain data integrity, trigger additional operations, and integrate with external systems.













MORE  EXPLANTION
************************************************************************************************************************


Rails callbacks are methods that are called at certain points in the lifecycle of an ActiveRecord object, 
such as when creating, updating, or destroying an object. Here are some examples of callbacks that can be used in Rails:

  
  
  
Creating an Object:
  
before_validation: This callback is called before the object is validated.
after_validation: This callback is called after the object is validated.
before_save: This callback is called before the object is saved.
around_save: This callback is called before and after the object is saved.
before_create: This callback is called before the object is created.
around_create: This callback is called before and after the object is created.
after_create: This callback is called after the object is created.
after_save: This callback is called after the object is saved.
after_commit / after_rollback: This callback is called after the transaction is committed or rolled back.
class User < ApplicationRecord
  before_save :encrypt_password

  private

  def encrypt_password
    self.password = PasswordEncryptor.encrypt(password)
  end
end






Updating an Object:
  
before_validation: This callback is called before the object is validated.
after_validation: This callback is called after the object is validated.
before_save: This callback is called before the object is saved.
around_save: This callback is called before and after the object is saved.
before_update: This callback is called before the object is updated.
around_update: This callback is called before and after the object is updated.
after_update: This callback is called after the object is updated.
after_save: This callback is called after the object is saved.
after_commit / after_rollback: This callback is called after the transaction is committed or rolled back.
class User < ApplicationRecord
  before_update :send_notification

  private

  def send_notification
    NotificationSender.send_notification(self)
  end
end





Destroying an Object:
  
before_destroy: This callback is called before the object is destroyed.
around_destroy: This callback is called before and after the object is destroyed.
after_destroy: This callback is called after the object is destroyed.
after_commit / after_rollback: This callback is called after the transaction is committed or rolled back.
class User < ApplicationRecord
  before_destroy :send_farewell_email

  private

  def send_farewell_email
    EmailSender.send_farewell_email(self)
  end
end







Other Callbacks:
  
after_initialize: This callback is called after a new object is instantiated.
after_find: This callback is called after a record is found by a finder method.
after_touch: This callback is called after an object is touched.
class User < ApplicationRecord
  after_initialize :set_default_role

  private

  def set_default_role
    self.role ||= 'user'
  end
end
In these examples, the methods in the User model have access to the data being saved, updated, or destroyed,
and the fields in the User table. The callbacks are used to perform additional operations during the lifecycle of 
an ActiveRecord object.

In summary, Rails callbacks provide a way to execute code at certain points in the lifecycle of an ActiveRecord object. 
There are callbacks for creating, updating, and destroying objects, as well as other callbacks like after_initialize,
after_find, and after_touch. The methods in the User model have access to the data being saved, updated, 
or destroyed, and the fields in the User table.
























































































































































..
