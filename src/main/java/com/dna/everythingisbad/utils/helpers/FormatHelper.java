package com.dna.everythingisbad.utils.helpers;

public class FormatHelper {
    public static String formatNumber(long count){
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "KMBTQE".charAt(exp-1));
    }
    public static String formatNumber(float count){
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "KMBTQE".charAt(exp-1));
    }
    public static String formatMoney(float money){
        String moneyString = Float.toString(money);
        if(money == 0.0f){
            return "$0.000";
        }
        if(money < 1000 && money > -1000) {
            return
                    "$" + moneyString.substring(
                            0,
                            Math.min(moneyString.indexOf(".") + 4,
                                    moneyString.length() - 1)
                    );
        }else{
            return "$" + formatNumber(money);
        }
    }
}
