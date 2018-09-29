package pers.lbreak.emoji.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pers.lbreak.emoji.Interface.FaceListener;
import pers.lbreak.emoji.R;
import pers.lbreak.emoji.adapter.MyAdapter;
import pers.lbreak.emoji.emojiUtils.EmojiUtils;
import pers.lbreak.emoji.model.Emoji;
import pers.lbreak.emoji.view.EmojiEditText;


/**
 * Created by 惠普 on 2018-03-10.
 */
public class EmojiFragment extends Fragment {
    private View view;
    private EditText editText;
    private TextView faceView;
    private RecyclerView rv;
    private MyAdapter adapter;
    private int emojiSize=25;
    public EmojiUtils emojiUtils;


    /**
     * 初始化设置
     * @param editText  文本框
     * @param faceView emoji展示
     * @param emojiSize emoji 大小
     */
    public void bind(EditText editText, TextView faceView, int emojiSize) {
        this.editText = editText;
        this.faceView = faceView;
        this.emojiSize=emojiSize;
    }
    /**
     * 初始化设置
     * @param editText  文本框
     * @param emojiSize emoji 大小
     */
    public void bind(EditText editText, int emojiSize) {
        this.editText = editText;
        this.emojiSize=emojiSize;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (view != null) {
            view = null;
            Toast.makeText(getContext(), "移除1", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && view != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_face, null);
        initView(view);
        emojiUtils = new EmojiUtils(getContext(), "\\[[\u4e00-\u9fa5\\w]+\\]");
        adapter = new MyAdapter(getContext(), emojiUtils.data);
        rv.setAdapter(adapter);


        adapter.setFaceListener(new FaceListener() {

            @Override
            public void display(final Emoji emoji, int position) {
                if (position == emojiUtils.data.size() - 1) {//删除
                    String text = editText.getText().toString();
                    if (!text.isEmpty()) {
                        int index = editText.getSelectionStart();//获取光标所在位置
                        Editable edit = editText.getEditableText();//获取EditText的文字
                        if (index <= 0) {
                            return;
                        }

                        if (index < edit.length()) {//光标不在末尾
                            text = text.substring(0, index);
                        }
                        if ("]".equals(text.substring(text.length() - 1, text.length()))) {
                            index = text.lastIndexOf("[");
                            boolean isEmoji=false;
                            for(Emoji e:emojiUtils.data){//判断是否表情
                                if (e.getName().equals(text.substring(index,text.length()))){
                                    editText.getText().delete(index, text.length());
                                    isEmoji=true;
                                    break;
                                }
                            }
                            if (!isEmoji){
                                editText.getText().delete(index - 1, index);
                            }
                        } else {
                            index = editText.getSelectionStart();
                            editText.getText().delete(index - 1, index);
                        }
                    }
                    return;
                }

                if (editText != null) {
                    int cursor = editText.getSelectionStart();
                    editText.getText().insert(cursor, emojiUtils.dealExpression(emoji.getName(),emojiSize));
                }


                if (faceView != null) {
                    faceView.setText(emojiUtils.displayEmoji( editText.getText().toString(), faceView, emojiSize));
                }


            }

        });

        return view;
    }

    private void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
    }
}

