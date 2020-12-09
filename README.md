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
	        implementation 'com.github.peoplewithnoname:ToastSimple:1.0.2'
	}
```

### 3.直接在调用如下方法即可

```
   private int i = 0;
   Toast.makeText(MainActivity.this, i + " 自定义的普通Toast", Toast.LENGTH_LONG).showMessage();
   Toast.makeText(MainActivity.this, i + " 自定义的警告Toast", Toast.LENGTH_LONG).showWarm();
   Toast.makeText(MainActivity.this, i + " 自定义的错误Toast", Toast.LENGTH_LONG).showError();
   Toast.makeText(MainActivity.this, i + " 自定义的成功Toast", Toast.LENGTH_LONG).showSuccess();

```

```
   //初始化为可选 Toast.init
   public static void init(int tipID, int warmID, int errorID, int successID)

```

## 1.0.1 开始去掉自定义View的Toast

[点我查看效果](https://blog.csdn.net/qq_16592085/article/details/110561466)