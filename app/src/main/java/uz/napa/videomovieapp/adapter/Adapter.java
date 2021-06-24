//package uz.napa.videomovieapp.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.List;
//
//import uz.napa.videomovieapp.R;
//import uz.napa.videomovieapp.constant.Constant;
//import uz.napa.videomovieapp.model.HeadModel;
//import uz.napa.videomovieapp.model.ModelItem;
//
//public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
//
//    private List<ModelItem> modelItemList;
//    private Context context;
//
//    public Adapter(Context context, List<ModelItem> modelItemList){
//        this.context = context;
//        this.modelItemList = modelItemList;
//    }
//    @NonNull
//    @NotNull
//    @Override
//    public Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull Adapter.ViewHolder holder, int position) {
//            ModelItem modelItem = modelItemList.get(position);
//
//            holder.txtName.setText(modelItem.getTitle());
//            holder.description.setText(modelItem.getOverview());
//
//        Glide
//                .with(context)
//                .load(Constant.IMG_URL + modelItem.getPoster_path())
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.load_image);
//    }
//
//    @Override
//    public int getItemCount() {
//        return modelItemList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView load_image;
//        public TextView txtName,description,sana;
//        public ViewHolder(@NonNull @NotNull View itemView) {
//            super(itemView);
//
//            load_image = itemView.findViewById(R.id.load_image);
//            txtName = itemView.findViewById(R.id.txtName);
//            description = itemView.findViewById(R.id.description);
//            sana = itemView.findViewById(R.id.sana);
//        }
//    }
//}
