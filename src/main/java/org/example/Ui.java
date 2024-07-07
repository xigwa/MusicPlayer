package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ui extends JFrame {
    private MusicFile musicFile;
    private JLabel statusLabel;
    private JLabel songLabel;
    private JList<String> playlist;
    private DefaultListModel<String> listModel;
    private JButton playButton;
    private JButton stopButton;

    public Ui() {
        musicFile = new MusicFile();
        listModel = new DefaultListModel<>();
        playlist = new JList<>(listModel);
    }

    public void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        playButton = new JButton("Play");
        stopButton = new JButton("Stop");
        statusLabel = new JLabel("Status: Stopped");
        songLabel = new JLabel("No song playing");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = playlist.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String selectedSong = listModel.getElementAt(selectedIndex);
                    musicFile.playMP3(selectedIndex);
                    statusLabel.setText("Status: Playing");
                    songLabel.setText(selectedSong);
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicFile.stop();
                statusLabel.setText("Status: Stopped");
                songLabel.setText("No song playing");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(new JScrollPane(playlist), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(playButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(stopButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(songLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(statusLabel, gbc);

        setVisible(true);
    }

    public void addToPlaylist(String songPath) {
        String fileName = new File(songPath).getName(); // Получаем чистое имя файла
        String songName = fileName.replace("_", " ").replace(".mp3", ""); // Убираем подчеркивания и расширение
        listModel.addElement(songName); // Добавляем в список чистое имя файла без расширения
        musicFile.addToPlaylist(songPath); // Добавляем полный путь к файлу в MusicFile
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ui ui = new Ui();
            ui.addToPlaylist("Music/Night_Lovell_Still_Cold_Cotneus_Remix_2023.mp3");
            ui.addToPlaylist("Music/Another_Song.mp3");
            ui.createAndShowGUI();
        });
    }
}
