package stu.cn.ua.lab4.model.network;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoriteNetworkEntity {
    private long id;
    private String name;
    private String fall;
    private Date year;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFall() {
        return fall;
    }

    public Date getYear() {
        return year;
    }
}
