package org.example;

public class Main {
    public static void main(String[] args) {
        MusicFile musicFile = new MusicFile();
        // Относительный путь к файлу в папке resources
        musicFile.playMusic("Music/Teya_Dora_Dzanum.mp3");
    }
}
