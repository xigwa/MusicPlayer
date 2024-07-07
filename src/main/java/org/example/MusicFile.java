package org.example;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicFile {
    private AdvancedPlayer player;
    private Thread playerThread;
    private List<String> playlist;
    private int currentSongIndex;
    private int pausedFrame = 0;
    private boolean isPaused = false;

    public MusicFile() {
        playlist = new ArrayList<>();
        currentSongIndex = -1;
    }

    public void addToPlaylist(String filePath) {
        playlist.add(filePath);
    }

    public void playMP3(int index) {
        if (index < 0 || index >= playlist.size()) {
            throw new IllegalArgumentException("Invalid song index");
        }

        stop();
        currentSongIndex = index;
        String filePath = playlist.get(currentSongIndex);
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            player = new AdvancedPlayer(inputStream);
            playerThread = new Thread(() -> {
                try {
                    player.setPlayBackListener(new PlaybackListener() {
                        @Override
                        public void playbackFinished(PlaybackEvent evt) {
                            pausedFrame = evt.getFrame();
                        }
                    });
                    player.play(pausedFrame, Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            playerThread.interrupt();
            player = null;
            pausedFrame = 0;
        }
    }

    public String getCurrentSong() {
        if (currentSongIndex >= 0 && currentSongIndex < playlist.size()) {
            return playlist.get(currentSongIndex);
        } else {
            return "No song playing";
        }
    }

    public int getPlaylistSize() {
        return playlist.size();
    }
}
