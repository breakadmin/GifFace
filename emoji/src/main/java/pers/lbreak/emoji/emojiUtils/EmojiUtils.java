package pers.lbreak.emoji.emojiUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pers.lbreak.emoji.R;
import pers.lbreak.emoji.model.Emoji;

/**
 * 表情包工具类
 */
public class EmojiUtils {
    private Pattern pattern;
    public List<Emoji> data=init();
    Context context;
    public EmojiUtils(Context context, String pattern) {
        this.context=context;
        this.pattern =Pattern.compile(pattern);
    }
    /**
     * 调整图片大小
     *
     * @param bitmap
     *            源
     * @param dst_w
     *            输出宽度
     * @param dst_h
     *            输出高度
     * @return
     */
    public static Bitmap imageScale(Bitmap bitmap, int dst_w, int dst_h) {
        int src_w = bitmap.getWidth();
        int src_h = bitmap.getHeight();
        float scale_w = ((float) dst_w) / src_w;
        float scale_h = ((float) dst_h) / src_h;
        Matrix matrix = new Matrix();
        matrix.postScale(scale_w, scale_h);
        Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix,
                true);
        return dstbmp;
    }
    /**
     * px 转dp
     * @param dp
     * @return
     */
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
    /**
     * 初始化表情信息
     * @return
     */
    private List<Emoji> init(){
        List<Emoji> data=new ArrayList<>();

        data.add(new Emoji("[调皮]", R.drawable.f1));
        data.add(new Emoji("[酷]", R.drawable.f2));
        data.add(new Emoji("[抠鼻]", R.drawable.f3));
        data.add(new Emoji("[鼓掌]", R.drawable.f4));
        data.add(new Emoji("[抓狂]", R.drawable.f5));
        data.add(new Emoji("[开心]", R.drawable.f6));
        data.add(new Emoji("[喜欢]", R.drawable.f7));
        data.add(new Emoji("[坏笑]", R.drawable.f8));
        data.add(new Emoji("[舔嘴]", R.drawable.f9));
        data.add(new Emoji("[吐]", R.drawable.f10));
        data.add(new Emoji("[微笑]", R.drawable.f11));
        data.add(new Emoji("[亲亲]", R.drawable.f12));
        data.add(new Emoji("[咒骂]", R.drawable.f13));
        data.add(new Emoji("[困]", R.drawable.f14));
        data.add(new Emoji("[不开心]", R.drawable.f15));
        data.add(new Emoji("[嘘]", R.drawable.f16));
        data.add(new Emoji("[难过]", R.drawable.f17));
        data.add(new Emoji("[瞪眼]", R.drawable.f18));
        data.add(new Emoji("[高傲]", R.drawable.f19));
        data.add(new Emoji("[难过]", R.drawable.f20));

        data.add(new Emoji("[炸弹]", R.drawable.f21));
        data.add(new Emoji("[疑问]", R.drawable.f22));
        data.add(new Emoji("[害羞]", R.drawable.f23));
        data.add(new Emoji("[zzz]", R.drawable.f24));
        data.add(new Emoji("[No]", R.drawable.f25));
        data.add(new Emoji("[惊恐]", R.drawable.f26));
        data.add(new Emoji("[猪]", R.drawable.f27));
        data.add(new Emoji("[再见]", R.drawable.f28));
        data.add(new Emoji("[晕圈]", R.drawable.f29));
        data.add(new Emoji("[可怜]", R.drawable.f30));
        data.add(new Emoji("[左哼哼]", R.drawable.f31));
        data.add(new Emoji("[右哼哼]", R.drawable.f32));
        data.add(new Emoji("[拥抱]", R.drawable.f33));
        data.add(new Emoji("[流鼻涕]", R.drawable.f34));
        data.add(new Emoji("[鄙视]", R.drawable.f35));
        data.add(new Emoji("[偷笑]", R.drawable.f36));
        data.add(new Emoji("[握手]", R.drawable.f37));
        data.add(new Emoji("[爱你]", R.drawable.f38));
        data.add(new Emoji("[拳头]", R.drawable.f39));
        data.add(new Emoji("[勾引]", R.drawable.f40));

        data.add(new Emoji("[敲头]", R.drawable.f41));
        data.add(new Emoji("[啤酒]", R.drawable.f42));
        data.add(new Emoji("[OK]", R.drawable.f43));
        data.add(new Emoji("[玫瑰]", R.drawable.f44));
        data.add(new Emoji("[删除]", R.drawable.face_delete));



        return data;
    }


    /**
     * TextView显示gif表情
     * @param message 文本消息
     * @param tv TextView
     * @param emojiIcon emoji大小
     */
    public SpannableString displayEmoji(String message, final TextView tv, int emojiIcon) {
        SpannableString value = SpannableString.valueOf(message);
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            int k = matcher.start();
            int m = matcher.end();
            if (m - k < 8) {
                for(Emoji emoji:data){
                    if (emoji.getName().equals(matcher.group(0))){
                        WeakReference<AnimatedImageSpan> localImageSpanRef = new WeakReference<AnimatedImageSpan>(new AnimatedImageSpan(
                                new AnimatedGifDrawable(context.getResources().openRawResource(emoji.getRes()), new AnimatedGifDrawable.UpdateListener() {
                                    @Override
                                    public void update() {//update the textview
                                        tv.postInvalidate();
                                    }
                                },dp2px(emojiIcon))));
                        value.setSpan(localImageSpanRef.get(), k, m, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
//                        tv.setText(value);
                    }
                }
            }
        }
        return value;
    }

    /**
     * EditText显示gif表情
     * @param message 文本消息
     * @param emojiIcon emoji大小
     */
    public  SpannableString dealExpression(String message,int emojiIcon)  {
        SpannableString value = SpannableString.valueOf(message);
        Matcher matcher = pattern.matcher(value);
        Bitmap bitmap = null;
        try {
                while (matcher.find()) {
                    int k = matcher.start();
                    int m = matcher.end();
                    if (m - k < 8) {
                        for(Emoji emoji:data){
                            if (emoji.getName().equals(matcher.group(0))){

                                   bitmap = BitmapFactory.decodeResource(context.getResources(), emoji.getRes());
                                   ImageSpan imageSpan = new ImageSpan(context, imageScale(bitmap,dp2px(emojiIcon),dp2px(emojiIcon)));
                                   value.setSpan(imageSpan,  k,m, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);////前后都不包括

                            }
                        }
                    }
                }
        }finally {
            if (bitmap!=null){
                bitmap=null;
            }
        }
        return value;
    }

}
