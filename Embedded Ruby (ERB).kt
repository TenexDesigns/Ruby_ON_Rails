Embedded Ruby (ERB) is a templating language that allows you to embed Ruby code within HTML or other text files.
It enables you to dynamically generate content and manipulate data based on logic and variables.

ERB is commonly used in Ruby web frameworks like Ruby on Rails to generate dynamic web pages.
Heres how it works:

Syntax:
ERB tags start with <% and end with %>. There are three main types of ERB tags:

<% Ruby code %>: Executes the Ruby code without outputting anything.This tag is used for executing Ruby code without outputting anything. 
                 It is typically used for control flow statements, loops, or variable assignments.

<%= Ruby expression %>: Evaluates the Ruby expression and inserts the result into the output.
                        This tag is used for evaluating a Ruby expression and inserting the result into the output.
                        The result is converted to a string and displayed in the rendered output.

<%# Comment %>: Adds a comment that is ignored during rendering.


Usage:
ERB is used within HTML or other template files to generate dynamic content.
You can use Ruby code and expressions to perform calculations, conditionals, loops, and access variables or objects.

For example, in an ERB template, you can iterate over a collection of students and generate an HTML table with their information:


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
  
In this example, <% @students.each do |student| %> is a Ruby loop that iterates over the @students collection, 
and <%= student.first_name %> dynamically inserts the first name of each student into the table row.

Rendering:
The ERB template is processed by the server-side Ruby interpreter, which evaluates the embedded Ruby code and expressions,
and generates the final output. The resulting HTML or text is then sent to the clients browser.

In frameworks like Ruby on Rails, the rendering process is handled automatically when a request is made to a corresponding
controller action. The ERB templates are associated with the actions and are rendered with the requested data.

Embedded Ruby allows you to generate dynamic content and build flexible, data-driven web pages.
It enables you to combine the power of Ruby with HTML or other text formats to create dynamic templates
and render dynamic content for your applications.

















































































































































































































...
