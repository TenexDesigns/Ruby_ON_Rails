In Ruby on Rails, you have the option to use both array methods and query methods when working with
ActiveRecord models and retrieving data from the database.
Heres an explanation of the differences between these two approaches:





Query Methods:
Query methods are provided by ActiveRecord and allow you to construct queries in a declarative and chainable manner.
These methods generate SQL queries that are executed on the database server,
which means the data is filtered and retrieved directly from the database, 
rather than loading the entire dataset into memory.
Examples of query methods include where, order, limit, joins, group, etc.


users = User.where(age: 18).order(name: :asc).limit(10)
In the above example, the query methods where, order, and limit are used to construct a query that filters users with an age of 18,
orders them by name in ascending order, and limits the result to 10 records.

Pros of Query Methods:

Efficiently retrieves only the necessary data from the database.
Takes advantage of the database's indexing and optimization capabilities.
Supports complex query composition using chainable methods.


Cons of Query Methods:

Limited to the functionality provided by ActiveRecord's query interface.
Less flexible for in-memory data processing compared to array methods.
May require knowledge of SQL syntax for complex queries.






Array Methods:
Array methods are methods provided by Rubys Array class. 
When you use array methods on ActiveRecord collections, it means that the data has already been retrieved 
from the database and is now being processed in memory.
Examples of array methods include each, map, select, reject, sort, etc.


users = User.all
users.each do |user|
  puts user.name
end

In the above example, User.all retrieves all records from the User table,
and the subsequent each method is an array method that iterates over the collection and performs an action on each user object.

Pros of Array Methods:

Familiar and intuitive for working with in-memory collections.
Allows you to use the full range of Ruby's array methods and transformations.
Provides flexibility for complex data processing and manipulation.

Cons of Array Methods:

Retrieves all data from the database, which can be inefficient for large datasets.
Limited to processing data that has already been fetched from the database.
Cannot take advantage of the database's optimization features.




In general, it is recommended to use query methods (where, order, joins, etc.) whenever 
possible to perform data retrieval and filtering directly at the database level.
This allows for more efficient and optimized queries, especially when dealing with large datasets.
However, there are scenarios where array methods may be more suitable,
such as when performing in-memory data transformations or working with a small subset of already fetched records.

Its worth noting that ActiveRecord provides a seamless integration between query methods and array methods,
allowing you to combine both approaches as needed for your specific use case.









MORE EXPLANATION
**************************************************************


Query methods in Rails are used to retrieve data from the database using ActiveRecord methods.
When a query method is called, it returns an ActiveRecord::Relation object, 
    which can be further modified with additional query methods. 
    Here are some examples of commonly used query methods in Rails:

where: This method is used to filter records based on one or more conditions.
  It can be used to search for records with specific attribute values,
  or to perform more complex searches using SQL-like syntax.
Book.where(out_of_print: true)
This retrieves all records from the books table where the out_of_print attribute is true.

joins: This method is used to join two or more tables together in a SQL query.
  It allows you to retrieve records from related tables based on a common attribute.
Book.joins(:author).where(authors: { name: 'J.K. Rowling' })
This retrieves all records from the books table that have an author with the name 'J.K. Rowling'.

select: This method is used to retrieve a subset of attributes from a table.
  It can be used to improve query performance by reducing the amount of data that needs to be retrieved from the database.
Book.select(:title, :author_id)
This retrieves all records from the books table, but only includes the title and author_id attributes.

order: This method is used to order records based on one or more attributes.
  It can be used to sort records in ascending or descending order.
Book.order(year_published: :desc)
This retrieves all records from the books table and orders them by the year_published attribute in descending order.

group: This method is used to group records based on one or more attributes. 
  It can be used to perform aggregate functions, such as counting or summing records.
Book.group(:author_id).count
This retrieves all records from the books table and groups them by the author_id attribute. 
It then counts the number of records in each group.

In summary, query methods in Rails are used to retrieve data from the database using ActiveRecord methods. 
They return an ActiveRecord::Relation object, which can be further modified with additional query methods.
  Commonly used methods include where, joins, select, order, and group.
























































































































...
