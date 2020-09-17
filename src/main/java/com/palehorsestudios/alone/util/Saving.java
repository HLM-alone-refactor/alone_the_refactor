package com.palehorsestudios.alone.util;

import com.palehorsestudios.alone.player.Player;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saving {

    private final Path DEFAULT_PATH = Paths.get("resources", "games");

    public boolean saveState(String filename, Player player, String log) {

        // make sure the default dir is there
        DEFAULT_PATH.toFile().mkdirs();

        // remove any surrounding whitespace or file separators in name
        filename = filename.strip().replaceAll(File.pathSeparator, "");

        File file = DEFAULT_PATH.resolve(filename).toFile();

        return saveState(file, player, log);
    }

    /*
    package private for testing to use
     */
    boolean saveState(File file, Player player, String log) {

        // make sure file is there to write to.
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){

            // write objects to file
            oos.writeObject(player);
            oos.writeObject(log);

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public Player readPlayer(File file) {
        return findObject(file, Player.class);
    }

    public String readLog(File file) {
        return findObject(file, String.class);
    }

    private <T> T findObject(File file, Class<T> c) {
        T result = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (result == null) {
                try {
                    result = tryCast(ois.readObject(), c);
                } catch (EOFException e) {
                    break; // not found in the file
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private <I, O>  O tryCast(I input, Class<O> out) {
        if (out.isAssignableFrom(input.getClass())) {
            return out.cast(input);
        } else {
            return null;
        }
    }

}
