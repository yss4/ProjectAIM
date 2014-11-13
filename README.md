ABET Information Manager
===========================
Semester-long team project in Software Engineering course (CSE308) at Stony Brook University.

My role:
I was in charge of intersection development like handling form datasets in order to import them into database by using J2EE and simple javascript code. 
Most of JSPs and servlets are made by me. In order to transfer input data, we used bean

Links of JSP files:
https://github.com/yss4/ProjectAIM/tree/master/CSE308/code/src/main/webapp

Our pages are implemented using JSPs. To explain briefly, user creates course with information and then JSP will be redirected to servlet with the object containing course information.

Links of servlet files :
https://github.com/yss4/ProjectAIM/tree/master/CSE308/code/src/main/java/com/cse308/projectaim/servlets

In servlets, they first check if its function is get, post, or put. And they carry out the operation based on each http request.
