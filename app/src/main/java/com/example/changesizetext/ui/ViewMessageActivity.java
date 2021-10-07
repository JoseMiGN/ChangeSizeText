package com.example.changesizetext.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.changesizetext.ChangeSizeApplication;
import com.example.changesizetext.R;
import com.example.changesizetext.data.model.Message;
import com.example.changesizetext.databinding.ActivityViewMessageBinding;

/**
 * Esta clase visualiza un mensaje que se ha inicializado en
 * la clase ChangeSizeTextActivity
 * Se configura el componente TextVIew con un texto y un tamaño de fuente.
 */
public class ViewMessageActivity extends AppCompatActivity {

    private static final String TAG = "ChangeSizeProject";
    ActivityViewMessageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //1. Recoger intent
        Intent intent = getIntent();
        //2. Recoger el mensaje
        Message message = (Message) intent.getExtras().getSerializable("message");
        //3. Actualizar la vista
        binding.tvMessage.setText(message.getMessage());
        binding.tvMessage.setTextSize(message.getSize());
        /*
        Todas las Activity tienen acceso a la información de la clase
        Application desde el método getApplication. Se debe realizar un casting
        explicito.
         */
        Log.d(TAG, ((ChangeSizeApplication)getApplication()).getUser().toString());
    }
}