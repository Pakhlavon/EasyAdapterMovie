package uz.napa.videomovieapp.library;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EasyAdapter<T> extends BaseEasyAdapter<T> {

    private List<T> mListItems;

    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems) {
        super(context, itemViewHolderClass);
        setItems(listItems);
    }

    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        super(context, itemViewHolderClass);
        mListItems = new ArrayList<T>();
    }

    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems, Object listener) {
        super(context, itemViewHolderClass, listener);
        setItems(listItems);
    }

    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        super(context, itemViewHolderClass, listener);
        mListItems = new ArrayList<>();
    }


    public void setItems(List<T> listItems) {
        mListItems = listItems;
        notifyDataSetChanged();
    }

    public void setItemsWithoutNotifying(List<T> listItems) {
        mListItems = listItems;
    }


    public List<T> getItems() {
        return mListItems;
    }

    public void addItem(T item) {
        mListItems.add(item);
        notifyDataSetChanged();
    }

    public boolean removeItem(T item) {
        if (mListItems.remove(item)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }


    public boolean addItems(Collection<? extends T> items) {
        if (mListItems.addAll(items)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    public boolean removeItems(Collection<? extends T> items) {
        if (mListItems.removeAll(items)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mListItems.get(position);
    }
}
