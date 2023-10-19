ActionController Parameters:

ActionController Parameters are a part of ActionController in Ruby on Rails and are responsible for handling 
and sanitizing user input from HTTP requests. They provide a convenient interface for accessing and manipulating
the parameters sent with a request.

Hash and Array Parameters:
When parameters are sent in a hash or array format, ActionController automatically converts them
into nested parameters accessible through the params hash. For example:


# Request: POST /users with parameters { user: { name: 'John', age: 25 } }
def create
  name = params[:user][:name]
  age = params[:user][:age]
  # ...
end

In this example, the params hash contains nested parameters under the user key, allowing easy access to individual values.

JSON Parameters:
ActionController also supports parsing JSON parameters. When a requests Content-Type is 
set to application/json, Rails automatically parses the request body and provides access to 
the JSON data through the params hash. For example:

# Request: POST /users with JSON data { "name": "John", "age": 25 }
def create
  name = params[:name]
  age = params[:age]
  # ...
end
In this case, the JSON parameters are accessible directly from the params hash without the need for explicit nesting.

Routing Parameters:
Routing parameters are additional parameters passed through the URL or route definitions.
They are accessible through the params hash as well. For example:


# Route: get '/users/:id'
# Request: GET /users/1
def show
  user_id = params[:id]
  # ...
end
In this case, the id parameter is extracted from the URL and made available through the params hash.

default_url_options:
The default_url_options method allows you to set default URL options for your application.
This is particularly useful when generating URLs in mailers or background jobs.
You can define it in your controller or application configuration. For example:


class ApplicationController < ActionController::Base
  def default_url_options
    { host: 'example.com' }
  end
end

This sets the default host for generated URLs to example.com.

Strong Parameters:
Strong Parameters provide a security mechanism to protect against mass assignment vulnerabilities.
It allows you to define a whitelist of permitted parameters for mass assignment in your controllers using the permit method.
For example:


def user_params
  params.require(:user).permit(:name, :email)
end

In this case, only the name and email attributes of the user parameter are allowed,
preventing any other parameters from being used in mass assignment.

ActionController Parameters play a crucial role in handling user input and ensuring data 
integrity and security in Ruby on Rails applications. They provide a flexible and convenient 
way to access and manipulate request parameters while protecting against potential vulnerabilities.












































































































...
