# employeeManager
Java Spring Framework Code Sample for Penn National Gaming



There were two major concious design decisions that I made throughout this project.

First - whether to make a Manager class that inherited the employee class.
  Ultimately I decided not to do this because there was no functional need for it. If there was any unique behavior to differentiate managers from other employees (i.e. a getAllEmployees() function that returned everyone under one manager, I would have used a different Class for that. There was also identical data for managers and employees, which meant they all had the same fields. This furthered my decision to keep just one Employee Class as well as had an impact on the MySQL database design
  
 Second - whether to do checks on the manager id reference when creating a new employee.
  Ultimately, I decided not to take any action here. Without a full understanding of the business, there could be instances where employees report to each other (employee 2 is employee 1's manager, while employee 1 is also employee 2's manager). There could also be an instance where a CEO is their own manager. Finally, there could be an instance when an entire team is hired together. Checking the manager ID would require that the manager be added to a team first. However, to make it easy to find any blatant errors that could have been made while registering an employee, if a manager does not exist, the export of the employee list will return 'N/A' in the managerFirstName column of the csv file. Rather than limit the business with certain checks, and since it is easy to change data, I decided to allow any ID to be placed as the manager ID.
 



MYSQL Information

// I just created one table here. In a more complex scenario with managers having different data (like I addressed earlier), I would have added a second table for managers but
// that wasn't necessary here. I just stored each field as it was described in the requirements

CREATE TABLE `employeetable` (
   `id` int NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(100) DEFAULT NULL,
  `last_Name` varchar(100) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `is_Manager` boolean DEFAULT NULL,
  `manager_ID` int DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
