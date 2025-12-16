#!/bin/sh
# Gradle wrapper script - downloads and runs Gradle

set -e

# Determine the Java command to use to start the JVM
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

# Increase the maximum file descriptors if we can
MAX_FD="maximum"

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}

APP_ARGS=$(save "$@")

# Use Gradle wrapper properties
GRADLE_VERSION="8.5"
GRADLE_DIST_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_USER_HOME="${GRADLE_USER_HOME:-$HOME/.gradle}"
GRADLE_APP_HOME="$GRADLE_USER_HOME/wrapper/dists/gradle-${GRADLE_VERSION}-bin"

# Download and extract Gradle if needed
if [ ! -d "$GRADLE_APP_HOME" ]; then
    echo "Downloading Gradle $GRADLE_VERSION..."
    mkdir -p "$GRADLE_APP_HOME"
    wget -q "$GRADLE_DIST_URL" -O /tmp/gradle.zip
    unzip -q /tmp/gradle.zip -d "$GRADLE_USER_HOME/wrapper/dists/"
    rm /tmp/gradle.zip
fi

GRADLE_HOME=$(find "$GRADLE_USER_HOME/wrapper/dists" -name "gradle-${GRADLE_VERSION}" -type d | head -n 1)

# Execute Gradle
exec "$JAVACMD" $JAVA_OPTS -Dorg.gradle.appname=gradlew -classpath "$GRADLE_HOME/lib/*" org.gradle.wrapper.GradleWrapperMain "$@"
