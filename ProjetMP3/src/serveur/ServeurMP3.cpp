#include <Ice/Ice.h>
#include <InterfaceMP3.h>

using namespace std;
using namespace ArchDistrib;

class ImplInterfaceMP3 : public InterfaceMP3 {
public:
    virtual void printString(const string& s, const Ice::Current&);
};

void ImplInterfaceMP3::printString(const string& s, const Ice::Current&) {
    cout << s << endl;
}

int main(int argc, char* argv[]) {
    int status = 0;
    Ice::CommunicatorPtr ic;

    try {
        ic = Ice::initialize(argc, argv);
        Ice::ObjectAdapterPtr adapter =
            ic->createObjectAdapterWithEndpoints("InterfaceMP3Adapter", "default -p 10000");
        Ice::ObjectPtr object = new ImplInterfaceMP3;
        adapter->add(object, ic->stringToIdentity("InterfaceMP3"));
        adapter->activate();
        ic->waitForShutdown();
    }
    catch (const Ice::Exception& e) {
        cerr << e << endl;
        status = 1;
    }
    catch (const char* msg) {
        cerr << msg << endl;
        status = 1;
    }

    if (ic) {
        try {
            ic->destroy();
        } catch (const Ice::Exception& e) {
            cerr << e << endl;
            status = 1;
        }
    }
    return status;
}
