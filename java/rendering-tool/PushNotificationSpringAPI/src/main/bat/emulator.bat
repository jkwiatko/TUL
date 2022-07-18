@echo on
::In order to make it work you need to add path to emulator.exe (inside tools dir in catalog with android-sdk) to your system variables.
::%EMULATOR% -avd %1 -port %2
%1 -avd %2 -port %3