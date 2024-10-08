package dev.danae.commons.commands;

import java.util.List;

public class CommandUtils 
{
  // Class that defines a search result for tab completion
  public static class SearchResult implements Comparable<SearchResult>
  {
    private final String string;
    private final int index;

    public SearchResult(String string, int index)
    {
      this.string = string;
      this.index = index;
    }

    public String getString()
    {
      return this.string;
    }

    public int getIndex()
    {
      return this.index;
    }

    public boolean isFound()
    {
      return this.index >= 0;
    }

    @Override
    public int compareTo(SearchResult other) 
    {
      return Integer.compare(this.index, other.index);
    }

    public static SearchResult find(String string, String query)
    {
      return new SearchResult(string, string.indexOf(query));
    }
  }


  // Handle tab completion for a list while performing a search for the argument
  public static List<String> handleSearchTabCompletion(String arg, List<String> list)
  {
    if (arg == null || arg.isEmpty())
      return list;

    return list.stream()
      .map(s -> SearchResult.find(s, arg))
      .filter(r -> r.isFound())
      .sorted()
      .map(r -> r.getString())
      .toList();
  }
}
