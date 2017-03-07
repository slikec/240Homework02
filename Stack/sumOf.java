package Stack;


/** Recursion
* Count sum of n.
* sum = 1 + 2 + ... + n.
*/
public class sumOf
{
	public static int sumOf(int n)
	{
	int sum;

	if(n==1)
		sum = 1;
	else
		sum = sumOf(n - 1) + n;//recursive call
	
	return sum;
	}
}
	
	int sum =0;
	
	for(i=1;i<n;i++){
		sum = sum + i;
		return sum;
	}

/** Iterator
* Count sum of n.
*/

public static int sumOf(int n)
{
	int sum;
	while(n >= 1 )
	{
		sum = sumOf(n-1) + n;
	}
	return sum;
}
