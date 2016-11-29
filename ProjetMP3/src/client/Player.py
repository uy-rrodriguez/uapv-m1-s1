# http://blog.computerbacon.com/playing-audio-in-python-with-libvlc.html
# https://www.codementor.io/princerapa/tutorials/python-media-player-vlc-gtk-favehuy2b

import vlc
import sys, os

class Player:
  def __init__(self, port):
    self.instance = vlc.Instance()

    #Create a MediaPlayer with the default instance
    self.player = self.instance.media_player_new()

    # Global MRL
    self.mrl = "rtp://@:" + port


  def play(self):
    #Load media with streaming options
    self.media = self.instance.media_new(self.mrl)

    #Add the media to the player
    self.player.set_media(self.media)

    #Play for 10 seconds then exit
    self.player.play()


  def stop(self):
    #Reduce the volume to 70%
    #player.audio_set_volume(50)
    self.player.stop()
