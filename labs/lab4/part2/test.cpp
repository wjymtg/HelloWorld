#include "arraylist.h"
#include "gtest/gtest.h"

class ArrayListTest : public testing::Test {
protected:
	// You can remove any or all of the following functions if its body is empty.

	ArrayListTest() {
		// You can do set-up work for each test here.
	}

	virtual ~ArrayListTest() {
		// You can do clean-up work that doesn't throw exceptions here.		
	}

	// If the constructor and destructor are not enough for setting up
	// and cleaning up each test, you can define the following methods:
	virtual void SetUp() {
		// Code here will be called immediately after the constructor (right
		// before each test).
		list.add(1);
		list.add(2);
		list.add(3);
	}

	virtual void TearDown() {
		// Code here will be called immediately after each test (right
		// before the destructor).
	}

	// Objects declared here can be used by all tests in the test case.
	ArrayList list;
};

TEST_F(ArrayListTest, GetNominal) {
	// Exepct 0th element to be 1, 1st elemnt to be 2, etc.
	for (int i = 0 ; i < 3; i++) {
		EXPECT_EQ(list.get(i), i + 1);
	}
}

TEST_F(ArrayListTest, AddNominal) {
	list.add(4);	
	list.add(5);
	list.add(6);
	list.add(7);
	list.add(8);
	list.add(9);
	list.add(10);
	list.add(11);
	for (int i = 0 ; i < 11; i++) {
		EXPECT_EQ(list.get(i), i + 1);
	}

}

TEST_F(ArrayListTest, RemoveNominal) {
	list.add(5);
	list.remove(0);
	list.remove(1);
	list.remove(1);
	list.remove(0);
	EXPECT_EQ(list.size(), 0);
	
}

TEST_F(ArrayListTest, SetOffNominal) {
	list.add(4);
	list.add(5);
	list.insert(-1,0);
	list.insert(-2,-1);
	list.remove(-5);
	list.insert(7,8);
	list.set(-1,10);
	for (int i = 0 ; i < 5; i++) {
		EXPECT_EQ(list.get(i), i + 1);
	}
	EXPECT_EQ(list.get(-2),0);
}
