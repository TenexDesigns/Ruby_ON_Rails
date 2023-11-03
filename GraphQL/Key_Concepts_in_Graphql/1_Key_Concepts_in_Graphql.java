We will Be refrencing this in the article bbelow ---------> https://github.com/TenexDesigns/graphql_app









In GraphQL, the main components are:

1. **Schema**: The schema is the structure of the data that can be requested from the server. It defines the types of data, the relationships between them, and the operations that can be performed. In your code, the schema is defined in the `GraphqlSchema` class.

2. **Query**: A query is a request for data from the server. It's similar to a GET request in REST. In your code, queries are defined in the `QueryType` class. For example, the `users` and `user` fields are queries that return all users and a specific user, respectively.

3. **Mutation**: A mutation is a request that changes data on the server. It's similar to POST, PUT, or DELETE requests in REST. In your code, mutations are defined in the `CreateUser` class. The `resolve` method in this class is where the business logic for creating a user is handled.

4. **Resolver**: A resolver is a function that fetches the data for a specific field in the schema. In your code, the `users` and `user` methods in the `QueryType` class and the `resolve` method in the `CreateUser` class are resolvers. They fetch the data for the `users`, `user`, and `createUser` fields, respectively.

5. **Type**: A type defines the shape of a piece of data. In your code, the `UserType` class is a type that defines the shape of a user. It has fields for `id`, `name`, `email`, and `posts`.

In your code, the data to be sent is determined by the resolvers. For example, the `users` resolver returns all users, and the `user` resolver returns a specific user. The business logic is also handled in the resolvers. For example, the `resolve` method in the `CreateUser` class handles the logic for creating a user.

The resolvers you create are used in the fields of your schema. For example, the `users` and `user` resolvers are used in the `users` and `user` fields of your schema, respectively.

The mutations return a hash, which is converted to JSON by GraphQL. The keys in the hash correspond to the fields in the mutation, and the values are the data for those fields.

You register the mutations and queries you create in the `MutationType` and `QueryType` classes, respectively. For example, to register the `CreateUser` mutation, you would add a field for it in the `MutationType` class:

```ruby
module Types
  class MutationType < Types::BaseObject
    field :createUser, mutation: Mutations::CreateUser
  end
end
```

Similarly, to register the `users` and `user` queries, you would add fields for them in the `QueryType` class:

```ruby
module Types
  class QueryType < Types::BaseObject
    field :users, [Types::UserType], null: false
    field :user, Types::UserType, null: false do
      argument :id, ID, required: true
    end
  end
end
```

The resolvers for these fields are the methods with the same name in the `QueryType` class.
