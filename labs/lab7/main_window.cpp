#include <QLabel>
#include <QMessageBox>
#include <QApplication>
#include "main_window.h"

MainWindow::MainWindow(Reddit* r) {
	reddit = r;

	layout = new QVBoxLayout();

	welcomeLabel = new QLabel("Welcome to CS104 Reddit!!");
	layout->addWidget(welcomeLabel);

	// setup the new posts container widget
	postsLayout = new QVBoxLayout();
	postsContainerWidget = new QWidget();
	postsContainerWidget->setLayout(postsLayout);

	// add the container widget to the main layout
	layout->addWidget(postsContainerWidget);

	setupPosts(r->getPosts());

	aboutButton = new QPushButton("About CS104 Reddit");
	layout->addWidget(aboutButton);

	// ADD YOUR CODE HERE
	// Create a new button with the text "Quit"
	quitButton = new QPushButton("Quit");
	layout->addWidget(quitButton);
	setLayout(layout);
	show();

	connect(aboutButton, SIGNAL(clicked()), this, SLOT(showAbout()));
	connect(quitButton, SIGNAL(clicked()), this, SLOT(quit()));
	// ADD YOUR CODE HERE
	// Connect the quit button to the quit slot function
}

MainWindow::~MainWindow() {
	clearPosts();

	delete welcomeLabel;
	delete aboutButton;
	delete quitButton;

	// ADD YOUR CODE HERE
	// Delete the quit button pointer
}

void MainWindow::setupPosts(std::vector<Post*> posts) {
	clearPosts();

	for(unsigned int i = 0; i < posts.size(); i++){
		PostWidget* pw = new PostWidget(posts[i]);
		layout->addWidget(pw);
		postWidgets.push_back(pw);
	}
	
	// ADD YOUR CODE HERE
	// Take in the vector of posts and for each of them:
	// 1. create a PostWidget
	// 2. add the PostWidget to the correct layout
	// 3. add the PostWidget to the vector so that we can delete the pointers later
}

void MainWindow::clearPosts() {
	// ADD YOUR CODE HERE
	// For each PostWidget pointer in the vector
	// 1. remove them from layout with removeWidget(QWidget*)
	// 2. delete the pointer
	// Then clear the vector

	for(unsigned int i = 0; i < postWidgets.size(); i++){
		layout->removeWidget(postWidgets[i]);
		delete postWidgets[i];
	}
	postWidgets.clear();
}

void MainWindow::showAbout() {
	QMessageBox::information(this, "About", "Simple Qt app for lab7!");
}

// ADD YOUR CODE HERE
// Create a quit function, that serves as a slot for the exit button.


void MainWindow::quit(){
	QApplication::exit();
}