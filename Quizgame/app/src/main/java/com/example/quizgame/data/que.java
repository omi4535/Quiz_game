//package com.example.quizgame.data;
//
//import java.util.ArrayList;
//
//public class que {
//     public data get_ques(){
//        data d = new data();
//        d.code =200;
//         Question q1 =new Question();
//         q1 = make_que("Which one of the following river flows between Vindhyan and Satpura ranges?",
//                "Narmada","Mahanadi","Son","Netravati");
//        q1.correct_answers.add(1);
//
////
////        Question q2 = make_que("Which one of the following river does not flows between Vindhyan and Satpura ranges?",
////                "Narmada","Mahanadi","Son","Netravati");
////        q1.correct_answers.add(2);
////        q1.correct_answers.add(3);
////        q1.correct_answers.add(4);
////
////
////        Question q3 = make_que("The Central Rice Research Station is situated in?",
////                "Chennai","Quilon","Bangalore","Cuttack");
////        q1.correct_answers.add(4);
////
////
////        Question q4 = make_que("Who among the following wrote Sanskrit grammar?",
////                "Aryabhatt","Panini","Charak","Kalidasa");
////        q1.correct_answers.add(2);
////
////
////        Question q5 = make_que("Which one of the following are the city of US",
////                "New York","Portland","Pune","Mumbai");
////        q1.correct_answers.add(2);
////        q1.correct_answers.add(1);
////
////        Question q6 = make_que("What is weight of earth ?",
////                "5.972 × 10^24 kg","5.972 × 10^21 t","Infinite","1.317 × 10^25 lbs");
////        q1.correct_answers.add(2);
////        q1.correct_answers.add(4);
////        q1.correct_answers.add(1);
////
//        d.result.questions.add(q1);
////        d.result.questions.add(q2);
////        d.result.questions.add(q3);
////        d.result.questions.add(q4);
////        d.result.questions.add(q5);
////        d.result.questions.add(q6);
////        d.result.timeInMinutes = 5;
//
//        return d;
//    }
//
//    ArrayList<Option> makeOptions(String st1, String st2, String st3, String st4){
//        Option op1 = new Option();
//        op1 .key = 1;
//        op1 .lable = st1;
//        Option op2 = new Option();
//        op2 .key = 2;
//        op2 .lable = st2;
//        Option op3 = new Option();
//        op3 .key = 3;
//        op3 .lable = st3;
//        Option op4 = new Option();
//        op4 .key = 4;
//        op4 .lable = st4;
//
//        ArrayList<Option> op = new ArrayList<Option>();
//        op.add(op1);
//        op.add(op2);
//        op.add(op3);
//        op.add(op4);
//        return op;
//    }
//
//    Question make_que(String l,String st1, String st2, String st3, String st4){
//        Question q = new Question();
//        q.lable = l;
//        q.options = makeOptions(st1,st2,st3,st4);
//        return q;
//    }
//
//
//}
