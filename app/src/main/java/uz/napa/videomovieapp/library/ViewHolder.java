package uz.napa.videomovieapp.library;

import android.content.Context;
import android.view.View;

import uz.napa.videomovieapp.library.annotations.FieldAnnotationParser;

public abstract class ViewHolder {

    private View mView;

    public ViewHolder(View view) {
        mView = view;
        FieldAnnotationParser.setViewFields(this, view);
    }

    public View getView() {
        return mView;
    }

    public Context getContext() {
        return mView.getContext();
    }

}
