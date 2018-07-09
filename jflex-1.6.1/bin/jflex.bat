@echo off
REM Please adjust JFLEX_HOME to suit your needs
REM (please do not add a trailing backslash)

set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_101"	
set JFLEX_HOME=C:\jflex-1.6.1

java -Xmx128m -jar %JFLEX_HOME%\lib\jflex-1.6.1.jar %1 %2 %3 %4 %5 %6 %7 %8 %9
