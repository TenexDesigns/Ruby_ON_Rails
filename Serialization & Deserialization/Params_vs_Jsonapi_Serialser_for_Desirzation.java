You don't necessarily need to use the jsonapi-serializer gem for deserialization and storing data in the database. The params hash provides direct access to the JSON data sent in the request, so you can extract the data from the params hash and use it to create or update records in the database.

However, if you want to enforce a specific JSON:API format for the incoming data and perform additional validation or transformation, you can use the jsonapi-serializer gem or other similar gems that provide JSON:API serialization and deserialization capabilities. These gems can help you handle complex relationships, metadata, and other aspects of the JSON:API specification.










  Using params vs. jsonapi-serializer for deserialization

Whether you should use params or jsonapi-serializer for deserialization depends on your specific needs. If you are only deserializing a few attributes, then using params is probably the simplest option. However, if you are deserializing a complex object with many attributes and relationships, then using jsonapi-serializer can make the process easier and less error-prone.

Recommendation

I recommend using jsonapi-serializer for deserialization if you are deserializing a complex object with many attributes and relationships. This will make the process easier and less error-prone.

Here is an example of how to use jsonapi-serializer to deserialize a book object:

Ruby
json_data = JSON.parse(request.body.read)
book = BookSerializer.new(json_data).deserialize

# Save the book to the database
book.save
Use code with caution. Learn more
This will deserialize the JSON data into a Book object and then save the book to the database.









  
