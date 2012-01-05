package edu.hiro.hcv.setup;

import org.springframework.batch.core.JobExecution;

public class SetupServiceImpl
{	
	public String echo(String message)
	{
		return message.toUpperCase();
	}
	
	/*
	public void loadSequences(String filename) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/batch-setup.xml");
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = (Job) context.getBean("loadSequences");

		JobParameters jobparams=new JobParametersBuilder().addString("resource",filename).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job,jobparams);
		System.out.println("Job ended with status: "+jobExecution.getExitStatus());
	}
	*/
	
	/*
	public void loadSequences(String filename) {
		JobExecution jobExecution = runJob("loadSequences","resource",filename);
		System.out.println("Job ended with status: "+jobExecution.getExitStatus());
	}
	
	private JobExecution runJob(String id, Object...args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
			"/META-INF/spring/batch-setup.xml");
		return SpringBatchHelper.runJob(context, id, args);		
	}
	
	public void loadXml(String xml, CMessageWriter writer)
	{
		try
		{
			CXmlDataReader xmlloader=new CXmlDataReader(writer);
			xmlloader.loadXml(xml);
		}
		catch(Exception e)
		{
			CFileHelper.writeFile("c:/setup.xml",xml);
			throw new CException(e);
		}
	}

	public void loadXmlFromFile(String filename, CMessageWriter writer)
	{
		loadXml(CFileHelper.readFile(filename),writer);
		writer.message("Finished loading resources from file: "+filename);
	}
	
	public void loadXmlFromFolder(String folder, CMessageWriter writer)
	{
		String xml=CXmlHelper.mergeXmlFiles(folder,CXmlDataReader.ROOT);
		loadXml(xml,writer);
		writer.message("Finished loading resources from folder: "+folder);
	}
	
	public void validate(String xml, String schema, CMessageWriter writer)
	{
		try
		{
			String xsd=CFileHelper.getResource(schema);
			CXmlHelper.validate(xml,xsd);
		}
		catch(CXmlValidationException e)
		{
			writer.message(e.getMessage());
		}
	}
	
	public void validateFolder(String folder, String schema, CMessageWriter writer)
	{
		List<String> filenames=CFileHelper.listFilesRecursively(folder,".xml");
		for (String filename : filenames)
		{
			String xml=CFileHelper.readFile(filename);
			validate(xml,schema,writer);
		}
	}
	*/
}