# PasswordManagerProject
This is a simple password manager I built during the Spring 2022 semester using WindowBuilder for Eclipse and SQLite. 
  The purpose of this applicaiton was to familiarize myself with the process of developing a GUI applicaiton, as well as
  practice using SQL to write queries and manipulate databases. I also chose to write my own encryption method for my login 
  credentials, as I planned to alter this program in the future to practice intrusion. 

This application was also made with the intention of being used as a teaching tool in the future. Security was not a 
  primary focus when writing the source code, which is why the encrypted creds.txt file and an unecrypted version of the database
  are stored directly next to the source files.
  
THIS APPLICATION IS PURELY EDUCATIONAL AND IS NOT AN END-USER PRODUCT.

The database is a one-table database with 4 fields for an email address, password, website name, and username. The website
  name is the primary key used to sort the database, although it wouldn't be hard to add an index of some kind
  to allow for websites with multiple accounts. The username field is optional. 

The application begins with the loginScreen.java class. If the correct credentials are entered, 
  pmAppWindow.java runs, which is the main application window. From here, the user can interact with the database, seeing
  all entries, finding specific entries, adding new entries, or deleting old entries. The user can also change the login credentials
  for the application.
