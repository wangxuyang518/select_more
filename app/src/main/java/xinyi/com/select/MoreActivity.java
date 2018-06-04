package xinyi.com.select;

import java.util.ArrayList;
import java.util.List;

import xinyi.com.select.more.SelectMoreActivity;

public class MoreActivity extends SelectMoreActivity<MainItem1> {
    @Override
    public List<MainItem1> getList() {
        List<MainItem1> addList = new ArrayList<>();
        for (int i = 0; i <= 34; i++) {
            MainItem1 mainItem1 = new MainItem1();
            mainItem1.setName("测试数据" + i);
            mainItem1.setSelect(false);
            mainItem1.setPostion(i);
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

    @Override
    public void saveChangedData(List<MainItem1> allList, List<MainItem1> selectList) {

    }

}
