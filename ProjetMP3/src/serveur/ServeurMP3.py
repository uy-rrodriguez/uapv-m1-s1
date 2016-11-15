import sys, traceback, Ice
import ArchDistrib

class ServeurMP3I(ArchDistrib.InterfaceMP3):
    def printString(self, s, current=None):
        print s

status = 0
ic = None
try:
    ic = Ice.initialize(sys.argv)
    adapter = ic.createObjectAdapterWithEndpoints("InterfaceMP3Adapter", "default -p 10000")
    object = ServeurMP3I()
    adapter.add(object, ic.stringToIdentity("InterfaceMP3"))
    adapter.activate()
    ic.waitForShutdown()
except:
    traceback.print_exc()
    status = 1

if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1

sys.exit(status)
