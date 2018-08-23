#!/bin/bash
path="/home/mdt-worker/poc"
rm -rf  $path/compile
rm -rf $path/work
mkdir -p $path/compile
mkdir -p $path/work
cd $path/compile
git clone https://github.com/dhirajdc/mdt-test-rig.git
cd $path/compile/mdt-test-rig/mdtpoc
mvn clean
mvn package
cp $path/compile/mdt-test-rig/mdtpoc/target/mdt-poc-1.0-SNAPSHOT-jar-with-dependencies.jar $path/compile/mdt-test-rig/notebooks/* $path/compile/mdt-test-rig/scripts/* $path/work/
cd $path/work
chmod 777 *.sh
