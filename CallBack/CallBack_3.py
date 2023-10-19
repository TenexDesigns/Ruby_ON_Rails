
In the provided code snippet:

class Post < ApplicationRecord
  has_many :comments, dependent: :destroy

  after_destroy :log_deleted_comments

  private

  def log_deleted_comments
    logger.info "Deleted #{comments.count} comments."
  end
end
has_many :comments, dependent: :destroy: This line establishes an association between the Post model and the Comment model 
        using a "has_many" relationship.
        It means that a post can have multiple comments associated with it.

The dependent: :destroy option specified in this line ensures that when a Post is destroyed, all associated Comment
    records are also destroyed. This is known as a cascading delete. In other words, when a Post is deleted, 
    all associated Comment records will be deleted as well to maintain data integrity.

after_destroy :log_deleted_comments: This line registers the log_deleted_comments method as an after_destroy callback.
    It means that after a Post object is destroyed, the log_deleted_comments method will be automatically triggered.

Inside the log_deleted_comments method, a log message is created using the logger object. It counts the number of 
associated comments for the post using comments.count and logs that information.

Overall, this code defines a Post model that has a one-to-many association with the Comment model. When a Post is destroyed, 
all associated Comment records are also destroyed. Additionally, after the destruction of a Post, the log_deleted_comments
method is triggered, which logs the number of comments that were deleted.

The dependent: :destroy option in the has_many association is a powerful feature in Rails that helps maintain data 
    integrity by automatically handling the deletion of associated records. It ensures that when a parent
    record is deleted, any associated child records are also removed, preventing orphaned or inconsistent data in the database.
