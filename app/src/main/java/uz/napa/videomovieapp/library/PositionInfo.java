package uz.napa.videomovieapp.library;

public class PositionInfo {

    private int mPosition;
    private boolean mFirst;
    private boolean mLast;

    public PositionInfo() {

    }

   public PositionInfo(int position, boolean first, boolean last) {
        setPosition(position);
        setFirst(first);
        setLast(last);
    }

        public int getPosition() {
        return mPosition;
    }

    public boolean isFirst() {
        return mFirst;
    }

    public boolean isLast() {
        return mLast;
    }


    public void setPosition(int position) {
        mPosition = position;
    }

     public void setFirst(boolean first) {
        mFirst = first;
    }

    public void setLast(boolean last) {
        mLast = last;
    }
}
