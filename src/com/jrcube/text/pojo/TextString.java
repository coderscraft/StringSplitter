package com.jrcube.text.pojo;

import java.util.List;

public class TextString {

	private String text;
	private List<String> textTokens;
	private int tokenPos = 0;
	public final String getText() {
		return text;
	}
	public final void setText(String text) {
		this.text = text;
	}
	public final List<String> getTextTokens() {
		return textTokens;
	}
	public final void setTextTokens(List<String> textTokens) {
		this.textTokens = textTokens;
	}
	@Override
	public String toString() {
		return "TextString [text=" + text + "]";
	}
	public String getCurrentToken()
	{
		return textTokens.get(tokenPos);
	}
	public String getNextToken( int pos )
	{
		if( textTokens.size() >= tokenPos + pos  )
		return textTokens.get(tokenPos + pos);
		else
			return null;
	}
	public String getPreviousToken( int pos )
	{
		if( tokenPos - pos >=0 )
		return textTokens.get(tokenPos - pos);
		else
			return null;
	}
	public void rollForward()
	{
		if (hasNext())
		tokenPos++;
	}
	public void rollBackward()
	{
		if (hasPrevious())
		tokenPos--;
	}
	
	public boolean hasNext()
	{
		if ( textTokens.size() - 1 > tokenPos )
			return true;
		else
			return false;
	}
	public boolean hasPrevious()
	{
		if (  tokenPos - 1 >= 0  )
			return true;
		else
			return false;
	}
	
}
