
:: =========================================
::  This .bat file demonstrates how to create or update an automation task in Zephyr for Jira Cloud, run this task, and publish test results to Zephyr.
::  Author: SmartBear Software
:: =========================================

:: =========================================
::  Zephyr base URL.
::  DON'T CHANGE THE CONSTANT BELOW. KEEP IT AS IT IS.
:: =========================================
set "$zephyrBaseUrl=https://prod-api.zephyr4jiracloud.com/connect" 

:: =========================================
::  Access and secret keys, and user id needed for connection to Zephyr for Jira. 
::  Replace the constants below with values relevant to your project and account.
:: =========================================
:: The accessKey and secretKey to access your project. You can find them in your Jira project: Zephyr > API Keys.
set "$accessKey=ZmUzNTkyM2UtODg4NS0zZTFlLTg3N2EtN2E3MDM3Yzc4ODFlIDYxYjgzNTVkMGYwMjQ5MDA2OWRlYjAxNSBVU0VSX0RFRkFVTFRfTkFNRQ"
set "$secretKey=PqbOOLv4KG2KMjZSot5gWH3h2SeJG8xmrMSAweeoCQg"
:: Id of the user who will create the automation task. You can find it in Jira.
set "$accountId=61b8355d0f02490069deb015"

:: =========================================
::  Create a JSON Web Token  (required to access Zephyr for Jira).
::  Keep this section as it is.
:: =========================================
echo "Generating a JSON Web Token ..."
for /F "tokens=*" %%i in (' curl -s -X POST -H "Content-Type: application/json" --data "{ \"accessKey\":\"%$accessKey%\", \"secretKey\":\"%$secretKey%\",\"accountId\":\"%$accountId%\",\"zephyrBaseUrl\": \"%$zephyrBaseUrl%\",\"expirationTime\":360000}" "https://prod-vortexapi.zephyr4jiracloud.com/api/v1/jwt/generate" ')  do set jwtgenerated=%%i
set "$finaljwt=%jwtgenerated%"
echo %$finaljwt%

:: =========================================
::  Define properties of the automation task.
::  Replace the values below with data relevant to your project.
:: =========================================

:: Task info

::set "$taskName=AutomationTask"
::set "$taskDescription=Sanity Tests"
::set "$automationFramework=TestNG"
::set "$automationVia=Upload"  
::set "$projectKey=MD"
::set "$versionName=Unscheduled"

set "$taskName=SanityTask"
set "$taskDescription=Serverless Sanity Tests"
set "$automationFramework=Junit"
set "$automationVia=Upload"  
set "$projectKey=MD"
set "$versionName=Unscheduled"

:: Cycle info
set $cycleName="DailySanity"
set $createNewCycle="false"
set $appendDateTimeInCycleName="false"

:: Folder info
set $folderName="ServerlessSanityResults"
set $createNewFolder="false"
set $appendDateTimeInFolderName="false"
set $assigneeUser="61b8355d0f02490069deb015"

:: Fully-qualitified name of the test result file
::$resultPath=@\"C:/Users/vignesh.gunasekaran/Documents/Integration script/testng-results.xml\" 
::set $resultPath=@"C:\Users\user/eclipse-workspace/frontierX/target/surefire-reports/testng-results.xml"
::echo "Pick File from ...->" @\%$resultPath%
::                "D:\Git Repos\frontierX\target\surefire-reports\TEST-TestSuite.xml"
set $resultPath=@\"C:/ProgramData/Jenkins/.jenkins/workspace/Sanity_WebApp/frontierX/target/surefire-reports/TEST-TestSuite.xml\"
::Mandatory Field required from JIRA
set $mandatoryFields={\"reporter\":{\"label\":\"Ravikiran HM\",\"name\":\"Ravikiran HM\",\"id\":\"61b8355d0f02490069deb015\"}}

:: "D:/Git Repos/frontierX/target/surefire-reports/TEST-TestSuite.xml"
:: =========================================
::  Create an automation task, run it, send test results to Zephyr.
::  Keep this section as it is.
:: =========================================
echo "Creating and running an automation task Fourth Frontier WebApp..."
curl -v -X POST  https://prod-vortexapi.zephyr4jiracloud.com/api/v1/automation/job/saveAndExecute  -H "Content-Type: multipart/form-data" -H "Content-Type: application/json" -H "accessKey: %$accessKey%" -H "jwt: %$finaljwt%" -F "jobName=%$taskName%" -F "jobDescription=%$taskDescription%" -F "automationFramework=%$automationFramework%" -F "projectKey=%$projectKey%" -F "versionName=%$versionName%" -F "cycleName=%$cycleName%" -F "createNewCycle=%$createNewCycle%" -F "appendDateTimeInCycleName=%$appendDateTimeInCycleName%" -F "folderName=%$folderName%" -F "createNewFolder=%$createNewFolder%" -F "mandatoryFields=%$mandatoryFields%" -F "appendDateTimeInFolderName=%$appendDateTimeInFolderName%" -F "assigneeUser=%$assigneeUser%" -F "file=%$resultPath%"

:: =========================================
::  Update the properties of automation task and  run it, send test results to Zephyr.
:: 
::  The code below uses "task properties" values defined in lines 38-56.
::  Update them to change task properties.
:: 
::  To run the code below, first comment out the lines 62-63 above that create your automation task, 
::  and then uncomment in the lines below.
:: =========================================

:: Id of your automation task. You get it after the task is created.
:: set "$taskId=XXXXXXXXXXXXXXXXXXXX42E96E8863BF7EEE44BDB95FF763DA3E9C3BC4776886"

:: echo "Updating and running the automation task"
:: curl -v -X PUT  https://prod-vortexapi.zephyr4jiracloud.com/api/v1/automation/job/updateAndExecute  -H "Content-Type: multipart/form-data" -H "Content-Type: application/json" -H "accessKey: %$accessKey%" -H "jwt: %$finaljwt%"  -F "jobId=%$taskId%" -F "jobName=%$taskName%" -F "jobDescription=%$taskDescription%" -F "automationFramework=%$automationFramework%" -F "versionName=%$versionName%" -F "cycleName=%$cycleName%" -F "createNewCycle=%$createNewCycle%" -F "appendDateTimeInCycleName=%$appendDateTimeInCycleName%" -F "folderName=%$folderName%" -F "createNewFolder=%$createNewFolder%" -F "appendDateTimeInFolderName=%$appendDateTimeInFolderName%" -F "assigneeUser=%$assigneeUser%" -F "file=%$resultPath%"
