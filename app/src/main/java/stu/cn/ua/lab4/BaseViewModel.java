package stu.cn.ua.lab4;

import androidx.lifecycle.ViewModel;

import stu.cn.ua.lab4.model.NasaService;

public class BaseViewModel extends ViewModel {
    private NasaService nasaService;

    public BaseViewModel(NasaService nasaService){
        this.nasaService = nasaService;
    }

    protected final NasaService getNasaService(){
        return nasaService;
    }
}
