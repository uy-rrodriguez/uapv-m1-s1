import Tkinter as tk

import os
from sys import path as syspath
syspath.append(os.path.join(os.path.dirname(__file__), "lib"))
import pygame

class App(tk.Frame):
  def __init__(self, srvProxy, master=None):
    tk.Frame.__init__(self, master)

    self.srv = srvProxy

    self.master.title("Client MP3")
    self.grid()
    self.createWidgets()

  def createWidgets(self):
    self.quitButton = tk.Button(self, text="Quit", command=self.quit)
    self.quitButton.grid()
