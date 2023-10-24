The code example provided in the article explains how to implement pagination in a Rails application without using any pagination gem. Instead, it relies on the `limit` and `offset` parameters to control the number of records displayed per page and the starting point for each page. Here's an explanation of the code with examples in both Rails and React:

**Rails Controller Code:**

```ruby
# app/controllers/users_controller.rb
def users
  users = User.limit(params[:limit]).offset(params[:offset])
  render json: { users: users }
end
```

In the Rails code:

- The `users` action in the `UsersController` is responsible for returning a paginated list of users.
- It uses the `limit` and `offset` methods to limit the number of records and specify the starting point for each page.
- The results are returned as JSON, including the paginated list of users.

**React Component Code:**

```javascript
import React from 'react';

const PER_PAGE = 50;  // Number of users to load per page

class Users extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      offset: 0,
      limit: PER_PAGE,
      users: []
    };
    this.handlePageClickNext = this.handlePageClickNext.bind(this);
    this.handlePageClickPrev = this.handlePageClickPrev.bind(this);
  }

  loadUsersFromServer() {
    fetch(`/users?limit=${this.state.limit}&offset=${this.state.offset}`)
      .then(response => response.json())
      .then(response => {
        this.setState({ users: response.users });
      });
  }

  handlePageClickPrev() {
    // Load the previous set of users
    let offset = this.state.offset - PER_PAGE;
    if (offset < 0) return;
    this.setState({ offset: offset }, () => {
      this.loadUsersFromServer();
    });
  }

  handlePageClickNext() {
    // Load the next set of users
    let offset = this.state.offset + PER_PAGE;
    if (offset >= this.state.total) return;
    this.setState({ offset: offset }, () => {
      this.loadUsersFromServer();
    });
  }

  componentDidMount() {
    // Load the initial set of users on component mount
    this.loadUsersFromServer();
  }

  render() {
    const users = this.state.users.map(user => (
      <li key={user.id}>{user.id}</li>
    ));

    return (
      <div>
        <ul>{users}</ul>
        <button onClick={this.handlePageClickPrev}>Prev</button>
        <button onClick={this.handlePageClickNext}>Next</button>
      </div>
    );
  }
}

export default Users;
```

In the React code:

- The `Users` component manages the state, including the current `offset` and `limit` for pagination.
- It uses the `fetch` API to make a GET request to the Rails API endpoint `/users`, passing the `limit` and `offset` parameters to load a specific page of users.
- The `handlePageClickPrev` and `handlePageClickNext` methods adjust the `offset` based on the previous or next page and fetch the corresponding set of users.
- The component loads the initial set of users when it mounts.

This code demonstrates a simple and efficient way to implement pagination in a Rails API and consume the paginated data in a React component. It leverages the `limit` and `offset` approach to load users in smaller, manageable sets for better performance and user experience.


...
