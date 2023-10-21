Ruby on Rails provides a collection of predefined HTTP response statuses as symbols that you can use in your controllers for common scenarios. Some of these include:

- `:ok` for a 200 OK response.
- `:created` for a 201 Created response.
- `:no_content` for a 204 No Content response.
- `:moved_permanently` for a 301 Moved Permanently response.
- `:found` for a 302 Found response.
- `:see_other` for a 303 See Other response.
- `:not_modified` for a 304 Not Modified response.
- `:temporary_redirect` for a 307 Temporary Redirect response.
- `:bad_request` for a 400 Bad Request response.
- `:unauthorized` for a 401 Unauthorized response.
- `:payment_required` for a 402 Payment Required response.
- `:forbidden` for a 403 Forbidden response.
- `:not_found` for a 404 Not Found response.
- `:method_not_allowed` for a 405 Method Not Allowed response.
- `:not_acceptable` for a 406 Not Acceptable response.
- `:proxy_authentication_required` for a 407 Proxy Authentication Required response.
- `:request_timeout` for a 408 Request Timeout response.
- `:conflict` for a 409 Conflict response.
- `:gone` for a 410 Gone response.
- `:precondition_failed` for a 412 Precondition Failed response.
- `:unprocessable_entity` for a 422 Unprocessable Entity response.
- `:locked` for a 423 Locked response.
- `:too_many_requests` for a 429 Too Many Requests response.
- `:internal_server_error` for a 500 Internal Server Error response.
- `:not_implemented` for a 501 Not Implemented response.
- `:bad_gateway` for a 502 Bad Gateway response.
- `:service_unavailable` for a 503 Service Unavailable response.
- `:gateway_timeout` for a 504 Gateway Timeout response.

These are some of the common HTTP response statuses that Ruby on Rails makes available for use. You can use these symbols when rendering responses in your controller actions to generate the appropriate HTTP status codes in the response to client requests.





  ......
