Active Record  methods for querying, associations, validations, and more explained with code samples




Certainly! Here are examples of Active Record methods for querying, associations, validations, and more:

  
  
Querying Records:

where: Find records that match specified conditions.


students = Student.where(name: 'John')
find: Find a record by its primary key.

student = Student.find(1)
first: Find the first record that matches the conditions.


student = Student.first
last: Find the last record that matches the conditions.

student = Student.last
order: Order records based on a specific attribute.

students = Student.order(:name)
limit: Limit the number of records returned.


students = Student.limit(10)
pluck: Retrieve a specific attribute from matching records.

student_names = Student.where(age: 20).pluck(:name)
Associations:

belongs_to: Define a one-to-one association between models.


class Enrollment < ActiveRecord::Base
  belongs_to :student
  belongs_to :course
end
has_many: Define a one-to-many association between models.


class Student < ActiveRecord::Base
  has_many :enrollments
  has_many :courses, through: :enrollments
end
has_one: Define a one-to-one association between models.

class Profile < ActiveRecord::Base
  belongs_to :user
end

class User < ActiveRecord::Base
  has_one :profile
end
has_many :through: Define a many-to-many association between models through a join model.


class Student < ActiveRecord::Base
  has_many :enrollments
  has_many :courses, through: :enrollments
end

class Course < ActiveRecord::Base
  has_many :enrollments
  has_many :students, through: :enrollments
end

class Enrollment < ActiveRecord::Base
  belongs_to :student
  belongs_to :course
end
Validations:

validates_presence_of: Ensure that a field is not blank.

class Student < ActiveRecord::Base
  validates_presence_of :name, :email
end
validates_uniqueness_of: Ensure that a field has a unique value.


class Student < ActiveRecord::Base
  validates_uniqueness_of :email
end
validates_length_of: Ensure that a field has a specific length.


class Student < ActiveRecord::Base
  validates_length_of :name, minimum: 2, maximum: 50
end
validates_numericality_of: Ensure that a field is a number.

class Student < ActiveRecord::Base
  validates_numericality_of :age
end
Callbacks:

before_save: Perform actions before a record is saved.

class Student < ActiveRecord::Base
  before_save :generate_slug

  def generate_slug
    self.slug = name.parameterize
  end
end
after_create: Perform actions after a record is created.

class Student < ActiveRecord::Base
  after_create :send_welcome_email

  def send_welcome_email
    # Code to send email
  end
end
before_destroy: Perform actions before a record is destroyed.


class Student < ActiveRecord::Base
  before_destroy :cleanup_associated_data

  def cleanup_associated_data
    # Code to remove associated records or data
  end
end

These examples demonstrate some commonly used Active Record methods for querying, associations, validations, and callbacks.
Active Record provides many more methods to handle various scenarios and relationships between models.
Refer to the Rails documentation for a comprehensive list of Active Record methods and their usage.
