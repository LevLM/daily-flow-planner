# DailyFlowPlanner  
Author:  Lev Lisus  

# Project Description
DailyFlowPlanner is an application designed for efficient task and schedule management.  
It combines elements of a to-do list and a calendar, allowing users to manage tasks flexibly by adjusting deadlines and priorities.  
The main goal is to help users organize their daily activities with reminders for the most relevant tasks.  

# Key Features  
- Simple and intuitive graphical interface (built with Swing)
- Easy to launch using an executable JAR file 
- Flexible deadline management
- Ability to add, edit, and delete tasks
- Each task has a title, deadline, status, and priority
- Task filtering: view only today's tasks or all; filter by completed/active
- (In development) Task types: task, reminder, meeting
- (In development) Reminders for tasks due today
- (In development) Improved sorting and viewing options

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

```
DailyFlowPlanner/
├── src/
│   └── main/
│       └── java/
│           ├── gui/                 # Main GUI classes
│           │   ├── Main.java
│           │   ├── MainWindow.java
│           │   └── TaskForm.java
│           ├── model/               # Task and logic classes
│           │   ├── Task.java
│           │   └── TaskPlanner.java
│           └── module-info.java
├── tasks.dat                        # Text file with saved tasks
├── target/                          # Compiled classes and .jar file
├── pom.xml                          # Maven configuration
└── README.md                        # Project documentation
```