package uz.napa.videomovieapp.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseEasyAdapter<T> extends BaseAdapter {

    private Class<? extends ItemViewHolder> mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;
    private Object mListener;

    public BaseEasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        init(context, itemViewHolderClass);
    }

    public BaseEasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        init(context, itemViewHolderClass);
        mListener = listener;
    }

    private void init(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mItemViewHolderClass = itemViewHolderClass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemLayoutId = EasyAdapterUtil.parseItemLayoutId(itemViewHolderClass);
    }

    @Override
    public abstract T getItem(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder<T> holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemLayoutId, parent, false);
            //Create a new view holder using reflection
            holder = EasyAdapterUtil.createViewHolder(convertView, mItemViewHolderClass);
            holder.setListener(mListener);
            holder.onSetListeners();
            if (convertView != null) convertView.setTag(holder);
        } else {
            //Reuse the view holder
            holder = (ItemViewHolder) convertView.getTag();
        }

        T item = getItem(position);
        holder.setItem(item);
        PositionInfo positionInfo = new PositionInfo(position, position == 0, position == getCount() - 1);
        holder.onSetValues(item, positionInfo);

        return convertView;
    }

}
