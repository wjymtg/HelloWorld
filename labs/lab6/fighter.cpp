#include "fighter.h"

using namespace std;

Fighter::Fighter(string name) : Player(name){

}

void Fighter::doAction(Player *target){
	if(!target) return;
	int x = target->getHP();
	target->setHP(x - 75);
}