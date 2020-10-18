# Medium Article 
https://medium.com/@volsahin/android-devs-lets-generate-our-own-strings-json-with-a-gradle-plugin-ecab36beb4a

# Gradle String Generator 
The idea is to show how to create a gradle plugin which makes class generation in build time.

Let's say we have a strings.json under assets intead of strings.xml under res folder.

So we want to use our json like R.string.blabla, this project generates a R class for our custom json

# Warning
This project uses local maven.

So at first there are no local maven plugin available for you. You need to do two things.

<b>./gradlew publishToMavenLocal </b>

<b>Uncomment plugin lines inside build.gradle(:app)</b>

# Summary
<p align="center">
  <img src="https://github.com/volsahin/string-generator-plugin/blob/master/assets/pluginsample.png">
</p>
<p align="center">
  <img src="https://github.com/volsahin/string-generator-plugin/blob/master/assets/stringsjson.png">
</p>
<p align="center">
  <img src="https://github.com/volsahin/string-generator-plugin/blob/master/assets/generatedr.png">
</p>
<p align="center">
  <img src="https://github.com/volsahin/string-generator-plugin/blob/master/assets/finalstate.png">
</p>
