#!/bin/bash

# Save current dir
DIR=$(pwd)

# Move to directory where files will be generated
cd src/main/java/android/demo

# Generate files
java -cp ~/.m2/repository/com/mobialia/androidemu/0.1/androidemu-0.1.jar android.utils.GenerateResources android.demo $DIR/source_android_project

cd $DIR

# Move properties files
mv src/main/java/android/demo/res/*.properties src/main/resources/android/demo/res
