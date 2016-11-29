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

public class TestVLC {

  private final AudioMediaPlayerComponent mediaPlayerComponent;

  public static void main(String[] args) {
    boolean found = new NativeDiscovery().discover();
    if (! found) {
      NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "lib/libvlc");
    }
    System.out.println(LibVlc.INSTANCE.libvlc_get_version());


    TestVLC tutorial = new TestVLC();

    //tutorial.start(args[0]);

    //String options = "sout=#duplicate{dst=rtp{access=udp,mux=ts,dst=127.0.0.1,port=1233},dst=display}";
    String options = "sout=#rtp{access=udp,mux=ts,dst=127.0.0.1,port=1233}";
    String mrl = "test.mp3";

    //String mrl = "rtp://127.0.0.1:1233";
    tutorial.start(mrl, options);

    try {
      Thread.currentThread().join();
    }
    catch(InterruptedException e) {
    }
  }

  private TestVLC() {
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

  private void start(String mrl, String opt) {
    mediaPlayerComponent.getMediaPlayer().playMedia(mrl, opt);
  }

  private void exit(int result) {
    mediaPlayerComponent.release();
    System.exit(result);
  }
}
