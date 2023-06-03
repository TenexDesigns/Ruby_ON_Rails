To display all the students on the browser, you can follow these steps:

Step 1: Add an Index Action
In the app/controllers/students_controller.rb file, add the following code:


def index
  @students = Student.all
end


Step 2: Create an Index View
Create a new file called index.html.erb in the app/views/students directory, and add the following code:


<h1>All Students</h1>
<table>
  <thead>
    <tr>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
    </tr>
  </thead>
  <tbody>
    <% @students.each do |student| %>
      <tr>
        <td><%= student.first_name %></td>
        <td><%= student.last_name %></td>
        <td><%= student.email %></td>
      </tr>
    <% end %>
  </tbody>
</table>


Step 3: Define the Index Route
In the config/routes.rb file, add the following line to define the index route for students:

get '/students', to: 'students#index', as: 'students'
get '/students/new', to: 'students#new', as: 'new_student'
post '/students', to: 'students#create'


Now, when you visit the route /students, you will see a table displaying all the students with
their first name, last name, and email.

Note: Make sure you have populated the database with student records using the form or using seed data. 
  You can create some seed data by editing the db/seeds.rb file and running rails db:seed command in the terminal.

Additionally, if you want to link to the index page from other views, you can use the following code:


<%= link_to 'All Students', students_path %>
This will create a link that takes you to the index page.






MORE EXPLANTION --------------> Use of as
**********************************************************************************************************************


In the context of defining routes in Rails, the as option is used to provide a custom name or alias for a route.
It allows you to specify a named route, which can be helpful when generating URLs or redirecting within your application.

For example, in the line get '/students', to: 'students#index', as: 'students', the as: 'students' part assigns
the name 'students' to the route. This means that you can refer to this route by its name in other parts of your application,
such as when generating URLs or using path helpers.

Using the named route, you can generate a URL or redirect to the /students path using the students_path helper. For example:


redirect_to students_path

This makes your code more readable and maintainable, as you dont need to hardcode the path each time you want to refer to it.

Note that the as option is optional. If you dont provide it, Rails will generate a default name for
the route based on the controller and action names.
However, using the as option allows you to have more control over the names of your routes.



















































































































































...
