package com.jdawidowska.client.model;

public class EquipmentItem {

    private Integer id;
    private String equipmentItem;
    private Integer availableAmount;
    private Integer totalAmount;

    public EquipmentItem(Integer id, String equipmentItem, Integer availableAmount, Integer totalAmount) {
        this.id = id;
        this.equipmentItem = equipmentItem;
        this.availableAmount = availableAmount;
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentItem() {
        return equipmentItem;
    }

    public void setEquipmentItem(String equipmentItem) {
        this.equipmentItem = equipmentItem;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}