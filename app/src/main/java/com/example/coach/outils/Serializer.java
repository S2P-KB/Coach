package com.example.coach.outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import android.content.Context;

/**
 * Classe qui permet de serialiser et deserialiser des objets
 * @author Emds
 *
 */
public abstract class Serializer {

    /**
     * Serialisation d'un objet
     * @param filename  nom du fichier
     * @param object    ce qu'il faut enregistrer
     */
    public static void serialize(String filename, Object object, Context context) {
        try {
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE) ;
            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(file);
                oos.writeObject(object) ;
                oos.flush() ;
                oos.close() ;
            } catch (IOException e) {
                // Erreur de serialisation
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Fichier non trouvé
            e.printStackTrace();
        }
    }

    /**
     * Déserialisation d'un objet
     * @param filename  nom du fichier
     * @param context   contexte
     * @return null
     */
    public static Object deSerialize(String filename, Context context) {
        try {
            FileInputStream file = context.openFileInput(filename) ;
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject() ;
                    ois.close() ;
                    return object ;
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // Fichier non trouvé
            e.printStackTrace();
        }
        return null ;
    }

}