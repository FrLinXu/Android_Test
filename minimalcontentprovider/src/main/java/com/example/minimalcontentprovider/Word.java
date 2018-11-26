package com.example.minimalcontentprovider;

/**
 * Author by Deil,  Date on 2018/11/22.
 * PS: Not easy to write code, please indicate.
 */
public class Word {

    private int _id;
    private  String name ;
    private  int frequency ;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
