listless
========

Listless is a basic Android application for creating and managing simple lists of tasks. Although there are plans to add the functionality, there is currently no ordering of the lists or status of individual items on lists. Yes, it is that limited. Plus, I did the icons myself ... so that probably gives you some indication of how high the quality is.


Dependencies
------------

`listless` depends on the `thedroid` project. It expects to find it locally when generating the `ant` build for the mobile device. The relative location of the `thedroid` library is specified in the file `ant.properties`.

    # Note, you will need to change this to the location of the thedroid library on your system relative to this file.
    android.library.reference.1=../libs/thedroid


Installation
------------

### Configuration
Installing `listless` requires the Android SDK with the `android` executable on the `path`. Inside your `listless` checkout directory, type: 

    android update project --path . 

This will generate the `local.properties` and `proguard` configuration required. The `android update project` command also provides you with other project configuration options, such as the target Android OS. For a full list of configuration options, consult the [Android Developer Documentation](http://developer.android.com/tools/projects/projects-cmdline.html#UpdatingAProject). 

### Target Android OS

`listless` has been written to operate on any 2.2 OS of Android (Froyo). However, it is possible to build `listless` for a different target OS. As mentioned above, using `android update project` is the approach to specify the target OS. In order to see a list of OS that your Android tools currently support, type: 

    android list targets

The output of this will provide each supported OS with an `id`. This `id` should be specified as the value for the `--target` parameter in the `android update project` command. E.g.

    android update project --path . --target android-8


### Installation

If you have correctly linked to the `thedroid` library as shown above and configured your project correctly, then installation 'should' proceed. Installation and deployment to your system (connected device or running emulator) is achieved through the `ant` build tools. Specifically: 

    ant debug install

### Troubleshooting

Generating a build requires all involved projects to have a valid build script. If this is not the case, you will commonly see this error:

    sdk.dir is missing. Make sure to generate local.properties using 'android update project' or to inject it through an env var

This error can occur for either `listless` itself, or any dependent projects (such as `thedroid`). As the error message suggests, the solution is to execute `android update project` with the relevant parameters for the problem project. A full guide to updating projects via the command line can be found [here.](http://developer.android.com/tools/projects/projects-cmdline.html#UpdatingAProject)


Screenshots
-----------

![Demo screenshot](https://github.com/disquieting-silence/listless/raw/master/screenshots/demo.png)
