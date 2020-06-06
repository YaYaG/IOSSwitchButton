# 安卓仿IOS switchButton

## 效果图 

<img src="https://raw.githubusercontent.com/YaYaG/IOSSwitchButton/master/img/cb.png" width="142" height="127" align="middle" />

## 引入依赖 App依赖：

### app项目中build.gradle添加：

```
    implementation 'com.yayaG.iosSwitchButton:iosswitchbutton:1.0.3'
    
```

### 在主项目中的build.gradle添加

```
    maven { url 'https://dl.bintray.com/wangjinya/maven' }
```

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://maven.google.com' }
        maven { url 'https://dl.bintray.com/wangjinya/maven' }
    }
}

```

### 在 maven 中配置如下

```
    <dependency>
    	<groupId>com.yayaG.iosSwitchButton</groupId>
    	<artifactId>iosswitchbutton</artifactId>
    	<version>1.0.3</version>
    	<type>pom</type>
    </dependency>
```

## 属性含义：
```
check : 是否开启
switchColor ：switch圆形颜色
check_color ： 选中颜色
uncheck_color ：未选中颜色
disable ：禁止切换
```

## 布局写法
```
<com.jackwang.ioscheck.IOSSwitchButton
        android:id="@+id/ios_switch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:check="true"
        />
```


```
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
```
#### 监听: 
```
iosSwitchButton.setSwitchListener(new SwitchListener() {
            @Override
            public void changeCheck(boolean check) {
                Toast.makeText(MainActivity.this, check ? "开启" : "关闭", Toast.LENGTH_SHORT).show();
            }
        });
```