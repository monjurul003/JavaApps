/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testproject.video;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;

 public class PlayerPanel extends JPanel {

     private File vlcInstallPath = new File("D:/vlc");
     private EmbeddedMediaPlayer player;

     public PlayerPanel() {
         NativeLibrary.addSearchPath("libvlc", vlcInstallPath.getAbsolutePath());
         EmbeddedMediaPlayerComponent videoCanvas = new EmbeddedMediaPlayerComponent();
         this.setLayout(new BorderLayout());
         this.add(videoCanvas, BorderLayout.CENTER);
         this.player = videoCanvas.getMediaPlayer();
     }

     public void play(String media) {
         player.prepareMedia(media);
         player.parseMedia();
         player.play();
     }
 }

 