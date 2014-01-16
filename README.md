##DEPRECATED

About 24 hours after publishing this library, I found out that a regular `LinearLayout` with `measureWithLargestChild="true"` can indeed be used to achieve the desired result. Here is an example of how this can be done:

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:measureWithLargestChild="true"
    android:layout_gravity="center_horizontal"
    >

    <Button
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:text="OK" />

    <Button
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:text="LargerText" />

</LinearLayout>
```

The key here is the `layout_gravity="center_horizontal"` part.

This library is thus deprecated. I don't expect to maintain it any further, unless I find really compelling reason to keep it alive.


##android-equiwidth-horizontal-layout

A horizontal linear layout in which the children take up equal width - without necessarily filling up the entire width. This screenshot sums up the reason for using this library:

![Here is a screenshot from the sample app][1]


###The Problem

In Android you often want to show a few buttons (probably up to three) in a horizontal `LinearLayout`. However, the text on the button would be varying in width. There are two ways to handle this:

  1. Use `wrap_content` as the widths for the buttons, and probably `center_horizontal` the entire layout. This looks ugly if the width difference of the texts is large.
  2. Use `0dp` as the width and a `layout_weight="1"` for the buttons. The buttons end up taking up the entire width of the layout. This is uglier in landscape mode, particularly on tablets.
  
Note that it is possible to use a combination of `weightSum` on the parent layout and individual weights on the buttons to overcome this. However, this is pretty much a "case-by-case-basis" solution.
 

###The Solution

The solution is to have a layout that does the following:

> Set the width of each child to be equal to the width of the largest child.

That's exactly what this library does - in the form of an extension to `LinearLayout` - namely: `EquiwidthHorizontalLinearLayout` (I know its quite a mouthful - sorry about that!)


###Usage

Just use `com.github.curioustechizen.equiwhl.EquiwidthHorizontalLinearLayout` instead of a regular horizontal `LinearLayout`. See the sample app for more details.

You can either add the Android library project `android-equiwidth-horizontal-layout` as a dependency or just directly download a JAR file from [here](jars/).


###Limitations

  - The library does not perform any validation - so for example if you use it with a `orientation="vertical"` I have no idea what to expect.
  - The `onMeasure` phase uses 2 passes. This could quickly run into a performance bottleneck if you have a complex layout nested inside of the `EquiwidthHorizontalLinearLayout`. I would recommend to use it only for simple use cases like buttons and the like.
  

###Contributing

The library is not exactly battle-tested - so, contributions are as always welcome. 

[1]: screenshots/sample.png "screenshot.png"
