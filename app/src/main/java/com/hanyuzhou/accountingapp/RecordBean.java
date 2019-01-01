package com.hanyuzhou.accountingapp;

import android.util.Log;

import java.io.Serializable;
import java.util.UUID;

public class RecordBean implements Serializable{

    public static String TAG = "RecordBean";

    public enum RecordType{
        RECORD_TYPE_EXPENSE,RECORD_TYPE_INCOME
    }

    private double amount;
    private RecordType type;
    private String category;
    private String remark;
    private String date;//2017-06-15

    private long timeStamp;
    private String uuid;

    public RecordBean(){
        uuid = UUID.randomUUID().toString();
        timeStamp = System.currentTimeMillis();
        date = DateUtil.getFormattedDate();
    }

    public static String getTAG() {
        return TAG;
    }

    public static void setTAG(String TAG) {
        RecordBean.TAG = TAG;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        if (this.type == RecordType.RECORD_TYPE_EXPENSE){
            return 1;
        }else {
            return 2;
        }
    }

    public void setType(int type) {
        if (type==1){
            this.type = RecordType.RECORD_TYPE_EXPENSE;
        }
        else {
            this.type = RecordType.RECORD_TYPE_INCOME;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
