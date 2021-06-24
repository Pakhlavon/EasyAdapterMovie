package uz.napa.videomovieapp.library.annotations;

import java.lang.annotation.Annotation;

public class ClassAnnotationParser {

    public static Integer getLayoutId(Class myClass) {
        Annotation annotation = myClass.getAnnotation(LayoutId.class);

        if (annotation instanceof LayoutId) {
            LayoutId layoutIdAnnotation = (LayoutId) annotation;
            return layoutIdAnnotation.value();
        }

        return null;
    }
}
