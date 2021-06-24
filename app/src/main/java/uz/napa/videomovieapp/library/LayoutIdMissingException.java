package uz.napa.videomovieapp.library;

public class LayoutIdMissingException extends RuntimeException {

    public LayoutIdMissingException() {
        super("ItemViewHolder children classes must be annotated with a layout id, please add @LayoutId(someLayoutId) ");
    }

}
