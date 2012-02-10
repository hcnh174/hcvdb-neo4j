package edu.hiro.hcv.setup;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import edu.hiro.hcv.neo4j.SequenceNode;

//import groovy.lang.GroovyShell;

public class Setup {
	
	// mvn test -Psetup -e
	public static void main(String ... args)
	{
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("META-INF/spring/applicationContext.xml");
		ctx.load("META-INF/spring/applicationContext-mongo.xml");
		ctx.load("META-INF/spring/applicationContext-neo4j.xml");
		ctx.refresh();
		
		//String filename="d:/temp/AB435162.2.gb";
		//String filename="d:/temp/NC_004102.1.gb";
		String filename="h:/hcvdatabase.etc/sequence.gb";
		List<SequenceNode> sequences=GenbankSequenceBuilder.parseFile(filename);
		SequenceNode sequence=sequences.get(0);
		//sequenceRepository.save(sequence);
		System.out.println("sequence="+sequence.toString());
		
		//SequenceService sequenceService=(SequenceService)ctx.getBean("sequenceService");
		//SequenceRepository sequenceRepository=(SequenceRepository)ctx.getBean(SequenceRepository.class);//"sequenceRepository");
		//sequenceService.testRepository();
		
		//ResourceService resourceService=(ResourceService)ctx.getBean("resourceService");
		//resourceService.testRepository();
		
		//GraphHelper.test();
//		List<GenbankSequence> sequences=Lists.newArrayList();
//		GenbankParser.parseFile("d:/temp/AB435162.2.gb",sequences);
//		
//		for (GenbankSequence seq : sequences)
//		{
//			//System.out.println("sequence="+seq.toString());
//			Sequence sequence=new Sequence(seq.getAccession(), seq.getSequence());
//			Feature feature=new Feature("gene","NS3",3401,3456);
//			sequence.addFeature(feature);
//			Tag tag=new Tag("genotype","1b");
//			sequence.addTag(tag);
//			sequenceRepository.save(sequence);
//			System.out.println("sequence="+sequence.toString());
//		}
		

		
		
//		Setup setup=new Setup();
//		String filename="c:/projects/analysis/personalized/weka/svr.arff";
//		WekaHelper.test(filename);
	
		
	}
	
	/*
	public Setup()
	{
		
		String dir="C:/dropbox/My Dropbox/vardb/dr-5/sequence/";
		//String dir="C:/Documents and Settings/nhayes/My Documents/My Dropbox/vardb/dr-5/sequence/";
		String filename=dir+"anaplasma.marginale_st_maries-msp2_p44_map1_omp.txt";
		
		try
		{
			JConsole console = new JConsole();
			SetupServiceImpl service=new SetupServiceImpl();
			Interpreter interpreter = new Interpreter(console);
			interpreter.set("service",service);
			interpreter.set("filename",filename);
			new Thread( interpreter ).start(); // start a thread to call the run() method
			
			JFrame frame = new JFrame("varDB shell");		
			frame.getContentPane().add( console, "Center" );
			frame.pack();
			frame.setSize(500,400);
			frame.setVisible(true);
		}
		catch (Exception e)
		{
			throw new CException(e);
		}
		
	}
	 */
}


//java groovy.lang.GroovyShell foo/MyScript.groovy [arguments]
//GroovyShell.main(new String[]{"setup.groovy"});
//groovy.lang.GroovyShell.main(groovy.lang.GroovyShell.EMPTY_ARGS);

//String password = new jline.ConsoleReader().readLine(new Character('*'));
//System.out.println("password: "+password);

//ConsoleRunner console = new ConsoleRunner();
//ConsoleRunner.main(new String[]{"bsh.Interpreter"});
//bsh.Console.main(new String[]{});
//bsh.Interpreter.main(new String[]{});


/*		
//String dir="C:/Documents and Settings/nhayes/My Documents/My Dropbox/vardb/dr-5/sequence/";
String dir=basedir+"sequence/";
Setup setup=new Setup();
String filename="anaplasma.marginale_st_maries-msp2_p44_map1_omp.txt";
setup.loadSequences(dir+filename);
*/	
