#!/usr/bin/env bash

set -e
app_name=onteon-demo-app-shopping-list-graalvm

SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
BUILD_SH_SOURCE_DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

mkdir -p ${BUILD_SH_SOURCE_DIR}/libs
pushd ${BUILD_SH_SOURCE_DIR}/libs

if [ ! -d "${BUILD_SH_SOURCE_DIR}/libs/graalvm" ]; then
  echo ""
  echo "graalvm jdk"
  wget https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.3.0/graalvm-ce-java11-linux-amd64-21.3.0.tar.gz -O graalvm.tar.gz
  tar -zxvf graalvm.tar.gz
  rm graalvm.tar.gz
  mv graalvm* graalvm
  ./graalvm/bin/gu install native-image
fi

pushd ${BUILD_SH_SOURCE_DIR}
JAVA_HOME=${BUILD_SH_SOURCE_DIR}/libs/graalvm mvn clean -Pnative -DskipTests package

pushd ${BUILD_SH_SOURCE_DIR}/target
mkdir -p tar/bin
mkdir -p tar/conf
cp ${app_name} tar/bin
cp ../conf.yml tar/conf

pushd ${BUILD_SH_SOURCE_DIR}/target/tar
tar -czvf "../${app_name}.tar.gz" *
