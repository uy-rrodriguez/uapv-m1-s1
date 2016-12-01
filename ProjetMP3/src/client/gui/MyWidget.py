import pygame

COLOR_BACKGROUND = (6,19,25,0)

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
