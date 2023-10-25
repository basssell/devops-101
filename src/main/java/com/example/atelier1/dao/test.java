package com.example.atelier1.dao;

public class test {
    public static void main(String[] args) {
        try {
            // Tentez de vous connecter à la base de données
            DbConnection.getConnection();
            System.out.println("Connexion réussie à la base de données!");

            // Fermez la connexion après le test
            DbConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("Échec de la connexion à la base de données!");
            e.printStackTrace();  // cela imprime le détail de l'erreur
        }
    }

}
