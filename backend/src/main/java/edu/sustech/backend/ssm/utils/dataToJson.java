//package edu.sustech.backend.ssm.utils;
//
//import com.google.gson.Gson;
//
//import javax.xml.crypto.Data;
//import java.util.ArrayList;
//import java.util.List;
//
//public class dataToJson {
//
//    private static Gson gson = new Gson();
//
//    public static void main(String[] args) {
//        Data data = new Data("May-2-3", "springboot", "1.3k", 1);
//        Data data1 =  new Data("July-1-2", "springtest", "2.2k", 2);
//
//        List<Data> lists = new ArrayList<>();
//        lists.add(data);
//        lists.add(data1);
//
//    }
//
//    public static String dataToJson(List<Data> lists){
//        dataFormat result = new dataFormat();
//
//        for (Data d : lists){
//            result.getDate().add(d.getDate());
//            result.getStars().add(d.getStars());
//        }
//
//        return gson.toJson(result);
//    }
//
//}
