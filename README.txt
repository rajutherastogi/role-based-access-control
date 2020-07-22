Project name: role-based-access-control
Language used: Java
IDE Used: STS4
Framework Used: Spring Boot

Expectations from project:
1. This project offers 3 services to a client:
	a. Check if an user (authenticated by userName and password) has access to a resource for given action type
	b. Add a role to an user
	c. Remove a role from user

2. Apart from these, there are in memory database for user and role vs resource access. I am quoting these below. These can be modified easily.
	a. User: user1 has role as MANAGER, user2 has role as ASSISTANT, user3 has role as GUEST
		[
		  {
		    "userName"="user1",
		    "password"="user1",
		    "roles"=[
		      "MANAGER"
		    ]
		  },
		  {
		    "userName"="user2",
		    "password"="user2",
		    "roles"=[
		      "ASSITANT"
		    ]
		  },
		  {
		    "userName"="user3",
		    "password"="user3",
		    "roles"=[
		      "GUEST"
		    ]
		  }
		]

	b. Role vs Resource Access: 
	GUEST has READ access to Resource R1, 
	ASSITANT has READ access to Resources (R1, R2 and R3), WRITE access to Resource R1 and DELETE access to R1
	MANAGER has READ, WRITE and DELETE access to all the resources.

	{
		"GUEST"={"READ"=["R1"],"WRITE"=[],"DELETE"=[]},
		"ASSISTANT"={"READ"=["R1","R3","R2"],"WRITE"=["R1"],"DELETE"=["R1"]},
		"MANAGER"={"READ"=["R6","R5","R1","R3","R7","R2","R4"],"WRITE"=["R6","R5","R1","R3","R7","R2","R4"],"DELETE"=["R6","R5","R1","R3","R7","R2","R4"]}
	}

3. ActionType can be of 3 types: READ, WRITE, DELETE
4. Role can be of 3 types: GUEST, ASSISTANT, MANAGER
5. All the classes are grouped in relevant packages: service, constant, entity, enums
6. RoleBasedAccessControlApplication is the main class. It is a command line runner with 3 services to be performed.
