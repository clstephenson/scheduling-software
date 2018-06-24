Assumptions and Decisions
==========================
1. using contact field on appointments table for user/consultant
2. using title field on appointments table for appointment type
3. Assume appointments can be held at the office locations in Phoenix, New York, or London
4. Assume "overlapping appointments" refers to appointments for the same user / consultant
5. Assume business hours is 9-5, 7 days per week

Rubric Items
============
A. **Login form** - Localized to both it_IT and fr_CA.  Added line of code in main.java to change the default locale.

B. **Customer records** - can be added, updated, and deleted.

C. **Appointments** - can be added, updated, and deleted.

D. **Calendar Views** - Selectable from "View" menu.  Can view all appointments, or view only current week or month.
   Also added a filter to view only appointments that have not ended (default) or all, including past appointments.

E. **Time Zones** - Added line of code in main.java to change the default time zone.

F. **Exception Controls** -
  1. Incorrect username/password - *AccessControlException* is thrown from *LoginSession.java* and caught in  
     LoginController.java.  A message is then displayed to the user.
  2. Appointment outside business hours - Custom exception thrown from *ScheduleValidator* and caught in *MainController*.
  3. Overlapping appointments - Custom exception thrown from *ScheduleValidator* and caught in *MainController*.
  4. Non-existent/invalid customer data - This should not happen.  When an appointment is added or edited, the  
     customer is selected from a populated list (non-editable).  Newly added customers are auto-added to the list,  
     and when a customer is deleted, all appointments for that customer also get removed.

G. **Lambda Expressions** - I used lambdas, streams and method references throughout the code.  Here are two examples...
  1. *ReportNumApptTypesByMonth.java*, line 45
  2. *MainController.java*, line 178

H. **Alerts** - *showUserAppointmentsDialog()* is called from *requestUserLogin()* in *MainController.java*, line 424.  
    The dialog is displayed if the user has an appointment beginning within 15 minutes from current time.

I. **Reports** -
  1. Number of appointment types by month - I chose to implement this as a bar chart, showing months on the x-axis,  
     and number of appointments on the y-axis. Code to generate report is in *ReportNumApptTypesByMonth.java*.
  2. Consultant Schedule - Gets the logged-in user's schedule for the current day.  Formats, and outputs to a
     text document.  Automatically attempts to open the document in the system's default editor. Code to generate
     report is in *ReportUserSchedule.java*.
  3. Customer List - Creates a list of all customer data as a comma-separated values (csv) file, and attempts to
     open the document in the system's default editor. Code to generate report is in *ReportCustomers.java*.

J. **Activity Log** - Writes evidence of each login attempt to *activityLog.txt*.  Sessions are logged from  
    *LoginSession.java*, and the logging code is implemented in *LoginActivityLogger.java*.