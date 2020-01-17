package com.gemini.always.experimentmanagementsystem.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Item implements MultiItemEntity {

    private String itemName;

    public Item(String itemName){
        this.itemName = itemName;
    }

    @Override
    public int getItemType() {
        return 1;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
