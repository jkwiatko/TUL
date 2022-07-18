
class stack
{
public:
	void push(int a);
	int pop();
	void clear();
	stack();
	~stack();
private:
	int top;
	int size;
	int	*dane;
};

