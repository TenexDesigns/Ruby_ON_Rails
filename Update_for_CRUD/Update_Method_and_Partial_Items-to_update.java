Your modified controller code allows you to update the `Book` partially, either just the `title`, just the `author`, or both. Here's your updated controller with explanations:

```ruby
module V1
  class BooksController < ApplicationController
    before_action :find_book, only: [:show, :update, :destroy]

    # ...

    def show
      render json: @book
    end

    def update
      if @book.update(book_params_update)
        render json: @book
      else
        render json: @book.errors, status: :unprocessable_entity
      end
    end

    # ...

    private
    def book_params
      params.require(:book).permit(:title, :author)
    end

    def book_params_update
      permitted_params = {}
      permitted_params[:title] = params[:book][:title] if params.dig(:book, :title)
      permitted_params[:author] = params[:book][:author] if params.dig(:book, :author)
      permitted_params
    end

    def find_book
      @book = Book.find(params[:id])
    rescue ActiveRecord::RecordNotFound => e
      render json: { error: "Book not found: #{e.message}" }, status: :not_found
    end
  end
end
```

Explanations:

1. `before_action` is used to specify methods that are run before certain controller actions. In your code, `:find_book` is a before action called before the `show`, `update`, and `destroy` actions. It finds the book by its ID and sets it as an instance variable `@book`.

2. The `update` action uses `book_params_update` to permit and update only the attributes that are present in the request. If `params.dig(:book, :title)` or `params.dig(:book, :author)` is present, it updates the respective attribute. This allows for partial updates.

With this code, you can update the book with just the `title`, just the `author`, or both, depending on what is present in the request parameters. If neither `:title` nor `:author` is present, it doesn't make any changes to those attributes. This code enables partial updates while preserving the ability to update both attributes when needed.



  ....
