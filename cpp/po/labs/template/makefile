testmap: testmap.cpp employee.h map_template.h
	g++ -g -Wall -pedantic $^ -o $@

.PHONY: clean testmap2

testmap2: testmap2.cpp book.h map_template.h
	g++ -g -Wall -pedantic $^ -o $@

clean:
	-rm testmap testmap2
