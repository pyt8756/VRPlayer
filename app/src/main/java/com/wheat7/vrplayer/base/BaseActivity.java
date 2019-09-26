package com.wheat7.vrplayer.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.wheat7.vrplayer.R;
import com.wheat7.vrplayer.utils.StatusBarUtil;


/**
 * Created by wheat7 on 05/07/2017.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private View mainView;
    private ViewDataBinding binding;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        super.onCreate(savedInstanceState);
        try {
            binding = DataBindingUtil.setContentView(this, layoutId);
            if (binding != null) {
                mainView = binding.getRoot();
            } else {
                mainView = LayoutInflater.from(this).inflate(layoutId, null);
                setContentView(mainView);
            }

        } catch (NoClassDefFoundError e) {
            mainView = LayoutInflater.from(this).inflate(layoutId, null);
            setContentView(mainView);
        }
        initView(savedInstanceState);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Set StatusBar,Child can Override to Set
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    public T getBinding() {
        return (T) binding;
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

}
