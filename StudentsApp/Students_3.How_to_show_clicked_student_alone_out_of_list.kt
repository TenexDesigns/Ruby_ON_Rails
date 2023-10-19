To show the clicked student alone in the browser using manual routes, you can use the following steps:

Define a route and a controller action to show a single student: In your config/routes.rb file,
you can define a route that maps to a controller action that shows a single student. For example:
   get '/students/:id', to: 'students#show'
This will define a route that maps to the show action in the students controller, and passes the id parameter in the URL.

Define the show action in the controller: In your app/controllers/students_controller.rb file,
you can define the show action to retrieve a single student from the database based on the id parameter,
and store it in the @student variable. For example:

   def show
     @student = Student.find(params[:id])
   end
This will retrieve a single student from the database based on the id parameter passed in the URL,
and store it in the @student variable.

Display the clicked student in the view: In your app/views/students/show.html.erb file,
you can display the clicked student using the same approach as in step 2 of the previous section. For example:

   <p>Firstname: <%= @student.firstname %></p>
   <p>Lastname: <%= @student.last_name %></p>
   <p>Email: <%= @student.email %></p>
This will display the values of each field for the clicked student.

To link to the show page for a specific student, you can use the link_to helper provided by Rails.
For example, in your app/views/students/index.html.erb file, you can generate a link to the show page for each student like this:

   <% @students.each do |student| %>
     <%= link_to student.firstname, "/students/#{student.id}" %>
   <% end %>
This will generate a link with the students firstname that points to the show page for that student.
When the link is clicked, the show action in the controller will be called, and the clicked student will be displayed in the view.

You can find more information about manually defining routes in
stackoverflow.com and about defining the show action in the controller in guides.rubyonrails.org.

