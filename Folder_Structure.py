



app                         	It works as the remainder of this directory. Basically it organizes our application component.
                              It holds MVC.
                              
                              
app/assets	                  Contains your images, javascript files, stylesheet files.
                              This folder contains static files required for application's front-end grouped 
                              into folders based on their type.




app/controllers              Control the code flow of the application	e.g were you run database queries, where views look to for what to display on the screen
                             All the controller files are stored here. A controller handles all the web requests from the user.



app/helpers                  	Contains view helpers. Can contain custom methods you want your helpers to have access to.
                              It contains all the helper functions to assist MVC.
                              
                              
app/mailers	                  Can contain logic for sending out emails.
                              It contains mail specific functions for the application.
app/models	                  It contains the models and data stored in our application's database.
                              Can store algoritns for your application

app/views	                    Everuthing the user sees will be here.
                              This folder contains the display templates to fill data in our application.


bin	                          It is not something you are going to use often.It basically contains Rails script that start your app. It can also contain other scripts use to setup, upgrade or run the app.
config	                      It configures our application's database, routes and more.It has all of your intializers
db	                          It contains our current database schema and database migrations.
lib	                          It contains extended module for your application.
log	                          It contains application log files.
public                      	It contains static files and compiled assets. This is the only folder seen by the world.
test	                        It contains unit tests, other test apparatus and fixtures.
tmp	                          It contains temporary files like cache and pid files.
vendor                       	It contains all third-party code like vendor gems.
Gemfile	                      Here all your app's gem dependencies are declared. It is mandatory as it includes Rails core gems among other gems.
Gemfile.lock	                It holds gems dependency tree including all versions for the app.
README.md	                    It is a brief instruction manual for your application.
Rakefile	                    It locates and loads tasks that can be run from the command line.
config.ru	                    Rack configuration for Rack based servers used to start the application.





















































































































...
