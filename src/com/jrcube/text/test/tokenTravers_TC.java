package com.jrcube.text.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import com.jrcube.text.pojo.TextString;
import com.jrcube.text.splitter.textSplitter;

public class tokenTravers_TC {
	
	TextString txs;

	@Before
	public void setUp() throws Exception {
		File f1 = new File("src\\employee.txt");
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
		txs = _ts.process();
	}

	@Test
	public void test() {
		txs.rollForward();
		txs.rollForward();
		txs.rollForward();
		txs.rollForward();
		assertEquals("dept.deptname",txs.getCurrentToken());
		assertEquals("from",txs.getNextToken(1));
		assertEquals("employee",txs.getNextToken(2));
		assertEquals("dept.deptId,",txs.getPreviousToken(1));
		assertEquals("emp.EmpName,",txs.getPreviousToken(2));
		assertEquals(null,txs.getPreviousToken(6));
		assertEquals(null,txs.getNextToken(25));
	}

}
