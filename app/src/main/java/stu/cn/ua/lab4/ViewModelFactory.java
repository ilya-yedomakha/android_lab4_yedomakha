package stu.cn.ua.lab4;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;

import stu.cn.ua.lab4.model.NasaService;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private NasaService nasaService;

    public static final String TAG = ViewModelFactory.class.getSimpleName();

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            Constructor<T> constructor = modelClass.getConstructor(NasaService.class);
            return constructor.newInstance(nasaService);
        } catch (ReflectiveOperationException e) {
            Log.e(TAG,"Error", e);
            RuntimeException wrapper = new RuntimeException();
            wrapper.initCause(e);
            throw wrapper;
        }
    }

    public ViewModelFactory(NasaService nasaService) {
        this.nasaService = nasaService;
    }
}
