Active Job is a framework for declaring jobs and making them run on a variety of queuing backends. These jobs can be anything that can be chopped up into small units of work and then executed independently. Examples of jobs include sending emails, image processing, updating smart feeds, and more.

Active Job provides an abstraction layer above different background job frameworks, which means you can switch between them with ease. This makes selecting your queue backend a technical decision, and you won't have to rewrite your jobs to switch between them [Source 1](https://guides.rubyonrails.org/active_job_basics.html).

Let's dive into how to set up Active Job with Sidekiq in a Rails application.

### Setting up Sidekiq

Sidekiq is a popular background job processing framework for Rails. It uses Redis to manage and process jobs, providing a simple and efficient way to manage background jobs.

First, add the Sidekiq gem to your Gemfile:

```ruby
gem 'sidekiq'
```

Then, run `bundle install` to install the gem.

Next, update your `config/application.rb` file to include the Sidekiq middleware:

```ruby
config.active_job.queue_adapter = :sidekiq
```

### Creating and Running Background Jobs using Sidekiq

To create a background job, you can use Rails' job generator:

```bash
rails generate job MyJob
```

This will create a new job file in the `app/jobs` directory:

```ruby
class MyJob < ApplicationJob
  queue_as :default

  def perform(*args)
    # Add your long-running task here
  end
end
```

Then, to enqueue the job to be performed as soon as the queuing system is free, you can call `perform_later`:

```ruby
MyJob.perform_later(args)
```

Finally, start the Sidekiq server by running `bundle exec sidekiq`.

### Monitoring Background Jobs with Sidekiq

Sidekiq provides a web interface for monitoring your background jobs. To access it, add the following to your `config/routes.rb` file:

```ruby
require 'sidekiq/web'
mount Sidekiq::Web => '/sidekiq'
```

Now, you can navigate to `http://localhost:3000/sidekiq` in your web browser to view the Sidekiq dashboard.

### Example: Sending Welcome Emails

Let's say you want to send a welcome email to each new user after they sign up. Here's how you can do it with Active Job and Sidekiq.

First, create a `UserMailer`:

```ruby
# app/mailers/user_mailer.rb
class UserMailer < ApplicationMailer
  def welcome_email(user)
    @user = user
    mail to: @user.email, subject: "Welcome Email!!"
  end
end
```

Then, create an Active Job to send the email:

```ruby
# app/jobs/send_emails_job.rb
class SendEmailsJob < ApplicationJob
  queue_as :default

  def perform(user)
    UserMailer.welcome_email(user).deliver
  end
end
```

Finally, enqueue the job when a user is created:

```ruby
class User < ApplicationRecord
  after_create :send_welcome_email

  private

  def send_welcome_email
    SendEmailsJob.perform_later(self)
  end
end
```

In this setup, the `SendEmailsJob` job will be enqueued as soon as a user is created. When the job is performed, it will send a welcome email to the user [Source 2](https://dev.to/ahmadraza/how-to-use-sidekiq-in-rails-7-background-jobs-1dmb), [Source 0](https://dev.to/truemark/setup-active-job-with-sidekiq-in-rails-560b).

