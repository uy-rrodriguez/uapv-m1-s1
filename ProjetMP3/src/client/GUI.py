# Thanks to http://stackoverflow.com/a/14368421 for the pygame lib already builted
# Icons from https://www.iconfinder.com/iconsets/pictograms-vol-1
import sys, os
from sys import path as syspath
syspath.append(os.path.join(os.path.dirname(__file__), "lib"))
import pygame

COLOR_BACKGROUND = (6,19,25,0)
FONT = None

class WidgetCollection:
  buttons = []
  button_over = None

  @staticmethod
  def deactivate_all():
    for b in WidgetCollection.buttons:
      b.deactivate()

  @staticmethod
  def checkButtonClick(evtOnClick):
    for b in WidgetCollection.buttons:
      if b.is_active() and b.rect.collidepoint(evtOnClick.pos):
        b.doClick()
        return

  @staticmethod
  def checkMouseOverButton(evtMouseMotion):
    b = WidgetCollection.button_over
    if (b != None and b.is_active()
        and not b.rect.collidepoint(evtMouseMotion.pos)):
      WidgetCollection.button_over.doMouseOut()
      WidgetCollection.button_over = None

    for b in WidgetCollection.buttons:
      if b.is_active() and b.rect.collidepoint(evtMouseMotion.pos):
        b.doMouseOver()
        WidgetCollection.button_over = b
        return


class MyWidget(pygame.sprite.Sprite):
  def __init__(self, parent, img):
    pygame.sprite.Sprite.__init__(self)
    self.parent = parent
    self.image = pygame.image.load(img).convert_alpha()
    self.rect = self.image.get_rect()
    self.active = False

  def is_active(self):
    return self.active

  def activate(self):
    self.active = True

  def deactivate(self):
    self.active = False

  def repaint(self):
    self.parent.screen.fill(COLOR_BACKGROUND, self.rect)
    self.parent.screen.blit(self.image, (self.rect.x, self.rect.y))


class Button(MyWidget):
  def __init__(self, parent, img, clickCallback = lambda: "Not implemented"):
    MyWidget.__init__(self, parent, img)
    WidgetCollection.buttons.append(self)

    self.doClick = clickCallback

    self.label = self.parent.font_button.render(('CLICK!'), True, (255,255,255))
    self.label_rect = self.label.get_rect()

  def doMouseOver(self):
    self.label_rect.center = self.rect.center
    self.parent.screen.blit(self.label, self.label_rect)

  def doMouseOut(self):
    self.repaint()


class App():
  def __init__(self, srvProxy):
    self.srv = srvProxy

    # Configuration PyGame
    pygame.init()
    self.screen = pygame.display.set_mode((400, 600))

    # Polices utilisees
    self.font_button = pygame.font.SysFont('Arial', 14, 16)

    # On utilise "statique" une classe qui gere les controles (buttons, etc.)
    # WidgetCollection

    # Creation des controles
    self.createWidgets()

    # Main menu
    self.changeScreen(self.frameMainMenu)
    self.changeScreen(self.frameSongList)


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


  def exit(self):
    pygame.event.clear()
    pygame.event.post(pygame.event.Event(pygame.QUIT))

  def onClickPower(self):
    self.exit()

  def onClickSongList(self):
    self.changeScreen(self.frameSongList)

  def onClickLogo(self):
    self.changeScreen(self.frameMainMenu)

  def createWidgets(self):
    self.logo       = Button(self, "img/logo.png",    self.onClickLogo)
    self.like       = Button(self, "img/heart.png")
    self.songlist   = Button(self, "img/list.png",    self.onClickSongList)
    self.melody     = Button(self, "img/melody.png")
    self.pause      = Button(self, "img/pause.png")
    self.play       = Button(self, "img/play.png")
    self.power      = Button(self, "img/power.png",   self.onClickPower)
    self.reloadlist = Button(self, "img/reload.png")
    self.search     = Button(self, "img/search.png")
    self.sound      = Button(self, "img/sound.png")
    self.tags       = Button(self, "img/tags.png")
    self.upload     = Button(self, "img/upload.png")


  '''
    Code generique pour changer mettre a jour la fenetre
  --------------------------------------------------------------------------------
  '''
  def changeScreen(self, callback):
    WidgetCollection.deactivate_all()
    self.screen.fill(COLOR_BACKGROUND)
    callback()


  '''
    Affichage en forme de tableau
  --------------------------------------------------------------------------------
  '''
  def grid(self, elements=[], centerX=True, centerY=True, margin=0, left=0, top=0):
    surf = pygame.Surface((self.screen.get_width(), self.screen.get_height()))
    surf.fill(COLOR_BACKGROUND)
    width = 0
    height = 0

    # affichage des elements et calcul de la taille necessaire pour la surface
    for row in elements:
      row_width = 0
      row_height = height

      for cell in row:
        cell.activate()
        surf.blit(cell.image, (row_width, height))
        rect = cell.rect
        rect.x = row_width
        rect.y = height

        row_width += rect.w + margin
        row_height = max(row_height, rect.h + margin)

      width = max(width, row_width)
      height += row_height

    width = min(width, surf.get_rect().w)
    height = min(height, surf.get_rect().h)
    sub = surf.subsurface((0, 0, width, height))

    # Peut-etre on veut centrer les controles
    screen_center = self.screen.get_rect().center
    if centerX and centerY:
      final_rect = sub.get_rect(center = screen_center)
    elif centerX:
      final_rect = sub.get_rect(x = screen_center[0]-width/2, y = top)
    elif centerY:
      final_rect = sub.get_rect(x = left, y = screen_center[1]-height/2)
    else:
      final_rect = sub.get_rect(x = left, y = top)

    # Mise a jour de la position des elements
    for row in elements:
      for cell in row:
        cell.rect.x += final_rect.x
        cell.rect.y += final_rect.y


    self.screen.blit(sub, final_rect)


  '''
    Frame Main menu
  --------------------------------------------------------------------------------
  '''
  def frameMainMenu(self):
    self.grid([[self.play, self.songlist, self.search],
               [self.upload, self.logo, self.power]],
              True, True, 10)


  '''
    Frame Liste de chansons
  --------------------------------------------------------------------------------
  '''
  def frameSongList(self):
    self.grid([[self.play, self.reloadlist]],
              False, False, 10, left = 10, top = 520)
    self.grid([[self.logo]],
              False, False, 10, left = 320, top = 520)


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
          WidgetCollection.checkButtonClick(event)

        elif event.type == pygame.MOUSEMOTION:
          WidgetCollection.checkMouseOverButton(event)


      # Refresh screen
      pygame.display.flip()

  #def createWidgets(self):
  #  self.quitButton = tk.Button(self, text="Quit", command=self.quit)
  #  self.quitButton.grid()


#App(None).mainloop()
