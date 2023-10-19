ActionController provides built-in protection against Cross-Site Request Forgery (CSRF) attacks,
which is enabled by default in Rails applications. CSRF protection helps prevent unauthorized requests
from being submitted to your application.

Here's an explanation of how ActionController implements CSRF protection:

CSRF Token:
ActionController generates an authenticity token for each form rendered in your views. 
This token is included as a hidden field in the form and stored in the session. When the form is submitted,
the token is sent back to the server to validate the authenticity of the request.

Protecting Controllers:
By default, CSRF protection is enabled for all controller actions
. The protect_from_forgery method is added in the ApplicationController to enable this protection. 
This method automatically inserts a before_action filter that checks the authenticity token for non-GET requests.


class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception
end
This means that any non-GET request (e.g., POST, PATCH, DELETE) will be checked for the presence of a valid CSRF token.

Adding CSRF Token to Forms:
Rails provides a helper method called form_authenticity_token that generates the authenticity token
for inclusion in forms. You can use this method in your view templates to include the CSRF token as a hidden field in your forms.


<form action="/posts" method="post">
  <input type="hidden" name="authenticity_token" value="<%= form_authenticity_token %>">
  <!-- Other form fields -->
  <button type="submit">Submit</button>
</form>


Including the CSRF token in your forms ensures that the token is sent back to the server when the form is submitted.

CSRF protection in ActionController helps mitigate the risk of unauthorized requests and protects
your application from CSRF attacks. By enabling this protection and including the CSRF token in your forms,
you can ensure the integrity and authenticity of requests made to your application.






























































...
