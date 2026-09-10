a#!/bin/bash

##

sourceDirectory="../"

##

reset

clear

##

set -e

set -x

##

echo
echo "start cascade template design"
echo

##

cd .cascade

bash cascade.bash
