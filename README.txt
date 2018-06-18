*********************************
**  Assumptions and Decisions  **
*********************************
1. using contact field on appointments table for user/consultant

2. using title field on appointments table for appointment type

3. Assume appointments can be held at the office locations in Phoenix, New York, or London

4. Assume "overlapping appointments" refers to appointments for the same user / consultant

5. Assume business hours is 9-5, 7 days per week

********************
**  Rubric Items  **
********************
A. Login form is localized to it_IT, and fr_CA.

F. Exception Controls
    1. Incorrect username/password - AccessControlException is thrown from LoginSession.java and caught in
       LoginController.java.  A message is then displayed to the user.

    2. Appointment outside business hours - Custom exception thrown from ScheduleValidator and caught in MainController.

    3. Overlapping appointments - Custom exception thrown from ScheduleValidator and caught in MainController.

    4. Non-existent/invalid customer data - This should not happen.  When an appointment is entered, the customer is
       selected from a populated list (non-editable).  Newly added customers are auto-added to the list, and when a
       customer is deleted, all appointments for that customer also get removed.

