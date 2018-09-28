package pers.lbreak.emoji.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;


/**
 * Created by break on 2018/9/14/014.
 */
public class EmojiEditText extends android.support.v7.widget.AppCompatEditText {
    private OnDelKeyEventListener delKeyEventListener;
    public EmojiEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public EmojiEditText(Context context) {
        super(context);
    }
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }
    private class ZanyInputConnection extends InputConnectionWrapper {
        public ZanyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }
        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                if (delKeyEventListener != null) {
                    delKeyEventListener.onDeleteClick();
                    return true;
                }
            }
            return super.sendKeyEvent(event);
        }
        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            if (beforeLength == 1 && afterLength == 0) {
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DEL));
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }
    }

    /**
     * EditText 删除回调
     */
    public void setDelKeyEventListener(OnDelKeyEventListener delKeyEventListener) {
        this.delKeyEventListener = delKeyEventListener;
    }
    public interface OnDelKeyEventListener {
        void onDeleteClick();
    }
}
