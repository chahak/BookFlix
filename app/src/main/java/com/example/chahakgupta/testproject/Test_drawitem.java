package com.example.chahakgupta.testproject;

/**
 * Created by CHAHAK GUPTA on 05-08-2015.
 */
public class Test_drawitem {

    String ItemName;
    int imgResID;

    public Test_drawitem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}
