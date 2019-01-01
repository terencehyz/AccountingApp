package com.hanyuzhou.accountingapp;

import android.content.Context;

import java.util.LinkedList;

public class GlobalUtil {

    private static final String TAG = "GlobalUtil";

    private static GlobalUtil instance;

    public RecordDatabaseHelper databaseHelper;
    private Context context;
    public MainActivity mainActivity;

    public LinkedList<CategoryResBean> costRes = new LinkedList<>();
    public LinkedList<CategoryResBean> earnRes = new LinkedList<>();

    private static int [] costIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_food_white,
            R.drawable.icon_drinking_white,
            R.drawable.icon_groceries_white,
            R.drawable.icon_shopping_white,
            R.drawable.icon_personal_white,
            R.drawable.icon_entertain_white,
            R.drawable.icon_movie_white,
            R.drawable.icon_social_white,
            R.drawable.icon_transport_white,
            R.drawable.icon_appstore_white,
            R.drawable.icon_mobile_white,
            R.drawable.icon_computer_white,
            R.drawable.icon_gift_white,
            R.drawable.icon_house_white,
            R.drawable.icon_travel_white,
            R.drawable.icon_ticket_white,
            R.drawable.icon_book_white,
            R.drawable.icon_medical_white,
            R.drawable.icon_transfer_white
    };
    private static int [] costIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_food,
            R.drawable.icon_drinking,
            R.drawable.icon_groceries,
            R.drawable.icon_shopping,
            R.drawable.icon_presonal,
            R.drawable.icon_entertain,
            R.drawable.icon_movie,
            R.drawable.icon_social,
            R.drawable.icon_transport,
            R.drawable.icon_appstore,
            R.drawable.icon_mobile,
            R.drawable.icon_computer,
            R.drawable.icon_gift,
            R.drawable.icon_house,
            R.drawable.icon_travel,
            R.drawable.icon_ticket,
            R.drawable.icon_book,
            R.drawable.icon_medical,
            R.drawable.icon_transfer
    };
    private static String[] costTitle = {"General", "Food", "Drinks","Groceries", "Shopping", "Personal","Entertain","Movies", "Social", "Transport",
            "App Store","Mobile","Computer","Gifts", "Housing", "Travel","Tickets","Books", "Medical","Transfer"};

    private static int[] earnIconRes = {
            R.drawable.icon_general_white,
            R.drawable.icon_reimburse_white,
            R.drawable.icon_salary_white,
            R.drawable.icon_redpocket_white,
            R.drawable.icon_parttime_white,
            R.drawable.icon_bonus_white,
            R.drawable.icon_investment_white};

    private static int[] earnIconResBlack = {
            R.drawable.icon_general,
            R.drawable.icon_reimburse,
            R.drawable.icon_salary,
            R.drawable.icon_redpocket,
            R.drawable.icon_parttime,
            R.drawable.icon_bonus,
            R.drawable.icon_investment};

    private static String[] earnTitle = {"General", "Reimburse", "Salary","RedPocket","Part-time", "Bonus","Investment"};

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        databaseHelper = new RecordDatabaseHelper(context,RecordDatabaseHelper.DB_NAME,null,1);

        for (int i=0;i<costTitle.length;i++){
            CategoryResBean res = new CategoryResBean();
            res.title = costTitle[i];
            res.resBlack = costIconResBlack[i];
            res.resWhite = costIconRes[i];
            costRes.add(res);
        }

        for (int i=0;i<earnTitle.length;i++){
            CategoryResBean res = new CategoryResBean();
            res.title = earnTitle[i];
            res.resBlack = earnIconResBlack[i];
            res.resWhite = earnIconRes[i];
            earnRes.add(res);
        }

    }

    public static GlobalUtil getInstance(){
        if (instance==null){
            instance = new GlobalUtil();
        }
        return instance;
    }

    public int getResourceIcon(String category){
        for (CategoryResBean res :
                costRes) {
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }

        for (CategoryResBean res :
                earnRes) {
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }

        return costRes.get(0).resWhite;
    }
}
