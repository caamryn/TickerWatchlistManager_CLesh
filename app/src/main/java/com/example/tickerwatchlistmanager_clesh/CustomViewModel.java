package com.example.tickerwatchlistmanager_clesh;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import java.util.LinkedList;

public class CustomViewModel extends ViewModel {

    private MutableLiveData<String>SelectedTicker;
    private MutableLiveData<LinkedList<Ticker>> tickers;

    public void setSelectedTicker(String newt){
        if(SelectedTicker == null){
            SelectedTicker = new MutableLiveData<>();
            SelectedTicker.setValue("https://seekingalpha.com");
        }
        SelectedTicker.setValue(newt);
    }

    public MutableLiveData<String> getSelectedTicker(){
        if(SelectedTicker == null){
            SelectedTicker = new MutableLiveData<>();
            SelectedTicker.setValue("https://seekingalpha.com");
        }
        return SelectedTicker;
    }

    public MutableLiveData<LinkedList<Ticker>>getTickers(){
        if(tickers == null){
            tickers = new MutableLiveData<>();
            LinkedList<Ticker> t = new LinkedList<>();
            t.add(new Ticker("BAC", "https://seekingalpha.com"));
            t.add(new Ticker("APPL", "https://seekingalpha.com"));
            t.add(new Ticker("DIS", "https://seekingalpha.com"));
            tickers.setValue(t);
        }
        return tickers;
    }


    public void setTickers(){
        if(tickers == null){
            tickers = new MutableLiveData<>();
            LinkedList<Ticker> tick = new LinkedList<>();
            tick.add(new Ticker("BAC", "https://seekingalpha.com"));
            tick.add(new Ticker("APPL", "https://seekingalpha.com"));
            tick.add(new Ticker("DIS", "https://seekingalpha.com"));
            tickers.setValue(tick);
        }
    }

    public void addTicker(Ticker t){
        LinkedList<Ticker> tt = tickers.getValue();
        if(tt.size() < 5){
            tt.add(t);
            tickers.setValue(tt);
        }
        if(tt.size() == 5){
            tt.removeLast();
            tt.add(t);
            tickers.setValue(tt);
        }
    }




}
