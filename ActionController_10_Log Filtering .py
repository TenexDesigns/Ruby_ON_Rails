ActionController provides log filtering mechanisms to ensure sensitive information, such as parameters and redirects, 
are not logged or displayed in log files or debug output.
This helps to protect sensitive data from being exposed in logs. Lets explore the two main log filtering techniques:

1. Parameters Filtering:

By default, ActionController filters sensitive parameters from being logged. 
These parameters usually include passwords, credit card numbers, and other confidential information.
The filtered parameters are replaced with the [FILTERED] placeholder in the logs. Heres an example:


# config/application.rb
config.filter_parameters += [:password, :credit_card_number]
In this example, the config.filter_parameters configuration option is set to an array of parameters that should be filtered.
Any occurrence of these parameters in the log will be replaced with [FILTERED].

2. Redirects Filtering:

In addition to parameter filtering, ActionController also provides redirect filtering to prevent sensitive information from being included in log files or debug output when performing redirects. By default, certain redirect locations are filtered to avoid exposing sensitive data, such as authentication tokens or session IDs. Here's an example:

# config/application.rb
config.filter_redirect << 'http://example.com/redirect_path'
In this example, the config.filter_redirect configuration option is set to an array of redirect locations that should be filtered. 
Any redirect to these locations will be replaced with [FILTERED] in the logs or debug output.

These log filtering mechanisms help protect sensitive information from being exposed in log files, 
which is crucial for maintaining the security of your application. By configuring parameter filtering and redirect filtering,
you can ensure that sensitive data is not logged or displayed unintentionally.

Please note that log filtering is a security measure to prevent sensitive information from being exposed,
but it should not be considered a foolproof method. It is still essential to properly secure and handle sensitive data in your application to minimize any potential risks.

































































































.
