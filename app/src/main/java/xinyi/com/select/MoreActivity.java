package xinyi.com.select;

import java.util.ArrayList;
import java.util.List;

import xinyi.com.select.more.SelectMoreActivity;
import xinyi.com.select.more.bean.MoreItem;

public class MoreActivity extends SelectMoreActivity {
    @Override
    public List<MoreItem> getList() {
        List<MoreItem> addList = new ArrayList<>();
        for (int i = 0; i <= 34; i++) {
            MoreItem mainItem1 = new MoreItem();
            mainItem1.setName("测试数据" + i);
            mainItem1.setSelect(false);
            mainItem1.setPostion(i);
            mainItem1.setIconUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528282856645&di=6369c4ab8293679ee7cca027c3ad9111&imgtype=0&src=http%3A%2F%2Fimages.vectorhq.com%2Fimages%2Fpremium%2Fpreviews%2F170%2Fpencil-with-ruler-icon_170450339.jpg");
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
