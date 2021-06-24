package uz.napa.videomovieapp.networks;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uz.napa.videomovieapp.constant.Constant;
import uz.napa.videomovieapp.model.HeadModel;
import uz.napa.videomovieapp.model.ModelItem;

public interface FileService {

    @GET("movie/top_rated?api_key=4bce18081084239d01ef184884390e48")
    Call<HeadModel> getMovie();
}
