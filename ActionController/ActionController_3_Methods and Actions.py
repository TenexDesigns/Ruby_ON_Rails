ActionController provides a variety of methods and actions that are used to handle HTTP requests
and generate appropriate responses in a Ruby on Rails application. Here are some commonly used methods and actions:

  
  
Methods for Handling Requests:

params: A method that provides access to the parameters sent with the request.
request: A method that provides information about the current request, such as headers, HTTP method, and URL.
render: A method used to render views or generate responses, allowing you to specify the format, status code, and content.
redirect_to: A method used to redirect the user to a different URL or route.
head: A method used to send a response with only the HTTP headers and no body content.
cookies: A method used to read, write, and delete cookies associated with the current request and response.
  
  
Actions:
  
Actions in Rails controllers are methods that handle specific HTTP requests.
Each action is responsible for processing the request, interacting with models or services,
and generating an appropriate response. Common actions include:

index: Handles a GET request to retrieve a collection of resources.
show: Handles a GET request to retrieve a specific resource.
new: Handles a GET request to display a form for creating a new resource.
create: Handles a POST request to create a new resource based on the submitted form data.
edit: Handles a GET request to display a form for editing an existing resource.
update: Handles a PATCH/PUT request to update an existing resource based on the submitted form data.
destroy: Handles a DELETE request to delete an existing resource.
  
  
  
Filters:
  
Filters are methods that are executed before, after, or around specific actions. 
They are used to perform tasks such as authentication, authorization, logging,
or modifying the request/response. Commonly used filters include:

before_action: A filter that runs before a specified action.
after_action: A filter that runs after a specified action.
around_action: A filter that wraps the execution of a specified action.
skip_before_action: A method to skip a specific before_action for certain actions.
  
  
  
  
Callbacks:
  
Callbacks are similar to filters and allow you to execute code at specific points in the request lifecycle.
Common callbacks include:

before_validation: Runs before the validation of a model.
after_create: Runs after a new record is created.
before_save: Runs before a model is saved.
after_save: Runs after a model is saved.
  
  
These are just a few examples of the methods and actions provided by ActionController.
They enable you to handle different types of requests, manipulate data, generate responses, 
and control the flow of your application. By leveraging these methods and actions,
you can build powerful and dynamic web applications in Ruby on Rails.














































































































...
