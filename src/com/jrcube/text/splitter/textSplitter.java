package com.jrcube.text.splitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.jrcube.text.pojo.TextString;

public class textSplitter {
	
	private TextString _ts = new TextString();
	private String _spliiter;
	private String _rule;
	private List<String> _rules = new ArrayList<String>();
	private String _textTokens[];
	
	public textSplitter() {
		super();
		_ts = null;
	}
	
	public textSplitter(String text) {
		super();
		this._ts.setText(text);
	}

	public TextString process()
	{
		process("grammar.txt");
		return _ts;
	}
	
	public TextString process( String fileName )
	{
		readGrammar(fileName);
		processGrammar();
		splitText();
		return _ts;
	}

	private void splitText() {
		_textTokens = _ts.getText().split(_spliiter);
		_ts.setTextTokens(Arrays.asList(_textTokens));
	}

	private void processGrammar() {
        Iterator<String> _itr = _rules.iterator();
        while(_itr.hasNext())
        {
        	String rule = _itr.next();
        	String ruleToken[] = rule.split(",");
        	if(ruleToken[0].indexOf(ruleToken[1]) >= 0 )
        	{
        		while(_ts.getText().indexOf(ruleToken[0]) >= 0 )
        		{
        			_ts.setText(_ts.getText().replaceAll(ruleToken[0], ruleToken[1]));
        		}
        	}
        	else
        	{
        		_ts.setText(_ts.getText().replaceAll(ruleToken[0], ruleToken[1]));
        	}
        }
	}

	private void readGrammar(String fileName) {
         String grammar;
         InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
         if ( in == null )
         {
        	 System.out.println("Please provide grammar.txt at classpath");
        	 System.exit(1);
         }
         InputStreamReader isr = new InputStreamReader(in);
         BufferedReader br = new BufferedReader(isr);
         try
         {
        	 while ( (grammar = br.readLine()) != null )
        	 {
        		 if(grammar.toUpperCase().startsWith("SPLIT:"))
        		 {
        			 _spliiter = grammar.substring(grammar.indexOf("[")+1, grammar.indexOf("]"));
        		 }
        		 else if(grammar.toUpperCase().startsWith("RULE:"))
        		 {
        			 while(grammar.length() > 1)
        			 {
        				  _rule = grammar.substring(grammar.indexOf("[")+1,grammar.indexOf("]"));
        				  _rules.add(_rule);
        				  grammar = grammar.substring(grammar.indexOf("]") + 1).trim();
        			 }
        		 }
        	 }
         }
         catch( IOException e )
         {
        	 e.printStackTrace();
         }
		
	}
	
	
	
	
	
	
}
