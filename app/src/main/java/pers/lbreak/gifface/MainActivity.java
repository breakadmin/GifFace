package pers.lbreak.gifface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pers.lbreak.emoji.adapter.MyFragmentAdapter;
import pers.lbreak.emoji.fragment.EmojiFragment;
import pers.lbreak.emoji.view.EmojiEditText;
import pers.lbreak.emoji.view.MyViewPager;


public class MainActivity extends AppCompatActivity {
    MyFragmentAdapter adapter;
    List<Fragment> data = new ArrayList<>();
    @BindView(R.id.vp)
    MyViewPager vp;
    @BindView(R.id.face)
    TextView face;
    @BindView(R.id.input)
    EmojiEditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        ButterKnife.bind(this);



        EmojiFragment fragment=new EmojiFragment();
        fragment.bind(input,face,25);
        data.add(fragment);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), data);
        vp.setAdapter(adapter);
//        input.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getKeyCode()==KeyEvent.KEYCODE_DEL){
//                    Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
//
//                }
//                return false;
//            }
//        });


    }
}
