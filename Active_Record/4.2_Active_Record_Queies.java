Active Record in Rails provides a powerful query interface for interacting with your database. Here are some common query operations along with code samples:

1. **Retrieving all records:**
   ```ruby
   books = Book.all
   ```

2. **Retrieving a single record by ID:**
   ```ruby
   book = Book.find(1)
   ```

3. **Retrieving records based on a condition:**
   ```ruby
   books = Book.where(out_of_print: true)
   ```

4. **Retrieving records based on a join:**
   ```ruby
   books = Book.joins(:author).where(authors: { name: 'J.K. Rowling' })
   ```

5. **Retrieving a limited number of records:**
   ```ruby
   books = Book.limit(10)
   ```

6. **Retrieving records in a specific order:**
   ```ruby
   books = Book.order(year_published: :desc)
   ```

7. **Retrieving a subset of attributes:**
   ```ruby
   books = Book.select(:title, :author_id)
   ```

8. **Basic query:**
   ```ruby
   users = User.all
   ```

9. **Conditions:**
   ```ruby
   users = User.where(age: 18)
   ```

10. **Chaining conditions:**
    ```ruby
    users = User.where(age: 18).where(active: true)
    ```

11. **Ordering results:**
    ```ruby
    users = User.order(name: :asc)
    ```

12. **Limit and offset:**
    ```ruby
    users = User.limit(10).offset(5)
    ```

13. **Joins:**
    ```ruby
    users = User.joins(:posts)
    ```

14. **Eager loading associations:**
    ```ruby
    users = User.includes(:posts)
    ```

15. **Aggregations:**
    ```ruby
    average_age = User.average(:age)
    ```

16. **Grouping and counting:**
    ```ruby
    users_by_age = User.group(:age).count
    ```

17. **Custom SQL queries:**
    ```ruby
    users = User.find_by_sql('SELECT * FROM users WHERE age > 18')
    ```

These examples show some of the many query methods available in Active Record. The query interface allows you to build complex queries in a simple and expressive way, making it easier to work with your database.

Active Record provides an abstraction layer over SQL, enabling you to use Ruby code to interact with your database without writing raw SQL queries. It's a powerful and flexible tool for working with databases in Rails applications.



  ....
