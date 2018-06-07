package xinyi.com.select;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import xinyi.com.select.more.SelectMoreActivity;
import xinyi.com.select.more.bean.MoreItem;

public class MoreActivity extends SelectMoreActivity {
    @Override
    public List<MoreItem> getList() {
        List<MoreItem> addList = new ArrayList<>();
        String s = getDataFromSp("more");
        if (s==null||s.equals("")) {
            for (int i = 0; i <= 34; i++) {
                MoreItem mainItem1 = new MoreItem();
                mainItem1.setName("测试数据" + i);
                mainItem1.setSelect(false);
                mainItem1.setPostion(i);
                mainItem1.setIconUrl("图片url");
                if (i % 5 == 0) {
                    mainItem1.setUiType(0);
                }
                addList.add(mainItem1);
            }
        }else {
            addList.addAll(com.alibaba.fastjson.JSONArray.parseArray(s,MoreItem.class));
        }
        return addList;
    }

    public String getDataFromSp(String key) {
        SharedPreferences sp = getSharedPreferences("selectMore", MODE_PRIVATE);
        return sp.getString(key, "");
    }

}
