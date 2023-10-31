Sure, let's go through the key areas you mentioned with code examples and explanations:

1. **Setup**: To set up Active Storage, you need to run specific Rails commands to install Active Storage and migrate the database.

```ruby
# To install Active Storage
$ bin/rails active_storage:install

# To migrate the database
$ bin/rails db:migrate
```

2. **Different Storage Services**: You can configure different storage services in the `config/storage.yml` file. Here's an example of how to configure the Disk service and Amazon S3:

```yaml
# Use Disk service for development and test environments
development:
  service: Disk
  root: <%= Rails.root.join("storage") %>

test:
  service: Disk
  root: <%= Rails.root.join("tmp/storage") %>

# Use Amazon S3 for production environment
production:
  service: S3
  access_key_id: <%= Rails.application.credentials.dig(:aws, :access_key_id) %>
  secret_access_key: <%= Rails.application.credentials.dig(:aws, :secret_access_key) %>
  region: <%= Rails.application.credentials.dig(:aws, :region) %>
  bucket: <%= Rails.application.credentials.dig(:aws, :bucket) %>
```

3. **Attaching Files to Records**: You can attach one or many files to a record using `has_one_attached` and `has_many_attached` methods. 

```ruby
# To attach one file to a record
class User < ApplicationRecord
  has_one_attached :avatar
end

# To attach many files to a record
class Message < ApplicationRecord
  has_many_attached :images
end
```

4. **Form Validation**: You can validate attachments in forms using Active Record validations. For example, you can validate that the attached file is an image and its size is less than 2MB:

```ruby
class User < ApplicationRecord
  has_one_attached :avatar

  validate :avatar_validation

  def avatar_validation
    if avatar.attached?
      if avatar.blob.byte_size > 2.megabytes
        avatar.purge
        errors.add(:avatar, 'is too big (max is 2 MB)')
      elsif !avatar.blob.content_type.starts_with?('image/')
        avatar.purge
        errors.add(:avatar, 'needs to be an image')
      end
    end
  end
end
```

5. **Transforming Images**: You can use variants to transform images. Here's how to create a variant of an attached image that is resized to fit within a 100x100 square:

```ruby
<%= image_tag user.avatar.variant(resize: "100x100") %>
```

6. **Previewing Files**: You can generate an image representation of a non-image file, such as a PDF or a video, using the `preview` method:

```ruby
<%= image_tag user.document.preview(resize: "500x500") if user.document.previewable? %>
```

7. **Direct Uploads**: Active Storage supports direct uploads from the client to the cloud storage service, bypassing your application servers. Here's how to set up a direct upload for a form:

```ruby
<%= form_with(model: user, local: true) do |form| %>
  <%= form.file_field :avatar, direct_upload: true %>
<% end %>
```

8. **Testing**: To clean up files stored during testing, you can add the following code to your test configuration:

```ruby
# config/environments/test.rb
Rails.application.configure do
  config.active_storage.service = :test

  config.after_initialize do
    ActiveStorage::Blob.service.clean
  end
end
```

These code samples show how you can use Active Storage in various parts of a Rails application, including models (for defining attachments), controllers (for handling file uploads and downloads), and views (for creating forms for file uploads) [Source 2](https://guides.rubyonrails.org/v5.2.0/active_storage_overview.html).






....
