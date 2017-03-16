#!/usr/bin/env bash

mvn -DskipTests=true clean install
cf cs google-stackdriver-trace default proxy-stackdriver-trace
cf push
