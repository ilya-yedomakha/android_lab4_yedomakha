package stu.cn.ua.lab4.model;

import java.util.Date;

import stu.cn.ua.lab4.model.db.MeteoriteDbEntity;
import stu.cn.ua.lab4.model.network.MeteoriteNetworkEntity;

public class Meteorite {

    private final long id;
    private final String name;
    private final String fall;
    private final Date year;

    public Meteorite(long id, String name, String fall, Date year) {
        this.id = id;
        this.name = name;
        this.fall = fall;
        this.year = year;
    }

    public Meteorite(MeteoriteDbEntity entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getFall(),
                entity.getYear()
        );
    }


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
