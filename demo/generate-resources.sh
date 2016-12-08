#!/bin/bash
SCRIPT=$(realpath $0)
SCRIPTPATH=$(dirname "$SCRIPT")

# First create the generate-resources.jar binary (it will be needed only if it does not exists in the script folder)
cd $SCRIPTPATH
if [ ! -f $SCRIPTPATH/generate-resources.jar ]; then
    echo "Building generate-resources.jar"
    cd $SCRIPTPATH/../androidemu
    gradle clean shadow
    cd $SCRIPTPATH
    cp $SCRIPTPATH/../androidemu/build/libs/generate-resources.jar .
fi

# Then generate files
java -jar generate-resources.jar android.demo $SCRIPTPATH $SCRIPTPATH/source_android_project
