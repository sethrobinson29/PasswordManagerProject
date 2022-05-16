# PasswordManagerProject
This is a simple password manager I built during the Spring 2022 semester using WindowBuilder for Eclipse and SQLite. 
  The purpose of this applicaiton was to familiarize myself with the process of developing a GUI applicaiton, as well as
  practice using SQL to write queries and manipulate databases. I also chose to write my own encryption method for my login 
  credentials, as I planned to alter this program in the future to practice intrusion. 

This application was also made with the intention of being used as a teaching tool in the future. Security was not a 
  primary focus when writing the source code, which is why the encrypted creds.txt file and an unecrypted version of the database
  are stored directly next to the source files.
  
THIS APPLICATION IS PURELY EDUCATIONAL AND IS NOT AN END-USER PRODUCT.

Credit to ProgrammingKnowledge (https://www.youtube.com/c/programmingknowledge) on YouTube for the sqlConnection class, 
  as well as a tutorial on how to set up the SQLite database, and the download link for rs2xml.jar, which enables the application 
   to interact with the database. 
   
rs2xml.jar can be downloaded here => https://sourceforge.net/projects/finalangelsanddemons/files/rs2xml.jar/download 
  You must add the unzipped file to the Referenced Libraries in your project.

The database is a one-table database with 4 fields for an email address, password, website name, and username. The website
  name is the primary key used to sort the database, although it wouldn't be hard to add an index of some kind
  to allow for websites with multiple accounts. The username field is optional. 
  
The database is initially populated with  test data, and can be removed one at a time from the Password Manager application,
  or can be further manipulated and altered by downloading DBBroswer for SQLite, which can be found at 
  https://sqlitebrowser.org/blog/version-3-12-2-released/

The application begins with the loginScreen.java class. If the correct credentials are entered, 
  pmAppWindow.java runs, which is the main application window. From here, the user can interact with the database, seeing
  all entries, finding specific entries, adding new entries, or deleting old entries. The user can also change the login credentials
  for the application.
  
The Validation class is solely used for checking the username and password credentials when logging in or changing passwords, as well as 
  encrypting the credentials when storing them to creds.txt.
