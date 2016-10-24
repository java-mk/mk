package root.module_a;

import root.module_a.leaf.Leaf;
import root.module_b.ImplB;

public class Pub  {

	public Integer foo() {
		new Leaf();
		new ImplB();
		return 1;
	}
	
}
