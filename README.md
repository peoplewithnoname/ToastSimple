# ToastSimple
## 使用简单 操作方便

#### 1.在最外层的 build.gradle 里面添加如下代码

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### 2.在要使用的module的 build.gradle 里面添加如下代码

```
	dependencies {
	        implementation 'com.github.peoplewithnoname:ToastSimple:1.0.0'
	}
```

### 3.直接在调用如下两个方法即可

```
   public static ToastWindowManager makeText(Context context, String text, long duration)

   public static ToastWindowManager makeText(Context context, View view, long duration)
```

[点我查看效果](https://blog.csdn.net/qq_16592085/article/details/110561466)