package dev.danae.commons.parser;


public class ParserException extends Exception
{  
  // Constructor
  public ParserException(String message)
  {
    super(message);
  }
  public ParserException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
