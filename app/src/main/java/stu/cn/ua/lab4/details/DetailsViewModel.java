package stu.cn.ua.lab4.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import stu.cn.ua.lab4.BaseViewModel;
import stu.cn.ua.lab4.model.Callback;
import stu.cn.ua.lab4.model.Cancellable;
import stu.cn.ua.lab4.model.NasaService;
import stu.cn.ua.lab4.model.Meteorite;
import stu.cn.ua.lab4.model.Result;

public class DetailsViewModel extends BaseViewModel {

    private MutableLiveData<Result<Meteorite>> meteoriteLiveData = new MutableLiveData<>();

    {
        meteoriteLiveData.setValue(Result.empty());
    }

    private Cancellable cancellable;

    @Override
    protected void onCleared() {
        super.onCleared();
        if (cancellable != null) cancellable.cancel();
    }

    public DetailsViewModel(NasaService nasaService) {
        super(nasaService);
    }

    public void loadMeteoriteById(long id) {
        meteoriteLiveData.setValue(Result.loading());
        cancellable = getNasaService().getMeteoriteById(id, new Callback<Meteorite>() {
            @Override
            public void onError(Throwable error) {
                meteoriteLiveData.postValue(Result.error(error));
            }

            @Override
            public void onResults(Meteorite data) {
                meteoriteLiveData.postValue(Result.success(data));
            }
        });
    }

    public LiveData<Result<Meteorite>> getResults() {
        return meteoriteLiveData;
    }
}
