package edu.hiro.hcv.setup;


public interface SetupService
{
	void updateTaxa();
	void updateRefs();
	void loadGenbankFile(String filename);
	void loadSampleData(int num);
	void clearDatabase();
}