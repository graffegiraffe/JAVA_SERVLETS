# Here, the following tasks are implemented:

1. Implement the logistics system that will write events to the Logs.txt file (login events: initialization of the sergete, processing of the request, destruction of the servlet).
2. Develop a new Servable, which will process requests at /logs. This servlet must read the contents of the Logs.txt file and display it to the browser in a readable format.
3. Create servlets for application from homework 22. Services must return the page about you or your goals. (Make sure that logging is configured.)
4. It is necessary to determine the configuration parameters in the Web.xml file. Parameters: AppName, Appversion, DeveloperName, Supportemail. Create a servlet that will extract these parameters from Web.xml and display them in a browser when moving to /settings.
5. Create a servlet that when the transition to /Count will print the number of visits to this page. When working, use the mechanism of sessions.
6. Add the opportunity to discard the visitor counter. This should be implemented through a separate URL ( /Reset), which dumps the number of visits to the page /Count
