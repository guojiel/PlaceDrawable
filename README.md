# PlaceDrawable
## 介绍
占位drawable
## 使用
### gradle配置
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  	dependencies {
	        implementation 'com.github.guojiel:PlaceDrawable:1.0.0'
	}
  
### 代码使用

#### 1.drawable加载器
        ......
        PlaceDrawable.setDrawableLoader(new DrawableLoader() {
            @Override
            public void loadDrawable(String url, final Listener listener) {
                Drawable drawable = ...
                listener.drawable(drawable);
            }
        });
        ......
        
#### 2.xml
    ......   
    <com.guojiel.library.placedrawable.PlaceDrawableTextView
        android:id="@+id/mTv"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:gravity="center_vertical"
        android:textColor="#000000"
        android:textSize="16sp"/>
    ......
       
#### 3.code
        ......
        public void onClick(View v) {
            PlaceDrawable drawable = new PlaceDrawable();
            drawable.setBounds(0,0,100,100);
            drawable.setUrl("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2896419389,2357132417&fm=26&gp=0.jpg");
            ImageSpan span = new ImageSpan(drawable);
            SpannableString spannable = new SpannableString("左骚: 一个小小小~s1234567890123456789");
            spannable.setSpan(span, 12, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTv.setText(spannable);
        }
        ......
