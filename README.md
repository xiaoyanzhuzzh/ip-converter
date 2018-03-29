# IP Converter

## Pre requisites
Please make sure your desktop has `sbt` installed beforehand.

For install `sbt`, refer to: https://www.scala-sbt.org/1.0/docs/Installing-sbt-on-Mac.html

## Running
Run sbt in your project directory with no arguments: `sbt` (it will start a sbt console).

And then run `compile` and `run 172.168.5.1` to compile and start your project.
You can run `exit` to exit the sbt console.

## Implementation Details
1. Using Regex to validate the input IP
2. Convert each decimal to 8-bit binary
3. Contact the binary to 32-bit binary
4. Convert 32-bit binary to decimal
