package cn.iwenddg.androidexample.basemvvm.activity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;

import cn.iwenddg.androidexample.BR;

/**
 * 使用MVVM封装的Activity基类
 * 把每个View和VM绑定的细节封装到BaseDBActivity内
 *
 * @author iwen大大怪
 * @create 2021/12/09 15:30
 */
public abstract class BaseBDActivity<VDB extends ViewDataBinding,VM extends ViewModel> extends SupperActivity {

    protected VDB binding;
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout();
    }

    protected void bindLayout(){
        bindLayout(getViewModelClass());
    }

    protected void bindLayout(Class<VM> clzVM){
        bindLayout(new ViewModelProvider(this).get(clzVM));
    }

    protected void bindLayout(VM vm){
        viewModel = vm;
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.viewModel,vm);
    }

    public abstract int getLayoutId();

    /**
     * getClass().getGenericSuperclass()).getActualTypeArguments()可以获取泛型的Class类型
     *
     * @return 泛型的Class类型
     */
    public Class<VM> getViewModelClass() {
        Class<VM> vmClass = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return vmClass;
    }

}