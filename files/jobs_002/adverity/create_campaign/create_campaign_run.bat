%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/log4j-1.2.16.jar;../lib/dom4j-1.6.1.jar;../lib/talendcsv.jar;../lib/postgresql-9.4-1201.jdbc41.jar;../lib/talend_file_enhanced_20070724.jar;create_campaign_0_1.jar; adverity.create_campaign_0_1.create_campaign --context=Default %* 