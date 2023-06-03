To create a form in Rails to receive user data and create a new student object with fields for first_name,
last_name, and email, you can follow these steps:

Step 1: Generate the Student Model
Run the following command in your Rails applications terminal to generate the Student model:


rails generate model Student first_name:string last_name:string email:string
This will create a migration file and the Student model file.




Step 2: Run Database Migrations
Run the following command in your terminal to migrate the database:


rails db:migrate



Step 3: Create a Controller
Generate a controller for the student model by running the following command:


rails generate controller Students




Step 4: Define Routes
Open the config/routes.rb file and add the following line to define the routes for the students:

  get '/students/new', to: 'students#new',
  post '/students', to: 'students#create'


Step 5: Create a New Action
In the app/controllers/students_controller.rb file, add the following code:


class StudentsController < ApplicationController
  def new
    @student = Student.new
  end

  def create
    @student = Student.new(student_params)
    if @student.save
      redirect_to @student
    else
      render 'new'
    end
  end

  private

  def student_params
    params.require(:student).permit(:first_name, :last_name, :email)
  end
end
Step 6: Create a New View
Create a new file called new.html.erb in the app/views/students directory, and add the following code:


<h1>New Student</h1>
<%= form_with(model: @student, url: students_path, local: true) do |form| %>
  <div>
    <%= form.label :first_name %>
    <%= form.text_field :first_name %>
  </div>
  <div>
    <%= form.label :last_name %>
    <%= form.text_field :last_name %>
  </div>
  <div>
    <%= form.label :email %>
    <%= form.email_field :email %>
  </div>
  <div>
    <%= form.submit %>
  </div>
<% end %>
Step 7: Create a Show Action
In the app/controllers/students_controller.rb file, add the following code:


def show
  @student = Student.find(params[:id])
end
Step 8: Create a Show View
Create a new file called show.html.erb in the app/views/students directory, and add the following code:


<h1><%= @student.first_name %> <%= @student.last_name %></h1>
<p>Email: <%= @student.email %></p>
Thats it! Now you should have a form to create a new student object with first_name, last_name,
and email fields. When the form is submitted, it will create a new student record in the database and redirect to the 
students show page, displaying their details.


































































..
