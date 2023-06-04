In Ruby on Rails, ActionController provides the Request and Response objects that allow you to access information 
about the incoming request and construct the response to be sent back to the client.
Heres an overview of the Request and Response objects and how they can be used:

Request Object:
The Request object encapsulates all the information about the incoming HTTP request. 
It provides access to parameters, headers, cookies, session data, and other request-related information. 
Some commonly used methods and attributes of the Request object include:

params: Returns a hash of request parameters, including URL parameters, form data, and query parameters.
headers: Provides access to the request headers as a hash.
cookies: Gives access to the request cookies as a hash.
session: Provides access to the session data associated with the request.
request_method: Returns the HTTP request method (e.g., GET, POST, PUT, DELETE).
path: Returns the path portion of the request URL.
xhr?: Indicates whether the request was made via an AJAX (XMLHttpRequest) call.
Here's an example of accessing request parameters and headers in a controller action:


class UsersController < ApplicationController
  def show
    user_id = params[:id]
    user_agent = request.headers['User-Agent']
    
    # ... rest of the action
  end
end
In this example, params[:id] accesses the id parameter from the request URL, 
and request.headers['User-Agent'] retrieves the User-Agent header of the request.

Response Object:
The Response object represents the response that will be sent back to the client. 
It allows you to set the response body, headers, status code, cookies,
and other response-related attributes. Some commonly used methods and attributes of the Response object include:

body=: Sets the response body content.
headers: Provides access to the response headers as a hash.
status=: Sets the HTTP status code of the response.
content_type=: Sets the content type of the response.
set_cookie: Sets a cookie in the response.
redirect: Initiates a redirect to a different URL.
Heres an example of setting the response body and headers in a controller action:

class UsersController < ApplicationController
  def index
    @users = User.all

    # Render response as JSON
    render json: @users, status: :ok, content_type: 'application/json'
  end
end
In this example, render json: @users sets the response body to the JSON representation of @users,
  and status: :ok sets the HTTP status code to 200 (OK). Additionally,
      content_type: 'application/json' sets the response content type to JSON.

The Request and Response objects in ActionController provide a powerful set
of methods and attributes to work with incoming requests and construct appropriate responses.
They enable you to access and manipulate various aspects of the HTTP request and response cycle, 
allowing you to build dynamic and interactive web applications.





















































...
