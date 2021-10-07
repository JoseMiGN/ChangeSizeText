package com.example.changesizetext.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.changesizetext.ChangeSizeApplication;
import com.example.changesizetext.R;
import com.example.changesizetext.data.model.Message;
import com.example.changesizetext.data.model.User;
import com.example.changesizetext.databinding.ActivityChangeSizeTextBinding;

/**
 * Clase que pide al usuario un texto y un tamaño mediante el componente SeekBar.
 * Despues se inicializa un TextView @see {@link ViewMessageActivity#onCreate(Bundle)}
 * @author Jose Miguel Godoy
 * @version 1.0
 */
public class ChangeSizeTextActivity extends AppCompatActivity {

    private static final String TAG = "ChangeSizeTextProject";
    ActivityChangeSizeTextBinding binding;
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeSizeTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btSend.setOnClickListener(v -> {
            //1. Crear objeto Bundle
            Bundle bundle = new Bundle();
            //2. Añadir el objeto mensaje al Bundle (contiene el usuario[nombre y correo], un mensaje y un entero)
            Message message = new Message(
                    ((ChangeSizeApplication) getApplication()).getUser(), // Casting de Application a ChangeSizeApplication, crear metodo getUser en la clase ChangeSizeApplication
                    binding.edMessage.getText().toString(),
                    binding.skSize.getProgress()
            );
            bundle.putSerializable("message", message); // Implementar la clase Serializable
            //3. Enviar Intent con el Bundle
            Intent intent = new Intent(this, ViewMessageActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        Log.d(TAG, "ChangeSizeTextActivity -> OnCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ChangeSizeTextActivity -> OnStart()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ChangeSizeTextActivity -> OnStop()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ChangeSizeTextActivity -> OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ChangeSizeTextActivity -> OnPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ChangeSizeTextActivity -> OnDestroy()");
    }
}