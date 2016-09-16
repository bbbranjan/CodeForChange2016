Feature: Data Entry
	In order to enter data obtained from the pregnant women
	As a user
	I want to be able to enter data into a form with offline support

Scenario: Enter data into a form 
	Given I am on the data entry form
	When I enter data into the form
Then I should be able to enter information (question – how will the unique ID be generated?)
When I have finished entering the data
Then I should be able to click on the save button and save the available information
And this should be possible to do offline



Feature: Data Editing
	In order to edit data from an existing form
	As a user
	I want to be able to view previously entered data and be able to edit the information

Scenario: Viewing previous entered information
	Given I am on the listing page of villages
	When I enter the name of the village
	Then there should be a list of all existing information of the pregnant women
	When I enter the name of the pregnant women in the search field
	Then the list should display only the names I searched
	When I click on the name on the listing
	Then I should brought to entry form page with the previously entered information

Scenario: Editing data of previously existing form
	Given I am on the form of a previously entered entry of a pregnant woman
	When I am viewing the form
Then I should not be able to edit key information such as the unique ID, name of the patient and date of birth
	When I want to edit existing information which needs to be updated
	Then I should be able to edit the information as required 
When I am done I should be able to save the edited information offline



Feature: Data Uploading
	In order to upload data to the database
	As a user
	I want to be able to upload new entries without histories into the database
	I want to be able to upload updated forms into the database without risk of corruption
I want to be able to see the differences of the forms and information in the event of a conflict and decide which version to keep


Scenario: Uploading data when connection is available
	Given that I am ready to upload data onto the database
When I am in a place with available wireless connection and I am ready to upload my information
	Then I should able to click upload to upload the data
	And I should be able to see the progress of the upload
	When there are no previous data with the associated unique ID
	Then I should be able to upload all data from my device
	When there are previous data with an older last updated date
	Then I should be able to upload all newly edited information that is of a later updated date
	And I should not be able to update key information such as unique ID, name and DOB
When there are previous data with a newer last updated date then the data on my device
Then I should first get a warning informing me that newer information already exists in the database
And if I want to check the difference in the available uploaded data and the data on my device
Then I should be able to do so and see the difference 
And decide if I want to overwrite this data



Feature: Doctors comments
	In order to advise on information for the pregnant women
	As a doctor
	I want to be able to write in comments in the remarks for specific pregnant ladies

Scenario: Viewing available entries
	Given that I want to view listings of the pregnant women in the listings
	When I click on the headers of the listings
Then I should be able to sort by the headings such as pregnancy stage, age, blood pressure and vitalities or urgency as required
When I click on a single pregnant woman
Then I should be brought to the page of the entry to see the full details

Scenario: Adding comments 
	Given that I want to add in comments for the pregnant women
	When I click on the remarks column
	Then I should be able to enter information and comments as required
	When I click on the flag on the entry
	Then I should be able to change the importance of the entry with “High” importance
	When I click on the save button
	Then I should be able to save the information I have written down
