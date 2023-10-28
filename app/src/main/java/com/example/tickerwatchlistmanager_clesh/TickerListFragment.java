package com.example.tickerwatchlistmanager_clesh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;


public class TickerListFragment extends Fragment {

    ListView listView;
    public CustomViewModel viewModel;

    public TickerListFragment() {
        // Required empty public constructor
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
        public void onItemClick (AdapterView<?> parent, View view, int position, long id){
            Ticker selected = (Ticker) parent.getItemAtPosition(position);
            viewModel.setSelectedTicker(selected.getLink());
        }
    };


    public static TickerListFragment newInstance(String param1, String param2) {
        TickerListFragment fragment = new TickerListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView.setOnClickListener((View.OnClickListener) listener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentRoot = inflater.inflate(R.layout.fragment_ticker_list, container, false);
        listView = fragmentRoot.findViewById(R.id.list_view);
        viewModel = new ViewModelProvider(requireActivity()).get(CustomViewModel.class);
        LinkedList<Ticker> tick = new LinkedList<>();
        tick = viewModel.getTickers().getValue();
        ArrayAdapter<Ticker> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, tick);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
        return fragmentRoot;
    }

    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        viewModel = new ViewModelProvider(requireActivity()).get(CustomViewModel.class);

        viewModel.getTickers().observe(getViewLifecycleOwner(), new Observer<LinkedList<Ticker>>(){

            @Override
            public void onChanged(LinkedList<Ticker> tickers) {
                ArrayAdapter<Ticker> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, tickers);
                listView.setAdapter(adapter);
            }
        });


    }
}