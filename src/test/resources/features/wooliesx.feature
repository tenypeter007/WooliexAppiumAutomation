Feature: Create a recurring meeting using Native Android/iOS Calendar App. 

@meeting
Scenario Outline: Create a new recurring(3 days a week) meeting, and make sure it doesn't repeat on successive days
	Given I have launched the Calendar App 
	And I delete existing event with name "<title>" 
	When It is not a non working day 
	And Meeting is not repeated on successive days 
	Then I want to book a meeting with the title "<title>" 
	And Set Meeting duration as "<#hrs>" and "<#Minutes>" in the evening 
	And I invite "<#Number>" of people 
	|teny.peter@test.com|
	|test@test.com|
	|sample@test.com|
	And I save the meeting 
	Then I Check if the meeting is created as expected  
	 |title| #hrs | #Minutes|#Number|
  | <title>|  <#hrs>|   <#Minutes> | <#Number>|
	 Examples:
   |title| #hrs | #Minutes|#Number|
  | Recurring-Team Catch Up|  15 |   30 | 2|

	
