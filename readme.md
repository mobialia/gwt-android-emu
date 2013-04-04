GWT Android Emu
===============

This is a very simple library emulating some Android APIs over GWT:

* Activities
* Menu and MenuItems
* AlertDialogs (partial)
* Toasts
* SharedPreferences
* Handlers and Messages
* Log
* FloatMath
* SystemClock

At Mobialia we used this library to port some of our Android apps to GWT:

* Chess: http://chess.mobialia.com
* Connect4: http://connect4.mobialia.com
* Slot Racing: http://slot.mobialia.com

It's far from complete and very fitted to our needs, but we make it public in the hope that it will be useful for other developers.

This library doesn't respect the Android Package names, you need to make a "Source->Organize imports" in Eclipse.

Usage:

	<module ...>
	...
	<inherits name="com.mobialia.gwt.androidemu" />

It's released under the MIT License, so feel free to use it anywhere. 