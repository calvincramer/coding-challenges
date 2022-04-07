#!/bin/bash
exe_name="719.exe"

g++ \
    719.cpp \
    -O2 -static -std=c++14 \
    -o ${exe_name}

if [ ! -f ${exe_name} ]; then
    exit 2
fi

strip --strip-all --strip-unneeded ${exe_name}

./${exe_name}
