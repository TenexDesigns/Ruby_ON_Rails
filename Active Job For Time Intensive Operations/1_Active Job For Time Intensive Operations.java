Active Job is a framework for declaring jobs in Ruby on Rails applications. It allows you to run any kind of code in the background, outside of the request-response cycle. This is particularly useful for time-intensive operations, such as sending emails, calling APIs, data processing, etc., which would otherwise slow down the response time of your application [Source 0](https://guides.rubyonrails.org/active_job_basics.html).

To use Active Job, you need to create a job. Each job is a class that extends `ApplicationJob` and implements a `perform` method. For example, if you have a time-intensive operation called `time_intensive_operation`, you can create a job like this:

```ruby
class TimeIntensiveJob < ApplicationJob
  queue_as :default

  def perform(*args)
    # Place your time-intensive operation here
    time_intensive_operation(args)
  end
end
```

To enqueue a job to be performed as soon as the queuing system is free, you can call `perform_later`:

```ruby
TimeIntensiveJob.perform_later(args)
```

Active Job provides hooks during the lifecycle of a job that allow you to trigger logic during the lifecycle of a job [Source 4](https://guides.rubyonrails.org/v4.2/active_job_basics.html):

```ruby
class TimeIntensiveJob < ApplicationJob
  queue_as :default

  before_enqueue do |job|
    # do something when job is enqueued
  end

  around_perform do |job, block|
    # do something before perform
    block.call
    # do something after perform
  end

  def perform(*args)
    # Place your time-intensive operation here
    time_intensive_operation(args)
  end
end
```

Active Job is backend-agnostic and comes with built-in adapters for multiple queuing backends (Sidekiq, Resque, Delayed Job, and others) [Source 0](https://guides.rubyonrails.org/active_job_basics.html). To set up a queuing backend, you need to decide on a 3rd-party queuing library that Rails should use. Most production apps will need to pick a persistent backend. For enqueuing and executing jobs in production, most queuing libraries require that you start a library-specific queuing service in addition to starting your Rails app [Source 0](https://guides.rubyonrails.org/active_job_basics.html).

Exceptions raised during the execution of the job can be handled with `rescue_from` [Source 0](https://guides.rubyonrails.org/active_job_basics.html):

```ruby
class TimeIntensiveJob < ApplicationJob
  queue_as :default

  rescue_from(StandardError) do |exception|
    # Do something with the exception
  end

  def perform(*args)
    # Place your time-intensive operation here
    time_intensive_operation(args)
  end
end
```

If an exception from a job is not rescued, then the job is referred to as "failed". If a passed record is deleted after the job is enqueued but before the `perform` method is called, Active Job will raise an `ActiveJob::DeserializationError` exception [Source 0](https://guides.rubyonrails.org/active_job_basics.html).
