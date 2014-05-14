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

The GWT entry point is a "AndroidManifest" class extending android.BaseAndroidManifest in this class contains the default Activity and the ResourceResolver (specific for this library).

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

We included a tool "GenerateResources" in the package "utils" to help with resource emulation generation.
There is a usage sample in the demo project: the generate_resources.sh script convert the resources from the source_android_project/res/ folder.

The emulated resources generated automatically are:

* R.id (searching for @+id/ in the xml files)
* R.string supports multiple languages, generates multiple GWT properties file, one for each language  
* R.array (also with i18n)
* R.menu
* R.layout from the "Layouts" class, see next section

This tools also generates the ResourceResolver to convert from numeric ids to strings, layouts, etc.

Migrating Layouts
=================

Layouts must be redesigned in HTML with the GWT UiBinder (or HTML).
You must create a "Layouts.java" class in the resource directory with a method to get each one of the GWT widgets.
The GenerateResources tool creates a R.layout.xxx entry for each one of the methods in the Layouts class.

There is a Layouts.java demo file in the demo project.

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


