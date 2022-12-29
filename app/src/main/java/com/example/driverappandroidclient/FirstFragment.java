package com.example.driverappandroidclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.driverappandroidclient.asynctasks.GetAdviceAsyncTask;
import com.example.driverappandroidclient.asynctasks.GetAllAdvicesAsyncTask;
import com.example.driverappandroidclient.asynctasks.SendAdviceRatingAsyncTask;
import com.example.driverappandroidclient.auxiliaries.Auxiliaries;
import com.example.driverappandroidclient.model.Advice;
import com.example.driverappandroidclient.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    public FragmentFirstBinding binding;
    //    long adviceNumberToShow;
    private Advice currentAdvice = null;
    private Toast toast;

    public Advice getCurrentAdvice() {
        return currentAdvice;
    }

    public void setCurrentAdvice(Advice currentAdvice) {
        this.currentAdvice = currentAdvice;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        binding.textviewName.setText("");
        binding.textviewContent.setText("");
        binding.textViewAdvicesCount.setText("");


        binding.buttonLoadAdvices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.textViewAdvicesCount.setText("aaa");;

                new GetAllAdvicesAsyncTask(FirstFragment.this).execute("100");

//                MyAsyncTask runner = new MyAsyncTask();
//                runner.execute("1");
                showInToast("Załadowano");


            }
        });

        binding.buttonAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ImageGetAsyncTask(FirstFragment.this).execute("100");

                if (binding.editTextAdviceNumber.getText().length() > 0) {
                    new GetAdviceAsyncTask(FirstFragment.this).execute(String.valueOf(binding.editTextAdviceNumber.getText()));
                } else {
                    binding.textviewName.setText("Podaj numer porady");
                }

            }
        });


        binding.buttonAdviceSendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentAdvice != null) {
                    if (Auxiliaries.isInt(binding.editTextAdviceRating.getText().toString())) {
                        String[] params = {binding.editTextAdviceRating.getText().toString(),
                                String.valueOf(currentAdvice.getId())};
                        new SendAdviceRatingAsyncTask(FirstFragment.this)
                                .execute(params);

                    } else {
//                        binding.editTextAdviceRating.setText("Podaj ocenę");
                        binding.editTextAdviceRating.setHint("Podaj ocenę");
                    }
                }else {
                    binding.textviewName.setText("Wybierz poradę");
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void showInToast(String st) { //"Toast toast" is declared in the class
        try {
            toast.getView().isShown();     // true if visible
            toast.setText(st);
        } catch (Exception e) {         // invisible if exception
//            toast = Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT);
            toast = Toast.makeText(getContext(), st, Toast.LENGTH_SHORT);
        }
        toast.show();  //finally display it
    }


}