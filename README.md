# EmergencyNexus
For Software Dev class
---------------------------------------------------------------------------
iNSTRUCTIONS:
Load program (netbeans)
right click the project file (the coffee icon)
set configuration -> customize
Category Run, VM Options:
--add-modules javafx.controls,javafx.fxml
Category Libraries
Compile:
Classpath (press the plus button next to classpath)
add path files to the all the javafx.jar files
ex: C:\Users\Rachel Liang\Desktop\javafx-sdk-11.0.2\lib\javafx-swt.jar
Run
Modulepath (press plus button next to the Modulepath)
Add the same path files to all the javafx.jar files
ex: C:\Users\Rachel Liang\Desktop\javafx-sdk-11.0.2\lib\javafx-swt.jar

REMEMBER IN SCENEBUILDER; UNDER DOCUMENT AND HEIRARCHY ON THE LEFTMOST SIDE, OPEN UP THE CONTROLLER TAB AND ADD THE CONTROLLER.
ex: emergencynexus.LoginScreenController

BEFORE RUNNING, IN THE FXML DOCUMENT IN NETBEANS, CHANGE the xmlns to:
xmlns="http://javafx.com/javafx" xmlns:fx="http://javafx.com/fxml"