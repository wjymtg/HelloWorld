#include <QApplication>
#include "profile_widget.h"

int main(int argc, char *argv[]) {
  QApplication app(argc, argv);

  ProfileWidget pw("Jiuyang Wang", "jiuyangw@usc.edu", "http://bits.usc.edu/cs104");
  pw.show();
  
  return app.exec();
}

