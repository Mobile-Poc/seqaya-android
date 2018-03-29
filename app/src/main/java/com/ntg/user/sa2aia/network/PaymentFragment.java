package com.ntg.user.sa2aia.network;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ntg.user.sa2aia.MainActivity;
import com.ntg.user.sa2aia.R;
import com.ntg.user.sa2aia.ViewUtil;
import com.ntg.user.sa2aia.model.Order;
import com.ntg.user.sa2aia.model.PaymentMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout bankTransfer, sadad, creditCard;
    Order order;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        bankTransfer = view.findViewById(R.id.bankTransfer);
        sadad = view.findViewById(R.id.sadad);
        creditCard = view.findViewById(R.id.creditCard);
        ViewUtil.addShadowToView(getContext(), bankTransfer);
        ViewUtil.addShadowToView(getContext(), sadad);
        ViewUtil.addShadowToView(getContext(), creditCard);
        Bundle bundle = getArguments();
        order = (Order) bundle.getSerializable(MainActivity.ORDER);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bankTransfer.setOnClickListener(this);
        sadad.setOnClickListener(this);
        creditCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bankTransfer:
                order.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
                navigateToOrderSumery(order);
                break;
            case R.id.sadad:
                order.setPaymentMethod(PaymentMethod.SADAD);
                navigateToOrderSumery(order);
                break;
            case R.id.creditCard:
                order.setPaymentMethod(PaymentMethod.CREDIT_CARD);
                navigateToOrderSumery(order);
                break;

        }
    }

    public void navigateToOrderSumery(final Order order) {
        ProductService productService = ApiClient.getClient().create(ProductService.class);
        Call<Order> call = productService.addNewOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                //   getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
}
