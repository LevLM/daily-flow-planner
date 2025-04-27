# DailyFlowPlanner  
Author:  Lev Lisus  

# Project Description
DailyFlowPlanner is a simple application designed for efficient task and schedule management.  
It combines elements of a to-do list and a calendar, allowing users to manage their daily activities flexibly by adjusting deadlines and priorities.  
The main idea was to create something useful for everyday life, especially for people who like to stay organized.

# Key Features  
- Simple and intuitive graphical interface (built with Swing)
- Easy to launch using an executable JAR file 
- Ability to add, edit, and delete tasks
- Each task has a title, deadline, status, and priority
- Flexible deadline management
- Task filtering: view only today's tasks or all; filter by completed/active
- Automatic reminder for today's tasks when the app starts
- (In development) Task types: task, reminder, meeting
- (In development) Improved sorting and viewing options
- Tasks are storing in a file using object serialization, allowing to save and load complete task objects with all fields

# Technologies and Tools  
- Programming Language: Java  
- IDE: Eclipse  
- Build System: Maven  
- GUI: Swing  
- Data Storage: Serialized file (tasks.dat)  
- Version Control: GitHub (the code must be executable from the repository)  
- Distribution Format: Executable JAR file

# How to Run
1. **Clone the repository**  

   	`git clone https://github.com/LevLM/daily-flow-planner.git`
   	
2. **Build the project using Maven**  

Open a terminal inside the project folder and run:

	`mvn clean package` 
   	
3. **Run the application**

After successful build, run the generated JAR:

	`java -jar target/DailyFlowPlanner-1.0-SNAPSHOT.jar`

📂 Important:
All tasks are automatically loaded from tasks.dat if it exists.

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