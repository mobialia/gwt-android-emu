GWT Android Emu
===============

GWT is the perfect framework to migrate your Android Apps to the web because it compiles Java into Javascript.

This library emulates some Android APIs over GWT.

The idea is to help you porting your Android Apps to HTML5 and run them in Chrome, Firefox OS, PhoneGAP, etc.
keeping a lot of Java code in common.

This library emulates:

* Activities: supports onCreate(), onResume(), onPause(), onDestroy()
* Intents: you can pass data in a Bundle, launch another activities, etc.
* Fragments with transactions, etc.
* Views (you cannot create them from code, must be searched with findViewById, each HTML element is mapped to a View type, see table below; ListViews work with custom adapters)
* Menu and MenuItems
* AlertDialogs and ProgressDialogs
* Toasts
* SharedPreferences: implemented using HTML5's LocalStorage
* Handlers and Messages
* Log
* FloatMath
* SystemClock

This is a work in progress in continuous evolution. At Mobialia we used this library to port some of our Android apps to GWT (mainly http://chess.mobialia.com).
It's far from complete and very fitted to our needs, but we make it public in the hope that it will be useful for other developers.

It's released under the MIT License, so feel free to use it anywhere.

Demo Project
============

We include a demo project to see some usage examples. You can view this demo at:

http://mobialia.com/gwt_android_emu/

It's a GWT app coded like an Android App, you can see the MainActivity class and find the differences:

https://github.com/albertoruibal/gwt_android_emu/blob/master/demo/src/main/java/androidemu/demo/MainActivity.java

It includes two Activities with Strings and Layouts as resources and shows the usage of Menus, Toasts, AlertDialogs, etc.

Emulating Resources
===================

The emulated resources are a bit different, they aren't numeric IDs:

* R.id contains String IDs (and, as a consequence, menu IDs are also String)
* R.string and R.array contains methods that return Strings or String[]
* R.menu contains methods that return a Menu object
* R.layout contains methods that return GWT Widgets, read the next section

We included some tools in the package "utils" to help with resource emulation:

* ConvertStrings: to convert from ant Android XML file to GWT Class + Key-properties files.
* ConvertMenus: to convert from ant Android XML menu to a Menus class with methods to retrieve them.
* GenerateIds: to generate the Ids class (with all the ids as Strings) from the Android java source files.

Migrating Layouts
=================

Layouts must be redesigned in HTML with the GWT UiBinder (or HTML). In the demo project we show a sample.

HTML Elements to Android Widgets
================================

This is how this library maps the HTML elements to Android widgets: (see also the ViewFactory class)

| HTML Element               | Android Widget |
| ---------------------------|----------------|
| `<div class="ListView">`   | ListView       |
| `<div class="ScrollView">` | ScrollView     |
| `<div>`                    | TextView       |
| `<input type="text">`      | EditText       |
| `<input type="number">`    | EditText       |
| `<input type="password">`  | EditText       |
| `<input type="button">`    | Button         |
| `<input type="radio">`     | RadioButton    |
| `<input type="checkbox">`  | CheckBox       |
| `<input type="image">`     | ImageButton    |
| `<select>`                 | Spinner        |
| `<img>`                    | ImageView      |


