package com.aldaircruz.droidtek.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.aldaircruz.droidtek.R;
import com.aldaircruz.droidtek.data.StaticData;
import com.aldaircruz.droidtek.model.Client;

import java.util.ArrayList;

public class AvNewClient extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av__new_client);
    }

    public void saveCliente(View view) {

        EditText edtNome = (EditText)findViewById(R.id.edt_nome);
        EditText edtMorada = (EditText)findViewById(R.id.edt_morada);
        EditText edtEmail = (EditText)findViewById(R.id.edt_email);
        EditText edtTelefone = (EditText)findViewById(R.id.edt_telefone);
        EditText edtDataNasc = (EditText)findViewById(R.id.edt_nascimento);


        CheckBox chb_cultura = (CheckBox)findViewById(R.id.chb_cultura);
        CheckBox chb_farmacia = (CheckBox)findViewById(R.id.chb_farmacy);
        CheckBox chb_mercearia = (CheckBox)findViewById(R.id.chb_mercearia);
        CheckBox chb_roupa = (CheckBox)findViewById(R.id.chb_roupa);


        String nome = edtNome.getText().toString();
        String morada = edtMorada.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String dataNasc = edtDataNasc.getText().toString();


        ArrayList<Client>clients = new ArrayList<>();
        Client client = new Client();

        client.setNome(nome);
        client.setDatanasc(dataNasc);
        client.setEmail(email);
        client.setMorada(morada);


        String noticias="";
        if(chb_farmacia.isChecked()) noticias+="farmacia;";
        if(chb_mercearia.isChecked())noticias+="mercearia;";
        if(chb_cultura.isChecked())noticias+="cultura;";
        if(chb_roupa.isChecked())noticias+="roupa;";

        client.setNoticias(noticias);

        clients.add(client);
        StaticData.client = clients;

        Intent intent = new Intent(this, AvCart.class);
        startActivity(intent);

    }
}
