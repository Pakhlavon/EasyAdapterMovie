package uz.napa.videomovieapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.ColorSpace;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.napa.videomovieapp.library.EasyRecyclerAdapter;
import uz.napa.videomovieapp.model.HeadModel;
import uz.napa.videomovieapp.model.ModelItem;
import uz.napa.videomovieapp.networks.ApiUtils;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private BroadcastReceiver MyReceiver = null;
    private RecyclerView recyclerView;
    private SearchView simpleSearchView;
    private MovieViewHolders movieViewHolders;
    List<ModelItem> modelItemList;
    private ArrayList<ModelItem> arraylist = new ArrayList<>();
   private EasyRecyclerAdapter adapter;
   public static TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nodata = findViewById(R.id.nodata);
        recyclerView = findViewById(R.id.recyclerview);
        simpleSearchView = findViewById(R.id.simpleSearchView);
        simpleSearchView.setOnQueryTextListener(MainActivity.this);
        MyReceiver = new Receiver();
        broadcastIntent();

        getDate();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void getDate() {

        Call<HeadModel> call = ApiUtils.getFileService().getMovie();
        call.enqueue(new Callback<HeadModel>() {
            @Override
            public void onResponse(Call<HeadModel> call, Response<HeadModel> response) {
                if (response.isSuccessful()) {
                   modelItemList = response.body().getResults();
//                    recyclerView.setAdapter(new Adapter(MainActivity.this,modelItemList));
                    adapter =new EasyRecyclerAdapter(MainActivity.this,modelItemList,MovieViewHolders.class,mPersonHolderListener);
                    recyclerView.setAdapter(adapter);
//                    recyclerView.setAdapter(new EasyRecyclerAdapter(
//                            MainActivity.this,
//                            MovieViewHolders.class,
//                            modelItemList,
//                            mPersonHolderListener));
                }
            }

            @Override
            public void onFailure(Call<HeadModel> call, Throwable t) {
                call.request();
            }
        });
    }
    private MovieViewHolders.MovieHolderListener mPersonHolderListener = new MovieViewHolders.MovieHolderListener() {

        @SuppressLint("StringFormatInvalid")
        @Override
        public void onMovieImageClicked(ModelItem item) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(getString(R.string.clicked, item.getTitle()))
                    .setPositiveButton(R.string.ok, null)
                    .create()
                    .show();
        }
    };

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }


//    private List<ModelItem> filter(List<ModelItem> dataList, String newText) {
//        newText=newText.toLowerCase();
//        String text;
//        arraylist=new ArrayList<>();
//        for (ModelItem dataFromDataList:dataList){
//            text=dataFromDataList.getTitle().toLowerCase();
//
//            if(text.contains(newText)){
//                arraylist.add(dataFromDataList);
//            }
//        }
//
//        return arraylist;
//    }
}