import pyglet

COLOR_BACKGROUND = (6,19,25,0)

class MyWidget(pyglet.sprite.Sprite):
  def __init__(self, parent, pathImg, px=0, py=0):
    self.parent = parent

    img = pyglet.resource.image(pathImg)
    pyglet.sprite.Sprite.__init__(self, img, x=px, y=py, batch=self.parent.widgetBatch)

    # self.rect = self.image.get_rect()
    self.active = False

  def is_active(self):
    return self.active

  def activate(self):
    self.active = True

  def deactivate(self):
    self.active = False

  '''
  def draw(self):
    self.image.blit()
    # self.parent.screen.fill(COLOR_BACKGROUND, self.rect)
    # self.parent.screen.blit(self.image, (self.rect.x, self.rect.y))
  '''
