package com.example.quizgame.data;

import java.util.ArrayList;

public class Question {

    public ArrayList<Option> options;
    public ArrayList<Integer> correct_answers;
    public String lable;

    public String getLable() {
        return lable;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public ArrayList<Integer> getCorrect_answers() {
        return correct_answers;
    }

}
