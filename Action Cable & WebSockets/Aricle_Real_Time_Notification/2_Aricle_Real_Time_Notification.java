To implement real-time notifications when a new article is published, you can use Action Cable in Ruby on Rails and WebSocket in Flutter. Here's a step-by-step guide on how to implement it in both languages:

**Ruby on Rails (with Action Cable):**

1. Set up Action Cable: Action Cable is integrated into Ruby on Rails and provides a framework for real-time communication. Make sure you have Rails 5 or above installed.

2. Generate a channel: Use the following command to generate a new channel for the notifications:
   ```
   $ rails generate channel ArticleNotification
   ```

3. Implement the channel: Open the generated `app/channels/article_notification_channel.rb` file and define the logic for subscribing, unsubscribing, and broadcasting the notifications. Here's an example implementation:
   ```ruby
   class ArticleNotificationChannel < ApplicationCable::Channel
     def subscribed
       stream_from "article_notifications"
     end

     def unsubscribed
       # Any cleanup needed when channel is unsubscribed
     end
   end
   ```

4. Broadcast notifications: In your article creation or publication logic, whenever a new article is published, you can broadcast the notification to all subscribers. Use the `ActionCable.server.broadcast` method to send the notification data. Here's an example:
   ```ruby
   ActionCable.server.broadcast(
     "article_notifications",
     { title: article.title, message: "A new article has been published!" }
   )
   ```

5. Handle notifications on the client-side: On the frontend, you can use JavaScript to subscribe to the channel and handle the received notifications. Here's an example using JavaScript:
   ```javascript
   // app/javascript/channels/article_notification.js
   import consumer from "./consumer"
   consumer.subscriptions.create("ArticleNotificationChannel", {
     received(data) {
       // Handle the received notification data
       console.log(data)
     }
   })
   ```

**Flutter (with WebSocket):**

1. Add dependencies: In your Flutter project's `pubspec.yaml` file, add the `web_socket_channel` dependency:
   ```yaml
   dependencies:
     web_socket_channel: ^2.1.0
   ```

2. Create a WebSocket connection: Use the `WebSocketChannel.connect` method to establish a connection to the WebSocket server. Here's an example:
   ```dart
   import 'package:web_socket_channel/web_socket_channel.dart';

   final channel = WebSocketChannel.connect(
     Uri.parse('wss://your-websocket-server.com'),
   );
   ```

3. Listen for new notifications: Use the `channel.stream` to listen for incoming messages from the WebSocket server. Here's an example:
   ```dart
   channel.stream.listen((message) {
     // Handle the received notification message
     print(message);
   });
   ```

4. Publish notifications from the server: On the server-side (Ruby on Rails), whenever a new article is published, you can send a message to the connected clients through the WebSocket connection. Here's an example in Ruby on Rails:
   ```ruby
   # Publish a notification to all connected clients
   ActionCable.server.broadcast(
     "article_notifications",
     { title: article.title, message: "A new article has been published!" }
   )
   ```

Remember to adapt the code to your specific use case and set up the WebSocket server accordingly.

These steps should help you implement real-time notifications when a new article is published using Action Cable in Ruby on Rails and WebSocket in Flutter.
