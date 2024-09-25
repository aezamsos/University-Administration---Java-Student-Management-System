# University-Administration---Java-Student-Management-System
The University Administration project is a Java-based student management system designed for college administration. It allows users to manage student data, generate reports, and interact with an Oracle SQL database. Features include adding, deleting, searching, and updating student records, as well as exporting data to Excel.

## Description

University Administration is a Java-based application designed to manage student records for a college or university. The system allows administrators to perform essential functions such as adding, updating, deleting, and searching student records, as well as exporting student data into an Excel format.

This project is integrated with an Oracle SQL database for efficient data storage and retrieval. The user interface is built using Java Swing, and features such as generating reports and exporting to Excel are also provided.

## Features

- Add, delete, update, and search student records
- Export data to Excel
- Display current time and date in the application
- Generate reports for individual or all students
- Compatible with Oracle SQL Database 19c

## Prerequisites

- Java 8 or higher
- Oracle SQL Database 19c
- JDBC Driver for Oracle Database
- Apache POI (for Excel file generation)

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/UniversityCollege.git
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, NetBeans, Eclipse, or Visual Studio Code).
3. Set up your Oracle Database with the necessary student tables. Example SQL script:
4. Configure your JDBC connection in the Java project with the appropriate URL, username, and password.

#How to Use
1. Run the Project: You can run the project by executing the UniversityCollege.java file.

2. Manage Student Records: Use the graphical user interface to manage student data. You can add, update, delete, and search records in the database.

3. Generate Reports: Use the "Generate Report" button to export all student data into an Excel file or generate a report for a specific student.

4. Export to Excel: Click the "Export" button to export the student records into an Excel spreadsheet.


You can upload your Java project to GitHub by creating a proper title, description, and README file. Here's a guide for your project titled "UniversityCollege" with a layout suitable for a GitHub repository.

1. Title: UniversityCollege - Java Student Management System
2. Description:
The UniversityCollege project is a Java-based student management system designed for college administration. It allows users to manage student data, generate reports, and interact with an Oracle SQL database. Features include adding, deleting, searching, and updating student records, as well as exporting data to Excel.

3. README.md file:
Create a README.md file in the root of your repository with the following content:

markdown
Copy code
# UniversityCollege - Java Student Management System

## Description

UniversityCollege is a Java-based application designed to manage student records for a college or university. The system allows administrators to perform essential functions such as adding, updating, deleting, and searching student records, as well as exporting student data into an Excel format.

This project is integrated with an Oracle SQL database for efficient data storage and retrieval. The user interface is built using Java Swing, and features such as generating reports and exporting to Excel are also provided.

## Features

- Add, delete, update, and search student records
- Export data to Excel
- Display current time and date in the application
- Generate reports for individual or all students
- Compatible with Oracle SQL Database 19c

## Prerequisites

- Java 8 or higher
- Oracle SQL Database 19c
- JDBC Driver for Oracle Database
- Apache POI (for Excel file generation)

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/UniversityCollege.git
Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, NetBeans, Eclipse, or Visual Studio Code).

Set up your Oracle Database with the necessary student tables. Example SQL script:

sql
Copy code
CREATE TABLE Student (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER,
    gender VARCHAR2(10),
    department VARCHAR2(50),
    year NUMBER
);
Configure your JDBC connection in the Java project with the appropriate URL, username, and password.

## How to Use
Run the Project: You can run the project by executing the UniversityCollege.java file.

Manage Student Records: Use the graphical user interface to manage student data. You can add, update, delete, and search records in the database.

Generate Reports: Use the "Generate Report" button to export all student data into an Excel file or generate a report for a specific student.

Export to Excel: Click the "Export" button to export the student records into an Excel spreadsheet.

## Code Overview
Main Classes:

UniversityCollege.java: The entry point of the application. It handles the graphical user interface and interaction with the Oracle database.

DatabaseConnection.java: This class manages the connection to the Oracle database using JDBC.

ExcelExporter.java: A utility class to handle exporting data to Excel format using Apache POI.

## Contributing
Feel free to contribute to this project by submitting a pull request or opening an issue if you find any bugs or have feature requests.
