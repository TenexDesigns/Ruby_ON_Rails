In Ruby on Rails, controllers are a fundamental part of the MVC (Model-View-Controller) architecture.
They handle the logic of processing incoming requests, interacting with models to fetch or manipulate data, 
and rendering the appropriate response back to the user. To create and use controllers in Rails, follow these steps:

1.Generate a new controller:
  
  Rails provides a generator to create controllers easily.
  Open a terminal or command prompt, navigate to your Rails project directory, and run the following command:

rails generate controller ControllerName
Replace "ControllerName" with the desired name of your controller. 
This command will create a controller file and associated view files.


2.Define actions:
  
  In the generated controller file (located at app/controllers/controller_name_controller.rb), 
  youll find a class declaration for your controller. Inside this class, define methods
  for each action you want to handle. For example:


class ControllerNameController < ApplicationController
  def index
    # Logic for the index action
  end

  def show
    # Logic for the show action
  end

  def create
    # Logic for the create action
  end

  # Define more actions as needed
end


3.Route requests to the controller:
  
  In config/routes.rb, you need to define routes that map URLs to the appropriate
  controller actions. For example:


Rails.application.routes.draw do
  get '/controller_name', to: 'controller_name#index'
  get '/controller_name/:id', to: 'controller_name#show'
  post '/controller_name', to: 'controller_name#create'
end


This sets up routes for the index, show, and create actions in your ControllerNameController.


or 
This establicses a rout in the url e.g    localhost:3000/contacts
  This url will, render the contects in the view folder called pages and the content in th contcts.html.erb file
  
  get 'contact', to: "pages#contact"
  get "about" ,to: "pages#about"



4,Implement action logic:
  
  Inside each action, you can perform operations like fetching data from models, manipulating data, 
  and rendering views. You can use ActiveRecord to interact with your database models. For example:


def index
  @items = Item.all
end

def show
  @item = Item.find(params[:id])
end

def create
  @item = Item.new(item_params)

  if @item.save
    redirect_to @item
  else
    render 'new'
  end
end


5.Use views for rendering:
  
  By default, Rails looks for a view with the same name as the action in a folder
  named after the controller inside app/views. For example, the view for index action would be located
  at app/views/controller_name/index.html.erb. Use these view files to define the HTML structure and display dynamic data.

Thats the basic process of creating and using controllers in Ruby on Rails.
Remember to handle error cases, implement proper security measures, and follow Rails conventions for naming and organizing your code.







MORE EXPLANTION
*******************************************************************************************************


To create and use controllers in Rails, follow these steps:

Open your terminal and navigate to your Rails application's root directory.
Generate a new controller using the rails generate controller NAME command. Replace "NAME" with the name of your controller, 
such as "Users" or "Products." This command will create several files, including a controller file, a views directory,
and a test file. For example, the following command generates a Greetings controller with an action of hello:
  
$ bin/rails generate controller Greetings hello

Open the controller file, which is located in the app/controllers directory, and define your controller actions.
Each action is responsible for handling a specific user request and returning a response.
For example, the following code defines a hello action that renders a view:
  
class GreetingsController < ApplicationController
  def hello
    @message = "Hello, world!"
    render "hello"
  end
end
Define your routes in the config/routes.rb file. Routes map incoming requests to controller actions. 
For example, the following route maps a GET request to the /greetings/hello URL to the hello action in the GreetingsController
:
get "/greetings/hello" => "greetings#hello"

Create views for your controller actions in the app/views directory.
Views are responsible for rendering HTML templates that are returned to the users browser.
For example, the following code defines a hello view that displays a message:
<h1><%= @message %></h1>
Test your controller using the built-in Rails testing framework or an external testing tool such as RSpec.
In summary, to create and use controllers in Rails, you need to generate a new controller,


define your controller actions, define your routes, create views for your actions, and test your controller. 
The controller is responsible for handling user requests and returning responses, 
and it acts as the logical center of a Rails application that coordinates the interaction between the user, the views, and the model.







MORE EXPLANTION
***************************************************************************************************************


A controller in Rails is a class that handles requests for a specific resource. It is responsible for:

Routing: The controller receives incoming requests and routes them to the appropriate action.
Model interaction: The controller interacts with the model layer to retrieve and manipulate data.
View rendering: The controller renders the appropriate view based on the request.
To create a controller in Rails, you need to create a file in the app/controllers directory. The name of the file should be the same as the name of the resource that the controller will handle. For example, if you are creating a controller for handling requests for products, the file name would be products_controller.rb.

The controller file should contain a class that inherits from the ApplicationController class. The class should define actions for handling the different types of requests that the controller will receive. For example, a products controller might have actions for listing all products, showing a single product, creating a new product, and updating an existing product.

To use a controller, you need to create an instance of the controller class and call the appropriate action. For example, to list all products, you would do the following:

Code snippet
products_controller = ProductsController.new
products_controller.index
Use code with caution. Learn more
This would call the index action on the ProductsController class and render a list of all products.

Here is an example of a controller that handles requests for products:

Code snippet
# app/controllers/products_controller.rb
class ProductsController < ApplicationController
  def index
    @products = Product.all
  end

  def show
    @product = Product.find(params[:id])
  end

  def new
    @product = Product.new
  end

  def create
    @product = Product.new(product_params)

    if @product.save
      redirect_to product_path(@product)
    else
      render :new
    end
  end

  def edit
    @product = Product.find(params[:id])
  end

  def update
    @product = Product.find(params[:id])

    if @product.update(product_params)
      redirect_to product_path(@product)
    else
      render :edit
    end
  end

  private

  def product_params
    params.require(:product).permit(:name, :price, :description)
  end
end
Use code with caution. Learn more
This controller has six actions: index, show, new, create, edit, and update. The index action displays a list of all products. The show action displays a single product. The new action renders a form that can be used to create a new product. The create action saves a new product to the database. The edit action renders a form that can be used to edit an existing product. The update action updates an existing product in the database.

To use the ProductsController class, you would first need to create an instance of the class. You can do this using the new method. Once you have created an instance of the controller, you can call the actions on the controller. For example, to display a list of all products, you would call the index action like this:

Code snippet
products_controller = ProductsController.new
products_controller.index
Use code with caution. Learn more
This would call the index action on the ProductsController class and render a list of all products.

Controllers are an important part of Rails applications. They are responsible for handling requests for resources and for rendering views.















