When you include mount_devise_token_auth_for 'User', at: 'auth' in your routes, the gem generates the following authentication-related routes under '/auth':

POST '/auth/sign_in' for signing in.
DELETE '/auth/sign_out' for signing out.
POST '/auth' for registering a new user (sign-up).
PUT '/auth' for updating user information.
DELETE '/auth' for destroying the user account.
POST '/auth/password' for resetting the password.
The authenticate_user! method, when added as a before_action in your controller, checks if the user is authenticated. It relies on the token-based authentication system provided by




  
