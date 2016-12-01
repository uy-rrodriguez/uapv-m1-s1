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
