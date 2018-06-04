package xinyi.com.select.more.recycleview;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import xinyi.com.select.R;
import xinyi.com.select.more.recycleview.divider.HorizontalDividerItemDecoration;
import xinyi.com.select.more.recycleview.divider.VerticalDividerItemDecoration;



/**
 * Created by long on 2016/3/30.
 * 视图帮助类
 */
public class RecyclerViewHelper {

    private RecyclerViewHelper() {
        throw new RuntimeException("RecyclerViewHelper cannot be initialized!");
    }


    public static LinearLayoutManager initRecyclerViewV(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.comm_grey300)
                    .showLastDivider()
                    .build());
        }
        view.setAdapter(adapter);
        return layoutManager;
    }

    /**
     * 配置水平列表RecyclerView
     */
    public static LinearLayoutManager initRecyclerViewH(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(view.getContext()).colorResId(R.color.comm_grey300).build());
        }
        view.setAdapter(adapter);
        return layoutManager;
    }

    /**
     * 配置网格列表RecyclerView
     */
    public static GridLayoutManager initRecyclerViewG(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter, final int column, HorizontalDividerItemDecoration horizontalDividerItemDecoration, VerticalDividerItemDecoration verticalDividerItemDecoration) {
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), column);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(horizontalDividerItemDecoration);
            view.addItemDecoration(verticalDividerItemDecoration);
        }
        view.setAdapter(adapter);
        return layoutManager;
    }

    /**
     * 配置瀑布流列表RecyclerView
     */
    public static StaggeredGridLayoutManager initRecyclerViewSV(RecyclerView view, boolean isDivided,
                                                                RecyclerView.Adapter adapter, int column) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.set(4, 4, 4, 4);
                }
            });
        }
        view.setAdapter(adapter);
        return layoutManager;
    }

}
