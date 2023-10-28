package com.example.tickerwatchlistmanager_clesh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class InfoWebFragment extends Fragment {

    WebView webView;
    private CustomViewModel viewModel;


    public InfoWebFragment() {
        // Required empty public constructor
    }


    public static InfoWebFragment newInstance(String param1, String param2) {
        InfoWebFragment fragment = new InfoWebFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentRoot = inflater.inflate(R.layout.fragment_info_web, container, false);
        webView = fragmentRoot.findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        webView.loadUrl("https://seekingalpha.com");
        return fragmentRoot;
    }

    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        viewModel = new ViewModelProvider(requireActivity()).get(CustomViewModel.class);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                webView.loadUrl(s);
            }
        };
        viewModel.getSelectedTicker().observe(getViewLifecycleOwner(), observer);

    }
}