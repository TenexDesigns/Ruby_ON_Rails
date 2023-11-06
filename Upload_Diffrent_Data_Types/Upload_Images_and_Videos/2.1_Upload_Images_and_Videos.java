In Rails, you can use Active Storage to handle file uploads. Active Storage facilitates uploading files to a cloud storage service like Amazon S3, Google Cloud Storage, or Microsoft Azure Storage Service. It also provides the ability to generate image representations of non-image files, and direct file uploads from the browser to the cloud storage, bypassing the application servers [Source 1](https://guides.rubyonrails.org/active_storage_overview.html).

First, you need to set up Active Storage in your Rails application. You can do this by running the following command in your terminal:

```bash
rails active_storage:install
```
This will generate a migration to add the necessary tables to your database. Run the migration with:

```bash
rails db:migrate
```
Next, you need to declare that your model (in this case, the `User` and `Post` models) has attached files. For instance, if a user can upload multiple images and videos, you would modify your `User` and `Post` models like so:

```ruby
class User < ApplicationRecord
  has_many_attached :images
  has_many_attached :videos
end

class Post < ApplicationRecord
  has_one_attached :image
  has_one_attached :video
end
```
In your form for creating or editing a user or post, you can add a file field for the images and videos:

```ruby
<%= form.file_field :images, multiple: true %>
<%= form.file_field :videos, multiple: true %>
```
And in your controller, permit the images and videos attributes:

```ruby
params.require(:user).permit(:name, images: [], videos: [])
params.require(:post).permit(:title, :content, :image, :video)
```
Once files are attached, you can retrieve them like this:

```ruby
@user.images.each do |image|
  <%= image_tag image %>
end

@user.videos.each do |video|
  <%= video_tag video %>
end
```
To remove an attachment, you can call `purge` or `purge_later` (for background removal) on the attachment:

```ruby
user.images[0].purge
user.videos[0].purge_later
```
By default, Active Storage uses local disk storage in development and test environments. In production, you'll likely want to use a cloud storage service. To do so, you need to configure your storage service in the `config/storage.yml` file and reference it in the `config/environments/production.rb` file:

```ruby
config.active_storage.service = :amazon
```
For Amazon S3, you would configure it in `storage.yml` like so:

```yaml
amazon:
  service: S3
  access_key_id: <%= Rails.application.credentials.dig(:aws, :access_key_id) %>
  secret_access_key: <%= Rails.application.credentials.dig(:aws, :secret_access_key) %>
  region: us-east-1
  bucket: your_own_bucket
```
Remember to replace `:access_key_id`, `:secret_access_key`, `region`, and `bucket` with your actual Amazon S3 credentials and information.

You can also track the progress of file uploads with the `DirectUpload` object provided by Active Storage. Here's an example of how you can use it:

```javascript
import { DirectUpload } from "@rails/activestorage"

class Uploader {
  constructor(file, url) {
    this.upload = new DirectUpload(this.file, this.url, this)
  }

  upload(file) {
    this.upload.create((error, blob) => {
      if (error) {
        // Handle the error
      } else {
        // Add an appropriately-named hidden input to the form with a value of blob.signed_id
      }
    })
  }

  directUploadWillStoreFileWithXHR(request) {
    request.upload.addEventListener("progress", event => this.directUploadDidProgress(event))
  }

  directUploadDidProgress(event) {
    // Use event.loaded and event.total to update the progress bar
  }
}
```
This script creates a new `DirectUpload` object and starts the upload. The `directUploadWillStoreFileWithXHR` method is called during the upload process, allowing you to attach your own progress handler to the XHR request [Source 1](https://guides.rubyonrails.org/active_storage_overview.html).

For displaying uploaded images and videos, Active Storage provides the `url_for` helper. You can use it in your views like this:

```ruby
<%= image_tag url_for(@user.images.first) if @user.images.attached? %>
<%= video_tag url_for(@user.videos.first) if @user.videos.attached? %>
```
This will generate an image or video tag for the first attached image or video of the user, if any exist [Source 26](https
                                                                                                                     
                                                                                                                     ..
