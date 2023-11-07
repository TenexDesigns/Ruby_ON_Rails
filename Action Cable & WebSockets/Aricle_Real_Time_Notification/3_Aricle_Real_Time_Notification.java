/Aricle_Real_Time_Notification
o implement real-time notifications when a new article is published using Action Cable, we can use the following steps:

Server side:

Create an Action Cable channel for new articles.
Add a method to the channel that will broadcast a message when a new article is published.
In the article model, add a callback that will call the broadcast method when an article is published.
Client side:

Subscribe to the Action Cable channel.
When a message is received, display a notification.
Here is a code sample of the server-side code:

Ruby
class ArticlesChannel < ApplicationCable::Channel
  def subscribed
    stream_from "articles:new"
  end

  def broadcast_new_article
    ActionCable.server.broadcast(
      "articles:new",
      new_article: ArticlesController.render(
        partial: 'articles/new_article_notification',
        locals: { article: self }
      )
    )
  end
end
Use code with caution. Learn more
This code creates an Action Cable channel for new articles. The subscribed method subscribes the client to the articles:new stream. The broadcast_new_article method broadcasts a message to the articles:new stream whenever a new article is published.

Here is a code sample of the client-side code in JavaScript:

JavaScript
import consumer from "./consumer"

consumer.subscriptions.create("ArticlesChannel", {
  received(data) {
    if (data.new_article) {
      this.displayNewArticleNotification(data.new_article);
    }
  },
  displayNewArticleNotification(newArticle) {
    const body = document.querySelector('body');
    body.insertAdjacentHTML('beforeend', newArticle);
    const newArticleNotification = document.querySelector('#new-article-notification');
    setTimeout(() => {
      body.removeChild(newArticleNotification);
    }, 3000);
  }
})
Use code with caution. Learn more
This code subscribes to the ArticlesChannel and displays a notification whenever a new article is published. The notification will include the title of the new article and a link to view it. The notification will also disappear after 3 seconds.

Frontend or client subscribes to the Action Cable channel:

The client subscribes to the Action Cable channel. This can be done in any JavaScript framework, such as React, Vue.js, or Angular.

Flutter:

To implement real-time notifications in Flutter, we can use the following steps:

Add the action_cable package to your Flutter project.
Create a class that extends ActionCableConnection.
In the subscribe method, subscribe to the articles:new stream.
When a message is received, display a notification.
Here is a code sample of the Flutter code:

Dart
import 'package:action_cable/action_cable.dart';

class ArticlesChannel extends ActionCableConnection {
  @override
  void subscribe() {
    super.subscribe("articles:new");
  }

  @override
  void onMessage(Map<String, dynamic> data) {
    // Display a notification with the title of the new article and a link to view it.
  }
}
Use code with caution. Learn more
This code creates a Flutter class that extends ActionCableConnection. The subscribe method subscribes to the articles:new stream. The onMessage method is called when a message is received from the server.

To use the ArticlesChannel class, we can create a new instance of it and call the subscribe method. We can then listen for messages from the server by adding a listener to the onMessage event.

For example, we could add the following code to our main.dart file:

Dart
import 'package:flutter/material.dart';
import 'articles_channel.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final articlesChannel = ArticlesChannel();
    articlesChannel.subscribe();

    articlesChannel.onMessage((data) {
      // Display a notification with the title of the new article and a link to view it.
    });

    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Real-time Notifications'),
        ),
      ),
    );
  }
}
Use code with caution. Learn more
This code will create a new ArticlesChannel object and call the subscribe method. It will then listen for messages from the server by adding a listener to the `on
