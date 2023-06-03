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










FOLLOW UP QUESTIONS  - How is data sent from user to data bsae
***************************************************************************************************************************

  In the above context, the data is obtained from the user through an HTML form. The form is defined using
the form_with helper method, which generates an HTML form and handles form submissions in Rails.
The form fields are associated with the @student object.

When the form is submitted, it triggers the create action in the StudentsController. In the create action, 
the data entered by the user is received and used to create a new Student object.

The student_params function is used to retrieve the parameters (data) submitted by the user from the form.
It is a private method defined within the StudentsController. The purpose of this method is to restrict the 
allowed parameters and prevent any unauthorized or
unwanted parameters from being assigned to the Student object.

The student_params method utilizes the params object, which contains all the parameters submitted in the form.
It uses the require method to ensure that the student parameter is present and then uses the permit method 
to specify the allowed fields (first_name, last_name, and email). This is a security measure to protect against
mass assignment vulnerabilities.

By making the student_params method private, it ensures that it can only be called from within the 
StudentsController and cannot be accessed outside the controller. This encapsulation prevents any potential misuse of the method and enhances the security of the application by controlling the allowed parameters.







FOLLOW UP QUESTIONS  - How does Rails know which action in controller to call
***************************************************************************************************************************
  
Yes, Rails provides a mechanism to ensure that the create action is triggered when the form is submitted,
as opposed to the update action. This is accomplished by the HTTP verb used in the form submission.

When you define the form using the form_with helper method in Rails, it automatically determines
the appropriate HTTP verb based on the attributes of the @student object. 
If the @student object is a new record (i.e., it hasnt been saved to the database yet),
the form will be submitted with an HTTP POST request, which maps to the create action in the controller.

On the other hand, if the @student object already exists in the database (e.g., when editing an existing student record),
the form will be submitted with an HTTP PATCH or PUT request, which maps to the update action in the controller.

Rails uses a feature called "method overriding" to simulate different HTTP verbs in HTML forms. 
This is achieved by including a hidden input field within the form that specifies the desired HTTP verb 
(POST, PATCH, or PUT) based on the objects state. Rails automatically includes this hidden input field when
you use the form_with helper method.

Therefore, in the provided context, since the form is for creating a new student object,
the form submission will trigger the create action in the StudentsController due to the HTTP POST request sent by the form.







Use of ------> <%= form_with(model: @student, url: students_path, local: true) do |form| %>
******************************************************************************************************************


In the above context, the url: students_path and local: true options within the form_with helper method have the following uses:

url: students_path: This option specifies the URL where the form data will be submitted when the form is submitted.
In this case, students_path is a Rails route helper method that generates the URL for the create action of the StudentsController. 
It corresponds to the relative path /students when the HTTP method is POST. So, when the form is submitted, 
the form data will be sent to the create action of the StudentsController.

local: true: This option indicates that the form should be submitted locally,
meaning the form submission should be handled within the current Rails application without making an external request. 
When local is set to true, Rails uses JavaScript to handle the form submission asynchronously, 
resulting in a smoother user experience. It avoids a full page reload upon form submission.

These options are provided within the form_with helper method to configure the behavior of the form and specify 
where the form data should be sent. By using students_path as the URL, the form will be submitted to the 
create action of the StudentsController. Additionally, setting local: true enables JavaScript-based form submission,
enhancing the user experience.



































..
