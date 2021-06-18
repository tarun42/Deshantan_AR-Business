package com.manet.deshatan;

import java.util.HashMap;

public class constants {
    public static String UniversalRoomNumber = "";
    public static String userName = "";
    public static boolean isInGame = false;
    public static String id = "-1";
    public static HashMap<String,String> cityMap = new HashMap<>();
    public static HashMap<String,String> priceMap = new HashMap<>();
    public static HashMap<Integer, String[]> questionsMap = new HashMap<>();
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
        
        questionsMap.put(1, new String[]{"The Qutub Minar was dedicated to?", "A Sufi Saint", "The Emperor himself", "The Emperor's queen", "Achievements of the Emperor", "A Sufi Saint"});
        questionsMap.put(2, new String[]{"The ______________ is the tallest minaret in India", "Taj Mahal", "Ashoka’s Pillar", "Qutb Minar", "Chand Minar", "Qutb Minar"});
        questionsMap.put(3, new String[]{"________________ authorized the construction of Qutb Minar", "Qutb-ud-din", "Ashoka", "Sikandar Lodi", "Shah Jahan", "Qutb-ud-din"});
        questionsMap.put(4, new String[]{"Qutub Minar was completed by", "Qutub-ud-din-Bakhtiyar", "Itlutmish", "Qutub-ud-din-Aibak", " Firuz Shah Tughlaq", "Itlutmish"});
        questionsMap.put(5, new String[]{"Qutub Minar is built in the memory of", "Illtumish", "Qutbuddin Aibak", "Qutbuddin Bakthiyar", " None of the above", "Qutbuddin Bakthiyar"});
        questionsMap.put(6, new String[]{"Qutub Minar is located in ___________", " New Delhi", " Hyderabad", "Secunderabad", "Maharashtra", " New Delhi"});
        questionsMap.put(7, new String[]{"Where is located Qutub Shahi Tombs?", "Andhra Pradesh", "Hyderabad", "Delhi", "Uttar Pradesh", "Hyderabad"});
        questionsMap.put(8, new String[]{"What is the English translation of Taj Mahal?", " Palaces of Princess", "Crown of Palaces", "Persian Palaces", "Palace of Rings", "Crown of Palaces"});
        questionsMap.put(9, new String[]{"Who was the architect of Taj Mahal?", "Mumtaz Mahal", "Shah Jahan", "Yamuna", "Ustad Ahmad Lahauri", "Ustad Ahmad Lahauri"});
        questionsMap.put(10, new String[]{"When was the Taj Mahal declared a winner of the New7Wonders of the World (2000–2007) initiative?", "2010", "2005", "2007", "2011", "2007"});
        questionsMap.put(11, new String[]{"Where is the Taj Mahal located?", "Agra, India", "Delhi, India", "Rabindranath Tagore", "Mumbai, India", "Agra, India"});
        questionsMap.put(12, new String[]{"Who was the Persian wife of the Mughal emperor whom the Taj Mahal was built for?", " Gauhara Begum", "Mumtaz Mahal", "Gur-e Amir", "Jama Masjid", "A Sufi Saint"});

    }
}
