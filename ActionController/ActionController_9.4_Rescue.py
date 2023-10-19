In ActionController, you can handle exceptions and errors using the rescue mechanisms provided by the framework. 
This allows you to catch and handle exceptions in a centralized way, providing a consistent response to errors.
Lets explore the different aspects of exception handling in ActionController:

1. The Default 500 and 404 Templates:

ActionController provides default error templates for HTTP status codes 500 (Internal Server Error) and 404 (Not Found). 
These templates are displayed when an unhandled exception occurs or when a route is not found. 
The default templates can be found in the public directory of your Rails application.

To customize these templates, you can create your own error pages and place them in the public directory,
following the naming convention: public/500.html and public/404.html.
  Rails will automatically render these custom error pages when the corresponding errors occur.

2. rescue_from:

The rescue_from method in ActionController allows you to catch and handle specific exceptions or error classes at the controller level.
It allows you to define custom behavior for handling exceptions, such as rendering specific views or redirecting to a different page. 
Here's an example:

class ApplicationController < ActionController::Base
  rescue_from ActiveRecord::RecordNotFound, with: :record_not_found
  rescue_from StandardError, with: :handle_error

  private

  def record_not_found
    render plain: 'Record not found', status: :not_found
  end

  def handle_error(exception)
    logger.error(exception.message)
    render plain: 'Something went wrong', status: :internal_server_error
  end
end
In this example, the rescue_from method is used to catch ActiveRecord::RecordNotFound and StandardError exceptions. 
    The record_not_found method is called when a RecordNotFound exception occurs, and it renders a plain text response 
    with a 404 status code. The handle_error method is called for any other exception and logs the error message and 
    renders a plain text response with a 500 status code.

By using rescue_from, you can handle specific exceptions and provide customized error handling logic at the controller level.

These are some of the ways you can handle exceptions and errors in ActionController.
By customizing the default error templates and using rescue_from, you can provide a better user
experience and gracefully handle errors that occur in your Rails application.


































































































...
