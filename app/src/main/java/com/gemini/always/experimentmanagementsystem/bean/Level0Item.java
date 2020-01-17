package com.gemini.always.experimentmanagementsystem.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Level0Item extends AbstractExpandableItem<Item> implements MultiItemEntity {

    private String itemName;

    public Level0Item(String itemName){
        this.itemName = itemName;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
