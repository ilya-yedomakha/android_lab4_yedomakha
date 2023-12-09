package stu.cn.ua.lab4.model.db;

import androidx.room.*;

import java.util.Date;

import stu.cn.ua.lab4.model.network.MeteoriteNetworkEntity;


@Entity(tableName = "meteorites")
@TypeConverters(DateTypeConverter.class)
public class MeteoriteDbEntity {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "fall")
    private String fall;

    @ColumnInfo(name = "year")
    private Date year;

    public MeteoriteDbEntity() {
    }

    public MeteoriteDbEntity(MeteoriteNetworkEntity meteoriteNetworkEntity) {
        this.fall = meteoriteNetworkEntity.getFall();
        this.year = meteoriteNetworkEntity.getYear();
        this.id = meteoriteNetworkEntity.getId();
        this.name = meteoriteNetworkEntity.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFall() {
        return fall;
    }

    public Date getYear() {
        return year;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public void setYear(Date year) {
        this.year = year;
    }
}
