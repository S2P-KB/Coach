package com.example.coach.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach.*;
import com.example.coach.controleur.Controle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private Controle controle;
    private EditText txtPoids, txtTaille, txtAge;
    private TextView lblImg;
    private RadioButton rdHomme, rdFemme;
    private ImageView imgSmiley;
    private Button btnCalc;

    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        lblImg = (TextView) findViewById(R.id.lblImg);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();
    }

    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0, taille = 0, age = 0, sexe = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}

                if(rdHomme.isChecked()) {
                    sexe = 1;
                }
                if(poids == 0 || taille == 0 || age == 0){
                    Toast.makeText(MainActivity.this, "Veuillez saisir une valeur dans chaque champ.", Toast.LENGTH_SHORT).show();
                } else{
                    affichResult(poids, taille, age, sexe);
                }
            }
        });
    }

    private void affichResult(int poids, int taille, int age, int sexe){
        controle.creerProfil(poids, taille, age, sexe, this);
        float imc = controle.getImc();
        String message = controle.getMessage();
        switch(message){
            case "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblImg.setTextColor(Color.GREEN);
                break;
            case "trop maigre":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblImg.setTextColor(Color.RED);
                break;
            case "trop de graisse":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblImg.setTextColor(Color.RED);
                break;
        }
        lblImg.setText(String.format("%.01f", imc)+" : IMG "+message);
    }

    private void recupProfil(){
        if(controle.getTaille() != null) {
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            if(controle.getSexe() == 0){
                rdFemme.setChecked(true);
            }else{
                rdHomme.setChecked(true);
            }
            btnCalc.performClick();
        }
    }


}