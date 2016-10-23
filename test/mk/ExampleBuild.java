package mk;

public interface ExampleBuild extends Build {

	Module 
		core = new Module(),
		test = new Module()
		;
	
	Goal
		compile = core.mirrored().as($class).in(target),
		compile_test = test.mirrored().as($class).in(target),
		jar = core.flattened().as($jar).in(target)
		;
		
}
