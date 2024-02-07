#!/bin/bash
cd ./samples
java -cp ../libs/msgcodec-generate-1.4.jar MessageGenerate -xml ./xml/TestMessagesFileSet.xml
cd ../
