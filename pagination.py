To add pagination in Rails using the Kaminari gem, you can follow these steps:

Add the Kaminari gem to your Gemfile and run bundle install:
gem 'kaminari'
In the controller action where you want to paginate, add the following code:
# app/controllers/topics_controller.rb
def index
  @topics = Topic.page(params[:page]).per(15)
end
Here were using the page method and passing in the parameters from the page as an argument.
Were also using the optional per method, this allows us to dictate how many records will be shown on each page.

In the view, add the following code to display the pagination links:
<!-- app/views/topics/index.html.erb -->
<div class="container">
  <div class="row">
    <%= render @topics %>
  </div>
  <%= paginate @topics %>
</div>
Now lets startup the rails server and navigate to the topics/ path.
It looks like the pagination is working, if you click from page to page you'll see that it's working.

Here are some key points to keep in mind when adding pagination in Rails using the Kaminari gem:

The Kaminari gem provides a simple and flexible way to paginate records in your Rails application [2] [8] [11].
To use the Kaminari gem, add it to your Gemfile and update your controller to paginate the desired model [1] [5] [11].
In the view, add the paginate helper to display the pagination links for the paginated collection [1] [5] [11].
You can customize the number of items per page and the appearance of the pagination links by passing options to the page,
per, and paginate methods, respectively [1] [5] [11].
In summary, adding pagination in Rails using the Kaminari gem is a simple process. 
Once you set up the gem and update your controller and view,
you can customize the appearance and behavior of the pagination links to suit your needs.




MORE EXPLANTION
***************************************************************************************************



Pagination in Rails allows you to divide a large set of data into smaller,
more manageable chunks called pages. This helps improve the performance and user experience 
by displaying a limited number of records per page.

To add pagination in Rails, you can follow these steps:

Add the kaminari gem to your Gemfile:

gem 'kaminari'
Run bundle install to install the gem and its dependencies.

Configure the pagination settings in an initializer file.
Create a new file, config/initializers/kaminari_config.rb, and add the following code:

Kaminari.configure do |config|
  config.default_per_page = 10
  config.max_per_page = 100
end
Adjust the default_per_page and max_per_page values as per your requirements.

In your controller, specify the pagination for the desired action.
For example, lets assume you have a PostsController and you want to paginate the index action:


class PostsController < ApplicationController
  def index
    @posts = Post.page(params[:page]).per(10)
  end
end

In this example, we use the page method provided by Kaminari to specify the current page,
and the per method to set the number of records per page.

In your view, display the paginated records using the paginate helper method. For example:

erb
Copy code
<% @posts.each do |post| %>
  <!-- Display post details -->
<% end %>

<%= paginate @posts %>
The paginate method generates pagination links based on the current page and the total number of records.
It automatically handles navigation between pages.

Thats it! With these steps, you have added pagination to your Rails application using the Kaminari gem.
You can customize the pagination style and appearance by modifying the generated pagination links.

Note that there are other pagination gems available for Rails, such as will_paginate.
The process of adding pagination may vary slightly depending on the gem you choose
but the general concepts and steps remain similar.
















































































































...
