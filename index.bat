echo off
echo NUL>_.class&&del /s /f /q *.class
cls
javac -cp com/krzem/minecraft/modules/jogl-all.jar;com/krzem/minecraft/modules/jogl-all-natives-windows-amd64.jar;com/krzem/minecraft/modules/gluegen-rt.jar;com/krzem/minecraft/modules/gluegen-rt-natives-windows-amd64.jar; com/krzem/minecraft/Main.java&&java -cp com/krzem/minecraft/modules/jogl-all.jar;com/krzem/minecraft/modules/jogl-all-natives-windows-amd64.jar;com/krzem/minecraft/modules/gluegen-rt.jar;com/krzem/minecraft/modules/gluegen-rt-natives-windows-amd64.jar; com/krzem/minecraft/Main test.xml
start /min cmd /c "echo NUL>_.class&&del /s /f /q *.class"