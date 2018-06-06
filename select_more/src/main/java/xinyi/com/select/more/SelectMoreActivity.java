package xinyi.com.select.more;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xinyi.com.select.more.adapter.MoreSelectAdapter;
import xinyi.com.select.more.adapter.RecycleItemTouchHelper;
import xinyi.com.select.more.bean.MoreItem;
import xinyi.com.select.more.recycleview.RecyclerViewHelper;
import xinyi.com.select.more.recycleview.divider.HorizontalDividerItemDecoration;
import xinyi.com.select.more.recycleview.divider.VerticalDividerItemDecoration;

/**
 * SelectActivity<MainItem1>.class
 * 选择更多
 */
public abstract class SelectMoreActivity extends AppCompatActivity implements IMoreSelect {

    private ImageView mBack;//
    private TextView mTextView;//编辑
    private RecyclerView selectRecyclerView;//选中的recycleView
    private RecyclerView allRecycleView;//所以选择的recycleView

    private List<MoreItem> selectDataList = new ArrayList<>();//选中的data
    private List<MoreItem> allSelectDataList = new ArrayList<>();//所以的item的data

    private MoreSelectAdapter selectAdapter;//选中的item
    private MoreSelectAdapter allSelectAdapter;//所有的adapter
    private RecycleItemTouchHelper itemTouch;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        mBack = findViewById(R.id.mBack);
        mTextView = findViewById(R.id.mTextView);
        selectRecyclerView = findViewById(R.id.selectRecyclerView);
        allRecycleView = findViewById(R.id.allRecycleView);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRecycleView();
        initText();
    }

    //编辑按钮
    protected void initText() {
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bl = false;
                if (mTextView.getText().equals("完成")) {
                    mTextView.setText("编辑");
                    itemTouch.isLongPress = false;
                    bl = false;
                    //保存所有item的状态，保存当前的select的顺序

                    String s = JSONObject.toJSONString(allSelectDataList);
                    //保存当前选中的应用的顺序
                    List<String> orders = new ArrayList<>();
                    for (MoreItem m : selectDataList
                            ) {
                        orders.add("" + m.getPostion());
                    }
                    String order = JSONObject.toJSONString(orders);
                    saveDataBySp(s, order);
                } else {
                    itemTouch.isLongPress = true;
                    mTextView.setText("完成");
                    bl = true;
                }
                for (MoreItem t : allSelectDataList
                        ) {
                    t.setShow(bl);
                }
                selectAdapter.notifyDataSetChanged();
                allSelectAdapter.notifyDataSetChanged();
            }
        });
    }

    public void initRecycleView() {
        allSelectDataList.clear();
        allSelectDataList.addAll(getList());
        for (MoreItem t : allSelectDataList
                ) {
            if (t.isSelect()) {
                t.setUiType(1);
                selectDataList.add(t);
            }
        }
        selectAdapter = new MoreSelectAdapter(selectDataList);
        allSelectAdapter = new MoreSelectAdapter(allSelectDataList);

        selectAdapter.setmOnItemLongClickListener(new MoreSelectAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(MoreSelectAdapter adapter, View view, int position) {
                if (mTextView.getText().equals("编辑"))
                    mTextView.performClick();
                else
                    return false;
                return false;
            }
        });

        HorizontalDividerItemDecoration horizontalDividerItemDecoration = new HorizontalDividerItemDecoration.Builder(SelectMoreActivity.this).drawable(R.drawable.divider_decoration).build();
        VerticalDividerItemDecoration verticalDividerItemDecoration = new VerticalDividerItemDecoration.Builder(SelectMoreActivity.this).drawable(R.drawable.divider_decoration).build();
        RecyclerViewHelper.initRecyclerViewG(selectRecyclerView, true, selectAdapter,
                4, horizontalDividerItemDecoration, verticalDividerItemDecoration);
        layoutManager = RecyclerViewHelper.initRecyclerViewG(allRecycleView, true, allSelectAdapter,
                4, horizontalDividerItemDecoration, verticalDividerItemDecoration);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                MoreItem t = allSelectDataList.get(position);
                if (t.getUiType() == 0) {
                    return layoutManager.getSpanCount();
                } else {
                    return 1;
                }

            }
        });
        //已选的RecycleView的滑动设置
        itemTouch = new RecycleItemTouchHelper(selectAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(itemTouch);
        helper.attachToRecyclerView(selectRecyclerView);
    }


    //点击小图标，更新ui
    public void updateSelectApp(MoreItem t) {
        if (!t.isSelect()) {
            selectDataList.add(t);
        } else {
            selectDataList.remove(t);
        }
        t.setSelect(!t.isSelect());
        allSelectAdapter.notifyDataSetChanged();
        selectAdapter.notifyDataSetChanged();
    }


    public void saveDataBySp(String content, String order) {
        SharedPreferences sp = getSharedPreferences("selectMore", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("more", content);//改变之后的状态保存起来
        editor.putString("order", order);//选择的item的顺序排列
        editor.commit();
    }
}
