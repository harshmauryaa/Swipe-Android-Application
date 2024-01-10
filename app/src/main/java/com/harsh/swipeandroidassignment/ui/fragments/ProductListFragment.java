package com.harsh.swipeandroidassignment.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harsh.swipeandroidassignment.R;
import com.harsh.swipeandroidassignment.adapters.ProductAdapter;
import com.harsh.swipeandroidassignment.ui.viewmodels.ProductListViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

public class ProductListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ProductListViewModel viewModel;
    private ProductAdapter productAdapter;
    private SearchView searchView;
    private KProgressHUD progressDialog;


    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product_list, container, false);

        searchView = root.findViewById(R.id.searchView);
        progressDialog = KProgressHUD.create(requireContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please Wait")
                .setDetailsLabel("Fetching the data...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        productAdapter = new ProductAdapter(requireContext());
        recyclerView.setAdapter(productAdapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        observeViewModel();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.filter(newText);
                return true;
            }
        });

    }
    private void observeViewModel() {
        progressDialog.show();
        viewModel.getProductListLiveData().observe(getViewLifecycleOwner(), productList -> {
            progressDialog.dismiss();
            if (productList!=null)
                productAdapter.submitList(productList);
            else
                Toast.makeText(requireContext(),"Unable to fetch the data. Please check your internet connection.",Toast.LENGTH_SHORT).show();
        });
    }


}