package android.view;

/**
 * Created by IntelliJ IDEA.
 * User: aglover
 * Date: 3/19/13
 * Time: 10:41 AM
 *
 * This is simply a mock implementation of Android's own MotionEvent class!!
 * Because the android.jar only provides stubs (the actual impl is on the device)
 * mocking Android libs is painful so I took the easy way out as I only need one
 * class to effectively test Gesticulate.
 */
public class MotionEvent {
    private float x;
    private float y;

    public MotionEvent() {
    }

    public MotionEvent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
