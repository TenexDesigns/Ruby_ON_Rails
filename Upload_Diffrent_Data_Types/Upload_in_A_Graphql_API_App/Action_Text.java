Action Text is a Rails feature that makes it easy to add rich text editing to your applications. It includes a WYSIWYG editor (What You See Is What You Get) that allows users to format their text without having to write any HTML.

Action Text can be used in both Rails API and Rails traditional applications. It is often used in blog applications, where users need to be able to format their text without having to write any HTML.

However, Action Text can be used in any Rails application where you need to allow users to format their text. For example, you could use it in a Rails API to allow users to create and edit rich text blog posts.

In your case, you are creating a Rails GraphQL API. Action Text can be used in Rails GraphQL APIs, but it is not required. You can also use other WYSIWYG editors in your Rails GraphQL APIs.

If you decide to use Action Text in your Rails GraphQL API, you will need to install the Action Text gem and configure it in your Rails application. You will also need to create a GraphQL mutation that allows users to upload and edit rich text content.

Here is an example of a GraphQL mutation that allows users to upload and edit rich text content using Action Text:

```graphql
mutation UploadRichTextContent($content: String!) {
  uploadRichTextContent(content: $content) {
    id
    content
  }
}
```

This mutation takes a `content` argument, which is a string containing the rich text content that the user wants to upload or edit. The mutation returns an object with the ID and content of the uploaded or edited rich text content.

You can use this mutation in your Rails GraphQL API to allow users to create and edit rich text content. For example, you could use it to allow users to create and edit blog posts, articles, or other types of content.

Whether or not you decide to use Action Text in your Rails GraphQL API is up to you. It is a good option if you want to make it easy for users to format their text without having to write any HTML. However, there are other WYSIWYG editors that you can use in your Rails GraphQL APIs as well.
