package uz.napa.videomovieapp.library;

import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

public abstract class ItemViewHolder<T> extends ViewHolder {

    private List<T> list;
    private T mItem;
    private Object mListener;

    public ItemViewHolder(View view) {
        super(view);
    }


    public ItemViewHolder(View view, T item) {
        super(view);
        setItem(item);
    }

    public T getItem() {
        return mItem;
    }


    public void setItem(T item) {
        mItem = item;
    }


    public abstract void onSetValues(T item, PositionInfo positionInfo);


    public void onSetListeners() {
    }

    @Nullable
    public Object getListener() {
        return mListener;
    }


    @Nullable
    public <P> P getListener(Class<P> type) {
        if (mListener != null) {
            return type.cast(mListener);
        }
        return null;
    }

    protected void setListener(Object listener) {
        mListener = listener;
    }

}
