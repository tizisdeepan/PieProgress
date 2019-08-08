# PieProgress
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

<table>
    <tr><td align="center"><img src="https://github.com/tizisdeepan/pieprogress/blob/master/Screenshots/ss1.png" alt="Single Selection Mode" width="100%"></td>
    <td align="center"><img src="https://github.com/tizisdeepan/pieprogress/blob/master/Screenshots/ss2.png" alt="Range Selection Mode" width="100%"></td>
</table>

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
