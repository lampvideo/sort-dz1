package com.company;
public class StatInfo {
    public void StatInfo(String algoName, int arr_size) {
        this.mAlgoName = algoName;
        this.mArraySize = arr_size;
    }
    public void StatInfo() {

    }
    private long mDuration;
    private String mAlgoName;
    private int mArraySize;
    private long mStart;
    private long mFinish;
    private long mExchanges;
    private long mCompares;
    //Корректно ли отсортирован массив
    private boolean mCorrect;
    //вызываем перед расчетом времени работы алгоритма
    public void start() {
        mStart = System.currentTimeMillis();
    }
    //при завершении рассчитываем продолжительность алгоритма
    public void stopTimer() {
        this.mFinish = System.currentTimeMillis();
        this.mDuration = mFinish - mStart;
    }
    //название алгоритма
    public void setDuration(long duration) {
        mDuration = duration;
    }
    //время работы алгоритма
    public long duration() {
        return mDuration;
    }
    //название алгоритма
    public void setAlgoName(String algoName)
    {
        mAlgoName = algoName;
    }

    //название алгоритма
    public String algoName() {
        return mAlgoName;
    }

    //размер сортируемого массива
    public int arraySize() {
        return mArraySize;
    }

    //размер сортируемого массива
    public void setArraySize(int size) {
        mArraySize = size;
    }

    //обмены
    public void setExchanges(long exchanges) {
        mExchanges = exchanges;
    }
    
    //сравнения
    public void setCompares(long compares) {
        mCompares = compares;
    }

    //Корректность сортировки (отсортирован или нет массив)
    public void setCorrect(boolean res) {
        mCorrect = res;
    }

    //корректен ли алгоритм сортировки
    public boolean correct( ) {
        return mCorrect;
    }
}
