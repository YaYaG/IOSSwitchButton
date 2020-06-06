# 安卓仿IOS switchButton

## 效果图 

<img src="https://raw.githubusercontent.com/YaYaG/IOSSwitchButton/master/img/cb.png" width="120" height="120" align="middle" />


##布局写法
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    tools:context=".MainActivity">

    <com.jackwang.ioscheck.IOSSwitchButton
        android:id="@+id/ios_switch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:check="true"
        />

    <com.jackwang.ioscheck.IOSSwitchButton
        android:id="@+id/ios_switch2"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginTop="10dp"
        android:clickable="false"
        app:switchColor="#FFF"
        app:check_color="#2196F3"
        app:uncheck_color="#DDDDDD"
        app:check="true"
        app:disable="false"
        />

</LinearLayout>
```

# IOSSwitchButton
