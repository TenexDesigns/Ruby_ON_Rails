**Resolvers** are responsible for returning the data for a given GraphQL query or mutation. They are defined as Ruby functions that take the query or mutation arguments as input and return the data as output.

**Mutations** are used to modify data in your GraphQL API. They are defined as GraphQL fields that can be invoked by clients to perform actions such as creating, updating, or deleting data.

**Schema** defines the structure of your GraphQL API. It specifies the types of data that can be queried or mutated, as well as the relationships between those types.

**Queries** are used to retrieve data from your GraphQL API. They are defined as GraphQL fields that can be invoked by clients to fetch data.

**In your code:**

* The `QueryType` and `MutationType` classes define the schema for your GraphQL API.
* The `users` and `user` fields on the `QueryType` class are queries that can be used to retrieve data about users.
* The `createUser` mutation on the `MutationType` class can be used to create new users.
* The `UserType` class defines the structure of the `User` type.

**How the data to be sent is determined:**

The data to be sent is determined by the query or mutation that is invoked by the client. The resolver for the query or mutation will then return the data that is requested.

**Where the business logic is handled:**

The business logic is handled in the resolvers. Resolvers are responsible for performing the necessary operations to get or modify the data that is requested by the client.

**Where the resolvers are used:**

Resolvers are used in both queries and mutations. The resolver for a query or mutation will be invoked when the client sends a request for that query or mutation.

**Do the mutations return JSON:**

Yes, mutations return JSON. The JSON data will contain the data that was modified by the mutation, as well as any errors that occurred.

**How to register the mutations and queries:**

To register the mutations and queries, you need to add them to the `MutationType` and `QueryType` classes, respectively. You can do this by adding them to the `fields` hash on each class.

**Where to put the resolvers:**

Resolvers can be put anywhere in your Rails application. However, it is common to put them in a separate directory, such as `app/graphql/resolvers`.

**Putting your code together:**

To put your code together, you would first need to create a GraphQL schema file. This file would define the `QueryType` and `MutationType` classes.

Next, you would need to create resolver files for each of the queries and mutations in your schema. These files would contain the Ruby functions that implement the business logic for each query and mutation.

Finally, you would need to register the queries and mutations with your GraphQL API. You can do this by adding them to the `GraphqlSchema.mutation` and `GraphqlSchema.query` methods, respectively.

Here is an example of how you would register the `CreateUser` mutation:

```ruby
GraphqlSchema.mutation(Types::MutationType)
```

Once you have registered the queries and mutations, you will be able to use them in your GraphQL API.

I hope this helps!
