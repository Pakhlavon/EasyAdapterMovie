package uz.napa.videomovieapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import uz.napa.videomovieapp.Utils.NetworkUtils;

public class Receiver extends BroadcastReceiver {
    Dialog dialog;
    TextView nettext;

    @Override
    public void onReceive(Context context, Intent intent) {

        String status = NetworkUtils.getConnectivityStatusString(context);
        dialog = new Dialog(context,android.R.style.Theme_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.customdialog);
        ImageView restartapp =dialog.findViewById(R.id.restartapp);
        nettext = dialog.findViewById(R.id.nettext);

        restartapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                Log.d("clickedbutton","yes");
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });
        Log.d("network",status);
        if(status.isEmpty()||status.equals("No internet is available")||status.equals("No Internet Connection")) {
            status="No Internet Connection";
            dialog.show();
        }
        Toast.makeText(context, status, Toast.LENGTH_LONG).show();
    }
}
