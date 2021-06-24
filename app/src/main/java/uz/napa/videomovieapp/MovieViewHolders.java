package uz.napa.videomovieapp;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import uz.napa.videomovieapp.constant.Constant;
import uz.napa.videomovieapp.library.ItemViewHolder;
import uz.napa.videomovieapp.library.PositionInfo;
import uz.napa.videomovieapp.library.annotations.LayoutId;
import uz.napa.videomovieapp.library.annotations.ViewId;
import uz.napa.videomovieapp.model.ModelItem;

@LayoutId(R.layout.single_item)
public class MovieViewHolders extends ItemViewHolder<ModelItem> {

    @ViewId(R.id.txtName)
    TextView txtxName;

    @ViewId(R.id.description)
    TextView description;

    @ViewId(R.id.sana)
    TextView sana;

    @ViewId(R.id.load_image)
    ImageView load_image;

    public MovieViewHolders(View view) {
        super(view);
    }
    @Override
    public void onSetValues(ModelItem item, PositionInfo positionInfo) {
        Glide
                .with(getContext())
                .load(Constant.IMG_URL + item.getPoster_path())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(load_image);
    txtxName.setText(item.getTitle());
    description.setText(item.getOverview());
    sana.setText(item.getRelease_date());
    }

    @Override
    public void onSetListeners() {
        load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieHolderListener listener = getListener(MovieHolderListener.class);
                if (listener!=null){
                    listener.onMovieImageClicked(getItem());
                }
            }
        });
        super.onSetListeners();
    }


    public  interface MovieHolderListener{
        void onMovieImageClicked(ModelItem item);
    }

}
