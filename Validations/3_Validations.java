Certainly! I'll provide code samples and explanations for the key areas covered in the article about Active Record validations in Ruby on Rails.

**1. Validating the Presence of Attributes:**
   In this section, the article demonstrates how to validate that certain attributes are not blank using the `presence` validation. Here's a code sample for this:

   ```ruby
   # app/models/user.rb
   class User < ApplicationRecord
       validates :name, presence: true
       validates :email, presence: true
   end
   ```

   If a form for the `User` model is submitted with missing values for `name` or `email`, an error message will be shown to the user.

**2. Validating Whether Two Attributes Match:**
   This section shows how to validate whether two fields submitted by a user match, such as a password and a password confirmation field. Here's a code sample:

   ```ruby
   # app/models/user.rb
   class User < ApplicationRecord
       validates :name, presence: true
       validates :email, presence: true

       validates :email_confirmation, presence: true
       validates :email, confirmation: true
   end
   ```

   This code uses the `confirmation` validation to ensure that `email` and `email_confirmation` match. If they don't match, a confirmation error is generated.

**3. Validating Input Format:**
   The article explains how to validate the format of user inputs using regular expressions. In this example, it ensures that the email address is properly formatted:

   ```ruby
   class User < ApplicationRecord
       validates :email, format: { with: /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\z/ }
   end
   ```

   This code checks if the `email` attribute follows a valid email address format. If it doesn't match, an error is generated.

**4. Validating the Length of an Input:**
   Here, the article discusses how to validate the length of an input, such as ensuring that a password has a specific length:

   ```ruby
   class User < ApplicationRecord
       validates :password, length: 8..15
   end
   ```

   This code ensures that the `password` attribute has a length between 8 and 15 characters. If the length is outside this range, an error is displayed.

**5. Validating Uniqueness of a Join Model:**
   This section demonstrates how to validate the uniqueness of a join model. In the example, it ensures that a user can only have one subscription:

   ```ruby
   # app/models/subscription.rb
   class Subscription < ApplicationRecord
       belongs_to :plan
       belongs_to :user

       validates_uniqueness_of :user_id, scope: :plan_id, message: "User can only have one subscription!"
   end
   ```

   This code uses `validates_uniqueness_of` to ensure that a user's ID is unique in the context of a plan. If a user attempts to create a second subscription, an error is shown.

**6. Handling Active Storage Validations:**
   The article briefly covers how to validate Active Storage attachments. In this example, it ensures that the uploaded file is in PDF format:

   ```ruby
   # app/models/user.rb
   class User < ApplicationRecord
       has_one_attached :resume

       validate :is_pdf

       private

       def is_pdf
           if resume.attached? && !resume.content_type.in?("application/pdf")
               errors.add(:resume, 'Resume should be PDF!')
           end
       end
   end
   ```

   This code validates that the attached `resume` is in PDF format. If it's not a PDF file, an error is generated.

**7. Errors and Messages:**
   The article explains how to customize error messages. In this example, attributes are passed to the error message of the `validates_presence_of` validation:

   ```ruby
   class User < ApplicationRecord
       validates_presence_of :name, message: Proc.new { |user, data|
           "#{data[:attribute]} is needed for all registrations!"
       }
   end
   ```

   This code customizes the error message for the `name` attribute. It provides more context in the error message shown to the user.

These code samples cover the key areas discussed in the article, demonstrating how to implement various validations in a Ruby on Rails application. You can use these examples as a starting point for implementing validations in your own Rails projects.



  ...
