# DailyFlowPlanner  
Author:  Lev Lisus  

# Project Description
DailyFlowPlanner is an application designed for efficient task and schedule management.  
It combines elements of a to-do list and a calendar, allowing users to manage tasks flexibly by adjusting deadlines and priorities.  
The main goal is to help users organize their daily activities with reminders for the most relevant tasks.  

# Key Features  
- Plan tasks for specific dates.  
- Display a detailed task list for today (if viewed in the first half of the day) or today + tomorrow (if viewed in the evening), with an option to view task details for future dates.    
- Flexible deadline management.  
- Store data in text files (without using a database).  
- Simple and intuitive graphical interface (Swing).  
- Notifications for tasks relevant to the current day.  
- Ability to edit and delete tasks.  

# Technologies and Tools  
- Programming Language: Java  
- IDE: Eclipse  
- Build System: Maven  
- GUI: Swing  
- Data Storage: Text files  
- Version Control: GitHub (the code must be executable from the repository)  
- Distribution Format: Executable JAR file

# How to Run
1. **Clone the repository**  

   	`git clone https://github.com/LevLM/daily-flow-planner.git`
   	
2. **Build with Maven**  

   	`mvn clean package`   -  inside the project directory
   	
3. **Run the application**

	`java -jar target/DailyFlowPlanner-1.0-SNAPSHOT.jar`

# Folder Structure

DailyFlowPlanner/
├── src/
│   └── main/
│       └── java/
│           ├── gui/           # Main GUI classes
│           │   ├── Main.java
│           │   ├── MainWindow.java
│           │   └── TaskForm.java
│           ├── model/         # Task and logic classes
│           │   ├── Task.java
│           │   └── TaskPlanner.java
│           └── module-info.java
├── tasks.dat                  # Text file with saved tasks
├── target/                    # Compiled classes and .jar file
├── pom.xml                    # Maven configuration
└── README.md                  # Project documentation
