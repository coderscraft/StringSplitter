package com.jrcube.text.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jrcube.text.pojo.TextString;
import com.jrcube.text.splitter.textSplitter;

public class SqlString_TC {

	@Test
	public void test() {
		textSplitter _ts = new textSplitter("SELECT EMPID FROM EMPLOYEE");
		TextString txs = _ts.process();
		System.out.println(txs);
		assertEquals(4, txs.getTextTokens().size());
	}

}
