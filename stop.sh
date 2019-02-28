#!/bin/sh

###########################
## Shell script to clone a list of github repositories and run `mvn clean install` on each one of them
##
## Usage: put a file called `repos.txt` in the folder of the script containing the repository URLs, line by line:
##
##https://github.com/pulse00/Composer-Eclipse-Plugin.git
##git@github.com:pulse00/Symfony-2-Eclipse-Plugin.git
##etc
###########################

docker-compose down
