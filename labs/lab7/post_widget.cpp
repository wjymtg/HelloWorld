#include <QString>
#include <QFont>
#include "post_widget.h"


PostWidget::PostWidget(Post* p) {
	post = p;
	layout = new QGridLayout();

	// ADD YOUR CODE HERE.
	// Create the suitable labels and add them to GridLayout properly.

	karmaLabel = new QLabel(QString::number(p->karma));
	layout->addWidget(karmaLabel, 0, 0, 3, 3);
	
	titleLabel = new QLabel(QString::fromStdString(post->title));
	layout->addWidget(titleLabel, 0, 3, 1, 15);

	subredditLabel = new QLabel(QString::fromStdString(post->subreddit));
	layout->addWidget(subredditLabel, 1, 3, 1, 3);

    urlLabel = new QLabel(QString::fromStdString("<a href=\"" + post->url + "\">" + post->url + "</a>"));
    urlLabel->setOpenExternalLinks(true);

	layout->addWidget(urlLabel, 2, 3, 1, 5);

	usernameLabel = new QLabel(QString::fromStdString(post->username));
	layout->addWidget(usernameLabel, 1, 15, 1, 3);
	setLayout(layout);
}

PostWidget::~PostWidget() {
	delete titleLabel;
	delete usernameLabel;
	delete subredditLabel;
	delete urlLabel;
	delete karmaLabel;
	delete layout;
}
