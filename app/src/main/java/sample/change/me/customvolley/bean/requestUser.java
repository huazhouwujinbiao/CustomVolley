package sample.change.me.customvolley.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/29.
 */

public class requestUser implements Serializable{
    private String user;
    private String pass;

    public String getName() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.user = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public requestUser(String name, String pass) {
        this.user = name;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "requestUser{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
