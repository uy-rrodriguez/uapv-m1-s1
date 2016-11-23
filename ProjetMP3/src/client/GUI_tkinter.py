import Tkinter as tk

import os
IMG_DIR = os.path.join(os.path.dirname(__file__), "img")


class MyWidget:
  def __init__(self):
    pass

  def checkDefaultRules(self, kw, props):
    for p in props.iterkeys():
      if p not in kw:
        kw[p] = props[p]

  def checkMinRules(self, kw, props):
    for p in props.iterkeys():
      if p not in kw or kw[p] < props[p]:
        kw[p] = props[p]

class MyFrame(tk.Frame, MyWidget):
  def __init__(self, master, **kw):
    self.checkMinRules(kw, {
        "width": 300,
        "height": 600
      })
    tk.Frame.__init__(self, master, kw)

class MyButton(tk.Button, MyWidget):
  def __init__(self, master, **kw):
    self.checkMinRules(kw, {
        "width": 12,
        "height": 5
      })
    tk.Button.__init__(self, master, kw)


class App:
  def __init__(self, srvProxy):
    self.srv = srvProxy

    WIDTH = 300
    HEIGHT = 500

    self.master = tk.Tk()
    self.master.resizable(width=False, height=False)
    self.master.minsize(width=WIDTH, height=HEIGHT)
    self.master.maxsize(width=WIDTH, height=HEIGHT)

    self.doMainMenu()
    self.master.title("Client MP3")

  def loadImg(self, img):
    return tk.PhotoImage(IMG_DIR+"/"+img)

  def doMainMenu(self):
    self.frame = frm = tk.Frame(self.master)
    frm.pack(pady=150)

    self.playBtn = MyButton(frm, text="Play", image=self.loadImg("play.gif"), command=self.play)
    self.playBtn.grid(row=1, column=1)

    self.listBtn = MyButton(frm, text="List", command=self.list)
    self.listBtn.grid(row=1, column=2)

    self.searchBtn = MyButton(frm, text="Search", command=self.search)
    self.searchBtn.grid(row=1, column=3)

    self.uploadBtn = MyButton(frm, text="Upload", command=self.upload)
    self.uploadBtn.grid(row=2, column=1)

    #self.quitButton = tk.Button(self.frm, text="Quit", command=self.quit)
    #self.quitButton.grid(row=2, column=3)

    self.powerBtn = MyButton(frm, text="Quit", command=self.master.quit)
    self.powerBtn.grid(row=2, column=3)


  def mainloop(self):
    self.master.mainloop()

  def play(self):
    self.frame.unpack()

  def list(self):
    pass

  def search(self):
    pass

  def upload(self):
    pass

  def power(self):
    pass

App(None).mainloop()
