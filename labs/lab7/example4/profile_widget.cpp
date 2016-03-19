#include "profile_widget.h"
#include <QString>
#include <QMessageBox>

ProfileWidget::ProfileWidget(std::string name, std::string email, std::string website) {
	layout = new QVBoxLayout();
	
	nameLabel = new QLabel(QString::fromStdString(name));
	layout->addWidget(nameLabel);

	emailLabel = new QLabel(QString::fromStdString(email));
	layout->addWidget(emailLabel);

	websiteLabel = new QLabel(QString::fromStdString(website));
	layout->addWidget(websiteLabel);

	addFriendButton = new QPushButton("Add friend");
	layout->addWidget(addFriendButton);

	messageButton = new QPushButton("Send message");
	layout->addWidget(messageButton);

	setLayout(layout);

	connect(addFriendButton, SIGNAL(clicked( )), this, SLOT(addFriend( )));
}

ProfileWidget::~ProfileWidget() {
	delete nameLabel;
	delete emailLabel;
	delete websiteLabel;
	delete addFriendButton;
	delete messageButton;
	delete layout;
}

void ProfileWidget::addFriend(){
	QMessageBox::information(this, "Add Friend", "Friend added!");
}