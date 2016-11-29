# http://blog.computerbacon.com/playing-audio-in-python-with-libvlc.html
# https://www.codementor.io/princerapa/tutorials/python-media-player-vlc-gtk-favehuy2b

import vlc
import time

instance = vlc.Instance()

#Create a MediaPlayer with the default instance
player = instance.media_player_new()

#Build vlc option string
# options = 'sout=#duplicate{dst=rtp{access=udp,mux=ts,dst=127.0.0.1,port=1233},dst=display}'
options = ""

#Load media with streaming options
# mrl = "test.mp3"
mrl = "rtp://@:1233"
media = instance.media_new(mrl, options)

#Load the media file
#media = instance.media_new('test.mp3')

#Add the media to the player
player.set_media(media)


#Reduce the volume to 70%
#player.audio_set_volume(50)

#Play for 10 seconds then exit
player.play()

time.sleep(60)
