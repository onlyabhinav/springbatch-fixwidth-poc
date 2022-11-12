package com.onlyabhinav.batchfixwidth.mockdata.writer;

import java.io.File;

public interface TheFileWriter {

	File file = null;
	
	public void openFile();
	public void writeFile();
	public void closeFile();
	
}
