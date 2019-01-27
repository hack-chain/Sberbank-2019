//package com.Sberbank.Sberbank.Payments;
//
//import java.util.ArrayList;
//import java.util.concurrent.RecursiveAction;
//
//public class Forker extends RecursiveAction {
//    private final String userId;
//    private final ArrayList<String> payers;
//    private final boolean isRoot;
//
//    public Forker(String userId, ArrayList<String> payers, boolean isRoot) {
//        this.userId = userId;
//        this.payers = payers;
//        this.isRoot = isRoot;
//    }
//
//    @Override
//    protected void compute() {
//        if (!isRoot) {
////            while (!QiwiPayer.checkWithRegExp()) {
////                sleep(5s);
////            }
//            System.out.println(userId);
//        }
//
////        for (String userId : payers) {
////            Forker task = new Forker(userId, payers, false);
////            task.fork(); // запустим асинхронно
////        }
//
//        int i = 0;
//        while (true) {
//            if (payers.size() > i) {
//                Forker task = new Forker(payers.get(i), payers, false);
//                task.fork(); // запустим асинхронно
//                i++;
//            }
//        }
//
//    }
//
//}
