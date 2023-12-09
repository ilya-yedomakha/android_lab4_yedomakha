package stu.cn.ua.lab4.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.Arrays;
import java.util.List;

import stu.cn.ua.lab4.BaseViewModel;
import stu.cn.ua.lab4.model.Callback;
import stu.cn.ua.lab4.model.Cancellable;
import stu.cn.ua.lab4.model.NasaService;
import stu.cn.ua.lab4.model.Meteorite;
import stu.cn.ua.lab4.model.Result;

public class MainViewModel extends BaseViewModel {

    private Result<List<Meteorite>> meteoritesResult = Result.empty();

    public Result<List<Meteorite>> getMeteoritesResult() {
        return meteoritesResult;
    }

    public void setMeteoritesResult(Result<List<Meteorite>> meteoritesResult) {
        this.meteoritesResult = meteoritesResult;
    }

    private MutableLiveData<Result<List<Meteorite>>> meteoriteLiveData = new MutableLiveData<>();

    {
        meteoriteLiveData.postValue(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) cancellable.cancel();
    }

    public MainViewModel(NasaService nasaService) {
        super(nasaService);
    }

    public void getMeteorites() {
        meteoriteLiveData.setValue(Result.loading());
        cancellable = getNasaService().getMeteorites(new Callback<List<Meteorite>>() {
            @Override
            public void onError(Throwable error) {
                if (meteoritesResult.getStatus() != Result.Status.SUCCESS) {
                    meteoriteLiveData.postValue(Result.error(error));
                }
            }

            @Override
            public void onResults(List<Meteorite> data) {
                meteoriteLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<List<Meteorite>>> getResults() {
        return meteoriteLiveData;
    }
}
