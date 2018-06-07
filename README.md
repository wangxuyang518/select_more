# select_more
仿照支付宝选择更多item

select module 库里面封装了选择应用管理的业务逻辑。包括xml布局，图片，drawable资源

## 类介绍：
select_module 内置 MoreItem的 数据类
```java
public class MoreItem {
    private String name = "";//item下面的名称
    private String iconUrl = "";//item的图片的url。url不存在或者为null 就会加载iconLocal
    private int iconLocal = R.mipmap.ic_launcher;//item 本地icon
    private boolean isShow = false;//是否显示编辑状态的ui
    private boolean isSelect = false;//item 是否被选中
    private int uiType = 1;//0表示item上面的标题布局   1表示item的图片加文字布局
    private int postion;//用于保存选择的item的顺序
}
```
IMoreSelect 接口 用来将item的数据源传入到SelectMoreActivity:
```java
public interface IMoreSelect {
    List<MoreItem> getList();
    //获取最初的List
}
```

## 使用：
继承SelectMoreActivity 重写getList()方法：例如

```java
public class MoreActivity extends SelectMoreActivity {
    @Override
    public List<MoreItem> getList() {
        List<MoreItem> addList = new ArrayList<>();
        for (int i = 0; i <= 34; i++) {
            MoreItem mainItem1 = new MoreItem();
            mainItem1.setName("测试数据" + i);
            mainItem1.setSelect(false);
            mainItem1.setPostion(i);
            mainItem1.setIconUrl("图片url");
            if (i % 5 == 0) {
                mainItem1.setUiType(0);
            }
            if (i % 7 == 0 && i != 0) {
                mainItem1.setSelect(true);
                mainItem1.setUiType(1);
            }
            addList.add(mainItem1);
        }
        return addList;
    }
}
```
**注意：**
setSelect 表示此item 被选中

setPostion 给item设置 一个position，用于后面保存选择的item的位置信息

setUiType 区分item的layout布局，0 表示只有一个title布局，1表示图片+文字+右上角小图标

setIconLocal 默认图片

setIconUrl 网络小图标


## 点击完成的保存动作，内置代码已经完成。
```java

   //点击TextView 完成 会自动将 选择好的应用的顺序保存起来，以及保存所以item的数据
    public void saveDataBySp(String content, String order) {
        SharedPreferences sp = getSharedPreferences("selectMore", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("more", content);//改变之后的状态保存起来
        editor.putString("order", order);//选择的item的顺序排列
        editor.commit();
    }
```
所以：
得到所有的item的状态可以通过 sp.getString("more")之后强转成List<MoreItem>即可。
    
getList 方法返回一个List即可，假如这个List事先设置了选中的项，需要这些项的postion依次放进一个List中，然后保存在Sp中，key为“order”
