#include "fib.h"
#include "gtest/gtest.h"

TEST(FibTest, Nominal) {
	Fibonacci f;
	EXPECT_EQ(f.get(5), 5);
	EXPECT_EQ(f.get(7), 13);
}

TEST(FibTest, Boundary) {
    Fibonacci f;
    EXPECT_EQ(f.get(1),1);
    EXPECT_EQ(f.get(2),1);
}

TEST(FibTest, OffNominal) {
	Fibonacci f;
	EXPECT_EQ(f.get(-5),0);
}