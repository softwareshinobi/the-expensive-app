#!/bin/bash

##

set -e

set -x

##

reset

clear


##


previousDir=`pwd`

cd src/main/resources/templates/

bash cascade.bash

cd $previousDir

##

docker compose down --remove-orphans

docker compose up --build -d
