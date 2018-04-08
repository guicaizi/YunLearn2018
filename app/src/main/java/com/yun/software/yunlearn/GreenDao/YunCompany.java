package com.yun.software.yunlearn.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yanliang
 * on 2018/3/9 11:11
 */
@Entity
public class YunCompany {
    @Id
    private Long id;
    @Property(nameInDb = "JOB")
    private String job;
    @Property(nameInDb = "NAME")
    private String name;
    @Generated(hash = 1473465)
    public YunCompany(Long id, String job, String name) {
        this.id = id;
        this.job = job;
        this.name = name;
    }
    @Generated(hash = 150391920)
    public YunCompany() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJob() {
        return this.job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
