#include "inventory.h"
#include <iostream>
using namespace std;

Inventory::Inventory(){

}

void Inventory::addItem(string str, int val){
	map<string, int>::iterator it = this->find(str);
	if(it != this->end()){
		it->second += val;
	}
	else
		this->insert(make_pair(str, val));
}
void Inventory::removeItem(string str){
	this->erase(this->find(str));

}
int Inventory::getNumItems(string str){
	return this->find(str)->second;
}

void Inventory::printAllItems(){
	map<string, int>::iterator it;
	for(it = this->begin(); it != this->end(); ++it){
		cout << it->first << " " << it->second << endl;
	}
}