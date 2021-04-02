#/bin/bash

for folder in *; do
    # Is folder
    if [ -d "$folder" ]; then
        echo $folder
        # for sub in "$folder/src/problem*"; do
        #     echo "  " $sub
        #     # mv $sub $folder/java
        # done

        if [ -d "$folder/src" ]; do

        done
    fi
done


find . -type d -empty