package com.harsh.swipeandroidassignment.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harsh.swipeandroidassignment.NotificationHelper;
import com.harsh.swipeandroidassignment.R;
import com.harsh.swipeandroidassignment.data.model.ProductModel;
import com.harsh.swipeandroidassignment.data.model.ProductResponse;
import com.harsh.swipeandroidassignment.ui.viewmodels.AddProductViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;


public class AddProductFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddProductFragment() {

    }

    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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

    private AddProductViewModel viewModel;
    private EditText productNameEditText;
    private Spinner productTypeSpinner;
    private EditText priceEditText;
    private EditText taxEditText;
    private KProgressHUD progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_product, container, false);

        progressDialog = KProgressHUD.create(requireContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please Wait")
                .setDetailsLabel("Fetching the data...")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f);

        productNameEditText = root.findViewById(R.id.editTextPrdName);
        productTypeSpinner = root.findViewById(R.id.spinnerPrdType);
        priceEditText = root.findViewById(R.id.editTextPrdPrice);
        taxEditText = root.findViewById(R.id.editTextPrdTax);
        Button addButton = root.findViewById(R.id.addPrdButton);

        addButton.setOnClickListener(v -> addProduct());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddProductViewModel.class);

        viewModel.getAddProductLiveData().observe(getViewLifecycleOwner(), productResponse -> {

            progressDialog.dismiss();

            if(productResponse!=null && productResponse.isSuccess())
            {
                productNameEditText.getText().clear();
                priceEditText.getText().clear();
                taxEditText.getText().clear();

                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.custom_dialog);

                int productId = productResponse.getProductId();
                String message = productResponse.getMessage();
                boolean isSuccess = productResponse.isSuccess();

                String status = isSuccess ? "Success" : "Failure";
                String fullMessage = "Product Id: " + productId + "\nMessage: " + message + "\nStatus: " + status;

                TextView viewMessage = dialog.findViewById(R.id.textView45);
                viewMessage.setText(fullMessage);

                Button apply = dialog.findViewById(R.id.button);
                apply.setOnClickListener(v1 -> {
                    dialog.cancel();
                });
                dialog.show();

                NotificationHelper.showNotification(requireContext(), fullMessage);

            }
            else
            {
                String fullMessage = "Product Id: NaN\nFailed to add product: \nStatus: NaN";

                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.custom_dialog);

                TextView viewMessage = dialog.findViewById(R.id.textView45);
                viewMessage.setText(fullMessage);

                Button apply = dialog.findViewById(R.id.button);
                apply.setOnClickListener(v1 -> {
                    dialog.cancel();
                });
                dialog.show();

                NotificationHelper.showNotification(requireContext(), fullMessage);
            }

        });
    }

    private void addProduct() {

        String productName = productNameEditText.getText().toString().trim();
        String productType = productTypeSpinner.getSelectedItem().toString();
        String price = priceEditText.getText().toString().trim();
        String tax = taxEditText.getText().toString().trim();

        if (productName.isEmpty() || productType.isEmpty() || price.isEmpty() || tax.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        boolean isPriceValid = isValidDecimalNumber(price);
        boolean isTaxValid = isValidDecimalNumber(tax);

        if (!isPriceValid || !isTaxValid) {
            Toast.makeText(requireContext(), "Please enter valid price and tax", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        viewModel.addProduct(productName, productType, price, tax);
    }

    private boolean isValidDecimalNumber(String number) {
        try {
            double value = Double.parseDouble(number);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}