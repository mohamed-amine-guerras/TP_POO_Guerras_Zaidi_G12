package com.company.model;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Amine on 17/04/2017.
 */
public class LoginChecker {
    private String usersFile;
    private HashMap<String,Player> usersHashMap ;
    public LoginChecker(){}

    public LoginChecker(String usersFile) {
        this.usersFile = usersFile;
    }
    /**
     * Cette méthode vérifie si un joueur existe ou pas dans le fichier des joueurs
     * **/
    public boolean Find(String pseudonyme) throws IOException, ClassNotFoundException {

        InitializeUsersHashMap();

        return usersHashMap.containsKey(pseudonyme);
    }
    /**
     * Cette méthode initialise la HashMap qui contient les joueurs à partir du fichier des joueurs
     * **/
    private void InitializeUsersHashMap()  {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(usersFile))));
            usersHashMap = (HashMap<String, Player>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            usersHashMap = new HashMap<>();
        }
    }
    /**
    * *Cette méthode ajoute un joueur au fichier des joueurs
     * */
    public void AddPlayer(Player player) throws IOException, ClassNotFoundException {
        InitializeUsersHashMap();
       if (usersHashMap.containsKey(player.getPseudonyme())) {
            usersHashMap.remove(player.getPseudonyme());
        }
            usersHashMap.put(player.getPseudonyme(), player);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(usersFile))));
            objectOutputStream.writeObject(usersHashMap);
            objectOutputStream.close();

        }


        public Player getPlayer(String pseudonyme){

        InitializeUsersHashMap();
        return (Player) usersHashMap.get(pseudonyme);

        }



        }



