package ru.suleymanovtat.dressingbabyweather.presentation.home.adapter;

public interface DiffItem {
    long getItemId();

    boolean isHeader();

    int getItemHash();
}
