
package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;


public final class Controle {
    private static Controle instance;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private AccesLocal accesLocal;

    private Controle(Context context){
        //  recupSerialize(context);
        accesLocal = AccesLocal.getInstance(context);
        profil = accesLocal.recupDernier();
    }

    public final static Controle getInstance(Context context) {
        if (instance == null) {
            instance = new Controle(context);
        }
        return instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(poids, taille, age, sexe, new Date());
        accesLocal.ajout(profil);
        // Serializer.serialize(nomFic, profil, context);
    }

    public float getImc(){
        if(profil == null){
            return 0;
        }
        return profil.getImc();
    }
    public String getMessage(){
        if(profil == null){
            return "";
        }
        return profil.getMessage();
    }

    public Integer getTaille(){
        if(profil == null){
            return null;
        }
        return profil.getTaille();
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        }
        return profil.getPoids();
    }

    public Integer getAge(){
        if(profil == null){
            return null;
        }
        return profil.getAge();
    }

    public Integer getSexe(){
        if(profil == null){
            return null;
        }
        return profil.getSexe();
    }

    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }

}
