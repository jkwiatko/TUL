@echo on
::In order to make it work you need to add path to adb.exe (inside platform-tools dir in catalog with android-sdk) to your system variables.
::%ADB% -s emulator-%1 install %2
%1 -s emulator-%2 install %3