In Ruby on Rails, the business logic can be placed in a variety of locations based on how you want to structure your application. Here are some common practices:

1. **Models**: The "Fat Model, Skinny Controller" approach suggests that business logic should be placed in the model. This keeps your controllers slim and focused on handling requests, while the models handle the business logic and interact with the database. However, this can lead to bloated models if not managed carefully [Source 1](https://blog.appsignal.com/2023/05/10/organize-business-logic-in-your-ruby-on-rails-application.html).

2. **Service Objects**: Service Objects are plain old Ruby objects (POROs) that encapsulate specific business logic operations. This approach is useful when the operation is complex and spans multiple models, or when the logic doesn't fit neatly into a single model. A service object should have a single responsibility and should be named after the action it performs [Source 3](https://sergiovp.dev/blog/how-to-manage-business-logic-in-rails/).

3. **Lib Directory**: Another place to put business logic that spans multiple models is the `lib` directory. This directory is intended for modules that are used across your application and don't fit into the MVC structure [Source 2](https://stackoverflow.com/questions/6969271/where-to-put-business-logic-that-spans-multiple-models).

4. **Concerns**: Rails provides a concerns directory in both models and controllers where you can place reusable pieces of code. Concerns are modules that allow you to use a 'mix-in' style of reusing methods across multiple models or controllers.

5. **Decorators/ Presenters**: If the business logic is related to the presentation of data, it might be best to use a decorator or a presenter. Libraries like Draper can be used to implement this pattern.

In Rails, there isn't a strict rule like in Spring Boot where you have a separate repository layer. In Rails, the model handles both the business logic and the data access, but you can abstract complex queries into Query Objects or use scopes for reusable simple queries.

Remember, the key is to keep your code organized, maintainable, and understandable. The best practice would depend on the specific requirements of your application [Source 6](https://www.aha.io/engineering/articles/organizing-code-in-a-rails-monolith).




.....
