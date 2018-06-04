package xinyi.com.select.more.bean;

import xinyi.com.select.R;

/**
 * 选择更多  item的属性基类
 */
public class MoreItem {

    private String name="";//item下面的名称
    private String iconUrl="";//item的图片的url
    private int  iconLocal= R.mipmap.ic_launcher;//item 本地icon
    private boolean isShow=false;//是否显示编辑状态的ui
    private boolean isSelect=false;//item 是否被选中
    private int uiType=1;//0表示item上面的标题布局   1表示item的图片加文字布局
    private int postion;
    public String getName() {
        return name;
    }

    public MoreItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public MoreItem setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }

    public int getIconLocal() {
        return iconLocal;
    }

    public MoreItem setIconLocal(int iconLocal) {
        this.iconLocal = iconLocal;
        return this;
    }

    public boolean isShow() {
        return isShow;
    }

    public MoreItem setShow(boolean show) {
        isShow = show;
        return this;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public MoreItem setSelect(boolean select) {
        isSelect = select;
        return this;
    }

    public int getUiType() {
        return uiType;
    }

    public MoreItem setUiType(int uiType) {
        this.uiType = uiType;
        return this;
    }

    public int getPostion() {
        return postion;
    }

    public MoreItem setPostion(int postion) {
        this.postion = postion;
        return this;
    }
}
