To use local storage to store the uploaded files, you would need to change the configuration of Active Storage.

By default, Active Storage is configured to store files in the cloud using Amazon S3. However, you can configure it to store files locally by setting the `ACTIVE_STORAGE_LOCAL_PATH` environment variable.

For example, to store files in the `/tmp/uploads` directory, you would set the following environment variable:

```
ACTIVE_STORAGE_LOCAL_PATH=/tmp/uploads
```

Once you have configured Active Storage to use local storage, you can upload files using the same API that is described above.

However, there are a few things to keep in mind when using local storage:

* Local storage is not as reliable as cloud storage. If your server crashes, you will lose all of your uploaded files.
* Local storage can be slower than cloud storage. This is because local storage is typically slower than network storage.
* Local storage can be more expensive than cloud storage. This is because you need to purchase enough storage space to store all of your uploaded files.

Overall, local storage is a good option for small applications or applications that do not require a lot of storage space. However, for larger applications or applications that require high reliability, it is best to use cloud storage.

Here is an example of how to use local storage to store uploaded files in the above context:

```ruby
class UploadMutation < GraphQL::Schema::Mutation
  field :blob_id, Integer, null: false

  argument :file, ApolloUploadServer::Upload, required: true

  def resolve(file:)
    blob_id = upload_image_or_video(file)

    {
      blob_id: blob_id
    }
  end

  private

  def upload_image_or_video(file)
    blob = ActiveStorage::Blob.create!(io: file, filename: file.original_filename)
    blob.update!(content_type: file.content_type)

    blob.id
  end
end

class PostsController < ApplicationController
  def upload_image_or_video
    mutation = UploadMutation.new(
      file: params[:file]
    )

    response = mutation.resolve
    blob_id = response[:blob_id]

    render json: { blob_id: blob_id }
  end

  def get_post_images_or_videos
    query = GetPostImagesOrVideosQuery.new(
      postId: params[:post_id]
    )

    response = query.resolve
    images_and_videos = response

    render json: { imagesAndVideos: images_and_videos }
  end
end
```

This code is the same as the previous example, except that it does not set the `ACTIVE_STORAGE_LOCAL_PATH` environment variable. This means that Active Storage will use its default configuration, which is to store files in the cloud using Amazon S3.

To use local storage, you would need to set the `ACTIVE_STORAGE_LOCAL_PATH` environment variable to the path where you want to store your uploaded files. For example, to store files in the `/tmp/uploads` directory, you would set the following environment variable:

```
ACTIVE_STORAGE_LOCAL_PATH=/tmp/uploads
```

Once you have set the environment variable, you can restart the Rails server and start uploading files. Active Storage will now store the files in the `/tmp/uploads` directory.


  ..
