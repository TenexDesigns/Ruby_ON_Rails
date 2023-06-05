Active Storage is a built-in library in Rails that provides a convenient way to handle file uploads and attachments. It simplifies the process of managing and serving files in your Rails application.

To use Active Storage in Rails, you can follow these steps:

Configure Active Storage: Run the following command to set up the necessary migrations and initializer:

bash
Copy code
rails active_storage:install
This command will create the necessary migrations for the active storage tables and generate an initializer file.

Run the database migrations to create the Active Storage tables:

bash
Copy code
rails db:migrate
Add the necessary associations to your models. For example, if you have a User model and you want to associate it with avatars:

ruby
Copy code
class User < ApplicationRecord
  has_one_attached :avatar
end
This code adds a single attachment called avatar to the User model.

Update your forms to include file upload fields. For example, if you have a form to create or edit a user:

erb
Copy code
<%= form_with(model: @user, local: true) do |form| %>
  <!-- Other form fields -->
  <%= form.file_field :avatar %>
  <%= form.submit %>
<% end %>
The file_field helper generates a file upload field for the avatar attachment.

Handle file uploads in your controller. For example, in your UsersController:

ruby
Copy code
class UsersController < ApplicationController
  def create
    @user = User.new(user_params)
    if @user.save
      redirect_to @user, notice: 'User was successfully created.'
    else
      render :new
    end
  end

  # Other controller actions

  private

  def user_params
    params.require(:user).permit(:name, :email, :avatar)
  end
end
Ensure that :avatar is permitted in the strong parameters.

Display uploaded files in your views. For example, to display the user's avatar:

erb
Copy code
<%= image_tag @user.avatar if @user.avatar.attached? %>
The attached? method checks if the avatar is attached to the user.

Active Storage takes care of storing the uploaded files in a storage service, such as the local file system or cloud storage providers like Amazon S3. You can configure the storage service in the config/storage.yml file.

By default, Active Storage uses the local disk service for development and testing environments. In production, you'll need to configure and set up the appropriate storage service.

With Active Storage, you can easily handle file uploads, associate attachments with your models, and perform common operations like resizing, cropping, and serving files in your Rails application.
