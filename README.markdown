listless
========

Listless is a basic Android application for creating and managing simple lists of tasks. Although there are plans to add the functionality, there is currently no ordering of the lists or status of individual items on lists. Yes, it is that limited. Plus, I did the icons myself ... so that probably gives you some indication of how high the quality is.


Dependencies
------------

`listless` depends on the `thedroid` project. It expects to find it locally when generating the `ant` build for the mobile device. The relative location of the `thedroid` library is specified in the file `ant.properties`.

    # Note, you will need to change this to the location of the thedroid library on your system relative to this file.
    android.library.reference.1=../libs/thedroid


Screenshots
-----------

![Demo screenshot](https://github.com/disquieting-silence/listless/raw/master/screenshots/demo.png)