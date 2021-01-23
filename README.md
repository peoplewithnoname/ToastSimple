# ToastSimple
## 使用简单 操作方便

### 注：本Toast基于Activity的   所以无法跨Activity显示

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
	        implementation 'com.github.peoplewithnoname:ToastSimple:1.0.4'
	}
```

### 3.直接在调用如下方法即可

```
   private Toast toast;//先声明
   toast = new Toast(this, getLifecycle());//初始化

   private int i = 0;
   toast.makeText(i + " 自定义的普通Toast", Toast.LENGTH_LONG).showMessage();//显示默认的
   toast.makeText(i + " 自定义的警告Toast", Toast.LENGTH_LONG).showWarm();//显示警告的
   toast.makeText(i + " 自定义的错误Toast", Toast.LENGTH_LONG).showError();//显示错误的
   toast.makeText(i + " 自定义的成功Toast", Toast.LENGTH_LONG).showSuccess();//显示成功的

```

```
   //初始化为可选 Toast.init
   public void init(int tipID, int warmID, int errorID, int successID)

```

[点我查看效果](https://blog.csdn.net/qq_16592085/article/details/110561466)