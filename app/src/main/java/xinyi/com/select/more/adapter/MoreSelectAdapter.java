package xinyi.com.select.more.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xinyi.com.select.R;
import xinyi.com.select.more.SelectMoreActivity;
import xinyi.com.select.more.bean.MoreItem;

/**
 * 更多选项
 */
public class MoreSelectAdapter<T extends MoreItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> dataSource;
    public static final int TYPE_UI_TITLE = 0;
    public static final int TYPE_UI_ITEM = 1;

    private OnItemLongClickListener mOnItemLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    public MoreSelectAdapter(List<T> dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建不同的 ViewHolder
        View view;
        if (viewType == TYPE_UI_TITLE) {
            view = View.inflate(parent.getContext(), R.layout.item_more_ui_title, null);
            return new MoreSelectTitleHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_more_ui_item, null);
            return new MoreSelectItemHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        final T t = dataSource.get(position);
        if (getItemViewType(position) == 0) {
            MoreSelectTitleHolder h = (MoreSelectTitleHolder) holder;
            h.titleView.setText(t.getName());
        } else {
            MoreSelectItemHolder h = (MoreSelectItemHolder) holder;
            final View p= (View) h.iconImageView.getParent();
            h.tagImageView.setVisibility(t.isShow() ? View.VISIBLE : View.INVISIBLE);
            p.setBackgroundColor(t.isShow() ? Color.parseColor("#F5F6F5") : Color.parseColor("#ffffff"));
            if (t.isShow()) {
                h.tagImageView.setImageResource(t.isSelect() ? R.mipmap.item_del : R.mipmap.item_add);
            }
            h.name.setText(t.getName());
            h.tagImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  SelectMoreActivity s= (SelectMoreActivity) v.getContext();
                  s.updateSelectApp(t);
                }
            });
            if (mOnItemLongClickListener!=null) {
                h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemLongClickListener.onItemLongClick(MoreSelectAdapter.this, v, position);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public int getItemViewType(int position) {
        T t = dataSource.get(position);
        if (t.getUiType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }


    private class MoreSelectItemHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView tagImageView;
        private ImageView iconImageView;

        public MoreSelectItemHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mTextView);
            tagImageView = itemView.findViewById(R.id.tagImage);
            iconImageView = itemView.findViewById(R.id.mImageView);

        }
    }


    private class MoreSelectTitleHolder extends RecyclerView.ViewHolder {
        private TextView titleView;

        public MoreSelectTitleHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.allItemTitle);
        }
    }

    public List  getDataSource() {
        return dataSource;
    }

    public MoreSelectAdapter  setDataSource(List  dataSource) {
        this.dataSource = dataSource;
        return this;
    }


    public interface OnItemLongClickListener {
        boolean onItemLongClick(MoreSelectAdapter adapter, View view, int position);
    }
    public interface OnItemClickListener {
        boolean onItemLongClick(MoreSelectAdapter adapter, View view, int position);
    }

    public MoreSelectAdapter<T> setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
        return this;
    }

    public MoreSelectAdapter<T> setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        return this;
    }
}
