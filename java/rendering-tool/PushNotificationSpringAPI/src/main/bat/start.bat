@echo on
::In order to make it work you need to add path to adb.exe (inside platform-tools dir in catalog with android-sdk) to your system variables.
::%ADB% -s emulator-%1 shell am start -n %2 -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -e uuid %3 -e message %4
::%1 -s emulator-%2 shell am start -n %3 -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -e uuid %4 -e title %5 -e body %6
echo am start -n %3 -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -e uuid %4 -e title %5 -e body %6
echo am start -n %3 -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -e uuid %4 -e title %5 -e body %6 -e actionA %7 -e actionB %8 -e largeImage %9 -e smallImage %10  > longcommand.sh
::chmod +x longcommand.sh
%1 -s emulator-%2 push longcommand.sh /data/local/tmp
%1 -s emulator-%2 shell sh /data/local/tmp/longcommand.sh
