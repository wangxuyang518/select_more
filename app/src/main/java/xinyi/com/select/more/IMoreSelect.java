package xinyi.com.select.more;

import java.util.List;

import xinyi.com.select.more.bean.MoreItem;

public interface  IMoreSelect<T extends MoreItem> {

    //获取最初的List
    List<T>getList();


     void saveChangedData( List<T> allList,List<T> selectList);

}
