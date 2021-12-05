## What's Included

A number of third party dependencies are included in this template. 
They are also documented inside the [documentation folder](/documentation).
The files inside this documentation folder are written in such a way that you can keep them in your real project,
to let team members read up on why dependencies are included and how they work.

*The dependencies in the template include:
* [Ktlint](/documentation/StaticAnalysis.md) for formatting.

*the first  breed is selected by default to make the first dog request+

*run unit test using gradle test

## Assumptions
/**
* there is a bug on v1/breeds , it return the wrong breeds ids
* instead of rblu, ycho, abys it return  1,2,3
* calling v1/images/search with the wrong ids return single dog
* here we repeat the call 15 until they fixed to fill the grid images
* when they fixed the ids bug you can safely remove the repeat
* keep track https://api.TheDogAPI.com/v1/images/search?breed_ids=beng
  *this bug is not found on thecatapi
  */