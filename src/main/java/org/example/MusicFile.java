package org.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;

public class MusicFile {
    public void playMusic(String filePath) {
        try {
            // Используем getResourceAsStream для чтения файла из папки resources
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new IllegalArgumentException("Файл не найден: " + filePath);
            }
            AdvancedPlayer player = new AdvancedPlayer(inputStream);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
