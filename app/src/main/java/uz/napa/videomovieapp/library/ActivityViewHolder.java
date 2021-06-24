package uz.napa.videomovieapp.library;

import android.app.Activity;

import uz.napa.videomovieapp.library.annotations.FieldAnnotationParser;

public abstract class ActivityViewHolder {

    private Activity mActivity;

    public ActivityViewHolder(Activity activity) {
        mActivity = activity;
        FieldAnnotationParser.setViewFields(this, activity);
    }
    public Activity getActivity() {
        return mActivity;
    }

}
