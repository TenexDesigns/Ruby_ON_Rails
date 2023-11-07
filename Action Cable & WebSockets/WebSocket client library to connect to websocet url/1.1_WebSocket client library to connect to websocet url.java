In Next.js or React, you can use various WebSocket client libraries to connect to a WebSocket server. Here are some options:

1. **[ws](https://www.npmjs.com/package/ws)**: The `ws` library is a popular WebSocket library for Node.js and can be used in both Next.js and React. It is fast, easy-to-use, well-documented, and supports almost all browsers. You can install it using npm with the following command:
   ```
   npm install ws
   ```
   Here's an example of how to use `ws` in a React component:
   ```javascript
   import { useEffect } from 'react';
   import WebSocket from 'ws';

   const MyComponent = () => {
     useEffect(() => {
       const socket = new WebSocket('wss://your-websocket-server.com');

       socket.onopen = () => {
         console.log('WebSocket connection established.');
       };

       socket.onmessage = (event) => {
         console.log('Received message:', event.data);
       };

       socket.onclose = () => {
         console.log('WebSocket connection closed.');
       };

       return () => {
         socket.close();
       };
     }, []);

     return <div>WebSocket example</div>;
   };

   export default MyComponent;
   ```

2. **[Socket.IO](https://socket.io/)**: Socket.IO is a real-time communication library that provides WebSocket-like functionality with additional features such as event-based communication and automatic reconnection. It has built-in support for both server-side (Node.js) and client-side (React) implementations. You can install it using npm with the following command:
   ```
   npm install socket.io-client
   ```
   Here's an example of how to use Socket.IO in a React component:
   ```javascript
   import { useEffect } from 'react';
   import io from 'socket.io-client';

   const MyComponent = () => {
     useEffect(() => {
       const socket = io('https://your-websocket-server.com');

       socket.on('connect', () => {
         console.log('WebSocket connection established.');
       });

       socket.on('message', (data) => {
         console.log('Received message:', data);
       });

       socket.on('disconnect', () => {
         console.log('WebSocket connection closed.');
       });

       return () => {
         socket.disconnect();
       };
     }, []);

     return <div>WebSocket example</div>;
   };

   export default MyComponent;
   ```

3. **[ReconnectingWebSocket](https://www.npmjs.com/package/reconnecting-websocket)**: ReconnectingWebSocket is a WebSocket client library that automatically reconnects to the server when the connection is lost. It provides a simple and reliable way to handle WebSocket connections in React. You can install it using npm with the following command:
   ```
   npm install reconnecting-websocket
   ```
   Here's an example of how to use ReconnectingWebSocket in a React component:
   ```javascript
   import { useEffect } from 'react';
   import ReconnectingWebSocket from 'reconnecting-websocket';

   const MyComponent = () => {
     useEffect(() => {
       const socket = new ReconnectingWebSocket('wss://your-websocket-server.com');

       socket.addEventListener('open', () => {
         console.log('WebSocket connection established.');
       });

       socket.addEventListener('message', (event) => {
         console.log('Received message:', event.data);
       });

       socket.addEventListener('close', () => {
         console.log('WebSocket connection closed.');
       });

       return () => {
         socket.close();
       };
     }, []);

     return <div>WebSocket example</div>;
   };

   export default MyComponent;
   ```

Choose the WebSocket client library that best fits your project requirements and follow the respective documentation for more details on usage and configuration.
