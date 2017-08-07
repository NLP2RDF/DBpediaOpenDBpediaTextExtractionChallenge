#!/bin/bash

function failure()
{
    echo "$@" >&2
    exit 1
}

if [ -z "$1" ]
  then
    echo "You need to specify the first argument which is a language (en|nl|de)."
    exit 1
fi

if [ -z "$2" ]
  then
    echo "You need to specify the second argument which is a version of the actual dbpedie."
    exit 1
fi

cd Core
if [ ! -f ../application.$1.conf ]; then
    echo "The config file not found!"
    exit 1
fi
cd ..

export LHD_DBPEDIA_VERSION=$2

service memcached start

(cd Downloader; mvn scala:run -DaddArgs=../application.$1.conf) || failure "Failed to download all datasets"

(cd Pipeline; mvn scala:run -DaddArgs="../application.$1.conf|remove-all") || failure "LHD Generation Failed"

echo "++ LHD EXTRACTION PROCESS HAS BEEN SUCCESSFUL ++"