This was an application required for a Java-related software course. The overall requirement was to write an application using Java FX and mySql that manages appointments for customers of a fictitious consulting company with three locations (three time zones).

# Running the Demo Application
- Download the repository using git and navigate to the project directory:
  - `git clone https://github.com/clstephenson/scheduling-software.git`
  - `cd scheduling-software`
- Start the database container: 
  - `docker-compose up`
  - If that command was successful, you should have a docker container running MySql with port 3306 exposed on localhost.
  - The docker image being used contains the database with some test data for the application.
- Build and run the application using Maven:
  - `mvn clean javafx:run`
  - Login with:
    - user: test
    - password: test


# Screenshots

### Login

![alt text](https://clstephenson.s3-us-west-2.amazonaws.com/apps/scheduling-software/scheduling-software-login.png "login window")

### Main

![alt text](https://clstephenson.s3-us-west-2.amazonaws.com/apps/scheduling-software/scheduling-software-main.png "main window")

### Edit Customer

![alt text](https://clstephenson.s3-us-west-2.amazonaws.com/apps/scheduling-software/scheduling-software-editCustomer.png "edit customer window")

### Appointment Types by Month Chart

![alt text](https://clstephenson.s3-us-west-2.amazonaws.com/apps/scheduling-software/scheduling-software-apptsByMonthChart.png "appointment types by month chart")

# Assumptions and Decisions

1. using contact field on appointments table for user/consultant
2. using title field on appointments table for appointment type
3. Assume appointments can be held at the office locations in Phoenix, New York, or London
4. Assume "overlapping appointments" refers to appointments for the same user / consultant
5. Assume business hours is 9-5, 7 days per week

# Rubric Items

### Login form

- Localized to both it_IT and fr_CA. Added line of code in main.java to change the default locale.

### Customer records

- can be added, updated, and deleted.

### Appointments

- can be added, updated, and deleted.

### Calendar Views

- Selectable from "View" menu. Can view all appointments, or view only current week or month. Also added a filter
  to view only appointments that have not ended (default) or all, including past appointments.

### Time Zones

- Added line of code in main.java to change the default time zone.

### Exception Controls

- Incorrect username/password - _AccessControlException_ is thrown from _LoginSession.java_ and caught in
  _LoginController.java_. A message is then displayed to the user.
- Appointment outside business hours - Custom exception thrown from _ScheduleValidator_ and caught in _MainController_.
- Overlapping appointments - Custom exception thrown from _ScheduleValidator_ and caught in _MainController_.
- Non-existent/invalid customer data - This should not happen. When an appointment is added or edited, the customer
  is selected from a populated list (non-editable). Newly added customers are auto-added to the list, and when a customer is deleted, all appointments for that customer also get removed.

### Lambda Expressions

- I used lambdas, streams and method references throughout the code. Here are two examples...
  1. _ReportNumApptTypesByMonth.java_, line 45
  2. _MainController.java_, line 178

### Alerts

- _showUserAppointmentsDialog()_ is called from _requestUserLogin()_ in _MainController.java_, line 424. The dialog
  is displayed if the user has an appointment beginning within 15 minutes from current time.

### Reports

- Number of appointment types by month - I chose to implement this as a bar chart, showing months on the x-axis,
  and number of appointments on the y-axis. Code to generate report is in _ReportNumApptTypesByMonth.java_.
- Consultant Schedule - Gets the logged-in user's schedule for the current day. Formats, and outputs to a text
  document. Automatically attempts to open the document in the system's default editor. Code to generate report
  is in _ReportUserSchedule.java_.
- Customer List - Creates a list of all customer data as a comma-separated values (csv) file, and attempts to open
  the document in the system's default editor. Code to generate report is in _ReportCustomers.java_.

### Activity Log

- Writes evidence of each login attempt to _activityLog.txt_. Sessions are logged from _LoginSession.java_, and
  the logging code is implemented in _LoginActivityLogger.java_.
