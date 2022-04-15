
# EmergencyNexus

##### For Software Dev class

## How to run the Emergency Nexus:
### First step:
Before running anything, navigate to the Dump20220415 folder in this repo and download that entire folder. In MySQL workbench, make a new connection, and then make a new database called emergency_nexus. Then, under the server settings which are near the top of the Workbench window, find the Data import option. Once in this option, select the freshly downloaded Dump20220415 folder. The emergency_nexus database should automatically pop up. Press the start import button, and the database is now local to your device!

### Running from IDE:
The code hosted in this repo will work on the Intellij IDE specifically. If opening and running from Intellij, please reload the Maven project first before you try running it. Also before running from Intellij, navigate to the database-properties.txt file and put your MySQL root password.

### Running from Jar file:
A runnable jar file can be found here: https://drive.google.com/drive/folders/1lJTIt7UZk29Ey6gxMTsvBxw4EuAH9npD?usp=sharing
If you want to run it from the jar file, please navigate to the shared google drive folder and download both the database-properties.txt file and the emergency-nexus.jar file. Before running the jar file, please add your MySQL root password into the txt file. If you cannot remember your MYSQL root password, you can follow this helpful video to reset the password: https://www.youtube.com/watch?v=rr_hDJLFvQE&list=PLieBumQqLguVUzIctr322URQE7yTo5FmJ&index=3 (Verified working on 4/0/2022).

### Login screen:
Once at the login screen, try using the accounts:

[System admin account]   
username: admin  
password: nexus 
  
[nurse account]   
username: nurse  
password: nexus  
  
[doctor account]   
username: gman  
password: menty

  
FOR DELIVERABLE 4 
=============
Vers. 1.0.0 of the Emergency Nexus software:
- Login Screen
  - Successful database connection
  - Able to check different user accounts
  - Will open different windows depending on the type of user
  - Will inform user if username or password entered is not found in the database
- System Admin UI
  - Able to view the users table from the database in a table
    - Able to toggle the password view on and off
    - TODO: implement search bar to look through the table
  - Able to create new users (From a separate tab in the window only so far)
  - Able to edit users (directly from the table, and from a separate tab in the window as well)
  - Able to delete users (directly from the table, and from a separate tab in the window as well)
    - when deleting users, will ask for password associated with account
  - Able to save drafts (local to session)
    - drafts can be saved for editing or creating user accounts
    - making a draft will prompt a draft name and will check to see if:
      - what you are trying to save as a draft is empty (err if it is)
      - if there already exists a draft with the same information in the system (err if there is) 
  - Able to see all the drafts in a table
    - drafts will display the name (as given by user, or defaults to "default name", type, and timestamp that they were made
    - drafts can be opened and resumed (will open in the corresponding tab of the type of draft), and submitted
    - drafts can be deleted
  - Able to see admin tickets in a table
    - will be able to assign the ticket to themselves, close or open a ticket, and only show open tickets
    - TODO: need to implement an admin ticket ui that other users can access if they want to make a ticket
- Nurse UI
  - Able to create a patient
    - will check if the patient form is empty before submission (err if it is)
    - will check if integral informatiton is missing from the form (err if it is)
    - will check for any missing items on the form at submission (warning if there is)
  - Able to create a visit form
    - will check if the patient form is empty before submission (err if it is)
    - will check if integral informatiton is missing from the form (err if it is)
    - will check for any missing items on the form at submission (warning if there is)  
    - visit form is linked with a specific PatientID and PhysicianID
  - Able to save drafts of forms (local to session)
    - will check if form is empty before saving as draft (err if it is)
    - will check if patient form already exists in database by patient name and birthday
  - Able to see all the drafts in a table
    - can open up each draft or delete them
    - drafts had a name (given by user or default to "default name"), a type, and a timestamp
  - TODO: need to implement the records table
- Doctor UI
  - The same as the Nurse UI
  - TODO: implement the couple of things that need to change between the Nurse and Doctor UIs
- TODO: Billing
  - Will be an option as seen in the records table in both the Nurse and Doctor UIs  
