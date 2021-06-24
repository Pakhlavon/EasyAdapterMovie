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

//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        modelItemList.clear();
//        if (charText.length() == 0) {
//            modelItemList.addAll(arraylist);
//        } else {
//            for (ModelItem wp : arraylist) {
//                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    modelItemList.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }




//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @SuppressWarnings("unchecked")
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                modelItemList = (List<ModelItem>) results.values;
//                notifyDataSetChanged();
//            }
//
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                List<ModelItem> filteredResults =null;
//                if (constraint.length() == 0) {
//                    filteredResults = modelItemList;
//                } else {
//                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
//                }
//                FilterResults results = new FilterResults();
//                results.values = filteredResults;
//                return results;
//            }
//        };
//    }
//
//
//    protected List<ModelItem> getFilteredResults(String constraint) {
//        List<ModelItem> results = new ArrayList<>();
//
//        for (ModelItem item : modelItemList) {
//            if (item.getTitle().toLowerCase().contains(constraint)) {
//                results.add(item);
//            }
//        }
//        return results;
//    }

    public void filter(CharSequence charSequence){
        ArrayList<ModelItem> tempArraylist = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for (ModelItem modelItem :modelItemList){
                if (modelItem.getTitle().toLowerCase().contains(charSequence)){
                    tempArraylist.add(modelItem);
                }
            }
        }
        else {
            tempArraylist.addAll(arrayListcopy);
            MainActivity.nodata.setVisibility(View.VISIBLE);
        }
        modelItemList.clear();
        modelItemList.addAll(tempArraylist);
        notifyDataSetChanged();
        tempArraylist.clear();
    }

}
