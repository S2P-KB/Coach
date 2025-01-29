
package com.example.coach.controleur;

import android.content.Context;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;


public final class Controle {
    private static Controle instance;
    private static Profil profil;
    private static String nomFic = "saveprofil";

    private Controle(Context context){
        // Super();
        recupSerialize(context);
    }

    public final static Controle getInstance(Context context) {
        if (instance == null) {
            instance = new Controle(context);
        }
        return instance;
    }

    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic, profil, context);
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
