package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ui ui = new Ui();
            ui.createAndShowGUI();
            ui.addToPlaylist("Music/Night_Lovell_Still_Cold_Cotneus_Remix_2023.mp3");
        });
    }
}
