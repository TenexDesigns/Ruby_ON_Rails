In Rails, Active Storage is a built-in library that facilitates file uploads to a variety of storage services, including local disk, Amazon S3, Google Cloud Storage, and Microsoft Azure Storage. With Active Storage, you can attach files to your models, generate representations for non-image files, and even upload files directly from the client to the cloud [Source 1](https://edgeguides.rubyonrails.org/active_storage_overview.html).

However, when working with a Rails API that uses GraphQL, you might encounter some challenges with file uploads. One solution is to use the `apollo-upload-server` gem, which provides a GraphQL type named `Upload` for handling file uploads [Source 2](https://dev.to/sulmanweb/file-uploading-in-graphql-api-in-rails-with-activestorage-4c54).

First, you need to include the `apollo-upload-server` gem in your Gemfile:

```ruby
gem 'apollo-upload-server'
```

Then, run `bundle install` to install the gem.

Next, you need to define the `Upload` type in your GraphQL schema:

```ruby
types do
  scalar :upload
end
```

You can then use the `Upload` type in your mutations to handle file uploads. Here's an example of a mutation that allows a user to upload an image:

```ruby
Types::MutationType = GraphQL::ObjectType.define do
  name 'Mutation'

  field :createImage, Types::ImageType do
    argument :file, ApolloUploadServer::Upload, required: true

    resolve ->(obj, args, ctx) {
      image = Image.new(file: args[:file])
      image.user = ctx[:current_user]
      image.save!

      image
    }
  end
end
```

In this example, the `createImage` mutation takes a `file` argument of the `Upload` type and assigns it to a new `Image` object. The `Image` object is then saved to the database. The `ctx[:current_user]` is assumed to be the currently authenticated user.

To retrieve the image, you can add a field to the `UserType`:

```ruby
Types::UserType = GraphQL::ObjectType.define do
  name 'User'

  field :images, types[Types::ImageType] do
    resolve ->(obj, args, ctx) {
      obj.images
    }
  end
end
```

This `images` field will return all the images that belong to the user.

To display the images, you can use the `url_for` method provided by Active Storage:

```ruby
Types::ImageType = GraphQL::ObjectType.define do
  name 'Image'

  field :url, types.String do
    resolve ->(obj, args, ctx) {
      Rails.application.routes.url_helpers.url_for(obj.file)
    }
  end
end
```

This `url` field will return the URL of the image, which can be used to display the image in the client application.

To remove an image, you can define a mutation like this:

```ruby
Types::MutationType = GraphQL::ObjectType.define do
  name 'Mutation'

  field :deleteImage, types.Boolean do
    argument :id, types.ID, required: true

    resolve ->(obj, args, ctx) {
      image = Image.find(args[:id])
      if image.user == ctx[:current_user]
        image.file.purge
        true
      else
        false
      end
    }
  end
end
```

This `deleteImage` mutation takes an `id` argument and finds the corresponding `Image` object. If the `Image` object belongs to the currently authenticated user, the file is purged and the mutation returns `true`. Otherwise, it returns `false`.

To handle file uploads directly from the client to the cloud, you can use the Direct Uploads feature of Active Storage. This feature provides a JavaScript library that supports uploading files directly from the client to the cloud, bypassing your application servers [Source 4](https://evilmartians.com/chronicles/active-storage-meets-graphql-direct-uploads).

Finally, you can store your uploaded files in a variety of storage services, including local disk, Amazon S3, Google Cloud Storage, and Microsoft Azure Storage. You can configure your preferred storage service in the `config/storage.yml` file and reference it in the `config/environments/production.rb` file [Source 1](https://edgeguides.rubyonrails.org/active_storage_overview.html).



      ...
