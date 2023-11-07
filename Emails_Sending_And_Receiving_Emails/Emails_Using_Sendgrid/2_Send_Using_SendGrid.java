Sending emails using an email service provider like SendGrid involves using their SMTP server to send out your emails. The steps to set up SendGrid with your Rails GraphQL API application are very similar to the above setup, but with some modifications specific to SendGrid.

First, you need to sign up for a SendGrid account and create an API key. The API key will be used to authenticate your application with SendGrid's SMTP server.

Next, you'll need to configure your Rails application to use SendGrid's SMTP server. You can do this in your environment configuration files (for example, `config/environments/production.rb` for the production environment). 

Here is a sample configuration:

```ruby
config.action_mailer.delivery_method = :smtp
config.action_mailer.smtp_settings = {
  address: 'smtp.sendgrid.net',
  port: 587,
  domain: 'yourdomain.com',
  user_name: 'apikey', # This is the string literal 'apikey', NOT the ID of your API key
  password: 'your_sendgrid_api_key', # This is the secret sendgrid API key which was issued during API key creation
  authentication: 'plain',
  enable_starttls_auto: true
}
```

Remember to replace `'your_sendgrid_api_key'` with your actual SendGrid API key [Source 1](https://sendgrid.com/docs/for-developers/sending-email/rubyonrails/).

Regarding sensitive information like your SendGrid API key, it's a good practice to not hardcode these in your application. You can use Rails' credentials feature to safely store sensitive information. You can add your SendGrid API key to your credentials file (`config/credentials.yml.enc`) like so:

```yml
sendgrid:
  api_key: your_sendgrid_api_key
```

And then reference it in your environment configuration file:

```ruby
password: Rails.application.credentials.dig(:sendgrid, :api_key)
```

As for receiving emails, yes, the process remains the same. You can use Action Mailbox, which was introduced in Rails 6. Action Mailbox routes incoming emails to controller-like mailboxes for processing in Rails. It ships with ingresses for Mailgun, Mandrill, Postmark, and SendGrid [Source 2](https://edgeguides.rubyonrails.org/action_mailbox_basics.html).

Here is a brief example of how to use Action Mailbox:

```ruby
# app/mailboxes/application_mailbox.rb
class ApplicationMailbox < ActionMailbox::Base
  routing /@mydomain.com/ => :my_mailbox
end

# app/mailboxes/my_mailbox.rb
class MyMailbox < ApplicationMailbox
  def process
    # do something with the email
  end
end
```

With this setup, any email sent to an address at `mydomain.com` will be routed to `MyMailbox` for processing.

In conclusion, using an email service provider like SendGrid can provide several advantages over using your own SMTP server, such as better deliverability, easier scalability, and access to advanced features like email analytics. However, the best choice depends on your specific requirements and constraints.
