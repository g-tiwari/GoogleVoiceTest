# GoogleVoiceTest
This is sample voice test written using Selenium with VoiceAutomationClient and VoiceAutomationServer

This demo will open chrome browser and perform voice search with given search text

## Pre-requisite
For successfully executing this demo, you must have maven and java(>=1.8) installed on your machine

Also you need to have VoiceAutomationServer up and running on the machine where your test would be executed
For that please refer to [VoiceAutomationClient](https://github.com/g-tiwari/VoiceAutomationClient)

## Steps
1. Clone the repository
2. Go to cloned folder **GoogleVoiceTest** from command-line
3. type **mvn test -D-DVoiceAutomationServerEndpoint="<VoiceServerEndPointURL>"  -DVoiceRssKey="<API_Key_FromVoiceRSS>"** and hit enter