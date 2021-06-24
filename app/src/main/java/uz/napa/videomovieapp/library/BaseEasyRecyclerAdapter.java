package uz.napa.videomovieapp.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import uz.napa.videomovieapp.model.ModelItem;

public abstract class BaseEasyRecyclerAdapter<T> extends RecyclerView.Adapter<BaseEasyRecyclerAdapter.RecyclerViewHolder> {

    private Class mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;
    private Object mListener;

     public BaseEasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        init(context, itemViewHolderClass);
    }

       public BaseEasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        init(context, itemViewHolderClass);
        mListener = listener;
    }

    private void init(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mItemViewHolderClass = itemViewHolderClass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemLayoutId = EasyAdapterUtil.parseItemLayoutId(itemViewHolderClass);
    }

    public abstract ModelItem getItem(int position);

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = mInflater.inflate(mItemLayoutId, parent, false);
        ItemViewHolder<T> itemViewHolder = EasyAdapterUtil.createViewHolder(itemView, mItemViewHolderClass);
        itemViewHolder.setListener(mListener);
        itemViewHolder.onSetListeners();
        return new RecyclerViewHolder(itemViewHolder);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        ModelItem item = getItem(position);
        ItemViewHolder<ModelItem> itemViewHolder = recyclerViewHolder.itemViewHolder;
        PositionInfo positionInfo = new PositionInfo(position, position == 0, position == getItemCount() - 1);
        itemViewHolder.setItem(item);
        itemViewHolder.onSetValues(item, positionInfo);
    }

    // A RecyclerView.ViewHolder that wraps an ItemViewHolder
    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ItemViewHolder itemViewHolder;

        public RecyclerViewHolder(ItemViewHolder itemViewHolder) {
            super(itemViewHolder.getView());
            this.itemViewHolder = itemViewHolder;
        }
    }
}
