To display all students in a Rails application and then show and edit a clicked student, you can follow these steps:

Step 1: Create the Student Model
First, create a Student model with the necessary attributes (such as first_name, last_name, and email).
Run the following command in your terminal:


rails generate model Student first_name:string last_name:string email:string
This will generate a migration file. Run the migration to create the students table in the database:


rails db:migrate


Step 2: Set up the Routes

Instead of using the resourceful route helper, you can define manual routes for the desired actions.
Open the config/routes.rb file and add the following lines:


get 'students', to: 'students#index'
get 'students/:id', to: 'students#show', as: 'student'
get 'students/:id/edit', to: 'students#edit', as: 'edit_student'
patch 'students/:id', to: 'students#update'





Step 3: Generate the Students Controller

Run the following command to generate a Students controller with the necessary actions:


rails generate controller Students




Step 4: Implement the Controller Actions

Open the app/controllers/students_controller.rb file and update it with the following code:


class StudentsController < ApplicationController
  def index
    @students = Student.all
  end

  def show
    @student = Student.find(params[:id])
  end

  def edit
    @student = Student.find(params[:id])
  end

  def update
    @student = Student.find(params[:id])
    if @student.update(student_params)
      redirect_to student_path(@student)
    else
      render 'edit'
    end
  end

  private

  def student_params
    params.require(:student).permit(:first_name, :last_name, :email)
  end
end






Step 5: Create Views

Create the necessary view files in the app/views/students directory.






index.html.erb:

<h1>All Students</h1>

<ul>
  <% @students.each do |student| %>
    <li><%= link_to student.first_name, student_path(student) %></li>
  <% end %>
</ul>
  
  
  
  
  
  
show.html.erb:

<h1>Student Details</h1>

<p><strong>First Name:</strong> <%= @student.first_name %></p>
<p><strong>Last Name:</strong> <%= @student.last_name %></p>
<p><strong>Email:</strong> <%= @student.email %></p>

<%= link_to 'Edit', edit_student_path(@student) %>
  
  
  
  
  
edit.html.erb:

<h1>Edit Student</h1>

<%= form_for @student, url: student_path(@student), method: :patch do |f| %>
  <p>
    <%= f.label :first_name %><br>
    <%= f.text_field :first_name %>
  </p>
  <p>
    <%= f.label :last_name %><br>
    <%= f.text_field :last_name %>
  </p>
  <p>
    <%= f.label :email %><br>
    <%= f.text_field :email %>
  </p>
  <p>
    <%= f.submit 'Update' %>
  </p>
<% end %>
  
  
  
  
Step 6: Start the Server

Finally, start your Rails server with the following command:


rails server

You can now visit http://localhost:3000/students to see the list of all students. 
Clicking on a students first name will take you to their individual page, where you can edit their details.
The changes will be updated when you submit the form.






DELETE ----> IS HERE
****************************************************************************************************************************88


To add the ability to delete a student in the show page, you need to modify the routes, the controller, 
and the show view as follows:



Step 1: Update the Routes

Open the config/routes.rb file and add the following line:

delete 'students/:id', to: 'students#destroy', as: 'delete_student'



Step 2: Update the Controller

In the StudentsController, add the destroy action:

def destroy
  @student = Student.find(params[:id])
  @student.destroy
  redirect_to students_path
end



Step 3: Update the Show View

In the app/views/students/show.html.erb file, add the delete link:


<h1>Student Details</h1>

<p><strong>First Name:</strong> <%= @student.first_name %></p>
<p><strong>Last Name:</strong> <%= @student.last_name %></p>
<p><strong>Email:</strong> <%= @student.email %></p>

<%= link_to 'Edit', edit_student_path(@student) %>
<%= link_to 'Delete', delete_student_path(@student), method: :delete, data: { confirm: 'Are you sure?' } %>
  
  
  
  
Step 4: Restart the Server

Restart your Rails server to apply the changes:

rails server

Now, in the show page of each student, you will see a "Delete" link. 
Clicking on this link will prompt a confirmation message. If confirmed, 
the student will be deleted, and you will be redirected to the index page listing all students.









































































































































...
