# Test Plan

**Author**: Team 30

## 1 Testing Strategy

### 1.1 Overall strategy

This is the Test Plan for the SmoothieCart application which is designed to run on Android devices. The application will be tested by the entire team but primarily the QA lead and the developer lead. The testing is composed of 4 categories : unit testing, integration testing, system testing, and regression-testing, these tests will not only test the Use Case Models defined but also the overall working of the application. The external systems will not be tested however the integration testing will test overall communication between the application and these systems.

### 1.2 Test Selection


| Test Method   | Scope              | Technique |Testers|
| ------------ | -------------------| ---------------| --------|
| Unit | Multiple tests per class  | White Box  | QA Lead |
| Integration    |  Between 2 or more classes      | White and Black Box  |QA Lead |
| System    | Completed App on different systems   | Black Box             | TEAM |
| Regression    |  Selection of the tests above  | White and Black Box  | TEAM |

### 1.3 Adequacy Criterion

The objective is to test the functionality of the system. All of the requirements and Use cases will be tested by their designated unit tests and system tests, it is considered adequate if both set of test methods pass for each requirement.

### 1.4 Bug Tracking

The QA Lead will document and maintain a spreadsheet called SmoothieCart_Issues that lists the issues found. The QA Lead will work closely with the Developer lead to address them.

The spreadsheet will have the following Categories:

**Description** - Provides description of the request.

**Bug/ER** - Identifies whether it's a bug or Enhancement request.

**Date found** - Date the issue was found.

**Severity** -  States the severity of the fix needed to prioritize the coding work, the severities are either Critical, Major, and Minor. Critical fixes will be addressed first.

**Date fixed** - Addresses whether the issue is fixed or not and lists the date that it is fixed on.


### 1.5 Technology

The App will be tested primarily with Unit and Manual testing.  

## 2 Test Cases

| Test case                                   	| Purpose                                               	| Test steps                                                                                                                                                                                                                                                                             	| Expected Results                                                                 	| Actual Results 	| Pass/Fail 	| Notes 	|
|---------------------------------------------	|-------------------------------------------------------	|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|----------------------------------------------------------------------------------	|----------------	|-----------	|-------	|
| 1.  Add New Customer                         	| Cart Manager adds new customer                        	| - Manager clicks "Add Customer" button, - Customer information screen  is displayed, - The info such as name, billing address, email will be entered - A randomly generated 32-digit unique hexadecimal is already assigned - Manager clicks "Submit" button                                                                                          	| - Unique ID is assigned automatically - The new customer is added to the system once Submit button is clicked.                                           	| - Unique ID is assigned as Customer ID  - successfully added Customer information to the system.               	| Pass          	|       	|
| 2. Edit Existing Customer                   	| Cart Manager edits existing customer                  	| - Manager clicks "Edit Customer" button, User Scans Customers QRcode and clicks "continue" button, - Customer information screen is displayed with existing info, - The required fields are edited - Manager clicks "Submit" button                                                                                          	| The updated Customer info is added to the system.                                          	| - The QRCode is read properly - Successfully edited Customer information to the system.               	| Pass          	|      	|
| 3. Failure to Add New Customer                  	| Cart Manager fails to add new customer properly       	| - Manager clicks "Add Customer" button, - Customer information screen is displayed, - Manager enters all fields except Email - Manager tries to click "Submit" button                                                                                                                                    	| Displays error if email field is empty.              	|  Pop-up window shows "Please type a valid email address."               	|      Pass     	|       	|
| 4. Failure to edit existing Customer information            	| Cart Manager fails to edit existing customer properly 	| - Manager clicks "Edit Customer" button, Manager scans the Customers card and clicks "continue" button, - Customer information screen is displayed with existing info, - Remove the current email address - Manager  tries to click "Submit" button                                                                           	| Displays error if email field is empty.             	| Pop-up window shows "Please type a valid email address."                	|    Pass       	|       	|
| 5. Order | Manager adds items to order                            	| - Manager clicks "Make a Purchase" button - App requests to scan for QR code - Manager scans the Customer card - Manager clicks continue once the code is scanned - Manager tries to purchase 10 Strawberry Smoothies,- Manager click to "Update" button                                                     	| Step successful if correct Total amount is displayed.                     	| Total Price of $50 is displayed.               	|  Pass         	|       	|
| 6.Failure to Order                    	| Manager fails to add items properly                   	| - Manager clicks "Make a Purchase" button - App requests to scan for QR code - Manager scans the Customer card - Manager clicks continue once the code is scanned - Manager has 0 quantity for each smoothie - Manager tries to checkout with zero smoothies in cart.                                                                                                      	| Application should display error.                        	|    Displayed "Your cart is empty! Add Smoothies to the order before proceeding to checkout."           	| Pass          	|       	|
| 7. Credit Card Reader                       	| To attain Credit card details                         	| - Prerequisite is to have an order ready - Manager clicks "Checkout" button which prompts to Scan Credit Card - The Manager scans the credit card and "Click here to read"                                                                                               	| The "Continue" button will enabled if card is read properly.                                               	| The Credit card is read and continue button is clickable.                	|  Pass         	|       	|
| 8. Failure to read Credit Card              	| Fail to access attain Credit card details             	| - Prerequisite is to have an order ready - Manager clicks "Checkout" button which prompts to Scan Credit Card - TThe Manager scans the credit card and "Click here to read".                                                                                               	| The "Continue" button will not be highlighted and a pop-up screen would be shown with error                          	|  Pop-up screen is displayed with message "Unable to read Credit Card. Please try again."             	| Pass           	|       	|
| 9. Payment Processing                       	| To achieve successful credit card processing                      	| - Prerequisite is to successfully read a credit card - Manager Clicks "Purchase" button - Credit card processor screen will show Payment Successful.                                                                                                                                   	| Payment processing successful if the text "Payment Successful" is shown on Credit card processor screen   	| Payment processing was successful as "Credit Card was successfully processed!"               	|  Pass          	|       	|
| 10. Payment Processing Failure              	| Unsuccessful Credit card processing                    	| - Prerequisite is to successfully read a credit card - Manager clicks "Purchase" button - Credit card processor screen  shows                                                                            Payment Declined message                                                                                                                                   	| Payment processing unsuccessful if an error is shown stating credit card is not accepted                   	|     "Credit card was declined!" message is displayed , once acknowledged, the manager is able to click "Retry Processing Credit Card"         	|   Pass        	|  This test will have to repeated several times to hit the error condition.     	|
| 11. Display Transactions                    	| Manager reviews Transactions for a customer           	| - Manager clicks "Display Transactions" button - Manager scans QR code and clicks "Continue" - A "Transactions" screen is displayed with all transactions for the customer 	| A list of transactions for the customer is populated.                           	|   The Transactions screen is displayed with Customer ID and their transactions made             	|   Pass        	|       	|
| 12. Obtain Credit                           	| Customer obtains $5 credit                            	| - Prerequisite of order valued at $50 - Prerequisite is to successfully read a credit card - Manager Clicks "Purchase" button - Credit card processor screen is shown with Payment Successful message along with Credits Obtained message.                                                                                           	| $5 Credit shown on Credit card processor page 	|   Pop-up is shown with "Credits Acquired email successfully sent!" indicating credits are acquired. The credits Earned are shown once the pop-up screen is acknowledged.             	|  Pass         	|       	|
| 13. Email Credit                            	| Customer receives email about $5 credit               	| - Prerequisite of order valued at $50 - Prerequisite is to successfully read a credit card - Manager Clicks "Purchase" button - Credit card processor screen is shown with Payment Successful message along with Credits Obtained.                                                                                                                                                                                  	| Credits Obtained email sent to Customer                                                 	|    Pop-up is shown with "Credits Acquired email successfully sent!" indicating credits are acquired            	|    Pass       	|       	|
| 14. Credit Deduction                        	| Customer credit subtracted from Total purchases       	| - Prerequisite is that the Customer has existing Credit - Manager clicks "Make a Purchase" button, App requests to scan for QR code - Manager scans the Customer card -Manager clicks continue once the code is scanned - Manager enters a Positive Integer in the box next to the smoothie name - Manager clicks "Update" button                                                                        	| Total price which is shown will include the credit deducted from the new Purchases.           	|  Once the credit is deducted, Total price shown will be actual cost minus credits. The Remaining credits field will also reflect the credits left.                	|       Pass    	|       	|
| 15. Obtain Gold Status                      	| Customer successfully obtains Gold Status             	| - The Prerequisite is to have an order valued at $500 - Prerequisite is to successfully read a credit card - Manager clicks "Purchase" button - Credit card processor screen is shown with Payment successful message along with "Achieved Gold status".                                                                                   	| "Achieved Gold status" is shown on Credit card processor screen and the Customer profile has Gold status                                                 	| Pop-up window with message "Gold Status Email is successfully sent!"  is shown, once acknowledged, the summary page shows Gold Status Achieved.           	|    Pass       	|       	|  
| 16. Email Gold Status                       	| Customer receives email about Gold Status             	| - The Prerequisite is to have an order valued at $500 - Prerequisite is to successfully read a credit card - Manager clicks "Purchase" button - Credit card processor screen is shown with Payment Successful message along with "Achieved Gold status".                                                                                   	| Gold Status email sent to Customer                                               	|      Pop-up window with message "Gold Status Email is successfully sent!"  is shown          	|      Pass     	|       	|
| 17. Duplicate Email ID Failure                  	| Cart Manager tries to add a new customer with an existing email address                  	| - Manager clicks "Add Customer" button, - Customer information screen  is displayed, - The info such as name, billing address, email will be entered - A randomly generated 32-digit unique hexadecimal is already assigned - Manager clicks "Submit" button                                                                                          	|  An error is returned by the system  and the  customer information is not added to the system.                                          	|  "Email already exists in the database. Please try another email."               	| Pass          	|      	|
| 18. Email send failure                       	| System unable to send Email after acquiring credits            	| - The Prerequisite is to have an order valued at $50 - Prerequisite is to successfully read a credit card - Manager clicks "Purchase" button - Credit card processor screen is shown with Payment Successful message along with "Credits Acquired email failure".                                                                                   	| "Credits Acquired email not sent" status will be seen                                               	|  "Credits Acquired email failed to be delivered"  pop-up window is shown which needs to be acknowledged by the User.             	|       Pass    	| This test will have to repeated several times to hit the error condition.      	|
| 19. Preserve Customer information after editing Existing Customer                   	| Cart Manager edits existing customer which should be saved                  	| - Manager clicks "Edit Customer" button, User Scans Customers QRcode and clicks "continue" button, - Customer information screen is displayed with existing info, - The required fields are edited - Manager clicks "Submit" button - Reboot phone and validate data still present.                                                                                         	| The updated Customer info is added to the system.                                          	| Reloaded the app and verified customer information to the system.               	| Pass          	|       	|
| 20.  Verify no credit exists after 1 year expiration                        	|  Customers existing credit should revert to $0.                       	| - Set Device time stamp to 1 year a head from Purchase date - Prerequisite is that the Customer has existing Credit - Manager clicks "Make a Purchase" button, App requests to scan for QR code - Manager scans the Customer card - Manager clicks continue once the code is scanned - The ordering screen should show Available Credits as $0.                                                                                                                                                   	| Credit expired due to 1 year time frame.                                           	| Credit shows as $0 as expected due to time expiry               	| Pass          	|      	| |