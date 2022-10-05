GWT Android Emu
===============
[![Release](https://jitpack.io/v/mobialia/gwt-android-emu.svg)](https://jitpack.io/#mobialia/gwt-android-emu)

GWT Android Emu helps to port Android Apps to HTML5 to make them work in any web browser keeping a lot of code in common.

It's also a framework to build web apps using Java an Android APIs.

Here is a running demo of the library:

http://mobialia.com/gwt-android-emu/

GWT is the perfect framework to migrate your Android Apps to the web because it compiles Java into Javascript.

The emulated Android APIs:

* Activities with their life cycle
* Services
* Intents: you can pass data in a Bundle, launch activities and services, etc.
* Handlers and Messages
* AlertDialogs, ProgressDialogs, Toasts...
* Views: It maps each HTML element to a View type, see table below
* ListViews with custom adapters
* RecyclerViews
* Menu and MenuItems (inflating menus from xml or from code)
* Fragments with transactions, ViewPager, DrawerLayout, etc. (emulating the v4 support library)
* AppCompatActivity (emulating the v7 support library)
* SharedPreferences: implemented using HTML5's LocalStorage
* Other utility classes: Log, FloatMath, SystemClock

The GWT entry point is a non-standard AndroidManifest class extending android.AndroidManifest.
This class specifies the default Activity and creates the Resources and the Application objects.

This is a work in progress in continuous evolution. At Mobialia we used this library to port some of our Android apps to GWT (like http://chess.mobialia.com).
It's far from complete and very fitted to our needs, but we make it public in the hope that it will be useful for other developers.

It's released under the MIT License, so feel free to use it anywhere.

Emulating Resources
===================

We included a tool "GenerateResources" in the package "utils" to help with resource emulation generation.

There is a usage sample in the demo project: the generate-resources.sh script converts the resources from the source android project in the /demo/source_android_project/ folder.

Generated files:

* <gwt_project_dir>/src/main/java/<package_folder>/R.java: with the resource IDs for menus, drawable, colors....
* <gwt_project_dir>/src/main/java/<package_folder>/res/Resources.java: mapping IDs to resources
* <gwt_project_dir>/src/main/java/<package_folder>/res/Strings.java
* <gwt_project_dir>/src/main/java/<package_folder>/res/Arrays.java
* <gwt_project_dir>/src/main/java/<package_folder>/res/Strings.properties with language variants
* <gwt_project_dir>/src/main/java/<package_folder>/res/Arrays.properties with language variants
* <gwt_project_dir>/src/main/java/<package_folder>/res/Menus.java

Generated from the GWT code:

* R.id.* for each id="xxxx" in the uibinder xml layouts at <gwt_project_dir>/src/main/resources/<package_folder>/res/layout/
* R.drawable.* for the images in <gwt_project_dir>/src/main/webapp/img/. Vector drawables can be used in SVG format.
* R.layout.* for each method in the <gwt_project_dir>/src/main/java/<package_folder>/res/Layouts.java class

Migrating Layouts
=================

Layouts must be redesigned in HTML with the GWT UiBinder.
You must create a "Layouts.java" class in the resource directory with a method to get each one of the GWT widgets.
There is a sample Layouts.java file in the demo project.

HTML Elements to Android Widgets
================================

This is how this library maps the HTML elements to Android widgets: (see also the ViewFactory class)

| HTML Element                | Android Widget |
| ----------------------------|----------------|
| `<div class="ListView">`    | ListView       |
| `<div class="RecyclerView">`| RecyclerView   |
| `<div class="ScrollView">`  | ScrollView     |
| `<div class="LinearLayout">`| LinearLayout   |
| `<div class="ViewPager">`   | ViewPager      |
| `<div>`                     | TextView       |
| `<textarea>`                | EditText       |
| `<input type="text">`       | EditText       |
| `<input type="number">`     | EditText       |
| `<input type="password">`   | EditText       |
| `<input type="button">`     | Button         |
| `<input type="radio">`      | RadioButton    |
| `<input type="checkbox">`   | CheckBox       |
| `<input type="image">`      | ImageButton    |
| `<select>`                  | Spinner        |
| `<img>`                     | ImageView      |


Using GWT Android Emu in other projects
=======================================
This library's JARs are distributed via Mobialia's bintray repo (https://bintray.com/mobialia/maven).
To use them, first include this repository in your gradle file:
```
repositories {
    maven { url 'https://jitpack.io' }
}
```
Then reference the gwt-android-emu libraries:
```
dependencies {
    implementation 'com.github.mobialia:gwt-android-emu:0.6.0'
    implementation 'com.github.mobialia:gwt-android-emu:0.6.0:sources'
}
```

Demo project
============

The demo project is a GWT app coded like an Android App, you can inspect the code at:k

https://github.com/mobialia/gwt-android-emu/blob/master/demo/src/main/java/android/demo/

It includes Activities with Fragments, a Drawer, emulated Strings, Layouts as resources, Menus, Toasts, AlertDialogs, etc.

Building the demo project
=========================
```
cd demo
./generate-resources.sh
../gradlew clean compileGwt
```

You can start a local webserver:
```
../gradlew appRun
```
and access with your web browser to http://localhost:8080/gwt-android-emu/