package com.wzh.geoquiz;

/**
 * Created by Administrator on 2015/9/4.
 */
public class Question {
    private int mQuestion;
    private boolean isTure;

    public Question (int mQuestion,boolean isTure){
        this.mQuestion=mQuestion;
        this.isTure=isTure;
    }
    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }
    public int getmQuestion() {
        return mQuestion;
    }

    public void setIsTure(boolean isTure) {
        this.isTure = isTure;
    }

    public boolean getIsTure(){
        return isTure;
    }

}
