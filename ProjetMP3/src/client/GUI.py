# Thanks to http://stackoverflow.com/a/14368421 for the pygame lib already builted
# Icons from https://www.iconfinder.com/iconsets/pictograms-vol-1
import sys, os
from sys import path as syspath
syspath.append(os.path.join(os.path.dirname(__file__), "lib"))
import pygame

COLOR_BACKGROUND = (6,19,25,0)

class Button(pygame.sprite.Sprite):
  def __init__(self, img):
    pygame.sprite.Sprite.__init__(self)
    self.image = pygame.image.load(img).convert_alpha()
    self.rect = self.image.get_rect()


class App():
  def __init__(self, srvProxy):
    self.srv = srvProxy

    # Configuration PyGame
    pygame.init()
    self.screen = pygame.display.set_mode((400, 600))

    # Images
    self.logo       = Button("img/logo.png")
    self.like       = Button("img/heart.png")
    self.songlist   = Button("img/list.png")
    self.melody     = Button("img/melody.png")
    self.pause      = Button("img/pause.png")
    self.play       = Button("img/play.png")
    self.power      = Button("img/power.png")
    self.reloadlist = Button("img/reload.png")
    self.search     = Button("img/search.png")
    self.sound      = Button("img/sound.png")
    self.tags       = Button("img/tags.png")
    self.upload     = Button("img/upload.png")

    # Liste de boutons
    self.buttons = [self.logo, self.like, self.songlist, self.melody,
                    self.pause, self.play, self.power, self.reloadlist,
                    self.search, self.sound, self.tags, self.upload]

    # Main menu
    self.changeScreen(self.main)


    # fond = pygame.image.load("background.jpg").convert()
    # perso = pygame.image.load("perso.png").convert_alpha()
    # image.set_colorkey((255,255,255))
    # fenetre.blit(fond, (0,0))
    # son = pygame.mixer.Sound("son.wav")
    # son.play()
    # son.stop()
    # pygame.mixer.pause()
    # pygame.mixer.unpause()
    # pygame.mixer.stop()
    # son.fadeout(300)
    # pygame.mixer.fadeout(300)
    # pygame.mixer.music.load("musique.wav")
    # pygame.mixer.music.queue("instruments.wav")
    # pygame.mixer.music.play()
    # pygame.mixer.music.stop()
    # pygame.mixer.music.pause() #Met la musique en pause
    # pygame.mixer.music.unpause() #Reprend la musique la ou elle a ete coupee
    # pygame.mixer.music.fadeout(400) #Fondu a 400ms de la fin des musiques
    # volume = pygame.mixer.music.get_volume() #Retourne la valeur du volume, entre 0 et 1
    # pygame.mixer.music.set_volume(0.5) #Met le volume a 0.5 (moitie)

    #self.master.title("Client MP3")
    #self.grid()
    #self.createWidgets()


  '''
    Code generique pour changer mettre a jour la fenetre
  --------------------------------------------------------------------------------
  '''
  def changeScreen(self, callback):
    self.screen.fill(COLOR_BACKGROUND)
    callback()
    pygame.display.flip()


  '''
    Affichage en forme de tableau
  --------------------------------------------------------------------------------
  '''
  def grid(self, elements=[], center=True, margin=0, left=0, top=0):
    surf = pygame.Surface((self.screen.get_width(), self.screen.get_height()))
    surf.fill(COLOR_BACKGROUND)
    width = left
    height = top

    # affichage des elements et calcul de la taille necessaire pour la surface
    for row in elements:
      row_width = left
      row_height = height

      for cell in row:
        surf.blit(cell.image, (row_width, height))
        rect = cell.rect
        rect.x = row_width
        rect.y = height

        row_width += rect.w + margin
        row_height = max(row_height, rect.h + margin)

      width = max(width, row_width)
      height += row_height

    sub = surf.subsurface((0, 0, width, height))
    final_rect = sub.get_rect(center = self.screen.get_rect().center)

    # Mise a jour de la position des elements
    for row in elements:
      for cell in row:
        cell.rect.x += final_rect.x
        cell.rect.y += final_rect.y


    self.screen.blit(sub, final_rect)


  '''
    Main menu
  --------------------------------------------------------------------------------
  '''
  def main(self):
    self.grid([[self.play, self.songlist, self.search],
               [self.upload, self.logo, self.power]],
              True, 10)


  '''
    Program loop
  --------------------------------------------------------------------------------
  '''
  def mainloop(self):
    while 1:
      for event in pygame.event.get():
        if event.type == pygame.QUIT:
          return 0

        elif event.type == pygame.MOUSEBUTTONDOWN:
          check = lambda btn: btn.rect.collidepoint(event.pos)
          if (check(self.power)):
            return 0



  #def createWidgets(self):
  #  self.quitButton = tk.Button(self, text="Quit", command=self.quit)
  #  self.quitButton.grid()

