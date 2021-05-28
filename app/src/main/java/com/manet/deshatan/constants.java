package com.manet.deshatan;

import java.util.HashMap;

public class constants {
    public static String UniversalRoomNumber = "";
    public static String userName = "";
    public static boolean isInGame = false;
    public static String id = "-1";
    public static HashMap<String,String> cityMap = new HashMap<>();
    public static HashMap<String,String> priceMap = new HashMap<>();
    static {
        cityMap.put("0","Qutub Minar Delhi");
        cityMap.put("1","Taj Mahal Agra");
        cityMap.put("2","Hawa Mahal Jaipur Rajasthan");
        cityMap.put("3","India Gate Delhi");
        cityMap.put("4","Murudeshwar Temple Karnataka");
        cityMap.put("5","City Palace Udaipur, Rajasthan");
        cityMap.put("6","Bahai Temple (Lotus Temple), New Delhi");
        cityMap.put("7","Mysore Palace, Mysore Karnataka");
        cityMap.put("8","Gateway of India Mumbai Maharashtra");
        cityMap.put("9","Konark temple Odisha");
        cityMap.put("10","RED Fort Delhi");
        cityMap.put("11","Charminar Hyderabad Telangana");
        cityMap.put("12","Golden Temple Amritsar");
        cityMap.put("13","Ajanta elora caves aurangabad");
        cityMap.put("14","Jama Masjid, Delhi");
        cityMap.put("15","Basilica of Bom Jesus");

        priceMap.put("0","450");
        priceMap.put("1","800");
        priceMap.put("2","400");
        priceMap.put("3","500");
        priceMap.put("4","350");
        priceMap.put("5","200");
        priceMap.put("6","100");
        priceMap.put("7","400");
        priceMap.put("8","500");
        priceMap.put("9","500");
        priceMap.put("10","600");
        priceMap.put("11","200");
        priceMap.put("12","400");
        priceMap.put("13","200");
        priceMap.put("14","300");
        priceMap.put("15","300");
    }
}
