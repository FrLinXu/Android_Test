package com.example.database;

public class Person {

    private long _id;
    private String name;
    private String tel;
    private long age;

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
    public Person ( String name ,String tel ) {this .name = name ; this .tel =tel;}
    public Person(long id, String name, String tel, long age) {
        this._id = id;
        this.name = name;
        this.tel = tel;
        this.age = age;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
