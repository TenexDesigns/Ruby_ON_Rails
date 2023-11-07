Styling emails in a Rails application typically involves using HTML and CSS. However, it's worth noting that email clients tend to have limited support for modern CSS, so you need to use inline styles and stick to basic CSS properties for the best compatibility. 

There are several ways to style emails in Rails:

1. **Inline CSS**: You can directly add the CSS in the HTML using the `style` attribute. This is the most compatible way across different email clients. However, it can get messy for complex designs. Here is an example:

    ```html
    <p style="color: blue;">This is a blue paragraph.</p>
    ```

2. **CSS in `<style>` tags**: You can include a `<style>` tag in the `<head>` of your HTML email. However, not all email clients support this. Here is an example:

    ```html
    <html>
    <head>
        <style>
            .blue { color: blue; }
        </style>
    </head>
    <body>
        <p class="blue">This is a blue paragraph.</p>
    </body>
    </html>
    ```

3. **CSS Frameworks**: There are several CSS frameworks like Foundation for Emails and MJML that provide pre-written CSS to help with email styling.

4. **Roadie gem**: Another approach is using the [Roadie](https://github.com/Mange/roadie) gem, which automatically inlines your CSS for you. Here is an example from [Source 2](https://www.imaginarycloud.com/blog/rails-send-emails-with-style/):

    ```ruby
    document = Roadie::Document.new "<html><body></body></html>"
    document.add_css "body { color: green; }"
    document.transform
    # => "<html><body style=\"color:green;\"></body></html>"
    ```

5. **Tailwind CSS**: If you're using Tailwind CSS, you can use the [Maizzle](https://maizzle.com/) framework to build your email templates. Maizzle uses PostCSS to process your Tailwind CSS and automatically inlines the styles for you. Here are some articles on how to use Tailwind CSS for emails: [Source 3](https://dev.to/chalmagean/how-to-send-tailwindcss-styled-emails-with-ruby-on-rails-7-3j0d), [Source 4](https://mixandgo.com/learn/ruby-on-rails/email-design-with-tailwindcss).

Remember that you can preview your emails in Rails using the `ActionMailer::Preview` feature as described in [Source 8](https://docs.w3cub.com/rails~6.0/guides/action_mailer_basics.html) and [Source 9](https://guides.rubyonrails.org/v5.0/action_mailer_basics.html). 

Also, note that if you want to skip the template rendering step and supply the email body as a string, you can use the `:body` option. In such cases don't forget to add the `:content_type` option. Rails will default to `text/plain` otherwise [Source 8](https://docs.w3cub.com/rails~6.0/guides/action_mailer_basics.html), [Source 9](https://guides.rubyonrails.org/v5.0/action_mailer_basics.html).

```ruby
class UserMailer < ApplicationMailer
  def welcome_email(user, email_body)
    mail(to: user.email,
         body: email_body,
         content_type: "text/html",
         subject: "Already rendered!")
  end
end
```


  ..
