*********************************
**  Assumptions and Decisions  **
*********************************
1. using contact field on appointments table for user/consultant

2. using title field on appointments table for appointment type

3. Assume appointments can be held at the office locations in Phoenix, New York, or London

4. Assume "overlapping appointments" refers to appointments for the same user / consultant

5. Assume business hours is 9-5 in the office's timezone

********************
**  Rubric Items  **
********************
F. Exception Controls
    1. Incorrect username/password - AccessControlException is thrown from LoginSession.java and caught in
       LoginController.java.  A message is then displayed to the user.

    2. Appointment outside business hours -

    3. Overlapping appointments -

    4. Non-existent/invalid customer data -