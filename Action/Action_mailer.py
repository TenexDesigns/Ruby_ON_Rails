Rails provides the ActionMailer component that enables applications to send and receive emails [7].
Here are the steps to send email using ActionMailer in Rails:

Generate a mailer using the rails generate mailer command:
rails generate mailer UserMailer
This will generate a user_mailer.rb file in the app/mailers directory, along with associated views and a test file.

Define a method in the mailer that sets up the email content:
class UserMailer < ApplicationMailer
  def welcome_email(user)
    @user = user
    @url  = 'http://example.com/login'
    mail(to: @user.email, subject: 'Welcome to My Awesome Site')
  end
end
In this example, were defining a welcome_email method that takes a user object as an argument 
and sets up the email content with instance variables @user and @url.
Were then calling the mail method to specify the recipient and subject of the email.

Create a view file for the email in the app/views/user_mailer directory:
<!DOCTYPE html>
<html>
  <head>
    <meta content='text/html; charset=UTF-8' http-equiv='Content-Type' />
  </head>
  <body>
    <h1>Welcome to example.com, <%= @user.name %></h1>
    <p>
      You have successfully signed up to example.com,
      your username is: <%= @user.login %>.<br>
    </p>
    <p>
      To login to the site, just follow this link: <%= @url %>.
    </p>
    <p>Thanks for joining and have a great day!</p>
  </body>
</html>
This view file defines the HTML content of the email and uses the instance variables @user and @url to
personalize the email for the recipient.

Call the mailer method from a controller action:
class UsersController < ApplicationController
  def create
    @user = User.new(user_params)

    if @user.save
      UserMailer.welcome_email(@user).deliver_now
      redirect_to @user
    else
      render 'new'
    end
  end
end
In this example, were calling the welcome_email method from the UserMailer mailer and passing
in the @user object as an argument. Were then calling the deliver_now method to send the email immediately.

Here are some key points to keep in mind when sending email in Rails using ActionMailer:

The ActionMailer component allows you to send emails from your Rails application [7].
To use ActionMailer, generate a mailer using the rails generate mailer command [1] [7].
Define a method in the mailer that sets up the email content, and create a view file for the email in the app/views directory [1] [4].
Call the mailer method from a controller action to send the email [1] [4].
In summary, sending email in Rails using ActionMailer is a straightforward process.
Once you set up the mailer and define the email content and view, 
you can call the mailer method from a controller action to send the email.









MORE EXPLANTION
********************************************************************************************************************





ActionMailer is a component in Ruby on Rails that facilitates sending emails from your application.
It provides a set of classes and methods to compose and deliver email messages.
Here's an overview of how ActionMailer works and how to send emails in Rails:

Configuration: To use ActionMailer, you need to configure the email delivery settings in your Rails application. 
  This typically involves specifying the SMTP server details, authentication credentials, and other options.
  The configuration can be done in the config/environments/*.rb files or in an initializer file like config/initializers/mail.rb.

Mailer Class: In Rails, you define mailers as Ruby classes that inherit from ActionMailer::Base.
      Each mailer represents a logical group of related email functionality.
      For example, you might have a UserMailer class for sending emails related to user actions. Mailer classes are typically stored in the app/mailers directory.

Mailer Methods: Within a mailer class, you define methods that correspond to different email templates or types.
  These methods are responsible for composing the email content and setting up the recipients,
  subject, and other details. The method names typically match the template names. For example:


class UserMailer < ActionMailer::Base
  def welcome_email(user)
    @user = user
    mail(to: user.email, subject: "Welcome to My App!")
  end
end
In this example, the welcome_email method sets up an instance variable @user and calls mail to compose 
and send the email to the user's email address.

Views and Templates: ActionMailer uses view templates to define the content of the email messages.
  These templates are similar to regular Rails view templates and are stored in the app/views directory within 
  a folder corresponding to the mailer class name. For example, the view template for the welcome_email method 
  in the UserMailer class would
  be stored at app/views/user_mailer/welcome_email.html.erb or app/views/user_mailer/welcome_email.text.erb, depending on 
  the desired format (HTML or plain text).

Sending Emails: To send an email, you instantiate the mailer class and call the method corresponding to the email you want to send.
  For example:

ruby
Copy code
UserMailer.welcome_email(user).deliver_now
In this example, the welcome_email method is called on the UserMailer class and passed the user object.
The deliver_now method triggers the email delivery immediately.

ActionMailer provides additional features like attachments, inline images, and multipart emails. 
You can also configure default email headers, handle email layouts, and use email previews for testing.

To summarize, ActionMailer is a powerful component in Rails for sending emails. 
By defining mailer classes and methods, you can easily compose and send email messages from your application.
It provides a straightforward way to integrate email functionality and keep your code organized.






















































































....
