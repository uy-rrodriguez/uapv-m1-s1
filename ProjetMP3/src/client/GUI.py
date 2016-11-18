import Tkinter as tk

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
