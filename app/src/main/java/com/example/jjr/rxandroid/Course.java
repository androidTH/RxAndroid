package com.example.jjr.rxandroid;

import java.io.Serializable;

/**
 * Created by jjr on 2016/1/16.
 */
public class Course implements Serializable {
    private String cName;

    public Course(String cName) {
        this.cName = cName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
