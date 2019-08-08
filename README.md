# PieProgress
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![](https://jitpack.io/v/tizisdeepan/pieprogress.svg)](https://jitpack.io/#tizisdeepan/pieprogress)

<img src="./Screenshots/ss3.gif" width="100%">

# What is Pie Progress?
Pie Progress is a type of circular progress view similar to the one that you can find in iOS devices while updating applications. It's a simple to use library where the progress gets updated by passing values to a single method.

## Implementation
### [1] In your app module gradle file
```gradle
dependencies {
    implementation 'com.github.tizisdeepan:pieprogress:1.0.0'
}
```

### [2] In your project level gradle file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

### [3] Use PieProgress in your layout.xml
```xml
<com.deepan.pieprogress.PieProgress
            android:id="@+id/pieProgress"
            android:layout_width="100dp"
            android:layout_height="100dp"
            app:progressColor="@color/white"/>
```

### [4] Set progress of PieProgress from your activity
```kotlin
    //setProgress(progress: Float)
    pieProgress.setProgress(progress)
```

Voila! You have implemented an awesome Pie Progress for your Android Project now!

Developed By
------------

* Deepan Elango - <tizisdeepan@gmail.com>

<a href="https://twitter.com/tizisdeepan">
  <img alt="Follow me on Twitter" src="./Screenshots/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/tizisdeepan/">
  <img alt="Add me to Linkedin" src="./Screenshots/linkedin.png" />
</a>
