cls
cd %~dp0
C:
cd src
java -cp ..\tools\jflex\JFlex.jar JFlex.Main -d scanner scanner\scanner.jflex
pause
cd..
cd tools\byaccj
yacc.exe -J -v -Jpackage=parser -Jsemantic=Object "../../src/parser/parser.y"
move Parser.java ../../src/parser
move y.output ../../src/parser
pause