#!/bin/bash
SCRIPT=$(realpath $0)
SCRIPTPATH=$(dirname "$SCRIPT")

# First create the generate-resources-fat.jar binary (it will be needed only if it does not exists in the script folder)
cd $SCRIPTPATH
if [ ! -f $SCRIPTPATH/generate-resources-fat.jar ]; then
    echo "Building generate-resources-fat.jar"
    cd $SCRIPTPATH/../androidemu
    ../gradlew clean shadow
    cd $SCRIPTPATH
    cp $SCRIPTPATH/../androidemu/build/libs/generate-resources-fat.jar .
fi

# Then generate files
java -jar generate-resources-fat.jar android.demo $SCRIPTPATH $SCRIPTPATH/source_android_project
