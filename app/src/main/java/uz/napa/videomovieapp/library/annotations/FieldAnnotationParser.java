package uz.napa.videomovieapp.library.annotations;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;


public class FieldAnnotationParser {

    public static void setViewFields(final Object object, final View view) {
        setViewFields(object, new ViewFinder() {
            @Override
            public View findViewById(int viewId) {
                return view.findViewById(viewId);
            }
        });
    }

    public static void setViewFields(final Object object, final Activity activity) {
        setViewFields(object, new ViewFinder() {
            @Override
            public View findViewById(int viewId) {
                return activity.findViewById(viewId);
            }
        });
    }

    private static void setViewFields(final Object object, final ViewFinder viewFinder) {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewId.class)) {
                field.setAccessible(true);
                ViewId viewIdAnnotation = field.getAnnotation(ViewId.class);
                try {
                    field.set(object, field.getType().cast(viewFinder.findViewById(viewIdAnnotation.value())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private interface ViewFinder {
        public View findViewById(int viewId);
    }
}
