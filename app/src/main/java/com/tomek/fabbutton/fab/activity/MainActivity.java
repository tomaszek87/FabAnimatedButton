package com.tomek.fabbutton.fab.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomek.fabbutton.R;
import com.tomek.fabbutton.fab.adapter.MyAdapter;
import com.tomek.fabbutton.fab.model.SimpleModel;
import com.tomek.fabbutton.fab.view.DividerItemDecoration;
import com.tomek.fabbutton.utils.activity.BaseActivity;
import com.tomek.fabbutton.utils.tools.CustomAnimation;
import com.tomek.fabbutton.utils.tools.FabAnimator;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    private boolean hidden = true;
    @Bind(R.id.layout_to_reveal)
    RelativeLayout llToReveal;
    @Bind(R.id.fab1)
    public
    RelativeLayout fab1;
    @Bind(R.id.fab2)
    public
    RelativeLayout fab2;
    @Bind(R.id.fab3)
    public
    RelativeLayout fab3;
    @Bind(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private MyAdapter adapter;
    private LinearLayoutManager manager;
    private MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomAnimation().toggleAnimation(llToReveal, hidden, 0, fab);
                setHidden();
                new FabAnimator().showAnimation(hidden, fab, instance);
            }
        });
        prepareList();
    }


    public void popupMenu(final RelativeLayout button) {
        final FloatingActionButton tmp1 = (FloatingActionButton) button.findViewById(R.id.fab_menu);
        final TextView tmp2 = (TextView) button.findViewById(R.id.fab_text);
        button.setVisibility(View.VISIBLE);
//        ValueAnimator anim = new ValueAnimator();
//        anim.setIntValues(0, 1);
//        anim.setEvaluator(new ArgbEvaluator());
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                System.out.println(valueAnimator.getAnimatedValue());
//                //   button.setAlpha((Float) valueAnimator.getAnimatedValue());
//            }
//        });
        ObjectAnimator anim = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f);
        anim.setDuration(200);
        anim.start();
        button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
    }

    public void hideMenu(RelativeLayout button) {
        button.setVisibility(View.INVISIBLE);
        final FloatingActionButton tmp1 = (FloatingActionButton) button.findViewById(R.id.fab_menu);
        final TextView tmp2 = (TextView) button.findViewById(R.id.fab_text);
        ObjectAnimator anim = ObjectAnimator.ofFloat(button, "alpha", 1f, 0f);
        anim.setDuration(200);
        anim.start();
        button.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));

    }

    private void prepareList() {
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(getDataSet());
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayout.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private ArrayList<SimpleModel> getDataSet() {
        ArrayList results = new ArrayList<SimpleModel>();
        SimpleModel obj = new SimpleModel("Tokyo", "13,297,629");
        results.add(obj);
        obj = new SimpleModel("Moscow", "12,197,596");
        results.add(obj);
        obj = new SimpleModel("Mexico City", "8,874,724");
        results.add(obj);
        obj = new SimpleModel("London", "8,538,689");
        results.add(obj);
        obj = new SimpleModel("New York City", "8,491,079");
        results.add(obj);
        obj = new SimpleModel("Rio de Janeiro", "6,429,923");
        results.add(obj);
        obj = new SimpleModel("Saint Petersburg", "5,191,690");
        results.add(obj);
        obj = new SimpleModel("Ankara", "4,470,800");
        results.add(obj);
        obj = new SimpleModel("Johannesburg", "4,434,827");
        results.add(obj);
        return results;
    }

    private void setHidden() {
        if (hidden)
            hidden = false;
        else
            hidden = true;
    }

}
