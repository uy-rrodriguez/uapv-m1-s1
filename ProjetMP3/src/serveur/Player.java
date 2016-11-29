// http://capricasoftware.co.uk/#/projects/vlcj/faq
// https://wiki.videolan.org/Media_resource_locator/
// https://wiki.videolan.org/Transcode/

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

import com.sun.jna.NativeLibrary;


public class Player {

  private final AudioMediaPlayerComponent mediaPlayerComponent;
  private static final String PORT = "1233";
  private static final String OPT = "sout=#rtp{access=udp,mux=ts,dst=<ip>,port=" + PORT + "}";


  public Player() {
    // Import des bibliotheques libvlc et libvlccore (.dll ou .os, Win ou Linux)
    boolean found = new NativeDiscovery().discover();
    if (! found)
      NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib/libvlc");

    //System.out.println(LibVlc.INSTANCE.libvlc_get_version());

    mediaPlayerComponent = new AudioMediaPlayerComponent();
    mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void stopped(MediaPlayer mediaPlayer) {
        exit(0);
      }

      @Override
      public void finished(MediaPlayer mediaPlayer) {
        exit(0);
      }

      @Override
      public void error(MediaPlayer mediaPlayer) {
        exit(1);
      }
    });
  }

  public void start(String mrl, String ipDest) {
    mediaPlayerComponent.getMediaPlayer().playMedia(mrl, OPT.replace("<ip>", ipDest));
  }

  public void stop() {
    mediaPlayerComponent.release();
  }

  public void exit(int result) {
    mediaPlayerComponent.release();
    //System.exit(result);
  }
}
