package com.jrcube.text.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import com.jrcube.text.pojo.TextString;
import com.jrcube.text.splitter.textSplitter;

public class SqlFile_TC {

	@Test
	public void test() {
		File f1 = new File("D:\\employee.txt");
		BufferedReader br = null;
		StringBuffer queryBuffer = new StringBuffer();
		String queryLine = null;
		String query = null;
		try
		{
			br = new BufferedReader(new FileReader(f1));
			while( (queryLine = br.readLine()) != null )
			{
				queryBuffer.append(" ").append(queryLine);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		query = queryBuffer.toString();
		query = query.trim();
		textSplitter _ts = new textSplitter(query);
		TextString txs = _ts.process();
		System.out.println(txs);
		assertEquals(25, txs.getTextTokens().size());
	}

}
