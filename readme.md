GWT Android Emu
===============

This library emulates some Android APIs over GWT. The idea is to allow porting your Android Apps to HTML5 and run them in Chrome, Firefox OS, PhoneGAP, etc.

The components that this library emulates are:

* Activities
* Menu and MenuItems
* AlertDialogs
* Toasts
* SharedPreferences
* Handlers and Messages
* Log
* FloatMath
* SystemClock
* Views (findViewById)

The "android." package names are renamed to "androidemu.", you need to make a "Source->Organize imports" in Eclipse.

This is a work in progress in continuous evolution. At Mobialia we used this library to port some of our Android apps to GWT. It's far from complete and very fitted to our needs, but we make it public in the hope that it will be useful for other developers.

Check the Demo project at http://github.com/albertoruibal/gwt_android_emu_demo to see some usage examples.

We also include a tool ConvertStrings (in the package utils) to convert from ant Android XML file to GWT Class + Key-properties files

It's released under the MIT License, so feel free to use it anywhere. 
