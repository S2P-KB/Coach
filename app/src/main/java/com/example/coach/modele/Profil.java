
package com.example.coach.modele;

import com.example.coach.outils.Serializer;

import java.util.Objects;

public class Profil extends Serializer {
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float imc = 0;
    private String message = "";

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImc() {
        if(imc == 0){
            float size = (float) taille / 100;
            imc = (float) (1.2 * (poids / (size * size)) + (0.23 * age) - (10.83 * sexe) - 5.4);
        }
        return imc;
    }

    public String getMessage() {
        if(message.equals("")){
            message = "normal";
            Integer min = minFemme, max = maxFemme;
            if(sexe == 1){
                min = minHomme;
                max = maxHomme;
            }
            imc = getImc();
            if(imc < min){
                message = "trop maigre";
            }else{
                if(imc > max){
                    message = "trop de graisse";
                }
            }
        }
        return message;
    }
}
