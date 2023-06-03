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


















MORE EXPLANTION
*********************************************************************************************************************************


  Partials are a powerful feature in Ruby on Rails that allow you to break up your views into smaller, reusable components.
Heres an explanation of how to use them, along with some code samples:

Creating a Partial
To create a partial, you simply create a new file in the app/views directory with a name that starts 
with an underscore (_) and ends with the same file extension as your views (usually .html.erb).
For example, if you wanted to create a partial for a product list item, you might create a file called _product.html.erb.

In the partial file, you can include any HTML, ERB code, or helper methods that you would normally include in a view.
For example, heres a simple _product.html.erb partial:

<div class="product">
  <h2><%= product.name %></h2>
  <p><%= product.description %></p>
  <p><%= number_to_currency(product.price) %></p>
</div>
  
  
Rendering a Partial
To render a partial in a view, you use the render method and pass in the name of the partial as a string. 
For example, to render the _product.html.erb partial for a single product, you might use the following code:

<%= render partial: 'product', locals: { product: @product } %>
In this example, the render method is passed the name of the partial ('product') as a string,
and a local variable (product) is passed in using the locals option. The local variable can then be used in
the partial to display information about the product.

Rendering a Collection of Partials
You can also use partials to render a collection of items, such as a list of products. 
To do this, you pass the collection to the render method instead of a single item.
For example, to render a list of products, you might use the following code:

<%= render partial: 'product', collection: @products %>
In this example, the render method is passed the name of the partial ('product') and a collection
of products (@products). The partial will be rendered once for each item in the collection,
and the local variable (product) will be set to the current item in the collection.

Passing Data to a Partial
You can pass additional data to a partial using the locals option.
For example, if you wanted to add a "selected" class to the current product in a list, you might use the following code:

<%= render partial: 'product', collection: @products, locals: { selected_product: @selected_product } %>
In this example, the locals option is used to pass an additional local variable (selected_product) to the partial.
The variable can then be used in the partial to add the "selected" class to the current product.

You can find more information about partials in Rails in








































































































































...
