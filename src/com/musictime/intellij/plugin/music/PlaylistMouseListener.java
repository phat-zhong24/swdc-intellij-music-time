package com.musictime.intellij.plugin.music;

import com.google.gson.JsonObject;
import com.musictime.intellij.plugin.SoftwareCoUtils;
import com.musictime.intellij.plugin.SoftwareResponse;
import com.musictime.intellij.plugin.actions.MusicToolWindow;
import com.musictime.intellij.plugin.models.DeviceInfo;
import com.musictime.intellij.plugin.musicjava.Apis;
import com.musictime.intellij.plugin.musicjava.DeviceManager;
import com.musictime.intellij.plugin.musicjava.MusicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaylistMouseListener extends MouseAdapter {
    PlaylistTree playlistTree;

    public PlaylistMouseListener(PlaylistTree playlistTree) {
        this.playlistTree = playlistTree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        PlaylistTreeNode node = (PlaylistTreeNode)
                playlistTree.getLastSelectedPathComponent();

        /* if nothing is selected */
        if (node == null || node.getId() == null) return;

        boolean parentExpanded = true;
        if (node.isLeaf() && node.getParent() != null) {
            // it's a child node
            PlaylistTreeNode parent = (PlaylistTreeNode) node.getParent();
            MusicControlManager.currentPlaylistId = parent.getId();
            MusicControlManager.currentTrackId = node.getId();
            MusicControlManager.currentTrackName = node.getName();
        } else if (!node.isLeaf() && node.getId() != null && node.getChildCount() > 0) {
            // it's a root node
            MusicControlManager.currentPlaylistId = node.getId();
            MusicControlManager.currentTrackId = ((PlaylistTreeNode)node.getChildAt(0)).getId();
            if (MusicControlManager.currentTrackId == null) {
                // still hasn't expanded
                parentExpanded = false;
            }
        }

        if (!parentExpanded) {
            // don't start playing the playlist unless they've expanded it
            return;
        }

        if (e.getButton() == 3) { // right-click button
            if(MusicControlManager.currentPlaylistId != null) {
                JPopupMenu popupMenu;
                if (node.isLeaf()) {
                    popupMenu = PopupMenuBuilder.buildSongPopupMenu(MusicControlManager.currentTrackId, MusicControlManager.currentPlaylistId);
                } else {
                    popupMenu = PopupMenuBuilder.buildPlaylistPopupMenu(MusicControlManager.currentPlaylistId);
                }

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        } else if(e.getButton() == 1) { // track play click
            DeviceManager.getDevices();
            DeviceInfo currentDevice = DeviceManager.getBestDeviceOption();

            if (currentDevice == null) {
                // first we need to launch a device in order to play anything
                MusicControlManager.launchPlayer();
                MusicToolWindow.lazilyCheckDeviceLaunch(5, true);
            } else {
                // play it
                PlayerControlManager.playSpotifyPlaylist();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        JTree tree=(JTree) e.getSource();
        tree.clearSelection();
    }
}
