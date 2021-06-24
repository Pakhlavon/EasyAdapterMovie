package uz.napa.videomovieapp.networks;

import uz.napa.videomovieapp.constant.Constant;

public class ApiUtils {

    private static FileService apiRequests;

    public static FileService getFileService() {
        return RetrofitClients.getClient(Constant.BASE_URL).create(FileService.class);
    }
}
