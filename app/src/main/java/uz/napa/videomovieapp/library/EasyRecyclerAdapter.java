package uz.napa.videomovieapp.library;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import uz.napa.videomovieapp.MainActivity;
import uz.napa.videomovieapp.model.ModelItem;

public class EasyRecyclerAdapter<T> extends BaseEasyRecyclerAdapter<T>{

    private List<T> mListItems;
    private List<ModelItem> modelItemList;
    private ArrayList<ModelItem> arraylist;
    ArrayList<ModelItem> arrayListcopy;
    private Context context;

    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<ModelItem> listItems) {
        super(context, itemViewHolderClass);
        setItems(listItems);
    }


    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        super(context, itemViewHolderClass);
        mListItems = new ArrayList<T>();
    }

    public EasyRecyclerAdapter(Context context, List<ModelItem> modelItems, Class<? extends ItemViewHolder> itemViewHolder, Object listener){
        super(context,itemViewHolder, listener);
        this.modelItemList = modelItems;
        arrayListcopy = new ArrayList<>();
        arrayListcopy.addAll(modelItems);

    }

    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<ModelItem> listItems, Object listener) {
        super(context, itemViewHolderClass, listener);
        setItems(listItems);
    }

    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        super(context, itemViewHolderClass, listener);
        mListItems = new ArrayList<T>();
    }


    public void setItems(List<ModelItem> listItems) {
        modelItemList = listItems;
        notifyDataSetChanged();
    }

    public void setItemsWithoutNotifying(List<T> listItems) {
        modelItemList = modelItemList;
    }


    public List<ModelItem> getItems() {
        return modelItemList;
    }

    public void addItem(ModelItem item) {
        modelItemList.add(item);
        notifyItemInserted(modelItemList.indexOf(item));
    }

    public boolean removeItem(ModelItem item) {
        int index = modelItemList.indexOf(item);
        if (index < 0) return false;
        modelItemList.remove(index);
        notifyItemRemoved(index);
        return true;
    }

    public boolean addItems(Collection<ModelItem> items) {
        if (items.isEmpty()) return false;
        int previousSize = getItemCount();
        if (modelItemList.addAll(items)) {
            notifyItemRangeInserted(previousSize, items.size());
            return true;
        }
        return false;
    }


    public boolean removeItems(Collection<? extends ModelItem> items) {
        if (modelItemList.removeAll(items)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public ModelItem getItem(int position) {
        return modelItemList.get(position);
    }

    @Override
    public int getItemCount() {
        return modelItemList.size();
    }

    public void filter(CharSequence charSequence){
        ArrayList<ModelItem> tempArraylist = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            MainActivity.liner.setVisibility(View.VISIBLE);
            MainActivity.nodata.setText("По запросу " + "'" + charSequence +"'\n" + "ничего не найдено");
            for (ModelItem modelItem :modelItemList){
                if (modelItem.getTitle().toLowerCase().contains(charSequence)){
                    tempArraylist.add(modelItem);
                    MainActivity.liner.setVisibility(View.GONE);
                }
            }
        }
        else {
            tempArraylist.addAll(arrayListcopy);
            MainActivity.liner.setVisibility(View.GONE);
        }
        modelItemList.clear();
        modelItemList.addAll(tempArraylist);
        notifyDataSetChanged();
        tempArraylist.clear();

    }

}
