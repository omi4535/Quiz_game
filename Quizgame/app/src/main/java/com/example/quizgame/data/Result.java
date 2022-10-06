package com.example.quizgame.data;

import java.util.ArrayList;

public class Result {
    public int timeInMinutes;
    public ArrayList<Question> questions;

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
