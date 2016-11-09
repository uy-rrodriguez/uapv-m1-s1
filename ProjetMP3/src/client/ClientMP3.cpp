#include <Ice/Ice.h>
#include <InterfaceMP3.h>

using namespace std;
using namespace ArchDistrib;

int main(int argc, char* argv[]) {
    int status = 0;
    Ice::CommunicatorPtr ic;

    try {
        ic = Ice::initialize(argc, argv);
        Ice::ObjectPrx base = ic->stringToProxy("InterfaceMP3:default -p 10000");
        InterfaceMP3Prx serveur = InterfaceMP3Prx::checkedCast(base);
        if (!serveur)
            throw "Invalid proxy";

        std::string mes;
        cin >> mes;
        while (mes != "exit") {
            serveur->printString(mes);
            cin >> mes;
        }
    }
    catch (const Ice::Exception& ex) {
        cerr << ex << endl;
        status = 1;
    }
    catch (const char* msg) {
        cerr << msg << endl;
        status = 1;
    }

    if (ic)
        ic->destroy();

    return status;
}
