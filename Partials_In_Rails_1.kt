Partials in Ruby on Rails allow you to extract reusable sections of code into separate files,
making your views more modular and easier to maintain.
They are useful for rendering common elements across multiple views or for breaking down complex views into smaller components. 
Heres an explanation of how to use partials in Rails with code samples:

Creating a Partial:
To create a partial, you can create a new file with a name starting with an underscore (_) in the app/views directory. 
For example, let's create a partial for displaying a student's information:

Create a new file _student_info.html.erb:

<p>Name: <%= student.name %></p>
<p>Email: <%= student.email %></p>
  
  
  
  
  
  
  
Rendering a Partial:

To render a partial within a view, you can use the render method. 
There are several ways to render a partial, depending on the context and requirements.





Rendering a Partial within a View:

Lets say you want to render the _student_info partial within the show.html.erb view for a specific student:


<h1>Student Details</h1>
<%= render 'student_info', student: @student %>
In the above example, the render method is used to render the _student_info partial,
passing in the @student instance variable as the local variable student.








Rendering a Collection of Partials:

You can also render a collection of partials, passing in a collection of objects to be iterated over.


<h1>All Students</h1>
<%= render partial: 'student_info', collection: @students %>
In this case, the render method is used with the partial option set to 'student_info' and the collection
option set to @students. Each student object in @students will be passed to the partial as a local variable 
named after the partial (e.g., student).






Passing Data to a Partial:

You can pass additional data to a partial by specifying local variables.


<%= render partial: 'student_info', locals: { student: @student, show_email: true } %>
In the above example, we pass two local variables to the _student_info partial: student and show_email.
These variables can be accessed within the partial as regular local variables.






Using Partials with a Collection:

When rendering a partial for a collection, you can access each object in the collection using the
local variable specified in the as option.


<%= render partial: 'student_info', collection: @students, as: :student %>
In this case, each student object in the @students collection will be accessible within the partial as a local variable named student.

By utilizing partials in Rails, you can keep your views organized, reusable, and easy to maintain. 
They help in reducing code duplication and make it easier to manage complex view structures.






























































































































































...
