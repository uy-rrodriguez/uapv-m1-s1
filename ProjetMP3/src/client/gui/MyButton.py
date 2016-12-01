from MyWidget import MyWidget
from WidgetCollection import WidgetCollection

COLOR_BACKGROUND = (6,19,25,0)

class MyButton(MyWidget):
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
