ActionController::Cookies is a module provided by Ruby on Rails that enables cookie management in ActionController.
    Cookies are a way to store small pieces of data on the client-side, which can be used for various purposes 
    like session management, remembering user preferences, tracking user behavior, and more.

Heres an explanation of how ActionController::Cookies works:

Accessing Cookies:
Within your controllers, you can access and manipulate cookies using the cookies object.
The cookies object acts as a hash-like container, allowing you to read, write, and delete cookies. For example:


class UsersController < ApplicationController
  def create
    cookies[:remember_token] = { value: "abc123", expires: 1.week.from_now }
    # ...
  end

  def show
    remember_token = cookies[:remember_token]
    # ...
  end

  def destroy
    cookies.delete(:remember_token)
    # ...
  end
end


In this example, the create action sets a cookie named remember_token with a value that expires in 1 week.
The show action retrieves the remember_token cookie, and the destroy action deletes the cookie.

Setting Cookies Options:
When setting a cookie, you can provide various options as part of a hash. Common options include:

value: The value to store in the cookie.
expires: The expiration time for the cookie.
domain: The domain name for which the cookie is valid.
secure: Whether the cookie should only be sent over secure HTTPS connections.
httponly: Whether the cookie is accessible only through HTTP and not JavaScript.
These options allow you to customize the behavior and security of your cookies.

Signed and Encrypted Cookies:
ActionController::Cookies provides support for signed and encrypted cookies.
Signed cookies ensure the integrity of the cookie data, preventing tampering by the client.
Encrypted cookies provide an additional layer of security by encrypting the cookie value.
To use signed or encrypted cookies, you can specify the signed: true or encrypted: true option when setting a cookie.
For example:


cookies.signed[:remember_token] = { value: "abc123", expires: 1.week.from_now }
cookies.encrypted[:user_id] = { value: 1, expires: 1.week.from_now }
In these examples, the remember_token cookie is signed, and the user_id cookie is encrypted.

ActionController::Cookies provides a convenient way to work with cookies in your Rails application.
It allows you to store and retrieve data on the client-side, enabling various features and functionality.
By utilizing cookies effectively, you can enhance the user experience and personalize your application.












































































































...
